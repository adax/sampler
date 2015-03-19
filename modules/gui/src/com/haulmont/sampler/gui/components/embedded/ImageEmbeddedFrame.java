package com.haulmont.sampler.gui.components.embedded;

import com.haulmont.cuba.core.app.FileStorageService;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.ClientType;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.gui.AppConfig;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.DataSupplier;
import com.haulmont.cuba.gui.export.ExportDisplay;
import com.haulmont.cuba.gui.export.ExportFormat;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

public class ImageEmbeddedFrame extends AbstractFrame {

    @Inject
    private GroupBoxLayout groupBoxImage;
    @Inject
    private Embedded embeddedImage;
    @Inject
    private FileUploadField uploadField;
    @Inject
    private Button clearImageBtn;
    @Inject
    private Button downloadImageBtn;
    @Inject
    private Embedded embeddedImageFile;
    @Inject
    private DataSupplier dataSupplier;
    @Inject
    private FileStorageService fileStorageService;
    @Inject
    private FileUploadingAPI fileUploading;
    @Inject
    private ExportDisplay exportDisplay;

    private FileDescriptor imageFile;

    private float img_height;
    private float img_width;

    @Override
    public void init(Map<String, Object> params) {
        img_height = groupBoxImage.getHeight() - 60;
        img_width = groupBoxImage.getWidth() - 60;

        // TODO Может стоит не позволять грузить свои фотки, а подготовить файл в БД заранее?
        uploadField.addListener(new FileUploadField.ListenerAdapter() {
            @Override
            public void uploadSucceeded(Event event) {
                FileDescriptor fd = uploadField.getFileDescriptor();
                try {
                    fileUploading.putFileIntoStorage(uploadField.getFileId(), fd);
                } catch (FileStorageException e) {
                    throw new RuntimeException(e);
                }
                imageFile = dataSupplier.commit(fd, null);
                displayImage();
                updateImageButtons(true);
            }

            @Override
            public void uploadFailed(Event event) {
                showNotification("Upload failed", NotificationType.HUMANIZED);
            }
        });
        updateImageButtons(false);

        if (AppConfig.getClientType() == ClientType.WEB) {
            embeddedImageFile.setSource("cuba-logo.png");
        }
    }

    public void onDownloadImageBtnClick() {
        if (imageFile != null)
            exportDisplay.show(imageFile, ExportFormat.OCTET_STREAM);
    }

    public void onClearImageBtnClick() {
        imageFile = null;
        displayImage();
        updateImageButtons(false);
    }

    private void updateImageButtons(boolean enable) {
        downloadImageBtn.setEnabled(enable);
        clearImageBtn.setEnabled(enable);
    }

    private void displayImage() {
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