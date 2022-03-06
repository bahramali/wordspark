package se.wordspark.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.wordspark.database.model.Word;
import se.wordspark.database.repository.WordReactiveRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WordServiceImpl implements WordsService {

    @Autowired
    private WordReactiveRepository wordsRepository;

    @Override
    public void add(Word word) {
        wordsRepository.save(word);
    }

    @Override
    public void addAll(Map<String, String> wordsFreq) {
        wordsFreq.forEach((key, value) -> wordsRepository.save(
                        Word.builder()
                                .term(key)
                                .author(value)
                                .build()
                )
        );
    }

    @Override
    public List<Word> getAll() {
        List<Word> words = new ArrayList<>();
        wordsRepository.findAll().toStream().forEach(words::add);
        return words;
    }
}
