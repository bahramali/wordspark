package se.wordspark.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.wordspark.entity.Word;


@Repository
public interface WordsRepository extends CrudRepository<Word, Integer> {

    //public List<Word> getAllWord();

}
