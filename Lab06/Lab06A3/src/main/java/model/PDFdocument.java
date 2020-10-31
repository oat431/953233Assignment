package model;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PDFdocument {
    private final String name;
    private final String filePath;
    private final PDDocument document;
    private LinkedHashMap<String, ArrayList<FileFreq>> uniqueSets;

    public PDFdocument(String filePath) throws IOException {
        this.name = Paths.get(filePath).getFileName().toString();
        this.filePath = filePath;
        System.out.println(filePath);
        File pdfFile = new File(filePath);
        this.document = PDDocument.load(pdfFile);
    }

    public PDDocument getDocument() {
        return document;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getName() {
        return name;
    }
}