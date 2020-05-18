package se.wordspark.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import se.wordspark.entity.Word;

import java.io.IOException;
import java.util.List;

public interface PdfService {

    public List<Word> getAllWords(String pdfPath) throws IOException;
}
