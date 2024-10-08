package org.example.cryptojavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    private Button btn;

    @FXML
    private void click(ActionEvent event) {
        btn.setText("You've clicked!");
    }
}