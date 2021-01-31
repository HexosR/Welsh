package uk.ac.aber.dcs.group2.main;

import com.google.gson.Gson;

import java.io.*;
import java.util.SortedMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class Dictionary {
    public SortedMap<String, Word> englishWordTree = new TreeMap<>();
    public SortedMap<String, Word> welshWordTree = new TreeMap<>();
    public SortedMap<String, Word> practiceTree = new TreeMap<>();

    public Dictionary() {
    }

    public void insertWord(Word w) {
        this.englishWordTree.put(w.welsh, w);
        this.welshWordTree.put(w.english, w);
    }

    public void save(String filename) {
        try (FileWriter writer = new FileWriter(filename); BufferedWriter buffer = new BufferedWriter(writer)) {
            Gson gson = new Gson();
            Word[] w = (Word[]) this.englishWordTree.values().toArray();
            gson.toJson(w, buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load(String filename) {
        try (FileReader read = new FileReader(filename); BufferedReader buffer = new BufferedReader(read)) {
            Gson gson = new Gson();
            Word[] w = gson.fromJson(buffer, Word[].class);
            for (int i = 0; i < w.length; i++) {
                this.insertWord(w[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SortedMap<String, Word> getEnglishWordTree() {
        return englishWordTree;
    }

    public void setEnglishWordTree(SortedMap<String, Word> englishWordTree) {
        this.englishWordTree = englishWordTree;
    }

    public SortedMap<String, Word> getWelshWordTree() {
        return welshWordTree;
    }

    public void setWelshWordTree(SortedMap<String, Word> welshWordTree) {
        this.welshWordTree = welshWordTree;
    }

    public SortedMap<String, Word> getPracticeTree() {
        return practiceTree;
    }

    public void setPracticeTree(SortedMap<String, Word> practiceTree) {
        this.practiceTree = practiceTree;
    }

}
