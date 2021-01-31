package uk.ac.aber.dcs.group2.main;

public class Controller {

    Dictionary dictionaryApp;

    public Controller() {
        this.dictionaryApp = new Dictionary();
        this.dictionaryApp.load("uk.ac.aber.cs221.group2.dictionary.json");
        System.out.println("Total words loaded: " + dictionaryApp.englishWordTree.size());
    }
}
