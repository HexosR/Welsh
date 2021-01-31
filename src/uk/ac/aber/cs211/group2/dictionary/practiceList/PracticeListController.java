package uk.ac.aber.cs211.group2.dictionary.practiceList;

import uk.ac.aber.cs211.group2.dictionary.Dictionary;
import uk.ac.aber.cs211.group2.dictionary.Word;
import uk.ac.aber.cs211.group2.dictionary.flashCards.LangChooseFlashcardsController;
import uk.ac.aber.cs211.group2.dictionary.tests.LangChooseTestController;
import uk.ac.aber.cs211.group2.dictionary.view.DictionaryController;
import uk.ac.aber.cs211.group2.dictionary.view.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class deals with how the practice list is displayed and how it can be changed, by buttons or otherwise. It controls how the Practice List window transitions to other windows, or vice versa.
 *  * @author Kacper Szymczyc[kas102]
 *  * @author Robert Mlynarczyk[rom57]
 */
public class PracticeListController extends MainController {

    /**
     * Method to display words to the user in a table.
     * @param c
     */
    @FXML
    public void displayWords(Collection c) {
        wordTable.getItems().clear();
        wordTable.refresh();
        wordTable.getItems().addAll(c);
    }

    /**
     * Method to search the practice list in english
     * @param query
     * @return
     */
    private ArrayList<Word> searchPracEng(String query) {
        return dictionary.wordManager.searchBeginningPracListEnglish(query);
    }

    /**
     * Method to search the practice list in welsh
     * @param query
     * @return
     */
    private ArrayList<Word> searchPracWel(String query) {
        return dictionary.wordManager.searchBeginningPracListWelsh(query);
    }

    /**
     * Method to set the focus of a dictionary, either main or practice.
     * @param d
     * @param change
     */
    public void setDict(Dictionary d,boolean change) {
        this.dictionary = d;
        try {
            if (!EnglishPracticeListController.getPracsEng().equals("")) {
                searchBox.appendText(EnglishPracticeListController.getPracsEng());
                if(change){
                    displayWords(searchPracWel(searchBox.getText()));
                }else{
                    displayWords(searchPracEng(searchBox.getText()));
                }
                EnglishPracticeListController.setPracsEng("");
            } else if (!EnglishPracticeListController.getPracsWel().equals("")) {
                searchBox.appendText(EnglishPracticeListController.getPracsWel());
                if(change){
                    displayWords(searchPracEng(searchBox.getText()));
                }else{
                    displayWords(searchPracWel(searchBox.getText()));
                }
                EnglishPracticeListController.setPracsWel("");
            } else {
                ArrayList<Word> words = this.dictionary.getPracticeList();
                displayWords(words);
            }
            wordTable.getSortOrder().add(first);
            searchBox.appendText("");
            first.setSortable(true);
            wordTable.refresh();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Empty");
        }
    }

    /**
     * This method manages the transition back to the main Dictionary window.
     * @param loader
     * @param event
     * @param controller
     * @throws IOException
     */
    public void goBackDictionary(FXMLLoader loader, ActionEvent event, DictionaryController controller) throws IOException {
        Parent root = loader.load();
        controller = loader.getController();
        controller.setDict(dictionary, false);
        String prev = controller.searchBox.getText();
        setScene(root, event, controller, true,false);
    }

    /**
     * This method contains the code to change the language display of the current scene.
     * @param event
     * @param isEnglish
     * @param controller
     * @throws IOException
     */
    public void goChangeLanguage(ActionEvent event, boolean isEnglish, PracticeListController controller) throws IOException {
        FXMLLoader loader;
        Parent root;
        if (isEnglish) {
            loader = new FXMLLoader(getClass().getResource("/WelshPracticeList.fxml"));
            root = loader.load();
            controller = loader.getController();
        } else {
            loader = new FXMLLoader(getClass().getResource("/EnglishPracticeList.fxml"));
            root = loader.load();
            controller = loader.getController();
        }
        setScene(root, event, controller, false,true);
    }

    /**
     * This method displays the Flashcards window, called after user clicks on “Flashcards” button and awaits user input.
     * @param actionEvent
     * @throws IOException
     */
    public void goFlashcards(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LangChoose.fxml"));
        Parent root = loader.load();
        LangChooseFlashcardsController lcfc = loader.getController();
        lcfc.setDict(dictionary);
        Stage window = new Stage();
        Scene flashcardsLangChoose = new Scene(root);
        window.setScene(flashcardsLangChoose);
        System.out.println("Transferred dictionary to Language Choice window " + dictionary);
        window.show();
    }

    /**
     *This method displays the language selection window for the tests and awaits user input.
     * @param actionEvent
     * @throws IOException
     */
    public void goTests(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LangChooseTests.fxml"));
        Parent root = loader.load();
        LangChooseTestController lctc = loader.getController();
        lctc.setDict(dictionary);
        Stage window = new Stage();
        Scene testLangChoose = new Scene(root);
        window.setScene(testLangChoose);
        System.out.println("Transferred dictionary to Tests " + dictionary);
        window.show();
    }

    /**
     * Method to handle the user clicking a word to moving it between dictionaries.
     */
    public void setHandlersPracEng() {
        searchBox.textProperty().addListener((observable, oldv, newv) -> displayWords(searchPracEng(newv)));
        wordTable.setOnMouseClicked((MouseEvent s) -> {
            if (s.getButton().equals(MouseButton.PRIMARY)) {
                int i = wordTable.getSelectionModel().getSelectedIndex();
                if (i >= 0) {
                    System.out.print(i + " " + wordTable.getItems().size());
                    Word w = (Word) wordTable.getItems().get(i);
                    checkMoveContains(w);
                    wordTable.getSelectionModel().clearSelection();
                    displayWords(searchPracEng(searchBox.getText()));
                }
            }
        });
    }

    /**
     * Method to handle the user clicking a word to moving it between dictionaries.
     */
    public void setHandlersPracWel() {
        searchBox.textProperty().addListener((observable, oldv, newv) -> displayWords(searchPracWel(newv)));
        wordTable.setOnMouseClicked((MouseEvent s) -> {
            if (s.getButton().equals(MouseButton.PRIMARY)) {
                int i = wordTable.getSelectionModel().getSelectedIndex();
                if (i >= 0) {
                    System.out.print(i + " " + wordTable.getItems().size());
                    Word w = (Word) wordTable.getItems().get(i);
                    checkMoveContains(w);
                    wordTable.getSelectionModel().clearSelection();
                    displayWords(searchPracWel(searchBox.getText()));
                }
            }
        });
    }
}
