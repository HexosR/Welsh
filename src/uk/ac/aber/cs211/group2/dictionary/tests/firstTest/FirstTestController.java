package uk.ac.aber.cs211.group2.dictionary.tests.firstTest;

import uk.ac.aber.cs211.group2.dictionary.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/** This class deals with how the “translate” tests are displayed.
 * @author Kacper Szymczyc[kas102]
 */
public class FirstTestController implements Initializable
{
    private Word translateWord;
    private boolean isEnglish;
    private boolean correct = false;
    private int currScore;
    private int currNo;
    @FXML
    public TextField translation;
    public Text word;
    public Text wordForm;
    public Text result;
    public Text score;
    public Button check;

    public void setEnglish(boolean lang){
    isEnglish = lang;
    }

    public void setCurrScore(int newScore, int no){
        currScore = newScore;
        currNo = no;
        score.setText("Score: " + currScore + "/" + currNo);
    }

    public void setTranslateWord(Word w){
        translateWord = w;

        if(isEnglish) {
            word.setText(w.getEnglish());
        } else {
            word.setText(w.getWelsh());
            wordForm.setText(w.getWordType());
        }
    }

    public boolean isCorrect(){
        return correct;
    }
    @FXML
    public void check(){
        if(!isEnglish){
            if(translation.getText().equals(translateWord.getEnglish())){
                word.setFill(Color.GREEN);
                wordForm.setFill(Color.GREEN);
                result.setText("Correct!");
                correct = true;
            } else {
                word.setFill(Color.RED);
                wordForm.setFill(Color.RED);
                result.setText("Incorrect! Correct answer: " + translateWord.getEnglish());
            }
        } else{
            if(translation.getText().equals(translateWord.getWelsh())){
                word.setFill(Color.GREEN);
                result.setText("Correct!");
                correct = true;
            } else {
                word.setFill(Color.RED);
                result.setText("Incorrect! Correct answer: " + translateWord.getWelsh());
            }
        }
        if(correct)  {
            currScore++;
            score.setText("Score: " + currScore + "/" + currNo);
        }
        System.out.println(translation.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        check.setOnMouseClicked((MouseEvent s) -> {
            if(s.getButton().equals(MouseButton.PRIMARY) && word.getFill().equals(Color.BLACK)) {
            check();
            check.setText("Next");
            } else if (s.getButton().equals(MouseButton.PRIMARY)){
                Stage stage = (Stage) check.getScene().getWindow();
                stage.close();
            }
        });
    }
}
