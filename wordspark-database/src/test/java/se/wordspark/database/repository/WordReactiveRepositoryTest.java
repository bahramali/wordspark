package se.wordspark.database.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import se.wordspark.database.model.Word;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest(classes = InfrastructureConfiguration.class)
class WordReactiveRepositoryTest {

  private static final String DEFAULT_TERM = "default_Term";
  private static final String TERM = "Term";
  private static final Word word = Word.builder()
      .term("word")
      .freq(1)
      .build();

  @Autowired
  WordReactiveRepository repository;

  @Autowired
  DatabaseClient client;

  @BeforeEach
  public void setup() {
    Hooks.onOperatorDebug();

    List<String> statements = Arrays.asList(
        "DELETE FROM WORD",
        "INSERT INTO WORD(TERM) VALUES ('" + DEFAULT_TERM + "'),('" + TERM + "')");
    statements.forEach(it -> client.sql(it)
        .fetch()
        .rowsUpdated()
        .as(StepVerifier::create)
        .expectNextCount(1)
        .verifyComplete());
  }

  @Test
  void should_save_word_in_database() {
    Mono<Word> actualMono = repository.save(word);

    StepVerifier.create(actualMono)
        .expectNext(word)
        .verifyComplete();
  }

  @Test
  void should_exist_in_database() {
    Word expected = Word.builder()
        .term(DEFAULT_TERM)
        .build();

    Mono<Word> actualMonoNumber = repository.findByTerm(DEFAULT_TERM);
    StepVerifier.create(actualMonoNumber)
        .expectNextMatches(actual -> isEqual(actual, expected))
        .verifyComplete();
  }

  @Test
  void should_not_exist_in_database() {
    Mono<Word> actualMonoNumber = repository.findByTerm("NOT_VALID_TERM");
    StepVerifier.create(actualMonoNumber)
        .expectNextCount(0)
        .verifyComplete();
  }


  @Test
  void should_return_all_phone_numbers() {
    Flux<Word> expectedNumbers = repository.findAll();
    StepVerifier.create(expectedNumbers)
        .expectNextCount(2)
        .verifyComplete();
  }

//  @Test
//  void should_delete_phone_by_id() {
//    Word actualMonoNumber = repository.findByMsisdn(DEFAULT_TERM).block();
//    Mono<Void> actualMono = repository.deleteById(actualMonoNumber.ge);
//    StepVerifier.create(actualMono)
//        .verifyComplete();
//
//    Mono<Word> byId = repository.findById(actualMonoNumber.getId());
//    StepVerifier.create(byId)
//        .expectNextCount(0)
//        .verifyComplete();
//  }

//  @Test
//  void should_return_true_when_deleting_existing_phone_by_phone_number() {
//    Mono<Boolean> deletedWord = repository.deleteByMsisdn(DEFAULT_TERM);
//    StepVerifier
//        .create(deletedWord)
//        .expectNext(Boolean.TRUE)
//        .verifyComplete();
//  }

//  @Test
//  void should_return_false_when_deleting_non_existing_phone_by_phone_number() {
//    Mono<Boolean> deletedWord = repository.deleteByMsisdn("NON_EXISTING_NUMBER");
//    StepVerifier
//        .create(deletedWord)
//        .expectNext(Boolean.FALSE)
//        .verifyComplete();
//  }

  @Test
  void should_fail_if_Word_is_without_number() {
    Word Word = new Word();
    Mono<Word> wordMono = repository.save(Word);
    StepVerifier.create(wordMono)
        .expectErrorMatches(e -> e instanceof DataIntegrityViolationException)
        .verify();
  }

  @Test
  void should_throw_exception_if_inserted_the_same_phone_number() {
    Word word = Word.builder()
        .term(DEFAULT_TERM).build();
    Mono<Word> wordMono = repository.save(word);
    StepVerifier.create(wordMono)
        .expectErrorMatches(e -> e instanceof DataIntegrityViolationException)
        .verify();
  }

  private boolean isEqual(Word actual, Word expected) {
    assertThat(actual.getTerm(), is(equalTo(expected.getTerm())));
    return true;
  }
}