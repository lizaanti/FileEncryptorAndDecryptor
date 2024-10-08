package org.example.cryptojavafx;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Collection;

public class Menu extends Application {
    Label response;

    public Menu(String file) {
    }

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Menu");
        //для демонстрации меню используется панель граничной компановки
        //типа BorderPane в качестве корневого узла
        BorderPane rootNode = new BorderPane();

        Scene myScene = new Scene(rootNode, 300, 300);
        stage.setScene(myScene);

        //создание метки для отображения результатов выбора из меню

        response = new Label("Menu");

        //демонстрация меню, создание строки меню
        MenuBar mb = new MenuBar();

        //создание меню File
        Menu fileMenu = new Menu("File");
        MenuItem open = new MenuItem("Open");
        MenuItem close = new MenuItem("Close");
        MenuItem save = new MenuItem("Save");
        MenuItem exit = new MenuItem("Exit");
        fileMenu.getItems().addAll(open, close, save, new SeparatorMenuItem(), exit);

        //ввести меню File в строку меню
        mb.getMenus().add(fileMenu);

        Menu optionsMenu = new Menu("Options");

        Menu colorsMenu = new Menu("Colors");
        MenuItem red = new MenuItem("Red");
        MenuItem green = new MenuItem("Green");
        MenuItem blue = new MenuItem("Blue");

        colorsMenu.getItems().addAll(red, green, blue);
        optionsMenu.getItems().addAll(colorsMenu);

        Menu priorityMenu = new Menu("Priority");
        MenuItem high = new MenuItem("High");
        MenuItem low = new MenuItem("Low");
        priorityMenu.getItems().addAll(high, low);
        optionsMenu.getItems().add(priorityMenu);
        //ввести разделитель
        optionsMenu.getItems().add(new SeparatorMenuItem());
        // создать пункт меню Reset
        MenuItem reset = new MenuItem("Reset"); //Сбросить
        optionsMenu.getItems().add(reset);
        //ввести меню Options в строку меню
        mb.getMenus().add(optionsMenu);
        //создать меню Help
        Menu helpMenu = new Menu("Help"); // Справка
        MenuItem about = new MenuItem("About"); // О программе
        helpMenu.getItems().add(about);
        // ввести меню Help в строку меню
        mb.getMenus().add(helpMenu);
        // создать один приемник действий для обработки
        // всех событий действия, наступающих в меню
        EventHandler<ActionEvent> MEHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = ((MenuItem)ae.getTarget()).getText();

                if(name.equals("Exit")) Platform.exit();

                response.setText(name + " selected");
            }
        };

        open.setOnAction(MEHandler);
        close.setOnAction(MEHandler);
        save.setOnAction(MEHandler);
        exit.setOnAction(MEHandler);
        red.setOnAction(MEHandler);
        green.setOnAction(MEHandler);
        blue.setOnAction(MEHandler);
        high.setOnAction(MEHandler);
        low.setOnAction(MEHandler);
        reset.setOnAction(MEHandler);
        about.setOnAction(MEHandler);

        rootNode.setTop(mb);
        rootNode.setCenter(response);

        stage.show();
    }

    private <E> Collection<E> getItems() {
        return java.util.List.of();
    }
}
