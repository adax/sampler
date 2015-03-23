package com.haulmont.sampler.gui;

import com.haulmont.bali.util.Dom4j;
import com.haulmont.cuba.core.global.Resources;
import com.haulmont.cuba.gui.components.Window;
import com.haulmont.cuba.gui.config.WindowConfig;
import com.haulmont.cuba.gui.config.WindowInfo;
import com.haulmont.cuba.gui.xml.XmlInheritanceProcessor;
import com.haulmont.sampler.gui.config.MenuItem;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import javax.annotation.ManagedBean;
import javax.annotation.Nullable;
import javax.inject.Inject;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author gorelov
 * @version $Id$
 */
@ManagedBean(SamplesHelper.NAME)
public class SamplesHelper {
    public static final String NAME = "sampler_SamplesHelper";
    private static final Map<String, Object> EMPTY_MAP = new HashMap<>();

    private Log logger = LogFactory.getLog(SamplesHelper.class);

    @Inject
    private Resources resources;

    @Inject
    private WindowConfig windowConfig;

    public Map<String, Object> getParams(MenuItem item) {
        WindowInfo info = windowConfig.getWindowInfo(item.getId());
        Map<String, Object> params = new HashMap<>();
        params.put("windowId", item.getId());
        params.put("caption", item.getCaption());
        params.put("controller", item.getController());
        params.put("otherFiles", item.getOtherFiles());
        params.put("description", item.getDescription());
        params.put("screenSrc", info.getTemplate());
        params.put("docUrlSuffix", item.getUrl());
        Element root = getWindowElement(info.getTemplate());
        if (root != null) {
            Collection<String> keys = getMessagesKeys(root);
            if (CollectionUtils.isNotEmpty(keys)) {
                params.put("messagesPack", getMessagePack(root));
                params.put("messagesKeys", keys);
            }
        }
        return params;
    }

    public String getFileContent(String src) {
        return resources.getResourceAsString(src);
    }

    private Collection<String> getMessagesKeys(Element root) {
        Set<String> keys = new HashSet<>();
        for (Element element : (List<Element>) root.elements()) {
            for (Attribute attribute : (List<Attribute>) element.attributes()) {
                String value = attribute.getValue();
                if (value.startsWith("msg://"))
                    keys.add(value.substring(6));
            }
            keys.addAll(getMessagesKeys(element));
        }
        return keys;
    }

    private String getMessagePack(Element root) {
        return root.attributeValue("messagesPack");
    }

    private String pathFromPackage(String screenClass) {
        return screenClass.replaceAll("[.]", "/") + ".java";
    }

    public String getFileName(String src) {
        Path p = Paths.get(src);
        return p.getFileName().toString();
    }

    @Nullable
    private Element getWindowElement(String src) {
        String text = resources.getResourceAsString(src);
        if (StringUtils.isNotEmpty(text)) {
            try {
                Document document = Dom4j.readDocument(text);
                XmlInheritanceProcessor processor = new XmlInheritanceProcessor(document, EMPTY_MAP);
                Element root = processor.getResultRoot();
                if (root.getName().equals(Window.NAME))
                    return root;
            } catch (RuntimeException e) {
                logger.error("Can't parse screen file: " + src);
            }
        } else {
            logger.error("File doesn't exist or empty: " + src);
        }
        return null;
    }
}
