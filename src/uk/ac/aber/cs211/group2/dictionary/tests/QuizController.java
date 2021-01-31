package uk.ac.aber.cs211.group2.dictionary.tests;

import uk.ac.aber.cs211.group2.dictionary.Dictionary;
import uk.ac.aber.cs211.group2.dictionary.Word;
import uk.ac.aber.cs211.group2.dictionary.tests.firstTest.FirstTestController;
import uk.ac.aber.cs211.group2.dictionary.tests.secondTest.SecondTestController;
import uk.ac.aber.cs211.group2.dictionary.tests.thirdTest.ThirdTestController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

/** Class that deals with how the Quizzes are displayed.
 * @author Kacper Szymczyc[kas102]
 * @author Robert Mlynarczyk[rom57]
 */
public class QuizController implements Initializable {

    private Random ran = new Random();
    private Dictionary dictionary;
    private ArrayList<Word> testWords;
    private boolean isEnglish;
    private int score = 0;
    private ArrayList<Word> words = new ArrayList<>();
    public static Stage window = new Stage();
    private int is_four = 0;


    @FXML
    public Button english;
    public Button welsh;

    /**
     * Method to set the current language.
     * @param lang
     */
    public QuizController(boolean lang) {
        isEnglish = lang;
    }

    /**
     * Method to get the focus of dictionary.
     * @param d
     */
    public void setDict(Dictionary d) {
        this.dictionary = d;
        testWords = new ArrayList<>(dictionary.getPracticeList());
    }


    /**
     * Method to create the quizzes
     * @throws IOException
     */
    public void run() throws IOException {
        {
            createTasks();
        }
    }

    /**
     * Method to display quizzes.
     * @throws IOException
     */
    private void createTasks() throws IOException {
        displayTasks();
    }

    /**
     * Method to display the first task window.
     * @param w
     * @throws IOException
     */
    private void displayFirstTask(Word w) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FirstTest.fxml"));
        Parent root = loader.load();
        FirstTestController ft = loader.getController();
        System.out.println(w);
        ft.setEnglish(isEnglish);
        ft.setTranslateWord(w);
        ft.setCurrScore(score, testWords.size());
        Stage window = new Stage();
        Scene firstTest = new Scene(root);
        window.setScene(firstTest);
        window.showAndWait();
        if (ft.isCorrect()) {
            score++;
        }
    }

    /**
     * Method to display the second task window.
     * @param w
     * @throws IOException
     */
    private void displaySecondTask(Word w) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SecondTest.fxml"));
        Parent root = loader.load();
        SecondTestController st = loader.getController();
        System.out.println(w);
        st.setEnglish(isEnglish);
        st.setTranslateWordAndButtons(w);
        st.setCurrScore(score, testWords.size());
        Stage window = new Stage();
        Scene secondTest = new Scene(root);
        window.setScene(secondTest);
        window.showAndWait();
        if (st.isCorrect()) {
            score++;
        }
    }

    /**
     * Method to display the third task window.
     * @param w
     */
    private void displayThirdTask(Word w){
        words.add(w);
        if (words.size() == 4) {
            is_four = 0;
            BorderPane root = new BorderPane();

            ThirdTestController rl = new ThirdTestController();
            root.setCenter(rl);
            System.out.println(words);
            rl.setEnglish(isEnglish);
            rl.setTranslateWord(words);
            rl.setCurrScore(score, testWords.size());

            Scene scene = new Scene(root, 640, 480);
            window.setScene(scene);
            window.showAndWait();
            score += rl.isCorrect();
        }
    }

    /**
     * Method to handle which type of quiz will be used.
     * @throws IOException
     */
    private void displayTasks() throws IOException {
        int size = testWords.size();
        if(size == 0) {
            Alert scoreW = new Alert(Alert.AlertType.INFORMATION);
            scoreW.setHeaderText("You must add at least one word to the practice list to start tests!");
            scoreW.showAndWait();
        }else{
            for (Word testWord : testWords) {
                if (size >= 4) {
                    int r = ran.nextInt(3);
                    if (r == 0 && is_four == 0) {
                        displayFirstTask(testWord);
                        size--;
                    } else if (r == 1 && is_four == 0) {
                        displaySecondTask(testWord);
                        size--;
                    } else if (r == 2 || is_four == 1) {
                        is_four = 1;
                        displayThirdTask(testWord);
                        if (is_four == 0) {
                            size -= 4;
                        }
                    }
                } else {
                    int r = ran.nextInt(2);
                    if (r == 0) {
                        displayFirstTask(testWord);
                    } else if (r == 1) {
                        displaySecondTask(testWord);
                    }
                }
            }
            Alert scoreW = new Alert(Alert.AlertType.INFORMATION);
            scoreW.setHeaderText("Your final score: " + score + "/" + testWords.size());
            scoreW.setContentText("Which makes it " + (double) score / testWords.size() * 100 + "%");
            scoreW.showAndWait();
        }
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
