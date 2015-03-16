/*
 * Copyright (c) ${YEAR} ${PACKAGE_NAME}
 */

package com.haulmont.sampler.gui.config;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gorelov
 * @version $Id$
 */
public class MenuItem {
//    private ScreensHelper screensHelper = AppBeans.get(ScreensHelper.NAME);

    private MenuItem parent;
    private List<MenuItem> children = new ArrayList<>();

    private String id;
    private String url;
    private String caption;

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
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuItem> getChildren() {
        return children;
    }

    public void addChild(MenuItem item) {
        children.add(item);
    }

    @Override
    public String toString() {
        return getCaption();
    }
}
