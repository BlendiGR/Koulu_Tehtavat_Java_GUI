// CurrencyDao.java
package SixPointTwo.data;

import SixPointTwo.entity.Currency;
import SixPointTwo.datasource.MariaDbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.NoSuchElementException;

public class CurrencyDao {

    public double getCurrency(String abbreviation) {
        final String sql = "SELECT conversion_rate FROM currency WHERE abbreviation = ?";

        try (Connection conn = MariaDbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, abbreviation);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("conversion_rate");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new NoSuchElementException("No currency with abbreviation: " + abbreviation);
    }

    // Get all currencies
    public ObservableList<Currency> getCurrencyList() {
        final String sql = "SELECT abbreviation, name, conversion_rate FROM currency";
        ObservableList<Currency> currencies = FXCollections.observableArrayList();

        try (Connection conn = MariaDbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String abbr = rs.getString("abbreviation");
                String name = rs.getString("name");
                double rate = rs.getDouble("conversion_rate");
                currencies.add(new Currency(abbr, name, rate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return currencies;
    }
}
