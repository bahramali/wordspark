package se.wordspark.application.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Map;

@Slf4j
@ExtendWith(MockitoExtension.class)
class PdfServiceImplTest {

  final String filePath = "../resources/Princess_Diana.pdf";

  @InjectMocks
  PdfServiceImpl sut;

  @Test
  void should_save_and_get_from_dataBase() throws IOException {

    Map<String, Integer> allUniqueWord = sut.fetchAllUniqueWord(filePath);

    log.info("List is %s", allUniqueWord.size());
    allUniqueWord.entrySet().forEach(System.out::println);

  }

/*    @Test
    void shouldReadPDF() throws IOException {
        Map<String, Integer> allWordsWithFrequency = pdfService.getAllWordsWithFrequency(filename);
        Map<String, Integer> sortByValue = Utility.sortDecreaseByValue(allWordsWithFrequency);

        LOGGER.info("{}", sortByValue);


    }*/

}