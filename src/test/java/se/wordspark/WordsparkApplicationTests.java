package se.wordspark;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.wordspark.entity.Word;
import se.wordspark.service.WordsService;

@SpringBootTest
class WordsparkApplicationTests {

	@Autowired
	WordsService service;

	@Test
	void contextLoads() {
		Word book = Word.builder().withFreq(1).withTerm("Book").build();
		service.add(book);





	}

}
