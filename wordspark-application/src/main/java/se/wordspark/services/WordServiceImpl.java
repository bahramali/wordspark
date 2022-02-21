package se.wordspark.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.wordspark.entity.Word;
import se.wordspark.repository.WordsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WordServiceImpl implements WordsService {

    @Autowired
    private WordsRepository wordsRepository;

    @Override
    public void add(Word word) {
        wordsRepository.save(word);
    }

    @Override
    public void addAll(Map<String, Integer> wordsFreq) {
        wordsFreq.forEach((key, value) -> wordsRepository.save(
                        Word.builder()
                                .withTerm(key)
                                .withFreq(value)
                                .build()
                )
        );
    }

    @Override
    public List<Word> getAll() {
        List<Word> words = new ArrayList<>();
        wordsRepository.findAll().forEach(words::add);
        return words;
    }
}
