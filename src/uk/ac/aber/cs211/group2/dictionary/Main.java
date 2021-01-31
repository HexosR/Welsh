package uk.ac.aber.cs211.group2.dictionary;

import uk.ac.aber.cs211.group2.dictionary.view.EnglishDictionaryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/** The Main class extends the imported JavaFX Application class, allowing us to provide the user an interface based on JavaFX. The JavaFX runtime then executes designated methods of this extending class, mentioned below.
 * @author Kacper Szymczyc[kas102]
 * @author Robert Mlynarczyk[rom57]
 * @author Huzefa Syed[hus3]
 */
public class Main extends Application {
    private Stage primaryStage;
    public static Dictionary dictionary = new Dictionary();

    /**
     * This is the first method that runs in our program, after the initialization of JavaFX.
     * It is passed a primaryStage object, which is the base stage of the interface. We then modify that object so that it contains the main window of our dictionary.
     * This is the first thing that the user sees.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Welsh Vocabulary Tutor");
        dictionary.loadDict("./dictionary.json");   // load words using word reader
        dictionary.loadPracList("./pracList.json");
        System.out.println("New dictionary " + dictionary);
        showMainView();
    }

    /**
     * Method changes the details of the main window – loads all the elements within the window, changes the Scene to the main window, modifies the window’s display size, and displays it.
     * @throws IOException
     */
    public void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EnglishDictionary.fxml"));
        Parent root = loader.load();
        EnglishDictionaryController dc = loader.getController();
        dc.setDict(dictionary,false);
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(250);
        primaryStage.setMinHeight(250);
        primaryStage.show();
        System.out.println("Transferred dictionary to English dictionary window " + dictionary);
    }

    /**
     * Method that runs when the program is to close.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
        System.out.println("Exiting...");
        dictionary.saveDictFinal("./dictionary.json");
        dictionary.savePracFinal("./pracList.json");
    }
}

