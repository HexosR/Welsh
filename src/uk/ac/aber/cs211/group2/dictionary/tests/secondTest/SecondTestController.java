package uk.ac.aber.cs211.group2.dictionary.tests.secondTest;

import uk.ac.aber.cs211.group2.dictionary.Main;
import uk.ac.aber.cs211.group2.dictionary.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

/** This class deals with how the “translate” six buttons tests are displayed.
 * @author Robert Mlynarczyk[rom57]
 */
public class SecondTestController implements Initializable {

    private Word translateWord;
    private boolean isEnglish;
    private boolean correct = false;
    private int currScore;
    private int currNo;
    Random ran = new Random();
    @FXML
    public Text word;
    public Text score;
    public Text wordForm;
    public Text result;
    public Button w1;
    public Button w2;
    public Button w4;
    public Button w5;
    public Button w3;
    public Button w6;
    ArrayList<Button> buttons = new ArrayList<>();

    public void setEnglish(boolean lang) {
        isEnglish = lang;
    }

    public void setCurrScore(int newScore, int no) {
        currScore = newScore;
        currNo = no;
        score.setText("Score: " + currScore + "/" + currNo);
    }

    // Method to deal with the buttons that the user will interact with in the quiz.
    public void setTranslateWordAndButtons(Word w) {
        buttons.add(w1);
        buttons.add(w2);
        buttons.add(w3);
        buttons.add(w4);
        buttons.add(w5);
        buttons.add(w6);
        Collections.shuffle(buttons);
        translateWord = w;

        if (isEnglish) {
            word.setText(w.getEnglish());
            buttons.get(0).setText(w.getWelsh());
            Object[] val = Main.dictionary.getDictionaryList().toArray();
            for (int i = 1; i < 6; i++) {
                Word ranWord = (Word) val[ran.nextInt(val.length)];
                buttons.get(i).setText(ranWord.getWelsh());
            }
        } else {
            word.setText(w.getWelsh());
            wordForm.setText(w.getWordType());
            buttons.get(0).setText(w.getEnglish());
            Object[] val = Main.dictionary.getDictionaryList().toArray();
            for (int i = 1; i < 6; i++) {
                Word ranWord = (Word) val[ran.nextInt(val.length)];
                buttons.get(i).setText(ranWord.getEnglish());
            }
        }
        onClick();
    }

    public boolean isCorrect() {
        return correct;
    }

    // Method that handles the code when the user clicks the buttons.
    public void onClick() {
        for (Button b : buttons) {
            b.setOnMouseClicked((MouseEvent s) -> {
                if (s.getButton().equals(MouseButton.PRIMARY) && word.getFill().equals(Color.BLACK)) {
                    if (!isEnglish) {
                        if (b.getText().equals(translateWord.getEnglish())) {
                            word.setFill(Color.GREEN);
                            wordForm.setFill(Color.GREEN);
                            result.setText("Correct!");
                            correct = true;
                        } else {
                            word.setFill(Color.RED);
                            wordForm.setFill(Color.RED);
                            result.setText("Incorrect! Correct answer: " + translateWord.getEnglish());
                        }
                    } else {
                        if (b.getText().equals(translateWord.getWelsh())) {
                            word.setFill(Color.GREEN);
                            result.setText("Correct!");
                            correct = true;
                        } else {
                            word.setFill(Color.RED);
                            result.setText("Incorrect! Correct answer: " + translateWord.getWelsh());
                        }
                    }
                    if (correct) {
                        currScore++;
                        score.setText("Score: " + currScore + "/" + currNo);
                    }
                } else if (s.getButton().equals(MouseButton.PRIMARY)) {
                    Stage stage = (Stage) b.getScene().getWindow();
                    stage.close();
                }
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

