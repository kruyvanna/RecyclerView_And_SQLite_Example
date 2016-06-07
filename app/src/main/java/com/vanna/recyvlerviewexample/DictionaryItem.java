package com.vanna.recyvlerviewexample;

/**
 * Created by kruyvanna on 6/7/16.
 */
public class DictionaryItem {
    String word;
    String definition;

    public DictionaryItem(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }
}
