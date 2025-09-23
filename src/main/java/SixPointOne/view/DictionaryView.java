package SixPointOne.view;

import SixPointOne.controller.DictionaryController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.NoSuchElementException;

public class DictionaryView extends Application {
    private final DictionaryController controller = new DictionaryController();

    public void start(Stage stage) {
        TextField wordField = new TextField();
        wordField.setPromptText("Enter word…");
        Button searchBtn = new Button("Search");
        TextArea output = new TextArea();
        output.setEditable(false);
        output.setPrefColumnCount(30);
        output.setPrefRowCount(6);
        output.setWrapText(true);
        Label status = new Label();

        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String input = wordField.getText();
                try {
                    String meaning = controller.searchMeaning(input);
                    output.setText(meaning);
                    status.setText("OK");
                } catch (IllegalArgumentException ex) {
                    output.clear();
                    status.setText("Please enter a word.");
                } catch (NoSuchElementException ex) {
                    output.clear();
                    status.setText("Word not found.");
                }
            }
        });

        wordField.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                searchBtn.fire();
            }
        });

        FlowPane root = new FlowPane(10, 10);
        root.setPadding(new Insets(12));
        root.getChildren().addAll(new Label("Word:"), wordField, searchBtn,
                new Label("Meaning:"), output, status);

        stage.setTitle("Virtual Dictionary");
        stage.setScene(new Scene(root, 480, 260));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
