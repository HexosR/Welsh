package uk.ac.aber.cs211.group2.dictionary.addNewWord;

import uk.ac.aber.cs211.group2.dictionary.Dictionary;
import uk.ac.aber.cs211.group2.dictionary.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class to handle adding a new word to the display
 *  * @author Kacper Szymczyc[kas102]
 *  * @author Robert Mlynarczyk[rom57]
 */
public class AddNewWordController implements Initializable {
    private Dictionary dictionary;

    @FXML
    public ToggleGroup typeChoice = new ToggleGroup();
    public RadioButton masc;
    public RadioButton fem;
    public RadioButton verb;
    public RadioButton other;
    public Button closeButton;
    public TextField englishWord;
    public TextField welshWord;


    /**
     * Method is for the purpose of initialising the controller, initialises radio buttons.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    masc.setToggleGroup(typeChoice);
    fem.setToggleGroup(typeChoice);
    verb.setToggleGroup(typeChoice);
    other.setToggleGroup(typeChoice);
    }

    /**
     * Method that prints a message based on radio buttons.
     */
    @FXML
    public void getWord(){
        String wordType;
        String displayWordType;
        if(typeChoice.getSelectedToggle() == masc){
            wordType = "nm";
            displayWordType = "male";
        } else if (typeChoice.getSelectedToggle() == fem){
            wordType = "nf";
            displayWordType = "female";
        } else if (typeChoice.getSelectedToggle() == verb){
            wordType = "verb";
            displayWordType = "verb";
        } else {
            wordType = "other";
            displayWordType = "";
        }

        Word newWord = new Word(englishWord.getText(), welshWord.getText(), wordType);
        newWord.setDisplayType(displayWordType);
        addWord(newWord);
    }

    /**
     * Method that closes the current window.
     */
    public void close(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Method to insert a Word into the dictionary.
     * @param w
     */
    private void addWord(Word w){
        dictionary.insertWord(w);
        dictionary.movePractice(w);
        dictionary.moveDict(w);
        dictionary.movePractice(w);

        System.out.println("Inserted new word " + w.toString() + " " + w.getWordType());
        close();
    }

    /**
     * Method to set the focus of a dictionary, either main or practice.
     * @param d
     */
    @FXML
    public void setDict(Dictionary d) {
        this.dictionary = d;
        System.out.println(dictionary);
    }
}
