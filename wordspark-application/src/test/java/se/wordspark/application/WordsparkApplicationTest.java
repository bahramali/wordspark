package se.wordspark.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import se.wordspark.application.services.PdfService;

class WordsparkApplicationTest {

    final static Logger LOGGER = LoggerFactory.getLogger(WordsparkApplication.class);
    final String filename = "Princess_Diana.pdf";

    @Autowired
    PdfService pdfService;


   /* @Test
    void shouldSaveAndGetFromDataBase() {
        Word book = Word.builder().withFreq(1).withTerm("Book").build();
        service.add(book);

        List<Word> all = service.getAll();

        LOGGER.info("List is %s", all);
    }

    @Test
    void shouldReadPDF() throws IOException {
        Map<String, Integer> allWordsWithFrequency = pdfService.getAllWordsWithFrequency(filename);
        Map<String, Integer> sortByValue = Utility.sortDecreaseByValue(allWordsWithFrequency);

        LOGGER.info("{}", sortByValue);


    }

*/
}