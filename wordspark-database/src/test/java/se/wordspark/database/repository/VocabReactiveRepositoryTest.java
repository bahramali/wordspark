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
import se.wordspark.database.model.Vocabulary;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest(classes = InfrastructureConfiguration.class)
class VocabReactiveRepositoryTest {

  private static final String DEFAULT_TERM = "default_Term";
  private static final String TERM = "Term";
  private static final Vocabulary VOCABULARY = se.wordspark.database.model.Vocabulary.builder()
      .term("word")
      .author("author")
      .build();

  @Autowired
  VocabReactiveRepository repository;

  @Autowired
  DatabaseClient client;

  @BeforeEach
  public void setup() {
    Hooks.onOperatorDebug();

    List<String> statements = Arrays.asList(
        "DELETE FROM VOCABULARY",
        "INSERT INTO VOCABULARY(TERM) VALUES ('" + DEFAULT_TERM + "'),('" + TERM + "')");
    statements.forEach(it -> client.sql(it)
        .fetch()
        .rowsUpdated()
        .as(StepVerifier::create)
        .expectNextCount(1)
        .verifyComplete());
  }

  @Test
  void should_save_word_in_database() {
    Mono<Vocabulary> actualMono = repository.save(VOCABULARY);

    StepVerifier.create(actualMono)
        .expectNext(VOCABULARY)
        .verifyComplete();
  }

  @Test
  void should_exist_in_database() {
    Vocabulary expected = se.wordspark.database.model.Vocabulary.builder()
        .term(DEFAULT_TERM)
        .build();

    Mono<Vocabulary> actualMonoNumber = repository.findByTerm(DEFAULT_TERM);
    StepVerifier.create(actualMonoNumber)
        .expectNextMatches(actual -> isEqual(actual, expected))
        .verifyComplete();
  }

  @Test
  void should_not_exist_in_database() {
    Mono<Vocabulary> actualMonoNumber = repository.findByTerm("NOT_VALID_TERM");
    StepVerifier.create(actualMonoNumber)
        .expectNextCount(0)
        .verifyComplete();
  }


  @Test
  void should_return_all_phone_numbers() {
    Flux<Vocabulary> expectedNumbers = repository.findAll();
    StepVerifier.create(expectedNumbers)
        .expectNextCount(2)
        .verifyComplete();
  }

  @Test
  void should_delete_phone_by_id() {
    Vocabulary actualVocabulary = repository.findByTerm(DEFAULT_TERM).block();
    Mono<Void> actualMono = repository.deleteById(actualVocabulary.getId());
    StepVerifier.create(actualMono)
        .verifyComplete();

    Mono<Vocabulary> byId = repository.findById(actualVocabulary.getId());
    StepVerifier.create(byId)
        .expectNextCount(0)
        .verifyComplete();
  }

  @Test
  void should_return_true_when_deleting_existing_phone_by_phone_number() {
    Mono<Boolean> deletedWord = repository.deleteByTerm(DEFAULT_TERM);
    StepVerifier
        .create(deletedWord)
        .expectNext(Boolean.TRUE)
        .verifyComplete();
  }

  @Test
  void should_return_false_when_deleting_non_existing_phone_by_phone_number() {
    Mono<Boolean> deletedWord = repository.deleteByTerm("NON_EXISTING_NUMBER");
    StepVerifier
        .create(deletedWord)
        .expectNext(Boolean.FALSE)
        .verifyComplete();
  }

  @Test
  void should_fail_if_Word_is_without_number() {
    Vocabulary Vocabulary = new Vocabulary();
    Mono<se.wordspark.database.model.Vocabulary> wordMono = repository.save(Vocabulary);
    StepVerifier.create(wordMono)
        .expectErrorMatches(e -> e instanceof DataIntegrityViolationException)
        .verify();
  }

  @Test
  void should_throw_exception_if_inserted_the_same_phone_number() {
    Vocabulary vocabulary = se.wordspark.database.model.Vocabulary.builder()
        .term(DEFAULT_TERM).build();
    Mono<Vocabulary> wordMono = repository.save(vocabulary);
    StepVerifier.create(wordMono)
        .expectErrorMatches(e -> e instanceof DataIntegrityViolationException)
        .verify();
  }

  private boolean isEqual(Vocabulary actual, Vocabulary expected) {
    assertThat(actual.getTerm(), is(equalTo(expected.getTerm())));
    return true;
  }
}