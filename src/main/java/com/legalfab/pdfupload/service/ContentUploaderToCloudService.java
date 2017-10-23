package com.legalfab.pdfupload.service;

import com.legalfab.pdfupload.CloudProvider;

import java.io.File;
import java.io.IOException;

public interface ContentUploaderToCloudService {

    public void uploadContent(String content, CloudProvider cloudProvider) throws IOException;
}
