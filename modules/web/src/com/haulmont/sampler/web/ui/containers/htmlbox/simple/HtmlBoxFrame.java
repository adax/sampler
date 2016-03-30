package com.haulmont.sampler.web.ui.containers.htmlbox.simple;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.VBoxLayout;

import javax.inject.Inject;
import java.util.Map;

public class HtmlBoxFrame extends AbstractFrame {
    @Inject
    private VBoxLayout waterLevel;

    @Inject
    private VBoxLayout fanFirst;

    @Inject
    private VBoxLayout fanSecond;

    @Inject
    private VBoxLayout fanThird;

    @Inject
    private VBoxLayout fanFourth;

    protected int waterLevelValue = 5;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        waterLevel.setStyleName("water position-" + waterLevelValue);
    }

    public void moveUp() {
        if (waterLevelValue + 1 < 11) {
            waterLevelValue++;
            waterLevel.setStyleName("water position-" + waterLevelValue);
        }
    }

    public void moveDown() {
        if (waterLevelValue - 1 >= 0) {
            waterLevelValue--;
            waterLevel.setStyleName("water position-" + waterLevelValue);
        }
    }

    public void speedDown() {
        fanFirst.setStyleName("fan fan-slow");
        fanSecond.setStyleName("fan fan-slow");
        fanThird.setStyleName("fan fan-slow");
        fanFourth.setStyleName("fan fan-slow");
    }

    public void speedNormal() {
        fanFirst.setStyleName("fan fan-normal");
        fanSecond.setStyleName("fan fan-normal");
        fanThird.setStyleName("fan fan-normal");
        fanFourth.setStyleName("fan fan-normal");
    }

    public void speedUp() {
        fanFirst.setStyleName("fan fan-fast");
        fanSecond.setStyleName("fan fan-fast");
        fanThird.setStyleName("fan fan-fast");
        fanFourth.setStyleName("fan fan-fast");
    }
}