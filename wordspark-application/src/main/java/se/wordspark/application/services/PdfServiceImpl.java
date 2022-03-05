package se.wordspark.application.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public Map<String, Integer> fetchAllUniqueWord(String pdfPath) {
        Map<String, Integer> wordsFrequency = new HashMap<>();
        try {
            PDDocument document = PDDocument.load(new File(pdfPath));
            List<String> allWords = Arrays.asList(
                    new PDFTextStripper()
                            .getText(document)
                            .toLowerCase()
                            .replaceAll("^[.!?;:‘’“”(),_[0-9]\\s]+", "")
                            .split("[.!?;:‘’“”(),_[0-9]\\s]+"));

            allWords.stream()
                    .filter(s -> s.matches("^[^\\d].*"))
                    .filter(s -> s.length() > 2)
                    .forEach(s -> {
                        wordsFrequency.merge(s, 1, Integer::sum);
                    });
            //}
            document.close();
        } catch (IOException e) {
            log.error("Not found {}", pdfPath);
        }
        return wordsFrequency;
    }
}