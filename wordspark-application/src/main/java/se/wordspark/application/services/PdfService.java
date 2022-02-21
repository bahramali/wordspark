package se.wordspark.application.services;

import java.io.IOException;
import java.util.Map;

public interface PdfService {

    Map<String, Integer> getAllWordsWithFrequency(String pdfPath) throws IOException;
}
