package se.wordspark.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String term;
    private Integer freq;

    private Word(Builder builder){
        this.term = builder.term;
        this.freq = builder.freq;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String term;
        private Integer freq;

        private Builder(){}

        public Builder(String term, Integer freq){
            this.term = term;
            this.freq = freq;
        }

        public Builder withTerm(String term){
            this.term =term;
            return this;
        }

        public Builder withFreq(Integer freq){
            this.freq = freq;
            return this;
        }

        public Word build(){
            return new Word(this);
        }

    }

}
