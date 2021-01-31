package uk.ac.aber.cs211.group2.dictionary.tests;

import uk.ac.aber.cs211.group2.dictionary.Dictionary;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/** Class that deals with which language to choose from during tests is displayed.
 * @author Kacper Szymczyc[kas102]
 * @author Robert Mlynarczyk[rom57]
 */
public class LangChooseTestController implements Initializable {
    private Dictionary dictionary;

    @FXML
    public Button english;
    public Button welsh;

    public void setDict(Dictionary d) {
        this.dictionary = d;
    }

    public void chooseWelsh() throws IOException {
        showTest(false);
        Stage stage = (Stage) welsh.getScene().getWindow();
        stage.close();
    }

    public void chooseEnglish() throws IOException {
        showTest(true);
        Stage stage = (Stage) english.getScene().getWindow();
        stage.close();
    }

    private void showTest(boolean isEnglish) throws IOException {
        {
        QuizController quizController = new QuizController(isEnglish);
        quizController.setDict(dictionary);
        quizController.run();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
