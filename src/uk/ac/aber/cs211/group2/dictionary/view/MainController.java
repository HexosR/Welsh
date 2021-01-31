package uk.ac.aber.cs211.group2.dictionary.view;

import uk.ac.aber.cs211.group2.dictionary.Dictionary;
import uk.ac.aber.cs211.group2.dictionary.Word;
import uk.ac.aber.cs211.group2.dictionary.practiceList.PracticeListController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

/** This class is a superclass for all controllers
 * @author Kacper Szymczyc[kas102]
 */
public class MainController implements Initializable{


    @FXML

    public TableView wordTable;
    public TableColumn first;
    public TableColumn second;
    public TableColumn third;
    public TextField searchBox;
    public TableColumn practice;
    public Button languageButton;

    public Dictionary dictionary;

    /**
     * Method to display all words to the user in a table
     * @param c
     */
    @FXML
    public void displayWords(Collection c) {
        wordTable.getItems().clear();
        wordTable.refresh();
        wordTable.getItems().addAll(c);
    }

    /**
     * This method initializes how the dictionary is displayed.
     * @param isEnglish
     * @throws ClassCastException
     */
    public void initDict(boolean isEnglish) throws ClassCastException{

            if (isEnglish) {
            first.setCellValueFactory(new PropertyValueFactory<>("english"));
            second.setCellValueFactory(new PropertyValueFactory<>("welsh"));
            third.setCellValueFactory(new PropertyValueFactory<>("displayType"));
            practice.setCellValueFactory(new PropertyValueFactory<>("practice"));
        } else {
            first.setCellValueFactory(new PropertyValueFactory<>("welsh"));
            second.setCellValueFactory(new PropertyValueFactory<>("displayType"));
            third.setCellValueFactory(new PropertyValueFactory<>("english"));
            practice.setCellValueFactory(new PropertyValueFactory<>("practice"));
        }
    }

    /**
     * This method decides the behaviour of a word when clicked if that if word is/isn’t in the practice list.
     * @param w
     */
    public void checkMoveContains(Word w) {
        if (dictionary.practiceList.contains(w)) {
            dictionary.moveDict(w);
            System.out.println("Moved " + w.getEnglish() + " to Dictionary");
        } else {
            dictionary.movePractice(w);
            System.out.println("Moved " + w.getEnglish() + " to Practice List");
        }
    }

    /**
     * Method Initialises a scene for use.
     * @param root
     * @param event
     * @param controller
     * @param isDict
     * @param change
     */
    public void setScene(Parent root, ActionEvent event, MainController controller, boolean isDict,boolean change) {
            if (isDict) {
                ((DictionaryController) controller).setDict(dictionary, change);
            } else {
                ((PracticeListController) controller).setDict(dictionary, change);
            }
            controller.searchBox.appendText("");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene mainScene = new Scene(root);
        window.setScene(mainScene);
        window.show();
    }

    /**
     * Method is for the purpose of initialising the main controller.
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
