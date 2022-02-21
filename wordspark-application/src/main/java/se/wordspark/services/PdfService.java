package se.wordspark.services;

import java.io.IOException;
import java.util.Map;

public interface PdfService {

    public Map<String, Integer> getAllWordsWithFrequency(String pdfPath) throws IOException;
}
