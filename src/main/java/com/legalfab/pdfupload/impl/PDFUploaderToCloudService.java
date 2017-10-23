package com.legalfab.pdfupload.impl;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFUploaderToCloudService extends BaseContentUploaderToCloudService {

    private static String FILE_NAME = "/Users/tolay/text.pdf";

    @Override
    public File getFileToBeUploaded(String content) throws IOException {

        File file = new File(FILE_NAME);
        file.createNewFile();
        Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            addMetaData(document);
            addTitlePage(document, content);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

    private static void addMetaData(Document document) {
        document.addTitle("LegalFab");
        document.addAuthor("Anurag Agrawal");
        document.addCreator("Anurag Agrawal");
    }

    private static void addTitlePage(Document document, String content)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        preface.add(new Paragraph(content));

        document.add(preface);
    }
}
