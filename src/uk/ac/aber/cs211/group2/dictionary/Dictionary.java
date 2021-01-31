package uk.ac.aber.cs211.group2.dictionary;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;

/**Dictionary Class. Stores all Dictionary data, contains the code for the dictionary object which contains all the methods and data structures that store all the words in the program.
 * It contains all the code to read and write JSON files within the program. It stores Word objects and produces JSON files using a dictionary filled with words. Additionally, contains the code to move words between the dictionary and practice dictionary.
 * @author Kacper Szymczyc[kas102]
 * @author Huzefa Syed[hus3]
 *
 */
public class Dictionary{
    public ArrayList<Word> dictionaryList = new ArrayList<>();
    public ArrayList<Word> practiceList = new ArrayList<>();
    public WordManager wordManager;

    public Dictionary() {
        wordManager = new WordManager(this);
    }

    /**
     * Method to save the current Dictionary object as a JSON file.
     * @param filename
     */
    public void saveDict(String filename) {
        try (FileWriter writer = new FileWriter(filename); BufferedWriter buffer = new BufferedWriter(writer)) {
            Gson gson = new Gson();
            Object[] w = dictionaryList.toArray();
            gson.toJson(w, buffer);
        } catch (IOException e) {
            System.err.println("ERROR FILE NOT FOUND\n");
            e.printStackTrace();
        }
    }

    /**
     * Method to save the current Dictionary object as a JSON file at end of program.
     * @param filename
     */
    public void saveDictFinal(String filename) {
        try (FileWriter writer = new FileWriter(filename); BufferedWriter buffer = new BufferedWriter(writer)) {
            Gson gson = new Gson();
            Object[] w = dictionaryList.toArray();
            for (Object word : w) {
                if (((Word)word).getWordType().equals("verb")){
                    String temp = ((Word) word).getEnglish();
                    ((Word) word).setEnglish(temp.substring(3));
                }
            }
            gson.toJson(w, buffer);
        } catch (IOException e) {
            System.err.println("ERROR FILE NOT FOUND\n");
            e.printStackTrace();
        }
    }

    /**
     * Method to save the current practice list object as a JSON file.
     * @param filename
     */
    public void savePracList(String filename) {
        try (FileWriter writer = new FileWriter(filename); BufferedWriter buffer = new BufferedWriter(writer)) {
            Gson gson = new Gson();
            Object[] words = this.practiceList.toArray();
            gson.toJson(words, buffer);
        } catch (IOException e) {
            System.err.println("ERROR FILE NOT FOUND\n");
            e.printStackTrace();
        }
    }

    /**
     * Method to save the current practice list object as a JSON file at the end of file.
     * @param filename
     */
    public void savePracFinal(String filename) {
        try (FileWriter writer = new FileWriter(filename); BufferedWriter buffer = new BufferedWriter(writer)) {
            Gson gson = new Gson();
            Object[] words = this.practiceList.toArray();
            gson.toJson(words, buffer);
        } catch (IOException e) {
            System.err.println("ERROR FILE NOT FOUND\n");
            e.printStackTrace();
        }
    }

    /**
     * Method to load the dictionary object from a JSON file.
     * @param filename
     */
    public void loadDict(String filename) {
        try (FileReader read = new FileReader(filename); BufferedReader buffer = new BufferedReader(read)) {
            Gson gson = new Gson();
            Word[] w = gson.fromJson(buffer, Word[].class);
            String prev;
            for (Word word : w) {
                if(word.getWordType().equals("other")) {
                    word.setDisplayType("");
                } else if (word.getWordType().equals("nf")){
                    word.setDisplayType("female");
                } else if (word.getWordType().equals("nm")){
                    word.setDisplayType("male");
                } else if (word.getWordType().equals("verb")){
                    word.setDisplayType("verb");
                    prev = word.getEnglish();
                    System.out.println(prev);
                    word.setEnglish("to " + prev);
                    System.out.println(word.getEnglish());
                }
                this.insertWord(word);
            }
        } catch (IOException e) {
            System.err.println("ERROR FILE NOT FOUND\n");
            e.printStackTrace();
        }
    }

    /**
     * Method to load the practice dictionary object from a JSON file.
     * @param filename
     */
    public void loadPracList(String filename){
        try (FileReader read = new FileReader(filename); BufferedReader buffer = new BufferedReader(read)) {
            Gson gson = new Gson();
            Word[] w = gson.fromJson(buffer, Word[].class);
            for (Word word : w) {
                    this.movePractice(word);
                    for (Word word2 : dictionaryList) {
                        if (word.getWordType().equals("verb") && word2.getEnglish().equals("to " + word.getEnglish())) {

                            String prev = word.getEnglish();
                            word.setEnglish("to " + prev);
                        }
                        if (word2.getEnglish().equals(word.getEnglish()) && word.getWordType().equals(word2.getWordType())) {
                            word.setDisplayType(word2.getDisplayType());
                            word2.practice = "in list";
                        }
                    }
                    }
        } catch (IOException e) {
            System.err.println("ERROR FILE NOT FOUND\n");
            e.printStackTrace();
        }
    }

    /**
     * Method to inset a word into the array list data structure.
     * @param w
     */
    public void insertWord(Word w) {
        w.english = w.english.trim();
        w.welsh = w.welsh.trim();
        this.dictionaryList.add(w);
    }

    /**
     * Method to move a word from Dictionary List to Practice List.
     * @param w
     */
    public void  movePractice(Word w){
        w.practice = "in list";
        practiceList.add(w);
        savePracList("./pracList.json");
    }

    /**
     * Method to move a word from Practice List to Dictionary List.
     * @param w
     */
    public void moveDict(Word w){
        w.practice = "";
        dictionaryList.get(dictionaryList.indexOf(w)).practice="";
        practiceList.remove(w);
        saveDict("./dictionary.json");
    }

    /**
     * Method to return the dictionary list.
     * @return
     */
    public ArrayList<Word> getDictionaryList() {
        return dictionaryList;
    }

    /**
     * Method to set the dictionary list.
     * @param words
     */
    public void setDictionaryList (ArrayList<Word> words) {
        this.dictionaryList = words;
    }

    /**
     * Method to return the practice list.
     * @return
     */
    public ArrayList<Word> getPracticeList() {
        return practiceList;
    }

    /**
     * Method the set the practice list.
     * @param practice
     */
    public void setPracticeList(ArrayList<Word> practice) {
        this.practiceList = practiceList;
    }

}