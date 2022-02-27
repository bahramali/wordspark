package se.wordspark.database.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import se.wordspark.database.model.Word;

@Repository
public interface WordReactiveRepository extends ReactiveSortingRepository<Word, Long> {

  Mono<Word> findByTerm(String term);

 // Mono<Boolean> deleteByMsisdn(String phoneNumber);

}
