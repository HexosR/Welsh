package uk.ac.aber.cs211.group2.dictionary;

import java.util.*;

public class WordManager {

    private Dictionary dictionary;

    /**Word Manager class Deals with all the operations on data structures within the Dictionary i.e. search.
     * @author Kacper Szymczyc[kas102]
     * @author Huzefa Syed[hus3]
     *
     */
    public WordManager(Dictionary dict) {
        this.dictionary = dict;
    }

    /**
     * Method used in the Search functionality. Returns all word objects with the English translation beginning with a given string in the dictionary.
     * @param search
     * @return
     */
    public ArrayList<Word> searchBeginningDictEnglish(String search) {
        ArrayList<Word> words = new ArrayList<>();
        search = search.toLowerCase();
        String temp;
        for (Word w : dictionary.dictionaryList) {
            temp = w.getEnglish();
            if (w.getWordType().equals("verb")) {
                temp = temp.substring(3);
            }
            if (temp.toLowerCase().startsWith(search)) {

                words.add(w);
            }
        }
        System.out.println(words);
        return words;
    }

    /**
     * Method used in the Search functionality. Returns all word objects with the Welsh translation beginning with a given string in the dictionary.
     * @param search
     * @return
     */
    public ArrayList<Word> searchBeginningDictWelsh(String search) {
        ArrayList<Word> words = new ArrayList<>();
        search = search.toLowerCase();
        for (Word w : dictionary.dictionaryList) {

            if (w.getWelsh().toLowerCase().startsWith(search)) {
                words.add(w);
            }
        }
        System.out.println(words);
        return words;
    }

    /**
     * Method used in the Search functionality. Returns all word objects with the English translation beginning with a given string in the practice dictionary.
     * @param search
     * @return
     */
    public ArrayList<Word> searchBeginningPracListEnglish(String search) {
        ArrayList<Word> words = new ArrayList<>();
        search = search.toLowerCase();
        String temp;
        for (Word w : dictionary.practiceList) {
            temp = w.getEnglish();
            if (w.getWordType().equals("verb")){
                temp = temp.substring(3);
            }
            if (temp.toLowerCase().startsWith(search)) {
                words.add(w);
            }
        }
        System.out.println(words);
        return words;
    }

    /**
     * Method used in the Search functionality. Returns all word objects with the Welsh translation beginning with a given string in the practice dictionary.
     * @param search
     * @return
     */
    public ArrayList<Word> searchBeginningPracListWelsh(String search) {
        ArrayList<Word> words = new ArrayList<>();
        search = search.toLowerCase();
        for (Word w : dictionary.practiceList) {
            if (w.getWelsh().toLowerCase().startsWith(search)) {
                words.add(w);
            }
        }
        System.out.println(words);
        return words;
    }

}
