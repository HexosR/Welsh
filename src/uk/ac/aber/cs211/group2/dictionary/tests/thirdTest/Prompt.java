package uk.ac.aber.cs211.group2.dictionary.tests.thirdTest;

import uk.ac.aber.cs211.group2.dictionary.tests.QuizController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Prompt {
    @FXML
    Text prompt;
    @FXML
    Button next;

    @FXML
    private void initialize() {
        next.setOnMouseClicked((
                MouseEvent s1) ->
        {
            ThirdTestController.window.close();
            QuizController.window.close();
            prompt.setText("");
        });
    }
}
