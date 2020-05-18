package se.wordspark.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import se.wordspark.entity.Word;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PdfServiceImpl implements PdfService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PdfServiceImpl.class);

    @Override
    public Map<String, Integer> getAllWordsWithFrequency(String pdfPath) throws IOException {
        Map<String, Integer> wordsFrequency = new HashMap<>();
        try {
            PDDocument document = PDDocument.load(new File(pdfPath));
            // if (!document.isEncrypted()) {
            //PDFTextStripper stripper = new PDFTextStripper().getText(document);
            //text = stripper.getText(document);
            List<String> allWords = Arrays.asList(
                    new PDFTextStripper()
                            .getText(document)
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
            LOGGER.error("Not found {}", pdfPath);
        }
        return wordsFrequency;
    }
}
