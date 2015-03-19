package com.haulmont.sampler.gui.components.embedded;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Embedded;

import javax.inject.Inject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class WebEmbeddedFrame extends AbstractFrame {
    @Inject
    private Embedded embedded;

    @Override
    public void init(Map<String, Object> params) {
        try {
            embedded.setSource(new URL("http://www.cuba-platform.com"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}