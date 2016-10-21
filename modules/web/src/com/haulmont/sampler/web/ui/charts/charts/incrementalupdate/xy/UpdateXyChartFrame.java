package com.haulmont.sampler.web.ui.charts.charts.incrementalupdate.xy;

import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.components.Timer;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.sampler.entity.PointValue;
import org.apache.commons.lang.math.RandomUtils;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class UpdateXyChartFrame extends AbstractFrame {

    @Inject
    private CollectionDatasource<PointValue, UUID> pointsDs;
    @Inject
    private TextField delayField;
    @Inject
    private Metadata metadata;

    private Timer timer;

    @Override
    public void init(Map<String, Object> params) {
        timer = (Timer) params.get("timer");
        timer.addActionListener(timer -> {
            addDate();
            removeDate();
        });
        timer.start();

        delayField.setValue(timer.getDelay());
        delayField.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                timer.setDelay((Integer) e.getValue());
            } else {
                delayField.setValue(e.getPrevValue());
            }
        });
    }

    private void addDate() {
        pointsDs.includeItem(generatePointValue());
    }

    private void removeDate() {
        if (!pointsDs.getItems().isEmpty()) {
            pointsDs.excludeItem(pointsDs.getItems().iterator().next());
        }
    }

    private PointValue generatePointValue() {
        PointValue pointValue = metadata.create(PointValue.class);
        pointValue.setX((RandomUtils.nextInt(20) - 5) * RandomUtils.nextDouble());
        pointValue.setY((RandomUtils.nextInt(40) - 20) * RandomUtils.nextDouble());
        pointValue.setValue(RandomUtils.nextInt(90) + 10);
        return pointValue;
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
}