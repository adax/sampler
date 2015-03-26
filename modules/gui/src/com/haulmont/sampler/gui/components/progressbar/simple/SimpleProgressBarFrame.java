package com.haulmont.sampler.gui.components.progressbar.simple;

import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.ProgressBar;
import com.haulmont.cuba.gui.executors.BackgroundTask;
import com.haulmont.cuba.gui.executors.BackgroundTaskHandler;
import com.haulmont.cuba.gui.executors.BackgroundWorker;
import com.haulmont.cuba.gui.executors.TaskLifeCycle;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SimpleProgressBarFrame extends AbstractFrame {
    @Inject
    protected ProgressBar progressBar;

    @Inject
    protected BackgroundWorker backgroundWorker;

    private static final int ITERATIONS = 5;

    @Override
    public void init(Map<String, Object> params) {
        BackgroundTask<Integer, Void> task = new BackgroundTask<Integer, Void>(300, TimeUnit.SECONDS, this) {
            @Override
            public Void run(TaskLifeCycle<Integer> taskLifeCycle) throws Exception {
                for (int i = 1; i <= ITERATIONS; i++) {
                    TimeUnit.SECONDS.sleep(2); // time consuming task
                    taskLifeCycle.publish(i);
                }
                return null;
            }

            @Override
            public void progress(List<Integer> changes) {
                float lastValue = changes.get(changes.size() - 1);
                progressBar.setValue(lastValue / ITERATIONS);
            }
        };

        BackgroundTaskHandler taskHandler = backgroundWorker.handle(task);
        taskHandler.execute();
    }
}