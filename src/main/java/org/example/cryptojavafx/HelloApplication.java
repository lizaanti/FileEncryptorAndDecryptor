package org.example.cryptojavafx;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;



public class HelloApplication extends Application {
    Label response;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage){
        /*stage.setTitle("КРИПТОАНАЛИЗАТОР");
        //использование панели поточной компоновки
        //типа FlowPane в качестве корневого узла
        FlowPane rootNode = new FlowPane(10, 10);
        rootNode.setAlignment(Pos.CENTER);
        Scene myScene = new Scene(rootNode, 300, 100);
        stage.setScene(myScene); //устанавливаем сцену на подмостках
        //создаем метку
        response = new Label("Push a button");

        Button btnAlpha = new Button("Alpha");
        Button btnBetta = new Button("Betta");
        //Обработка события действия кнопки Alpha
        btnAlpha.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                response.setText("Alpha was pressed!");
            }
        });
        //Обработка события действия кнопки Betta
        btnBetta.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                response.setText("Betta was pressed!");
            }
        });
        rootNode.getChildren().addAll(btnAlpha, btnBetta, response);
        stage.show();*/
    }

}