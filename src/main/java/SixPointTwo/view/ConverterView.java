package SixPointTwo.view;

import SixPointTwo.controller.ConverterController;
import SixPointTwo.model.ConverterModel;
import SixPointTwo.entity.Currency;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ConverterView extends Application {

    private TextField amountField;
    private TextField resultField;
    private ComboBox<Currency> fromBox;
    private ComboBox<Currency> toBox;
    private Button convertButton;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        Label headerMain = new Label("Enter an amount, choose currencies, then press Convert.");
        headerMain.getStyleClass().add("header");
        BorderPane.setAlignment(headerMain, Pos.CENTER);
        BorderPane.setMargin(headerMain, new Insets(10, 0, 20, 0));
        root.setTop(headerMain);

        GridPane grid = new GridPane();
        grid.setHgap(12);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15));

        Label fromLbl = new Label("From currency");
        Label toLbl   = new Label("To currency");
        fromLbl.getStyleClass().add("field-label");
        toLbl.getStyleClass().add("field-label");
        grid.add(fromLbl, 0, 0);
        grid.add(toLbl,   1, 0);

        fromBox = new ComboBox<>();
        toBox   = new ComboBox<>();
        fromBox.getStyleClass().add("input");
        toBox.getStyleClass().add("input");
        grid.add(fromBox, 0, 1);
        grid.add(toBox,   1, 1);

        Label amountLbl    = new Label("Amount");
        Label convertedLbl = new Label("Converted");
        amountLbl.getStyleClass().add("field-label");
        convertedLbl.getStyleClass().add("field-label");
        grid.add(amountLbl,    0, 2);
        grid.add(convertedLbl, 1, 2);

        amountField = new TextField();
        amountField.setPromptText("Type amount...");
        amountField.getStyleClass().add("input");

        resultField = new TextField();
        resultField.setEditable(false);
        resultField.setPromptText("Result");
        resultField.getStyleClass().addAll("input", "result");

        grid.add(amountField, 0, 3);
        grid.add(resultField, 1, 3);

        convertButton = new Button("Convert");
        convertButton.getStyleClass().add("primary");
        GridPane.setColumnSpan(convertButton, 2);
        GridPane.setHalignment(convertButton, HPos.CENTER);
        GridPane.setMargin(convertButton, new Insets(6, 0, 0, 0));
        grid.add(convertButton, 0, 4);

        root.setCenter(grid);
        root.getStyleClass().add("app");

        Scene scene = new Scene(root, 600, 300);
        scene.getStylesheets().add("styles.css");

        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();

        ConverterModel model = new ConverterModel();
        ConverterController controller = new ConverterController(model, this);
        controller.init();
    }

    public TextField getAmountField() { return amountField; }
    public TextField getResultField() { return resultField; }
    public ComboBox<Currency> getFromBox() { return fromBox; }
    public ComboBox<Currency> getToBox() { return toBox; }
    public Button getConvertButton() { return convertButton; }

}
