package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.Locale;

public class Currencies {

    public static ArrayList<String> allCurrencies = getAllCurrencies();

    public static ArrayList<String> getAllCurrencies() {
        ArrayList<String> currencies = new ArrayList<String>();
        Locale[] locs = Locale.getAvailableLocales();

        for (Locale loc : locs) {
            try {
                String val = Currency.getInstance(loc).getCurrencyCode();
                if (!currencies.contains(val))
                    currencies.add(val);
            } catch (Exception exc) {
                // Locale not found
            }
            Collections.sort(currencies);
        }

        return currencies;
    }
}
