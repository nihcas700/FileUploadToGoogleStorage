package com.legalfab.pdfupload.impl;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WordUploaderToCloudService extends BaseContentUploaderToCloudService {

    private static String FILE_NAME = "/Users/tolay/text.docx";

    @Override
    public File getFileToBeUploaded(String text) throws IOException {

        File file = new File(FILE_NAME);
        file.createNewFile();

        createWord(text, file);

        return file;
    }

    public void createWord(String line, File file) throws IOException {

        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(file);

        //create Paragraph
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(line);
        document.write(out);

        out.close();
    }

}
