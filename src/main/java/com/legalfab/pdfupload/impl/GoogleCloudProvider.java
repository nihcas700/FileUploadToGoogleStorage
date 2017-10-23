package com.legalfab.pdfupload.impl;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.legalfab.pdfupload.CloudProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GoogleCloudProvider implements CloudProvider {

    @Override
    public void uploadFile(File file) throws IOException {

        Storage storage = StorageOptions.newBuilder()
                .setProjectId("lf-test-179522")
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("/Users/tolay/Documents/key.json")))
                .build()
                .getService();
        Path path = Paths.get(file.toURI());
        String contentType = Files.probeContentType(path);
        String blob = path.getFileName().toString();
        BlobInfo blobInfo = BlobInfo.newBuilder("lf_test", blob).setContentType(contentType).build();
        uploadToStorage(storage, path, blobInfo);
    }

    private void uploadToStorage(Storage storage, Path uploadFrom, BlobInfo blobInfo) throws IOException {
        if (Files.size(uploadFrom) > 1_000_000) {
            // When content is not available or large (1MB or more) it is recommended
            // to write it in chunks via the blob's channel writer.
            try (WriteChannel writer = storage.writer(blobInfo)) {
                byte[] buffer = new byte[1024];
                try (InputStream input = Files.newInputStream(uploadFrom)) {
                    int limit;
                    while ((limit = input.read(buffer)) >= 0) {
                        try {
                            writer.write(ByteBuffer.wrap(buffer, 0, limit));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        } else {

            byte[] bytes = Files.readAllBytes(uploadFrom);
            storage.create(blobInfo, bytes);
        }
        System.out.println("Blob was created");
    }

}
