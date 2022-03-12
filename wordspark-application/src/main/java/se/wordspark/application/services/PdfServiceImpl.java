package se.wordspark.application.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import se.wordspark.database.model.Vocabulary;
import se.wordspark.database.repository.VocabReactiveRepository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PdfServiceImpl implements PdfService {

    private VocabReactiveRepository repository;

    public PdfServiceImpl(VocabReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Integer> fetchAllUniqueWord(String pdfPath, int numberOfLetter) {
        Map<String, Integer> wordsFrequency = new HashMap<>();
        try {
            PDDocument document = PDDocument.load(new File(pdfPath));
            List<String> allVocabularies = Arrays.asList(
                    new PDFTextStripper()
                            .getText(document)
                            .toLowerCase()
                            .replaceAll("^[.!?;:‘’“”(),_/[0-9]\\s]+", "")
                            .split("[.!?;:‘’“”(),_/[0-9]\\s]+"));

            log.info("\nAll un-unique words fond in book {} are {}\n",
                    document.getDocumentInformation().getTitle(), allVocabularies.size());

            allVocabularies.stream()
                    .filter(item -> item.matches("^[^\\d].*"))
                    .filter(item -> item.length() > numberOfLetter)
                    .map(item -> removeCharacter(item, "'"))
                    .forEach(item -> {
                        wordsFrequency.merge(item, 1, Integer::sum);
                    });
            //}
            document.close();
        } catch (IOException e) {
            log.error("Not found {}", pdfPath);
        }
        return wordsFrequency;
    }

    @Override
    public boolean save(String pdfPath) {
        Map<String, Integer> allUniqueWord = fetchAllUniqueWord(pdfPath, 2);
        allUniqueWord.forEach((key, value) -> {
            Vocabulary vocabulary = repository.findByTerm(key).block();

            if (vocabulary.getTerm() != null) {
                Vocabulary build = se.wordspark.database.model.Vocabulary.builder()
                        .term(vocabulary.getTerm())
                        .author(vocabulary.getAuthor())
                        .build();
                repository.save(build);
            }
        });
        return false;
    }

    private String removeCharacter(String word, String target) {
        if (word.contains("'")) {
            word = word.replace(target, "");
        }
        return word;
    }

}