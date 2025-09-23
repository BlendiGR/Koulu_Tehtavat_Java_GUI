package SixPointTwo.view;


import SixPointTwo.controller.ConverterController;
import SixPointTwo.model.ConverterModel;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import SixPointTwo.Currency;


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
        headerMain.getStyleClass().add("headerMain");

        root.setTop(headerMain);
        BorderPane.setAlignment(headerMain, Pos.CENTER);
        BorderPane.setMargin(headerMain, new Insets(10, 0, 20, 0));


        GridPane grid = new GridPane();
        grid.setHgap(12);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Label formLbl = new Label("From currency");
        Label toLbl = new Label("To currency");

        fromBox = new ComboBox<>();
        toBox = new ComboBox<>();
        grid.add(fromBox, 0, 1);
        grid.add(toBox, 1, 1);

        root.setCenter(grid);

        grid.setPadding(new Insets(15));

        Label amountLbl = new Label("Amount");
        Label convertedLbl = new Label("Converted");
        grid.add(amountLbl, 0, 2);
        grid.add(convertedLbl, 1, 2);

        amountField = new TextField();
        amountField.setPromptText("Type amount...");

        resultField = new TextField();
        resultField.setEditable(false);
        resultField.setPromptText("Result");

        grid.add(amountField, 0, 3);
        grid.add(resultField, 1, 3);

        convertButton = new Button("Convert");
        GridPane.setColumnSpan(convertButton, 2);
        GridPane.setHalignment(convertButton, HPos.CENTER);
        GridPane.setMargin(convertButton, new Insets(6, 0, 0, 0));
        grid.add(convertButton, 0, 4);


        Scene scene = new Scene(root, 480, 280);

        scene.getStylesheets().add("styles.css");

        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();

        ConverterModel model = new ConverterModel();
        ConverterController controller = new ConverterController(model, this);
        controller.init();
    }

    public TextField getAmountField() {
        return amountField;
    }

    public TextField getResultField() {
        return resultField;
    }

    public ComboBox<Currency> getFromBox() {
        return fromBox;
    }

    public ComboBox<Currency> getToBox() {
        return toBox;
    }

    public Button getConvertButton() {
        return convertButton;
    }

}