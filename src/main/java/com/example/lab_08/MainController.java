package com.example.lab_08;

import com.example.lab_08.classes.Program;
import com.example.lab_08.classes.Settings;
import com.example.lab_08.classes.ThreadPool;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MainController {
    @FXML
    VBox g1;
    @FXML
    ImageView g1Icon;

    public MainController() {
        Program p = new Program();

        ThreadPool.getInstance().executeRunnable(new Runnable() {
            @Override
            public void run() {
                p.start();
            }
        });
    }

    @FXML
    private void apply() {

    }



}