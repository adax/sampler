package com.haulmont.sampler.gui.components.table.icon;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Table;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Map;

public class IconTableFrame extends AbstractFrame {
    @Inject
    private Table customerTable;

    @Override
    public void init(Map<String, Object> params) {
        customerTable.setIconProvider(new Table.IconProvider() {
            @Nullable
            @Override
            public String getItemIcon(Entity entity) {
                return "icons/ok.png";
            }
        });
    }
}