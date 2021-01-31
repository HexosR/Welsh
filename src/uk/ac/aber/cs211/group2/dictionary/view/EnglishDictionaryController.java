package uk.ac.aber.cs211.group2.dictionary.view;

import uk.ac.aber.cs211.group2.dictionary.practiceList.EnglishPracticeListController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** Class that controls how the english dictionary will be displayed.
 * @author Kacper Szymczyc[kas102]
 * @author Robert Mlynarczyk[rom57]
 */
public class EnglishDictionaryController extends DictionaryController {

    public static String sEng = "";

    public static String sWel = "";

    public static String getsEng() {
        return sEng;
    }

    public static void setsEng(String sEng) {
        EnglishDictionaryController.sEng = sEng;
    }

    public static void setsWel(String sWel) {
        EnglishDictionaryController.sWel = sWel;
    }

    public static String getsWel() {
        return sWel;
    }

    public void initDict() {

        initDict(true);

    }

    public void practiceList(ActionEvent event) throws IOException {
        if (!searchBox.equals("")) {
            EnglishPracticeListController.setPracsEng(searchBox.getText());
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EnglishPracticeList.fxml"));
        Parent root = loader.load();
        EnglishPracticeListController plc = loader.getController();
        setScene(root, event, plc, false,false);
        System.out.println("Transferred dictionary to English Practice List window " + dictionary);
    }

    public void changeLanguage(ActionEvent event) throws IOException {
        if (!searchBox.equals("")) {
            sEng = searchBox.getText();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WelshDictionary.fxml"));
        Parent root = loader.load();
        WelshDictionaryController plc = loader.getController();
        setScene(root, event, plc, true,true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHandlersEng();
        initDict();
    }
}
