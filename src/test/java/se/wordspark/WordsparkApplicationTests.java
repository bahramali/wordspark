package se.wordspark;


import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.wordspark.common.Utility;
import se.wordspark.entity.Word;
import se.wordspark.service.PdfService;
import se.wordspark.service.WordsService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class WordsparkApplicationTests {

    final static Logger LOGGER = LoggerFactory.getLogger(WordsparkApplicationTests.class);
    @Autowired
    WordsService service;

    @Autowired
    PdfService pdfService;

    final String filename = "/Users/azad/Desktop/sample.pdf";
    final String filename2 = "/Users/azad/Desktop/the_hobbit.pdf";


    @Test
    void shouldSaveAndGetFromDataBase() {
        Word book = Word.builder().withFreq(1).withTerm("Book").build();
        service.add(book);

        List<Word> all = service.getAll();

        LOGGER.info("List is %s", all);
    }

    @Test
    void shouldReadPDF() throws IOException {
        Map<String, Integer> allWordsWithFrequency = pdfService.getAllWordsWithFrequency(filename2);
        Map<String, Integer> sortByValue = Utility.sortDecreaseByValue(allWordsWithFrequency);

        LOGGER.info("{}",sortByValue);


    }

}
