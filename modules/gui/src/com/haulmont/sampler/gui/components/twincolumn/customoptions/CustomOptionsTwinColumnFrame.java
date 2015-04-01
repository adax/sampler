package com.haulmont.sampler.gui.components.twincolumn.customoptions;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.TwinColumn;

import javax.inject.Inject;
import java.util.*;

public class CustomOptionsTwinColumnFrame extends AbstractFrame {

    @Inject
    private TwinColumn twinColumnWithOptionsList;
    @Inject
    private TwinColumn twinColumnWithOptionsMap;

    @Override
    public void init(Map<String, Object> params) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(7);
        twinColumnWithOptionsList.setOptionsList(list);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("two", 2);
        map.put("four", 4);
        map.put("five", 5);
        map.put("seven", 7);
        twinColumnWithOptionsMap.setOptionsMap(map);
    }
}