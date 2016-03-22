package com.haulmont.sampler.web.ui.stockcharts.multiplepanels;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.sampler.entity.StockData;
import org.apache.commons.lang.time.DateUtils;

import javax.inject.Inject;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class StockChartMultiplePanelsFrame extends AbstractFrame {
    private static final int DAYS_COUNT = 2000;

    @Inject
    private CollectionDatasource<StockData, UUID> stockChartDs;

    private Random random = new Random();

    @Override
    public void init(Map<String, Object> params) {
        stockChartDs.refresh();

        Date startDate = DateUtils.addDays(new Date(), -DAYS_COUNT);
        for (int i = 0; i < DAYS_COUNT; i++) {
            stockChartDs.includeItem(generateStockData(DateUtils.addDays(startDate, i), i));
        }
    }

    private StockData generateStockData(Date date, int i) {
        Long open = Math.round(random.nextDouble() * 30 + 100);
        Long close = open + Math.round(random.nextDouble() * 15 - random.nextDouble() * 10);

        Long low = (open < close ? open : close) - Math.round(random.nextDouble() * 5);
        Long high = (open < close ? close : open) + Math.round(random.nextDouble() * 5);

        Long volume = Math.round(random.nextDouble() * (1000 + i)) + 100 + i;
        Long value = Math.round(random.nextDouble() * 30 + 100);

        return stockData(date, value, volume, open, close, high, low);
    }

    private StockData stockData(Date date, Long value, Long volume,
                                Long open, Long close, Long high, Long low) {
        StockData dateValueVolume = new StockData();
        dateValueVolume.setDate(date);
        dateValueVolume.setValue(value);
        dateValueVolume.setVolume(volume);
        dateValueVolume.setOpen(open);
        dateValueVolume.setClose(close);
        dateValueVolume.setHigh(high);
        dateValueVolume.setLow(low);
        return dateValueVolume;
    }
}