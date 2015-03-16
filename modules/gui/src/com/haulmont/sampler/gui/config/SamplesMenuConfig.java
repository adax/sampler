package com.haulmont.sampler.gui.config;

import com.haulmont.bali.util.Dom4j;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Resources;
import com.haulmont.cuba.core.sys.AppContext;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;
import org.springframework.core.io.Resource;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.MissingResourceException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author gorelov
 * @version $Id$
 */
@ManagedBean(SamplesMenuConfig.NAME)
public class SamplesMenuConfig {

    public static final String NAME = "sampler_SamplesConfig";
    public static final String MENU_CONFIG_XML_PROP = "sampler.SamplesMenuConfig";

    private static Log log = LogFactory.getLog(SamplesMenuConfig.class);

    @Inject
    private Resources resources;

    @Inject
    private Messages messages;

    private List<MenuItem> rootItems = new ArrayList<>();

    private volatile boolean initialized;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private String docTemplate;

    public String getMenuItemCaption(String id) {
        try {
            return messages.getMainMessage("sample-config." + id);
        } catch (MissingResourceException e) {
            return id;
        }
    }

    public String getDocTemplate() {
        if (docTemplate == null) {
            String url = messages.getMainMessage("sample-config.docUrl");
            if (StringUtils.isNotBlank(url)) {
                docTemplate = url + "%s.html";
            }
        }
        return docTemplate;
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

            if ("menu".equals(element.getName())) {
                String id = element.attributeValue("id");

                if (StringUtils.isBlank(id)) {
                    log.warn(String.format("Invalid sample-config: 'id' attribute not defined for tag" + element.getName()));
                }

                menuItem = new MenuItem(parentItem, id);
                menuItem.setMenu(true);

                loadMenuItems(element, menuItem);

            } else if ("item".equals(element.getName())) {
                String id = element.attributeValue("id");
                if (StringUtils.isNotBlank(id)) {
                    menuItem = new MenuItem(parentItem, id);
                    String docUrl = element.attributeValue("docUrlSuffix");
                    if (StringUtils.isNotBlank(docUrl)) {
                        menuItem.setUrl(docUrl);
                    }
                }
            } else {
                log.warn(String.format("Unknown tag '%s' in sample-config", element.getName()));
            }

            if (parentItem != null) {
                addItem(parentItem.getChildren(), menuItem);
            } else {
                addItem(rootItems, menuItem);
            }
        }
    }

    private void addItem(List<MenuItem> items, MenuItem menuItem) {
        if (menuItem != null) {
            menuItem.setCaption(getMenuItemCaption(menuItem.getId()));
            items.add(menuItem);
        }
    }
}
