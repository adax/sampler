package com.haulmont.sampler.gui.components;

import com.haulmont.cuba.core.global.GlobalConfig;
import com.haulmont.cuba.core.global.MessageTools;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.sampler.gui.SamplesHelper;
import com.haulmont.sampler.gui.config.SamplesMenuConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.haulmont.cuba.gui.components.SourceCodeEditor.Mode;

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

    @Inject
    protected UserSessionSource userSessionSource;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        String id = (String) params.get("windowId");
        add(openFrame(null, id), 0);

        String caption = (String) params.get("caption");
        if (StringUtils.isEmpty(caption))
            caption = id;
        setCaption(caption);

        String descriptionsPack = (String) params.get("descriptionsPack");

        if (StringUtils.isNotEmpty(samplesMenuConfig.getDocTemplate())) {
            String docUrlSuffix = (String) params.get("docUrlSuffix");
            if (StringUtils.isNotEmpty(docUrlSuffix)) {
                addTab(messages.getMessage(getClass(), "sampleBrowser.description"),
                        createDescription(descriptionsPack, docUrlSuffix, id));
            }
        }

        String screenSrc = (String) params.get("screenSrc");
        addSourceTab(screenSrc, Mode.XML);

        String controller = (String) params.get("controller");
        if (StringUtils.isNotEmpty(controller))
            addSourceTab(controller, getMode(samplesHelper.getFileName(controller)));

        List<String> otherFiles = (List<String>) params.get("otherFiles");
        if (CollectionUtils.isNotEmpty(otherFiles)) {
            for (String src : otherFiles) {
                addSourceTab(src, getMode(samplesHelper.getFileName(src)));
            }
        }

        String messagesPack = (String) params.get("messagesPack");
        if (StringUtils.isNotEmpty(messagesPack)) {
            Collection<String> messagesKeys = (Collection<String>) params.get("messagesKeys");
            createMessagesContainers(messagesPack, messagesKeys);
        }
    }

    private Container createContainer() {
        VBoxLayout vBox = componentsFactory.createComponent(VBoxLayout.NAME);
        vBox.setMargin(true);
        vBox.setHeight("100%");

        return vBox;
    }

    private Component createDescription(String descriptionsPack, String docUrlSuffix, String frameId) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(descriptionsPack)) {
            String text = samplesHelper.getFileContent(getDescriptionFileName(descriptionsPack, frameId));
            if (StringUtils.isNotEmpty(text)) {
                sb.append(text);
                sb.append("<hr>");
            }
        }
        sb.append("<p>");
        sb.append(messages.getMessage(getClass(), "sampleBrowser.documentation"));
        sb.append(": ");

        Locale defaultLocale = messageTools.getDefaultLocale();
        Map<String, Locale> availableLocales = globalConfig.getAvailableLocales();
        for (String localeName : availableLocales.keySet()) {
            Locale locale = availableLocales.get(localeName);
            if (!defaultLocale.equals(locale)) {
                sb.append(" | ");
            }

            String url = String.format(samplesMenuConfig.getDocTemplate(locale), docUrlSuffix);
            sb.append(String.format("<a href=\"%s\" target=\"_blank\">%s</a>", url, localeName));
        }
        sb.append("</p>");

        Label doc = componentsFactory.createComponent(Label.NAME);
        doc.setHtmlEnabled(true);
        doc.setWidth("100%");
        doc.setValue(sb.toString());

        return doc;
    }

    private String getDescriptionFileName(String descriptionsPack, String frameId) {
        StringBuilder sb = new StringBuilder(descriptionsPack);
        if (!descriptionsPack.endsWith("/"))
            sb.append("/");
        sb.append(frameId).append("-");
        sb.append(getUserLocale().toString());
        sb.append(".html");
        return sb.toString();
    }

    private SourceCodeEditor createSourceCodeEditor(Mode mode) {
        SourceCodeEditor editor = componentsFactory.createComponent(SourceCodeEditor.NAME);
        editor.setMode(mode);
        editor.setEditable(false);
        editor.setWidth("100%");
        editor.setHeight("100%");

        return editor;
    }

    private void createMessagesContainers(String messagesPack, Collection<String> messagesKeys) {
        Locale defaultLocale = messageTools.getDefaultLocale();
        for (Locale locale : globalConfig.getAvailableLocales().values()) {
            SourceCodeEditor sourceCodeEditor = createSourceCodeEditor(Mode.Properties);
            StringBuilder sb = new StringBuilder();
            String tabTitle;
            if (defaultLocale.equals(locale)) {
                tabTitle = "messages.properties";
            } else {
                tabTitle = String.format("messages_%s.properties", locale.toString());
            }
            for (String key : messagesKeys) {
                String message = messages.findMessage(messagesPack, key, locale);
                if (StringUtils.isNotEmpty(message))
                    sb.append(key).append(" = ").append(message).append("\n");
            }
            if (sb.length() > 0) {
                sourceCodeEditor.setValue(sb.toString());
                addTab(tabTitle, sourceCodeEditor);
            }
        }
    }

    private void addTab(String name, Component component) {
        Container container = createContainer();
        container.add(component);
        tabSheet.addTab(name, container);
        TabSheet.Tab tab = tabSheet.getTab(name);
        tab.setCaption(name);
    }

    private void addSourceTab(String src, Mode mode) {
        if (StringUtils.isNotEmpty(src)) {
            SourceCodeEditor sourceCodeEditor = createSourceCodeEditor(mode);
            sourceCodeEditor.setValue(samplesHelper.getFileContent(src));
            addTab(samplesHelper.getFileName(src), sourceCodeEditor);
        }
    }

    private Mode getMode(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index >= 0) {
            String extension = fileName.substring(index + 1);
            return Mode.parse(extension);
        }
        return Mode.Text;
    }

    private Locale getUserLocale() {
        return userSessionSource.getUserSession().getLocale();
    }
}