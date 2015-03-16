package com.haulmont.sampler.gui.components;

import com.haulmont.cuba.core.global.GlobalConfig;
import com.haulmont.cuba.core.global.MessageTools;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.sampler.gui.SamplesHelper;
import com.haulmont.sampler.gui.config.SamplesMenuConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ComponentSampleBrowser extends AbstractWindow {
    @Inject
    private TabSheet tabSheet;

    @Inject
    private ComponentsFactory componentsFactory;

    @Inject
    private SamplesHelper samplesHelper;

    @Inject
    private SamplesMenuConfig samplesMenuConfig;

    @Inject
    private Messages messages;

    @Inject
    private MessageTools messageTools;

    @Inject
    private GlobalConfig globalConfig;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        String id = (String) params.get("windowId");
        add(openFrame(null, id), 0);

        String caption = (String) params.get("caption");
        if (StringUtils.isEmpty(caption))
            caption = id;
        setCaption(caption);

        if (StringUtils.isNotEmpty(samplesMenuConfig.getDocTemplate())) {
            String url = (String) params.get("docUrlSuffix");
            if (StringUtils.isNotEmpty(url)) {
                addTab("Documentation",
                        createDescription(caption, String.format(samplesMenuConfig.getDocTemplate(), url)));
            }
        }

        String screenSrc = (String) params.get("screenSrc");
        addSourceTab(screenSrc, SourceCodeEditor.Mode.XML);

        String controllerSrc = (String) params.get("controllerSrc");
        addSourceTab(controllerSrc, SourceCodeEditor.Mode.Java);

        List<String> otherClassesSrc = (List<String>) params.get("otherClassesSrc");
        if (CollectionUtils.isNotEmpty(otherClassesSrc)) {
            for (String src : otherClassesSrc) {
                addSourceTab(src, SourceCodeEditor.Mode.Java);
            }
        }

        String messagesPack = (String) params.get("messagesPack");
        if (StringUtils.isNotEmpty(messagesPack)) {
            List<String> messagesKeys = (List<String>) params.get("messagesKeys");
            createMessagesContainer(messagesPack, messagesKeys);
        }
    }

    private Container createContainer() {
        VBoxLayout vBox = componentsFactory.createComponent(VBoxLayout.NAME);
        vBox.setMargin(true);
        vBox.setHeight("100%");

        return vBox;
    }

    private Component createDescription(String text, String url) {
        Label doc = componentsFactory.createComponent(Label.NAME);
        doc.setHtmlEnabled(true);
        doc.setValue(String.format("%s<br/><br/><a href=\"%s\" target=\"_blank\">%s</a>", text, url, url));

        return doc;
    }

    private SourceCodeEditor createSourceCodeEditor(SourceCodeEditor.Mode mode) {
        SourceCodeEditor editor = componentsFactory.createComponent(SourceCodeEditor.NAME);
        editor.setMode(mode);
        editor.setEditable(false);
        editor.setWidth("100%");
        editor.setHeight("100%");

        return editor;
    }

    private void createMessagesContainer(String messagesPack, List<String> messagesKeys) {
        Locale defaultLocale = messageTools.getDefaultLocale();
        for (Locale locale : globalConfig.getAvailableLocales().values()) {
            SourceCodeEditor sourceCodeEditor = createSourceCodeEditor(SourceCodeEditor.Mode.Properties);
            StringBuilder sb = new StringBuilder();
            String tabTitle;
            if (defaultLocale.equals(locale)) {
                tabTitle = "messages.properties";
            } else {
                tabTitle = String.format("messages_%s.properties", locale.toString());
            }
            for (String key : messagesKeys) {
                sb.append(key).append(" = ").append(messages.getMessage(messagesPack, key, locale)).append("\n");
            }
            sourceCodeEditor.setValue(sb.toString());
            addTab(tabTitle, sourceCodeEditor);
        }
    }

    private void addTab(String name, Component component) {
        Container container = createContainer();
        container.add(component);
        tabSheet.addTab(name, container);
        TabSheet.Tab tab = tabSheet.getTab(name);
        tab.setCaption(name);
    }

    private void addSourceTab(String src, SourceCodeEditor.Mode mode) {
        if (StringUtils.isNotEmpty(src)) {
            SourceCodeEditor sourceCodeEditor = createSourceCodeEditor(mode);
            sourceCodeEditor.setValue(samplesHelper.getFileContent(src));
            addTab(samplesHelper.getFileName(src), sourceCodeEditor);
        }
    }
}