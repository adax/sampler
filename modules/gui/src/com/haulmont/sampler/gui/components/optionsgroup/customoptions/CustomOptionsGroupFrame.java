package com.haulmont.sampler.gui.components.optionsgroup.customoptions;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.OptionsGroup;

import javax.inject.Inject;
import java.util.*;

public class CustomOptionsGroupFrame extends AbstractFrame {

    @Inject
    private OptionsGroup optionsGroupWithList;
    @Inject
    private OptionsGroup optionsGroupWithMap;

    @Override
    public void init(Map<String, Object> params) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(7);
        optionsGroupWithList.setOptionsList(list);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("two", 2);
        map.put("four", 4);
        map.put("five", 5);
        map.put("seven", 7);
        optionsGroupWithMap.setOptionsMap(map);
    }
}