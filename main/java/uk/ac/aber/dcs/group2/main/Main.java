package uk.ac.aber.dcs.group2.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.util.Stack;
import java.util.concurrent.Callable;

public class Main extends Application {
    Dictionary dictionary;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        primaryStage.setTitle("Welsh English Dictionary");
        primaryStage.setScene(new Scene(root, 800, 600));
        Main m = new Main();
        primaryStage.setScene(m.makeButton());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private Scene makeButton(){

        Button button = new Button("Click me");
        button.setOnAction(e -> button.setOnAction((EventHandler<ActionEvent>) (this.dictionary = new Dictionary())));
        this.dictionary.load("uk.ac.aber.cs221.group2.dictionary.json");
        System.out.println("Total words loaded: " + dictionary.englishWordTree.size());;
        Scene scene = new Scene(button);
        return scene;
    }
}
