package SixPointTwo.model;

import SixPointTwo.Currency;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConverterModel {
    private final ObservableList<Currency> currencies = FXCollections.observableArrayList(
            new Currency("USD", "US Dollar", 1.00),
            new Currency("EUR", "Euro",     1.18),
            new Currency("GBP", "Pound",    1.35)
    );

    public ObservableList<Currency> getCurrencies() {
        return currencies;
    }

    public double converter(double amount, Currency from, Currency to){
        if (from == null || to == null) throw new IllegalArgumentException("Pick both currencies");
        return amount * (from.getUsdPerUnit() / to.getUsdPerUnit());
    }
}


