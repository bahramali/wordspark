package se.wordspark.service;

import se.wordspark.entity.Word;

import java.util.List;
import java.util.Map;

public interface WordsService {

    void add(Word word);
    void addAll(Map<String,Integer> wordsFreq);
    List<Word> getAll();
}
