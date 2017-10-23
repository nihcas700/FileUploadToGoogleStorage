package com.legalfab.pdfupload;

import java.io.File;
import java.io.IOException;

public interface CloudProvider {

    void uploadFile(File file) throws IOException;
}
