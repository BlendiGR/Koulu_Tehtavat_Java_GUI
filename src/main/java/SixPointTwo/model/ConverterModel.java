package SixPointTwo.model;

import SixPointTwo.data.CurrencyDao;
import SixPointTwo.entity.Currency;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConverterModel {

    public double converter(double amount, double from, double to){
        if (from == 0.0 || to == 0.0) throw new IllegalArgumentException("Pick both currencies");
        return amount * (from / to);
    }
}


