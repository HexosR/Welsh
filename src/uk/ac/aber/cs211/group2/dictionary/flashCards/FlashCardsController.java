package uk.ac.aber.cs211.group2.dictionary.flashCards;

import uk.ac.aber.cs211.group2.dictionary.Dictionary;
import uk.ac.aber.cs211.group2.dictionary.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *  This class deals with how the flashcards are displayed.
 *  @author Kacper Szymczyc[kas102]
 *  @author Robert Mlynarczyk[rom57]
 */
public class FlashCardsController implements Initializable {

    @FXML
    public Button btnLeft;
    public Button btnRight;
    public Button btnDown;
    public Text word;
    public Text form;
    public VBox window;
    private ArrayList<Word> words;
    private int currWord = 0;
    private boolean isEnglish;
    private boolean currEnglish;

    /**
     * Method to get the focus of dictionary.
     * @param d
     */
    public void setDict(Dictionary d) {
        words = new ArrayList<>(d.getPracticeList());
    }

    /**
     * Sets the flashcard language.
     * @param choice
     */
    public void setEnglish(boolean choice){
        isEnglish = choice;
        currEnglish = choice;
        System.out.println("is english: " + isEnglish);
        displayWord(words.get(currWord));
    }

    /**
     * Displays the word to the User based on the language.
     * @param w
     */
    private void displayWord(Word w){
        if (currEnglish){
            displayEnglish(w);
        } else {
            displayWelsh(w);
        }
    }

    /**
     * Displays the word to the User based in Welsh.
     * @param w
     */
    private void displayWelsh(Word w){
        word.setText(w.getWelsh());
        form.setText("[" + w.getWordType() + "]");
    }

    /**
     * Method to move the display to the next word in the queue.
     */
    public void nextWord(){
        if (currWord < words.size()-1) {
            currWord++;
            currEnglish = isEnglish;
            displayWord(words.get(currWord));
        }
    }

    /**
     * Method to move the display to the previous word in the queue.
     */
    public void prevWord(){
        if (currWord > 0) {
            currWord--;
            currEnglish = isEnglish;
            displayWord(words.get(currWord));
        }
    }

    /**
     * Method to display the word in the opposite language.
     */
    public void translateWord(){
        currEnglish = !currEnglish;
      displayWord(words.get(currWord));
    }

    /**
     * Displays the word to the User based in English.
     * @param w
     */
    private void displayEnglish(Word w){
        word.setText(w.getEnglish());
        form.setText("");

    }

    /**
     * Method is for the purpose of initialising the controller.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
