package se.wordspark.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.wordspark.database.model.Vocabulary;
import se.wordspark.database.repository.VocabReactiveRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VocabServiceImpl implements VocabService {

    @Autowired
    private VocabReactiveRepository repository;

    @Override
    public void add(Vocabulary vocabulary) {
        repository.save(vocabulary);
    }

    @Override
    public void addAll(Map<String, String> wordsFreq) {
        wordsFreq.forEach((key, value) -> repository.save(
                        Vocabulary.builder()
                                .term(key)
                                .author(value)
                                .build()
                )
        );
    }

    @Override
    public List<Vocabulary> getAll() {
        List<Vocabulary> vocabularies = new ArrayList<>();
        repository.findAll().toStream().forEach(vocabularies::add);
        return vocabularies;
    }
}
