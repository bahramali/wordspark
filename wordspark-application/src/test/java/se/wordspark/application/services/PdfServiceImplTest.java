package se.wordspark.application.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@Slf4j
@ExtendWith(MockitoExtension.class)
class PdfServiceImplTest {

  final String filePath = "src/test/resources/Princess_Diana.pdf";

  @InjectMocks
  PdfServiceImpl sut;

  @Test
  void should_list_all_word_from_pdf() {

    Map<String, Integer> allUniqueWord = sut.fetchAllUniqueWord(filePath, 2);

    log.info("List is {}", allUniqueWord.size());
    allUniqueWord.entrySet().forEach(System.out::println);
  }

  @Test
  void should_save_all_word_from_pdf_in_database() {

    Map<String, Integer> allUniqueWord = sut.fetchAllUniqueWord(filePath, 2);

    sut.save(filePath);


  }

}