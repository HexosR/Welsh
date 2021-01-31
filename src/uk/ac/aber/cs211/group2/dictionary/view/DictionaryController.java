package uk.ac.aber.cs211.group2.dictionary.view;

import uk.ac.aber.cs211.group2.dictionary.Dictionary;
import uk.ac.aber.cs211.group2.dictionary.Word;
import uk.ac.aber.cs211.group2.dictionary.addNewWord.AddNewWordController;
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

/** Class that manages the display of common elements of the dictionaries.
 * @author Kacper Szymczyc[kas102]
 * @author Robert Mlynarczyk[rom57]
 */
public class DictionaryController extends MainController {

     ArrayList<Word> searchEng(String query) {
        return dictionary.wordManager.searchBeginningDictEnglish(query);
    }

     ArrayList<Word> searchWel(String query) {
        return dictionary.wordManager.searchBeginningDictWelsh(query);
    }

    /**
     * This method manages the behaviour of the “Add New Word” window.
     * @param event
     * @throws IOException
     */
    @FXML
    void addNewWord(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddNewWord.fxml"));
        Parent root = loader.load();
        AddNewWordController awc = loader.getController();
        awc.setDict(dictionary);
        Stage window = new Stage();
        Scene addNewWord = new Scene(root);
        window.setScene(addNewWord);
        System.out.println("Transferred dictionary to Add New Word window " + dictionary);
        window.showAndWait();
        System.out.println("Refreshed the word list");
        setDict(dictionary,false);
    }

    /**
     * Method to set the focus of a dictionary, either main or practice.
     * @param d
     * @param change
     */
    public void setDict(Dictionary d, boolean change) {
        this.dictionary = d;
        try {
            if (!EnglishDictionaryController.getsEng().equals("")) {
                searchBox.appendText(EnglishDictionaryController.getsEng());
                if(change){
                    displayWords(searchWel(searchBox.getText()));
                }else{
                    displayWords(searchEng(searchBox.getText()));
                }
                EnglishDictionaryController.setsEng("");
            } else if (!EnglishDictionaryController.getsWel().equals("")) {
                searchBox.appendText(EnglishDictionaryController.getsWel());
                if(change){
                    displayWords(searchEng(searchBox.getText()));
                }else{
                    displayWords(searchWel(searchBox.getText()));
                }
                EnglishDictionaryController.setsWel("");
            } else {
                ArrayList<Word> words = this.dictionary.getDictionaryList();
                displayWords(words);
            }
            wordTable.getSortOrder().add(first);
            wordTable.refresh();
            wordTable.sort();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Empty");
        }

    }

    /**
     * Method to handle the user searching for a word in the English dictionary.
     */
    public void setHandlersEng() {
        searchBox.textProperty().addListener((observable, oldv, newv) -> displayWords(searchEng(newv)));
        wordTable.setOnMouseClicked((MouseEvent s) -> {
            if (s.getButton().equals(MouseButton.PRIMARY)) {
                int i = wordTable.getSelectionModel().getSelectedIndex();
                if (i >= 0) {
                    System.out.print(i + " " + wordTable.getItems().size());
                    Word w = (Word) wordTable.getItems().get(i);
                    checkMoveContains(w);
                    wordTable.getSelectionModel().clearSelection();
                    wordTable.refresh();
                }
            }
        });
    }

    /**
     * Method to handle the user searching for a word in the Welsh dictionary.
     */
    public void setHandlersWel() {
        searchBox.textProperty().addListener((observable, oldv, newv) -> displayWords(searchWel(newv)));
        wordTable.setOnMouseClicked((MouseEvent s) -> {
            if (s.getButton().equals(MouseButton.PRIMARY)) {
                int i = wordTable.getSelectionModel().getSelectedIndex();
                if (i >= 0) {
                    System.out.print(i + " " + wordTable.getItems().size());
                    Word w = (Word) wordTable.getItems().get(i);
                    if (dictionary.practiceList.contains(w)) {
                        dictionary.moveDict(w);
                        System.out.println("Moved " + w.getWelsh() + " to Dictionary");
                    } else {
                        dictionary.movePractice(w);
                        System.out.println("Moved " + w.getWelsh() + " to Practice List");
                    }
                    wordTable.getSelectionModel().clearSelection();
                    wordTable.refresh();
                }
            }
        });
    }
}
