package com.haulmont.sampler.gui;

import com.haulmont.cuba.core.global.GlobalConfig;
import com.haulmont.cuba.core.global.MessageTools;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import java.util.*;

import static com.haulmont.cuba.gui.components.SourceCodeEditor.Mode;

public class SampleBrowser extends AbstractWindow {

    private static final String DOC_URL_MESSAGES_KEY = "docUrl";

    @Inject
    private TabSheet tabSheet;

    @Inject
    private ComponentsFactory componentsFactory;

    @Inject
    private SamplesHelper samplesHelper;

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
        Map<String, Object> screenParams = (Map<String, Object>) params.get("screenParams");
        IFrame frame = openFrame(null, id, screenParams);
        frame.setId("sampleFrame");
        add(frame, 0);

        String caption = (String) params.get("caption");
        if (StringUtils.isEmpty(caption))
            caption = id;
        setCaption(caption);

        String descriptionsPack = (String) params.get("descriptionsPack");

        if (StringUtils.isNotEmpty(messages.getMessage(frame.getClass(), DOC_URL_MESSAGES_KEY))) {
            String docUrlSuffix = (String) params.get("docUrlSuffix");
            if (StringUtils.isNotEmpty(docUrlSuffix)) {
                addTab(messages.getMessage(getClass(), "sampleBrowser.description"),
                        createDescription(frame.getClass(), descriptionsPack, docUrlSuffix, id));
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

    private Component createDescription(Class<? extends IFrame> aClass, String descriptionsPack, String docUrlSuffix, String frameId) {
        ScrollBoxLayout scrollBoxLayout = componentsFactory.createComponent(ScrollBoxLayout.NAME);
        scrollBoxLayout.setWidth("100%");
        scrollBoxLayout.setHeight("100%");
        scrollBoxLayout.setSpacing(true);

        if (StringUtils.isNotEmpty(descriptionsPack)) {
            scrollBoxLayout.add(descriptionText(frameId, descriptionsPack));
        }

        HBoxLayout hbox = componentsFactory.createComponent(HBoxLayout.NAME);
        hbox.setWidth("100%");

        Component docLinks = documentLinks(aClass, docUrlSuffix);
        hbox.add(docLinks);
        hbox.expand(docLinks);

        hbox.add(permalink(frameId));

        scrollBoxLayout.add(hbox);

        return scrollBoxLayout;
    }

    private Component descriptionText(String frameId, String descriptionsPack) {
        StringBuilder sb = new StringBuilder();
        String text = samplesHelper.getFileContent(getDescriptionFileName(descriptionsPack, frameId));
        if (StringUtils.isNotEmpty(text)) {
            sb.append(text);
            sb.append("<hr>");
        }
        Label doc = componentsFactory.createComponent(Label.NAME);
        doc.setHtmlEnabled(true);
        doc.setWidth("100%");
        doc.setValue(sb.toString());
        return doc;
    }

    private Component documentLinks(Class<? extends IFrame> aClass, String docUrlSuffix) {
        StringBuilder sb = new StringBuilder();
        sb.append(messages.getMessage(getClass(), "sampleBrowser.documentation"));
        sb.append(": ");

        Locale defaultLocale = messageTools.getDefaultLocale();
        Map<String, Locale> availableLocales = globalConfig.getAvailableLocales();
        for (String localeName : availableLocales.keySet()) {
            Locale locale = availableLocales.get(localeName);
            if (!defaultLocale.equals(locale)) {
                sb.append(" | ");
            }

            String url = messages.getMessage(aClass, DOC_URL_MESSAGES_KEY, locale) + docUrlSuffix + ".html";
            sb.append(String.format("<a href=\"%s\" target=\"_blank\">%s</a>", url, localeName));
        }

        Label docLinks = componentsFactory.createComponent(Label.NAME);
        docLinks.setHtmlEnabled(true);
        docLinks.setValue(sb.toString());

        return docLinks;
    }

    private Component permalink(String frameId) {
        Link permalink = componentsFactory.createComponent(Link.NAME);
        permalink.setAlignment(Alignment.TOP_RIGHT);
        permalink.setDescription("Permalink");
        permalink.setUrl("open?screen=" + frameId);
        permalink.setIcon("font-icon:EXTERNAL_LINK");

        return permalink;
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
//            StringBuilder sb = new StringBuilder();
            String tabTitle;
            if (defaultLocale.equals(locale)) {
                tabTitle = "messages.properties";
            } else {
                tabTitle = String.format("messages_%s.properties", locale.toString());
            }

            String content = samplesHelper.getFileContent(samplesHelper.packageToPath(messagesPack) + "/" + tabTitle);
            if (StringUtils.isNotBlank(content)) {
                sourceCodeEditor.setValue(content);
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