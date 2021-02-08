package com.unclezs.simples.datafx.tutorial1;

import io.datafx.controller.flow.Flow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author blog.unclezs.com
 * @since 2021/02/08 15:16
 */
public class Tutorial1Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new Flow(SimpleController.class).startInStage(primaryStage);
    }
}