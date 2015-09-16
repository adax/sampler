package com.haulmont.sampler.gui.config;

import com.haulmont.bali.util.Dom4j;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Resources;
import com.haulmont.cuba.core.sys.AppContext;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;
import org.springframework.core.io.Resource;

import javax.annotation.ManagedBean;
import javax.annotation.Nullable;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author gorelov
 * @version $Id$
 */
@ManagedBean(SamplesMenuConfig.NAME)
public class SamplesMenuConfig {

    public static final String NAME = "sampler_SamplesConfig";
    public static final String MENU_CONFIG_XML_PROP = "sampler.samplesMenuConfig";

    private static Log log = LogFactory.getLog(SamplesMenuConfig.class);

    @Inject
    private Resources resources;

    @Inject
    private Messages messages;

    private List<MenuItem> rootItems = new ArrayList<>();

    private volatile boolean initialized;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public String getMenuItemCaption(String id) {
        try {
            return messages.getMainMessage("sample-config." + id);
        } catch (MissingResourceException e) {
            return id;
        }
    }

    public String getDocTemplate() {
        return getDocTemplate(null);
    }

    public String getDocTemplate(@Nullable Locale locale) {
        String url;
        if (locale == null)
            url = messages.getMainMessage("sample-config.docUrl");
        else
            url = messages.getMainMessage("sample-config.docUrl", locale);

        return url + "%s.html";
    }

    private void checkInitialized() {
        if (!initialized) {
            lock.readLock().unlock();
            lock.writeLock().lock();
            try {
                if (!initialized) {
                    init();
                    initialized = true;
                }
            } finally {
                lock.readLock().lock();
                lock.writeLock().unlock();
            }
        }
    }

    private void init() {
        rootItems.clear();

        String configName = AppContext.getProperty(MENU_CONFIG_XML_PROP);

        StrTokenizer tokenizer = new StrTokenizer(configName);
        for (String location : tokenizer.getTokenArray()) {
            Resource resource = resources.getResource(location);
            if (resource.exists()) {
                InputStream stream = null;
                try {
                    stream = resource.getInputStream();
                    Element rootElement = Dom4j.readDocument(stream).getRootElement();
                    loadMenuItems(rootElement, null);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    IOUtils.closeQuietly(stream);
                }
            } else {
                log.warn("Resource " + location + " not found, ignore it");
            }
        }
    }

    /**
     * Make the config to reload screens on next request.
     */
    public void reset() {
        initialized = false;
    }

    /**
     * Main menu root items
     */
    public List<MenuItem> getRootItems() {
        lock.readLock().lock();
        try {
            checkInitialized();
            return Collections.unmodifiableList(rootItems);
        } finally {
            lock.readLock().unlock();
        }
    }

    private void loadMenuItems(Element parentElement, MenuItem parentItem) {
        for (Element element : ((List<Element>) parentElement.elements())) {
            MenuItem menuItem = null;
            String id = element.attributeValue("id");
            if (StringUtils.isNotBlank(id)) {
                if ("menu".equals(element.getName())) {
                    menuItem = new MenuItem(parentItem, id);
                    menuItem.setMenu(true);

                    loadMenuItems(element, menuItem);
                } else if ("item".equals(element.getName())) {
                    menuItem = parseItem(element, parentItem, id);
                } else {
                    log.warn(String.format("Unknown tag '%s' in sample-config", element.getName()));
                }
            } else {
                log.warn(String.format("Invalid sample-config: 'id' attribute not defined for tag" + element.getName()));
            }

            if (parentItem != null) {
                addItem(parentItem.getChildren(), menuItem);
            } else {
                addItem(rootItems, menuItem);
            }
        }
    }

    private MenuItem parseItem(Element element, MenuItem parentItem, String id) {
        MenuItem menuItem = new MenuItem(parentItem, id);
        String docUrl = element.attributeValue("docUrlSuffix");
        if (StringUtils.isNotBlank(docUrl)) {
            menuItem.setUrl(docUrl);
        }

        String descriptionsPack = element.attributeValue("descriptionsPack");
        if (StringUtils.isNotBlank(descriptionsPack)) {
            menuItem.setDescriptionsPack(messages.getMainMessage(descriptionsPack));
        }

        String controller = element.attributeValue("controller");
        if (StringUtils.isNotBlank(controller)) {
            menuItem.setController(controller);
        }

        Element otherFilesElement = element.element("otherFiles");
        if (otherFilesElement != null && !otherFilesElement.elements().isEmpty()) {
            List<String> otherFiles = new ArrayList<>();
            for (Element file : ((List<Element>) otherFilesElement.elements())) {
                String fileName = file.attributeValue("name");
                if (StringUtils.isNotEmpty(fileName))
                    otherFiles.add(fileName);
            }
            menuItem.setOtherFiles(otherFiles);
        }

        Element screenParamsElement = element.element("screenParams");
        if (screenParamsElement != null && !screenParamsElement.elements().isEmpty()) {
            Map<String, String> params = new HashMap<>();
            for (Element param : ((List<Element>) screenParamsElement.elements())) {
                String paramName = param.attributeValue("name");
                if (StringUtils.isNotEmpty(paramName)) {
                    String value = param.attributeValue("value");
                    params.put(paramName, value);
                }
            }
            menuItem.setScreenParams(params);
        }

        return menuItem;
    }

    private void addItem(List<MenuItem> items, MenuItem menuItem) {
        if (menuItem != null) {
            items.add(menuItem);
        }
    }

    @Nullable
    public MenuItem findItemById(String id) {
        return (MenuItem) CollectionUtils.find(getItemsAsList(), new MenuItemPredicate(id));
    }

    private List<MenuItem> getItemsAsList() {
        return getItemsAsList(getRootItems());
    }

    private List<MenuItem> getItemsAsList(List<MenuItem> allItems) {
        List<MenuItem> items = new ArrayList<>();
        for (MenuItem item : allItems) {
            items.add(item);
            if (item.isMenu())
                items.addAll(getItemsAsList(item.getChildren()));
        }
        return items;
    }

    private class MenuItemPredicate implements Predicate {
        private String id;

        public MenuItemPredicate(String id) {
            this.id = id;
        }

        @Override
        public boolean evaluate(Object object) {
            return id.equals(((MenuItem) object).getId());
        }
    }
}
