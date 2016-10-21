package com.haulmont.sampler.web.ui.charts.charts.incrementalupdate.line;

import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.components.Timer;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.sampler.entity.DateValue;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;

import javax.inject.Inject;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class UpdateLineChartFrame extends AbstractFrame {
    @Inject
    private CollectionDatasource<DateValue, UUID> dateValueDs;
    @Inject
    private TextField delayField;
    @Inject
    private Metadata metadata;

    private Date lastDate;
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
        dateValueDs.includeItem(generateDateValue());
    }

    private void removeDate() {
        if (!dateValueDs.getItems().isEmpty()) {
            dateValueDs.excludeItem(dateValueDs.getItems().iterator().next());
        }
    }

    private DateValue generateDateValue() {
        DateValue dateValue = metadata.create(DateValue.class);
        lastDate = DateUtils.addDays(getLastDate(), 1);
        dateValue.setDate(lastDate);
        dateValue.setValue(RandomUtils.nextInt(50));
        return dateValue;
    }

    private Date getLastDate() {
        if (lastDate == null) {
            final Iterator<DateValue> itr = dateValueDs.getItems().iterator();
            DateValue lastElement = itr.next();
            while (itr.hasNext()) {
                lastElement = itr.next();
            }
            return lastElement.getDate();
        }
        return lastDate;
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
}