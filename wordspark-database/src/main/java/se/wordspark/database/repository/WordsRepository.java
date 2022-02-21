package se.wordspark.database.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import se.wordspark.database.entity.Word;


@Repository
public interface WordsRepository extends ReactiveSortingRepository<Word, Integer> {

  Mono<Word> findByTerm(String term);

}
