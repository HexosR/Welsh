package uk.ac.aber.cs211.group2.dictionary.view;

import uk.ac.aber.cs211.group2.dictionary.practiceList.EnglishPracticeListController;
import uk.ac.aber.cs211.group2.dictionary.practiceList.WelshPracticeListController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/** Class that controlls how the welsh dictionary will be displayed.
 * @author Kacper Szymczyc[kas102]
 * @author Robert Mlynarczyk[rom57]
 */
public class WelshDictionaryController extends DictionaryController {

    public void initDict() {
        initDict(false);
    }

    public void practiceList(ActionEvent event) throws IOException {
        if (!searchBox.equals("")) {
            EnglishPracticeListController.setPracsWel(searchBox.getText());
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WelshPracticeList.fxml"));
        Parent root = loader.load();
        WelshPracticeListController plc = loader.getController();
        setScene(root, event, plc, false,false);
        System.out.println("Transferred dictionary to Welsh Practice List window " + dictionary);
    }

    public void changeLanguage(ActionEvent event) throws IOException {
        if (!searchBox.equals("")) {
            EnglishDictionaryController.setsWel(searchBox.getText());
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EnglishDictionary.fxml"));
        Parent root = loader.load();
        EnglishDictionaryController plc = loader.getController();
        setScene(root, event, plc, true,true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHandlersWel();
        initDict();

    }
}
