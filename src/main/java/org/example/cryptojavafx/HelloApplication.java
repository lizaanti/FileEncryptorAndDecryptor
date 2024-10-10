package org.example.cryptojavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    private static int rotn;

    public static int getRotn() {
        return rotn;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Cryptanalyzer");
        stage.setScene(scene);
        Stage inputStage = new Stage();
        inputStage.setTitle("Ввод количества символов для сдвига");
        //создаем элементы интерфейса
        Label label = new Label("Введите количество символов для сдвига:");
        TextField textField = new TextField();
        Button submitButton = new Button("Подтвердить");
        submitButton.setOnAction(event -> {
            rotn = Integer.parseInt(textField.getText());
            System.out.println("Количество символов для сдвига: " + rotn);
            inputStage.close();
        });
        //размещаем элементы в окне
        VBox layout = new VBox(label, textField, submitButton);
        Scene inputScene = new Scene(layout, 300, 80);
        inputStage.setScene(inputScene);
        inputStage.showAndWait();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}