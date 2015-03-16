package com.haulmont.sampler.web;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.config.WindowConfig;
import com.haulmont.cuba.gui.config.WindowInfo;
import com.haulmont.cuba.web.AppWindow;
import com.haulmont.cuba.web.app.folders.FoldersPane;
import com.haulmont.sampler.gui.SamplesHelper;
import com.haulmont.sampler.gui.config.MenuItem;
import com.haulmont.sampler.gui.config.SamplesMenuConfig;
import com.vaadin.data.Property;
import com.vaadin.ui.*;

import java.util.List;
import java.util.Map;

/**
 * @author gorelov
 * @version $Id$
 */
public class LeftPanel extends FoldersPane {

    private VerticalLayout menuLayout;
    private Tree tree;
    private WindowInfo sampleWindow;

    private WindowConfig windowConfig = AppBeans.get(WindowConfig.NAME);
    private SamplesHelper samplesHelper = AppBeans.get(SamplesHelper.NAME);
    private SamplesMenuConfig samplesMenuConfig = AppBeans.get(SamplesMenuConfig.NAME);

    public LeftPanel(MenuBar menuBar, AppWindow appWindow) {
        super(menuBar, appWindow);
    }

    @Override
    public void init(Component parent) {
        super.init(parent);
        createMenuPanel();
    }

    private void createMenuPanel() {
        Label label = new Label(messages.getMessage(getClass(), "LeftPanel.caption"));
        label.setStyleName("cuba-folders-pane-caption");
        menuLayout = new VerticalLayout();
        menuLayout.setMargin(true);
        menuLayout.setSpacing(true);
        menuLayout.addComponent(label);
        addComponent(menuLayout, 0);

        createMenuTree();
    }

    private void createMenuTree() {
        tree = new Tree();

        fillTree(samplesMenuConfig.getRootItems(), null, true);

        tree.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Object value = tree.getValue();
                if (value != null && value instanceof MenuItem) {
                    MenuItem item = (MenuItem) value;
                    if (item.isMenu()) {
                        switchExpandCollapseState(item);
                    } else {
                        openWindow(item);
                    }
                    tree.unselect(item);
                }
            }
        });

        menuLayout.addComponent(tree);
    }

    private void switchExpandCollapseState(MenuItem item) {
        if (tree.isExpanded(item)) {
            tree.collapseItem(item);
        } else {
            tree.expandItem(item);
        }
    }

    private void fillTree(List<MenuItem> items, MenuItem parent, boolean expand) {
        for (MenuItem item : items) {
            tree.addItem(item);
            tree.setParent(item, parent);
            if (item.isMenu()) {
                fillTree(item.getChildren(), item, expand);
                if (expand) tree.expandItem(item);
            } else {
                tree.setChildrenAllowed(item, false);
            }
        }
    }

    private void openWindow(MenuItem item) {
        if (sampleWindow == null) {
            sampleWindow = windowConfig.getWindowInfo("component-sample-browser");
        }

        Map<String, Object> params = samplesHelper.getParams(item);
        App.getInstance().getWindowManager().openWindow(sampleWindow, WindowManager.OpenType.NEW_TAB, params);
    }
}
