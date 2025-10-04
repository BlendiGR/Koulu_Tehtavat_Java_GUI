package SevenPointTwo.controller;

import SevenPointTwo.data.CurrencyDao;
import SevenPointTwo.entity.Currency;
import SevenPointTwo.model.ConverterModel;
import SevenPointTwo.view.ConverterView;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert;
import java.util.function.UnaryOperator;


public class ConverterController {
    private final ConverterModel model;
    private final ConverterView view;
    private final CurrencyDao dao = new CurrencyDao();

    public ConverterController(ConverterModel model, ConverterView view) {
        this.model = model;
        this.view = view;
    }

    public void init() {
        view.getFromBox().setItems(dao.getCurrencyList());
        view.getToBox().setItems(dao.getCurrencyList());

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getControlNewText();
            return text.matches("[0-9]*([.,][0-9]{0,2})?") ? change : null;
        };

        view.getAmountField().setTextFormatter(new TextFormatter<>(filter));

        view.getConvertButton().setOnAction(action -> onConvert());
    }

    public void onConvert() {
        try {
            String rawNumber = view.getAmountField().getText().replace(",", ".");

            if (rawNumber.isBlank()) { showError("Type an amount."); return; }

            double amount = Double.parseDouble(rawNumber);

            Currency from = view.getFromBox().getValue();
            Currency to = view.getToBox().getValue();

            if (from == null || to == null){
                throw new Exception("Pick both currencies.");
            }

            double fromRate = dao.getCurrency(from.getCode());
            double toRate = dao.getCurrency(to.getCode());



            double result = model.converter(amount, fromRate, toRate);

            view.getResultField().setText(String.valueOf(result));

        } catch (NumberFormatException e){
            showError("Amount must be a number");
        } catch (Exception e){
            showError(e.getMessage());
        }
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

}
