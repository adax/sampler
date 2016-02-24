package com.haulmont.sampler.web.sys;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.web.App;
import com.haulmont.cuba.web.sys.LinkHandler;
import com.haulmont.sampler.gui.SamplesHelper;
import com.haulmont.sampler.gui.config.MenuItem;
import com.haulmont.sampler.gui.config.SamplesMenuConfig;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author gorelov
 */
public class SamplerLinkHandler extends LinkHandler {
    @Inject
    private SamplesHelper samplesHelper;
    @Inject
    private SamplesMenuConfig samplesMenuConfig;

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

        MenuItem item = samplesMenuConfig.findItemById(screenName);
        if (item != null && !item.isMenu()) {
            Map<String, Object> params = samplesHelper.getParams(item);
            App.getInstance().getWindowManager().openWindow(samplesHelper.getSampleBrowser(), WindowManager.OpenType.NEW_TAB, params);
        } else {
            super.handle();
        }
    }
}
