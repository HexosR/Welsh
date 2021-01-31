package uk.ac.aber.dcs.group2.main;

import java.util.Scanner;
import java.util.SortedMap;

public class WordDealer {
    private Scanner scanner;
    Dictionary dictionary = new Dictionary();

    public void saveWord(String word, SortedMap<String, Word> treeName){ // user input word, result = saving word to desired tree
        scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        // show pop up to insert word
    }

    public void loadWord(String word){ // user will input word, will result in printing the word.

    }

    private boolean ifWordExist(String word, SortedMap<String, Word> treeName){ //if word exist show error
        if (treeName.containsKey(word)) return true;
        return false;
    }
}
