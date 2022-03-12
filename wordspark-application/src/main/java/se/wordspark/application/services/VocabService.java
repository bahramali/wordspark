package se.wordspark.application.services;

import se.wordspark.database.model.Vocabulary;

import java.util.List;
import java.util.Map;

public interface VocabService {

    void add(Vocabulary vocabulary);

    void addAll(Map<String, String> wordsFreq);

    List<Vocabulary> getAll();
}
