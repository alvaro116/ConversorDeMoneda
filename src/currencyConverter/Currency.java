package currencyConverter;

import java.util.ArrayList;
import java.util.HashMap;

public class Currency {
	private String name;
	private String shortName;
	private HashMap<String, Double> exchangeValues = new HashMap<>();

	public Currency(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
	}

	// Método para convertir una cantidad dada usando un valor de cambio
	public static Double convert(Double amount, Double exchangeValue) {
		if (amount == null || exchangeValue == null) {
			throw new IllegalArgumentException("Amount and exchange value must not be null");
		}
		Double price = amount * exchangeValue;
		price = Math.round(price * 100d) / 100d; // Redondear a 2 decimales
		return price;
	}

	public String getName() {
		return this.name;
	}

	public String getShortName() {
		return this.shortName;
	}

	public HashMap<String, Double> getExchangeValues() {
		return this.exchangeValues;
	}

	public void setExchangeRate(String targetCurrencyShortName, Double rate) {
		this.exchangeValues.put(targetCurrencyShortName, rate);
	}

	public static ArrayList<Currency> init() {
		ArrayList<Currency> currencies = new ArrayList<>();
		currencies.add(new Currency("Dólar estadounidense", "USD"));
		currencies.add(new Currency("Euro", "EUR"));
		currencies.add(new Currency("Peso Colombiano", "COP"));
		currencies.add(new Currency("Peso Mexicano", "MXN"));
		currencies.add(new Currency("Libra Esterlina", "GBP"));
		currencies.add(new Currency("Yen Japonés", "JPY"));
		currencies.add(new Currency("Franco Suizo", "CHF"));
		currencies.add(new Currency("Dólar Canadiense", "CAD"));
		currencies.add(new Currency("Dólar Australiano", "AUD"));
		currencies.add(new Currency("Peso Argentino", "ARS"));
		// The loop that called currency.defaultValues() has been removed.
		// Exchange rates will now be set dynamically.
		return currencies;
	}
}