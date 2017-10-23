package com.legalfab.pdfupload.controller;

import com.legalfab.pdfupload.impl.GoogleCloudProvider;
import com.legalfab.pdfupload.impl.PDFUploaderToCloudService;
import com.legalfab.pdfupload.impl.WordUploaderToCloudService;
import com.legalfab.pdfupload.service.ContentUploaderToCloudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class FileUploadController {

    @RequestMapping("/")
    public String index() {
        return "form.html";
    }

    @RequestMapping(value = "/upload_file", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@ModelAttribute FormData formData) throws IOException {

        ContentUploaderToCloudService uploadService;

        uploadService = Integer.parseInt(formData.getNumeralData()) % 2 == 0 ? new PDFUploaderToCloudService() :
                new WordUploaderToCloudService();

        GoogleCloudProvider cloudProvider = new GoogleCloudProvider();
        uploadService.uploadContent(formData.getStringData(), cloudProvider);

        return "Done";
    }
}
