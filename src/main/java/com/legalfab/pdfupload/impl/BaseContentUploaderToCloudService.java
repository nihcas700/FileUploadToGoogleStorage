package com.legalfab.pdfupload.impl;

import com.legalfab.pdfupload.CloudProvider;
import com.legalfab.pdfupload.service.ContentUploaderToCloudService;

import java.io.File;
import java.io.IOException;

public abstract class BaseContentUploaderToCloudService implements ContentUploaderToCloudService {

    @Override
    public void uploadContent(String content, CloudProvider cloudProvider) throws IOException {

        File file = getFileToBeUploaded(content);
        cloudProvider.uploadFile(file);
    }

    public abstract File getFileToBeUploaded(String text) throws IOException;
}
