package com.haulmont.sampler.gui.components.embedded.image;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.ClientType;
import com.haulmont.cuba.gui.AppConfig;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.export.FileDataProvider;

import javax.inject.Inject;
import java.util.*;

public class ImageEmbeddedFrame extends AbstractFrame {

    @Inject
    private Embedded imageFromFileStorage;
    @Inject
    private Embedded image;

    @Override
    public void init(Map<String, Object> params) {
        loadImageFromFileStorage();

        if (AppConfig.getClientType() == ClientType.WEB) {
            image.setSource("cuba-logo.png");
        }
    }

    private void loadImageFromFileStorage() {
        // A file descriptor entity is usually stored in the database and referenced by an attribute of your data
        // model entity. Here we simply create it for the purpose of the example.
        FileDescriptor imageFile = new FileDescriptor();
        imageFile.setId(UUID.fromString("61a6a1ee-f13a-f44f-0201-c2f9b3288305"));
        imageFile.setName("platform-logo.png");
        imageFile.setCreateDate(new Date(1427857200503L));
        imageFile.setExtension("png");

        FileDataProvider provider = new FileDataProvider(imageFile);
        imageFromFileStorage.setSource(imageFile.getName(), provider);
        imageFromFileStorage.setType(Embedded.Type.IMAGE);
        imageFromFileStorage.setVisible(true);
    }
}