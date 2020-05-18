package se.wordspark.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import se.wordspark.entity.Word;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PdfService {

    public Map<String,Integer> getAllWordsWithFrequency(String pdfPath) throws IOException;
}
