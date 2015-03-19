package com.haulmont.sampler.web.sys;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.web.App;
import com.haulmont.cuba.web.sys.LinkHandler;
import com.haulmont.sampler.gui.SamplesHelper;
import com.haulmont.sampler.gui.config.MenuItem;
import com.haulmont.sampler.gui.config.SamplesMenuConfig;

import java.util.Iterator;
import java.util.Map;

/**
 * @author gorelov
 * @version $Id$
 */
public class SamplerLinkHandler extends LinkHandler {
    public SamplerLinkHandler(App app, String action, Map<String, String> requestParams) {
        super(app, action, requestParams);
    }

    @Override
    public void handle() {
        String screenName = requestParams.get("screen");
        if (screenName == null) {
            log.warn("ScreenId not found in request parameters");
            return;
        }

        // TODO need to test
        SamplesMenuConfig samplesMenuConfig = AppBeans.get(SamplesMenuConfig.NAME);
        MenuItem item = samplesMenuConfig.findItemById(screenName);
        if (item != null && !item.isMenu()) {
            requestParams.put("screen", "component-sample-browser");

            SamplesHelper samplesHelper = AppBeans.get(SamplesHelper.NAME);
            Map<String, Object> params = samplesHelper.getParams(item);
            requestParams.put("params", mapToParam(params));
        }

        super.handle();
    }

    private String mapToParam(Map<String, Object> params) {
        Iterator<Map.Entry<String, Object>> i = params.entrySet().iterator();
        if (!i.hasNext())
            return "";

        StringBuilder sb = new StringBuilder();
        for (; ; ) {
            Map.Entry<String, Object> e = i.next();
            String key = e.getKey();
            Object value = e.getValue();
            sb.append(key).append(':').append(value);
            if (i.hasNext())
                sb.append(',');
            return sb.toString();
        }
    }
}
