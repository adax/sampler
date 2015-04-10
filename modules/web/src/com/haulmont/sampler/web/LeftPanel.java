package com.haulmont.sampler.web;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.config.WindowInfo;
import com.haulmont.cuba.web.AppWindow;
import com.haulmont.cuba.web.app.folders.FoldersPane;
import com.haulmont.cuba.web.toolkit.ui.CubaTextField;
import com.haulmont.cuba.web.toolkit.ui.CubaTree;
import com.haulmont.sampler.gui.SamplesHelper;
import com.haulmont.sampler.gui.config.MenuItem;
import com.haulmont.sampler.gui.config.SamplesMenuConfig;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ShortcutListener;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author gorelov
 * @version $Id$
 */
public class LeftPanel extends FoldersPane {

    private static final String PROPERTY_CAPTION = "caption";

    private VerticalLayout menuLayout;
    private Tree tree;
    private WindowInfo sampleWindow;
    private Container.Filter filter;

    private SamplesHelper samplesHelper = AppBeans.get(SamplesHelper.NAME);
    private SamplesMenuConfig samplesMenuConfig = AppBeans.get(SamplesMenuConfig.NAME);

    public LeftPanel(MenuBar menuBar, AppWindow appWindow) {
        super(menuBar, appWindow);
    }

    @Override
    public void init(Component parent) {
        super.init(parent);
        samplesMenuConfig.reset();
        sampleWindow = samplesHelper.getSampleBrowser();
        createMenuPanel();
    }

    private void createMenuPanel() {
        menuLayout = new VerticalLayout();
        menuLayout.setMargin(true);
        menuLayout.setHeight("100%");
        menuLayout.setWidth("100%");

        createMenuSearch();
        createMenuHeader();
        createMenuTree();

        addComponent(menuLayout);
    }

    private void createMenuSearch() {
        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.setSpacing(true);
        searchLayout.setMargin(new MarginInfo(true, false, true, false));
        searchLayout.setWidth("100%");

        final CubaTextField searchField = new CubaTextField();
        searchField.setWidth("100%");
        searchField.addShortcutListener(new ShortcutListener("", com.vaadin.event.ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                search(searchField.getValue());
            }
        });
        searchField.focus();
        searchLayout.addComponent(searchField);
        searchLayout.setExpandRatio(searchField, 1);

        filter = null;
        Button searchButton = new Button(messages.getMessage(getClass(), "LeftPanel.search"));
        searchButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                search(searchField.getValue());
            }
        });
        searchLayout.addComponent(searchButton);
        searchLayout.setComponentAlignment(searchButton, Alignment.MIDDLE_RIGHT);

        menuLayout.addComponent(searchLayout);

    }

    private void createMenuHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setSpacing(true);
        header.setWidth("100%");

        Label label = new Label(messages.getMessage(getClass(), "LeftPanel.caption"));
        label.setStyleName("cuba-folders-pane-caption");
        header.addComponent(label);
        header.setExpandRatio(label, 1);

        // TODO For development convenience only
        if (BooleanUtils.toBoolean(AppContext.getProperty("sampler.developerMode"))) {
            Button refresh = createButton("Refresh", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    resetAllMenuItems();
                }
            });
            header.addComponent(refresh);
            header.setComponentAlignment(refresh, Alignment.MIDDLE_RIGHT);
        }

        Button collapseAll = createButton("LeftPanel.collapseAll", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                collapseAll();
            }
        });
        header.addComponent(collapseAll);
        header.setComponentAlignment(collapseAll, Alignment.MIDDLE_RIGHT);

        Button expandAll = createButton("LeftPanel.expandAll", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                expandAll();
            }
        });
        header.addComponent(expandAll);
        header.setComponentAlignment(expandAll, Alignment.MIDDLE_RIGHT);

        menuLayout.addComponent(header);
    }

    private Button createButton(String captionKey, Button.ClickListener listener) {
        Button button = new Button(messages.getMessage(getClass(), captionKey));
        button.addStyleName(Reindeer.BUTTON_LINK);
        button.addStyleName("small-link");
        button.addStyleName("dark");
        button.addClickListener(listener);

        return button;
    }

    private void createMenuTree() {
        tree = new CubaTree();
        tree.setHeight("100%");
        tree.setWidth("100%");
        tree.setSelectable(false);
        tree.addItemClickListener(new MenuItemClickListener());
        tree.setItemCaptionPropertyId(PROPERTY_CAPTION);
        tree.setContainerDataSource(createTreeContent());

        menuLayout.addComponent(tree);
        menuLayout.setExpandRatio(tree, 1);
    }

    private HierarchicalContainer createTreeContent() {
        HierarchicalContainer container = new HierarchicalContainer();
        container.addContainerProperty(PROPERTY_CAPTION, String.class, "");
        fillContainer(container, samplesMenuConfig.getRootItems(), null);
        return container;
    }

    private void fillContainer(HierarchicalContainer container, List<MenuItem> items, MenuItem parent) {
        for (MenuItem item : items) {
            Item containerItem = container.addItem(item);
            Property<String> caption = containerItem.getItemProperty(PROPERTY_CAPTION);
            caption.setValue(samplesMenuConfig.getMenuItemCaption(item.getId()));
            container.setParent(item, parent);
            if (item.isMenu()) {
                fillContainer(container, item.getChildren(), item);
            } else {
                container.setChildrenAllowed(item, false);
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

    private void expandAll() {
        for (MenuItem item : (Collection<MenuItem>) tree.rootItemIds())
            tree.expandItemsRecursively(item);
    }

    private void collapseAll() {
        for (MenuItem item : (Collection<MenuItem>) tree.rootItemIds())
            tree.collapseItemsRecursively(item);
    }

    private void search(final String searchRequest) {
        Container.Filterable container = (Container.Filterable) tree.getContainerDataSource();
        if (StringUtils.isBlank(searchRequest)) {
            tree.setItemStyleGenerator(null);
            container.removeContainerFilter(filter);
        } else {
            tree.setItemStyleGenerator(new TreeSearchStyle(searchRequest));
            if (filter != null)
                container.removeContainerFilter(filter);
            filter = new TreeSearchFilter(PROPERTY_CAPTION, searchRequest);
            container.addContainerFilter(filter);
            expandAll();
        }
    }

    private void resetAllMenuItems() {
        samplesMenuConfig.reset();
        tree.setItemStyleGenerator(null);
        tree.setContainerDataSource(createTreeContent());
        expandAll();
    }

    private class TreeSearchStyle implements Tree.ItemStyleGenerator {

        private String searchString;

        public TreeSearchStyle(String searchString) {
            this.searchString = searchString.toLowerCase();
        }

        @Override
        public String getStyle(Tree source, Object itemId) {
            MenuItem item = (MenuItem) itemId;
            if (samplesMenuConfig
                    .getMenuItemCaption(item.getId())
                    .toLowerCase()
                    .contains(searchString)) {
                return "bold";
            }
            return null;
        }
    }

    private class TreeSearchFilter implements Container.Filter {

        private String propertyId;
        private String searchRequest;

        public TreeSearchFilter(String propertyId, String searchRequest) {
            this.propertyId = propertyId;
            this.searchRequest = searchRequest.toLowerCase();
        }

        @Override
        public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException {
            Property property = item.getItemProperty(propertyId);
            if (property == null || !property.getType().equals(String.class))
                return false;

            String value = (String) property.getValue();
            return match(value) || checkParents((MenuItem) itemId);
        }

        @Override
        public boolean appliesToProperty(Object propertyId) {
            return propertyId != null &&
                    propertyId.equals(this.propertyId);
        }

        private boolean checkParents(MenuItem item) {
            if (item.getParent() != null) {
                MenuItem parent = item.getParent();
                return match(samplesMenuConfig.getMenuItemCaption(parent.getId())) || checkParents(parent);
            }
            return false;
        }

        private boolean match(String value) {
            return value.toLowerCase().contains(searchRequest);
        }
    }
}
