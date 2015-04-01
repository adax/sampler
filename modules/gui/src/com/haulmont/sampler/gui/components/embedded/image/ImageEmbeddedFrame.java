package com.haulmont.sampler.gui.components.embedded.image;

import com.haulmont.cuba.core.app.FileStorageService;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.ClientType;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.gui.AppConfig;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Embedded;
import com.haulmont.cuba.gui.components.GroupBoxLayout;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class ImageEmbeddedFrame extends AbstractFrame {

    @Inject
    private GroupBoxLayout groupBoxImage;
    @Inject
    private Embedded embeddedImage;
    @Inject
    private Embedded embeddedImageFile;
    @Inject
    private FileStorageService fileStorageService;

    @Override
    public void init(Map<String, Object> params) {
        displayImage();

        if (AppConfig.getClientType() == ClientType.WEB) {
            embeddedImageFile.setSource("cuba-logo.png");
        }
    }

    private void displayImage() {
        float img_height = groupBoxImage.getHeight() - 60;
        float img_width = groupBoxImage.getWidth() - 60;

        FileDescriptor imageFile = new FileDescriptor();
        imageFile.setId(UUID.fromString("61a6a1ee-f13a-f44f-0201-c2f9b3288305"));
        imageFile.setName("platform-logo.png");
        imageFile.setCreateDate(new Date(1427857200503L));
        imageFile.setExtension("png");

        byte[] bytes = null;
        if (imageFile != null) {
            try {
                bytes = fileStorageService.loadFile(imageFile);
            } catch (FileStorageException e) {
                showNotification("Unable to load image file", NotificationType.HUMANIZED);
            }
        }
        if (bytes != null) {
            embeddedImage.setSource(imageFile.getName(), new ByteArrayInputStream(bytes));
            embeddedImage.setType(Embedded.Type.IMAGE);
            BufferedImage image;
            try {
                image = ImageIO.read(new ByteArrayInputStream(bytes));
                int width = image.getWidth();
                int height = image.getHeight();

                if (((double) height / (double) width) > ((double) img_height / (double) img_width)) {
                    embeddedImage.setHeight(String.valueOf(img_height));
                    embeddedImage.setWidth(String.valueOf(width * img_height / height));
                } else {
                    embeddedImage.setWidth(String.valueOf(img_width));
                    embeddedImage.setHeight(String.valueOf(height * img_width / width));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            embeddedImage.setVisible(true);
        } else {
            embeddedImage.setVisible(false);
        }
    }
}