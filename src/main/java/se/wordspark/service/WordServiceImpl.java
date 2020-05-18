package se.wordspark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.wordspark.entity.Word;
import se.wordspark.repository.WordsRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class WordServiceImpl implements WordsService {

    @Autowired
    private WordsRepository wordsRepository;

    @Override
    public void add(Word word) {
        wordsRepository.save(word);
    }

    @Override
    public List<Word> getAll() {
        List<Word> words = new ArrayList<>();
        wordsRepository.findAll().forEach(words::add);
        return words;
    }
}
