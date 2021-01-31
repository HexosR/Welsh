package uk.ac.aber.cs211.group2.dictionary.flashCards;

import uk.ac.aber.cs211.group2.dictionary.Dictionary;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class that deal with how choosing the language for the flashcards is displayed.
 * @author Kacper Szymczyc[kas102]
 * @author Robert Mlynarczyk[rom57]
 */
public class LangChooseFlashcardsController implements Initializable {
    private Dictionary dictionary;

    @FXML
    public Button english;
    public Button welsh;

    public void setDict(Dictionary d) {
        this.dictionary = d;
    }

    public void chooseWelsh() throws IOException {
        showFlashcards(false);
        Stage stage = (Stage) welsh.getScene().getWindow();
        stage.close();
    }

    public void chooseEnglish() throws IOException {
        showFlashcards(true);
        Stage stage = (Stage) english.getScene().getWindow();
        stage.close();
    }

    private void showFlashcards(boolean isEnglish) throws IOException {
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FlashCards.fxml"));
            Parent root = loader.load();
            FlashCardsController fcc = loader.getController();
            fcc.setDict(this.dictionary);
            fcc.setEnglish(isEnglish);
            Stage window = new Stage();
            Scene flashcards = new Scene(root);
            window.setScene(flashcards);
            System.out.println("Transferred dictionary to FlashCard window " + dictionary);
            window.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
