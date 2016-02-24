package com.haulmont.sampler.gui.config;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author gorelov
 */
public class MenuItem {

    private final MenuItem parent;
    private final List<MenuItem> children = new ArrayList<>();

    private final String id;
    private String url;
    private String controller;
    private String descriptionsPack;
    private String sampleHeight;
    private String splitEnabled;
    private List<String> otherFiles;
    private Map<String, Object> screenParams;

    private boolean isMenu = false;

    public MenuItem(MenuItem parent, String id) {
        this.parent = parent;
        this.id = id;
    }

    public MenuItem getParent() {
        return parent;
    }

    public String getId() {
        return id;
    }

    public boolean isMenu() {
        return isMenu;
    }

    public void setMenu(boolean isMenu) {
        this.isMenu = isMenu;
    }

    @Nullable
    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    @Nullable
    public String getSampleHeight() {
        return sampleHeight;
    }

    public void setSampleHeight(String sampleHeight) {
        this.sampleHeight = sampleHeight;
    }

    @Nullable
    public String getSplitEnabled() {
        return splitEnabled;
    }

    public void setSplitEnabled(String splitEnabled) {
        this.splitEnabled = splitEnabled;
    }

    public List<String> getOtherFiles() {
        if (otherFiles == null)
            return Collections.emptyList();
        return otherFiles;
    }

    public void setOtherFiles(List<String> otherFiles) {
        this.otherFiles = otherFiles;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Nullable
    public String getDescriptionsPack() {
        return descriptionsPack;
    }

    public void setDescriptionsPack(String description) {
        this.descriptionsPack = description;
    }

    public List<MenuItem> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(MenuItem item) {
        children.add(item);
    }

    @Override
    public String toString() {
        return id;
    }

    public Map<String, Object> getScreenParams() {
        return screenParams != null
                ? Collections.unmodifiableMap(screenParams)
                : Collections.<String, Object>emptyMap();
    }

    public void setScreenParams(Map<String, Object> screenParams) {
        this.screenParams = screenParams;
    }
}
