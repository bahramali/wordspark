package se.wordspark.service;

import se.wordspark.entity.Word;

import java.util.List;

public interface WordsService {

    void add(Word word);
    List<Word> getAll();
}
