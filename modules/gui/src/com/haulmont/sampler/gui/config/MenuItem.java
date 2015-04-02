package com.haulmont.sampler.gui.config;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author gorelov
 * @version $Id$
 */
public class MenuItem {
    private MenuItem parent;
    private List<MenuItem> children = new ArrayList<>();

    private String id;
    private String url;
    private String controller;
    private String descriptionsPack;
    private List<String> otherFiles;

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
        return children;
    }

    public void addChild(MenuItem item) {
        children.add(item);
    }

    @Override
    public String toString() {
        return id;
    }
}
