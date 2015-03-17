package com.haulmont.sampler.web;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.config.WindowConfig;
import com.haulmont.cuba.gui.config.WindowInfo;
import com.haulmont.cuba.web.AppWindow;
import com.haulmont.cuba.web.app.folders.FoldersPane;
import com.haulmont.cuba.web.toolkit.ui.CubaTree;
import com.haulmont.sampler.gui.SamplesHelper;
import com.haulmont.sampler.gui.config.MenuItem;
import com.haulmont.sampler.gui.config.SamplesMenuConfig;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.shared.MouseEventDetails;
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
        samplesMenuConfig.reset();
        createMenuPanel();
    }

    private void createMenuPanel() {
        menuLayout = new VerticalLayout();
        menuLayout.setMargin(true);
        menuLayout.setHeight("100%");
        menuLayout.setWidth("100%");

        Label label = new Label(messages.getMessage(getClass(), "LeftPanel.caption"));
        label.setStyleName("cuba-folders-pane-caption");
        menuLayout.addComponent(label);

        addComponent(menuLayout);

        createMenuTree();
    }

    private void createMenuTree() {
        tree = new CubaTree();
        tree.setHeight("100%");
        tree.setWidth("100%");
        tree.setSelectable(false);
        tree.addItemClickListener(new MenuItemClickListener());

        fillTree(samplesMenuConfig.getRootItems(), null, true);

        menuLayout.addComponent(tree);
        menuLayout.setExpandRatio(tree, 1);
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

    @Override
    protected Component createSearchFoldersPane() {
        return null;
    }

    private class MenuItemClickListener implements ItemClickEvent.ItemClickListener {

        @Override
        public void itemClick(ItemClickEvent event) {
            MenuItem item = (MenuItem) event.getItemId();
            if (item.isMenu()) {
                Component tree = event.getComponent();
                if (tree instanceof Tree)
                    switchExpandState((Tree) tree, item);
            } else {
                if (event.getButton() == MouseEventDetails.MouseButton.LEFT)
                    openWindow(item);
            }
        }

        private void openWindow(MenuItem item) {
            if (sampleWindow == null) {
                sampleWindow = windowConfig.getWindowInfo("component-sample-browser");
            }

            Map<String, Object> params = samplesHelper.getParams(item);
            App.getInstance().getWindowManager().openWindow(sampleWindow, WindowManager.OpenType.NEW_TAB, params);
        }

        private void switchExpandState(Tree tree, MenuItem item) {
            if (tree.isExpanded(item)) {
                tree.collapseItem(item);
            } else {
                tree.expandItem(item);
            }
        }
    }
}
