package se.wordspark.database.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import se.wordspark.database.model.Vocabulary;

@Repository
public interface VocabReactiveRepository extends ReactiveSortingRepository<Vocabulary, Long> {

  Mono<Vocabulary> findByTerm(String term);

  Mono<Boolean> deleteByTerm(String term);

}
