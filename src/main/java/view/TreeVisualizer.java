package main.java.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import main.java.controller.MorseController;

public class TreeVisualizer extends Application {
    private MorseController controller;
    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) {
        controller = new MorseController();

        VBox root = new VBox(10);

        canvas = new Canvas(800, 600);
        controller.getMorseTree().drawTree(canvas);

        TextField inputField = new TextField();
        inputField.setPromptText("Digite código Morse (ex: ... --- ...)");

        Button decodeButton = new Button("Decodificar");
        Label resultLabel = new Label();

        decodeButton.setOnAction(e -> {
            String morseCode = inputField.getText();
            String decoded = controller.decode(morseCode);
            resultLabel.setText("Decodificado: " + decoded);
        });

        TextField encodeField = new TextField();
        encodeField.setPromptText("Digite texto para codificar");

        Button encodeButton = new Button("Codificar");
        Label encodeResultLabel = new Label();

        encodeButton.setOnAction(e -> {
            String text = encodeField.getText();
            String encoded = controller.encode(text);
            encodeResultLabel.setText("Codificado: " + encoded);
        });

        root.getChildren().addAll(
                canvas,
                new HBox(10, inputField, decodeButton, resultLabel),
                new HBox(10, encodeField, encodeButton, encodeResultLabel)
        );

        Scene scene = new Scene(root, 800, 700);
        primaryStage.setTitle("Visualizador de Árvore Morse");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}