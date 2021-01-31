package uk.ac.aber.cs211.group2.dictionary.practiceList;

import uk.ac.aber.cs211.group2.dictionary.view.EnglishDictionaryController;
import uk.ac.aber.cs211.group2.dictionary.view.WelshDictionaryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/** This class deals with how the practice list window is displayed in the Welsh first version.
 * @author Kacper Szymczyc[kas102]
 * @author Robert Mlynarczyk[rom57]
 */
public class WelshPracticeListController extends PracticeListController {

    public void initDict(){
       initDict(false);
    }

    @FXML
    private void backDictionary(ActionEvent event) throws IOException {
        if (!searchBox.equals("")) {
            EnglishDictionaryController.setsWel(searchBox.getText());
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WelshDictionary.fxml"));
        WelshDictionaryController wdc = new WelshDictionaryController();
        goBackDictionary(loader, event, wdc);
        System.out.println("Transferred dictionary to Dictionary window " + dictionary);
    }

    public void changeLanguage(ActionEvent event) throws IOException {
        if (!searchBox.equals("")) {
            EnglishPracticeListController.setPracsWel(searchBox.getText());
        }
        EnglishPracticeListController wpc = new EnglishPracticeListController();
        goChangeLanguage(event, false, wpc);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHandlersPracWel();
        initDict();
    }
}
