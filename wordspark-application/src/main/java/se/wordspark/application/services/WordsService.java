package se.wordspark.application.services;

import se.wordspark.database.model.Word;

import java.util.List;
import java.util.Map;

public interface WordsService {

    void add(Word word);

    void addAll(Map<String, Integer> wordsFreq);

    List<Word> getAll();
}
