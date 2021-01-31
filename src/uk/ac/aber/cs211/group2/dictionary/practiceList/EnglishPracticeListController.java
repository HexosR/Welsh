package uk.ac.aber.cs211.group2.dictionary.practiceList;

import uk.ac.aber.cs211.group2.dictionary.view.EnglishDictionaryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class deals with how the practice list window is displayed in the English first version.
 * @author Kacper Szymczyc[kas102]
 * @author Robert Mlynarczyk[rom57]
 */
public class EnglishPracticeListController extends PracticeListController {

    public static String PracsEng = "";

    public static String PracsWel = "";

    public static String getPracsEng() {
        return PracsEng;
    }

    public static void setPracsEng(String sEng) {
        EnglishPracticeListController.PracsEng = sEng;
    }

    public static String getPracsWel() {
        return PracsWel;
    }

    public static void setPracsWel(String sWel) {
        EnglishPracticeListController.PracsWel = sWel;
    }

    @FXML
    private void backDictionary(ActionEvent event) throws IOException {
        if (!searchBox.equals("")) {
            EnglishDictionaryController.setsEng(searchBox.getText());
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EnglishDictionary.fxml"));
        EnglishDictionaryController edc = new EnglishDictionaryController();
        goBackDictionary(loader, event, edc);
        System.out.println("Transferred dictionary to English Dictionary window " + dictionary);
    }

    public void initDict(){
      initDict(true);
    }

    public void changeLanguage(ActionEvent event) throws IOException {
        if (!searchBox.equals("")) {
            PracsEng = searchBox.getText();
        }
        WelshPracticeListController wpc = new WelshPracticeListController();
        goChangeLanguage(event, true, wpc);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHandlersPracEng();
        initDict();
    }
}
