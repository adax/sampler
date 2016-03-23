package com.haulmont.sampler.web.ui.stockcharts.intradaydata;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.sampler.entity.DateValueVolume;
import org.apache.commons.lang.time.DateUtils;

import javax.inject.Inject;
import java.util.*;

public class StockChartIntradayDataFrame extends AbstractFrame {
    private static final int MINUTES_COUNT = 1000;

    @Inject
    private CollectionDatasource<DateValueVolume, UUID> stockChartDs;

    private Random random = new Random();

    @Override
    public void init(Map<String, Object> params) {
        stockChartDs.refresh();

        Date startDate = DateUtils.addDays(getZeroTime(new Date()), -MINUTES_COUNT);
        for (int i = 0; i < MINUTES_COUNT; i++) {
            stockChartDs.includeItem(addDateValueVolumeTime(DateUtils.addMinutes(startDate, i), i));
        }
    }

    private DateValueVolume addDateValueVolumeTime(Date date, int i) {
        Long value = Math.round(random.nextDouble() * (40 + i)) + 100 + i;
        Long volume = Math.round(random.nextDouble() * 100000000);
        return dateValueVolume(date, value, volume);
    }

    private DateValueVolume dateValueVolume(Date date, Long value, Long volume) {
        DateValueVolume dateValueVolume = new DateValueVolume();
        dateValueVolume.setDate(date);
        dateValueVolume.setValue(value);
        dateValueVolume.setVolume(volume);
        return dateValueVolume;
    }

    private Date getZeroTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}