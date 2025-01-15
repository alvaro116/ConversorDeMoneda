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

	public void defaultValues() {
		switch (this.name) {
			case "Dólar estadounidense":
				exchangeValues.put("USD", 1.0);
				exchangeValues.put("EUR", 0.93);
				exchangeValues.put("GBP", 0.79);
				exchangeValues.put("JPY", 148.50);
				exchangeValues.put("COP", 3912.0);
				exchangeValues.put("MXN", 17.50);
				exchangeValues.put("CHF", 0.88);
				exchangeValues.put("CAD", 1.36);
				exchangeValues.put("AUD", 1.52);
				exchangeValues.put("ARS", 365.0);
				break;
			case "Euro":
				exchangeValues.put("USD", 1.073);
				exchangeValues.put("EUR", 1.0);
				exchangeValues.put("GBP", 0.85);
				exchangeValues.put("JPY", 159.50);
				exchangeValues.put("COP", 4200.0);
				exchangeValues.put("MXN", 18.80);
				exchangeValues.put("CHF", 0.95);
				exchangeValues.put("CAD", 1.46);
				exchangeValues.put("AUD", 1.63);
				exchangeValues.put("ARS", 392.0);
				break;
			case "Peso Colombiano":
				exchangeValues.put("USD", 0.00026);
				exchangeValues.put("EUR", 0.00024);
				exchangeValues.put("GBP", 0.00020);
				exchangeValues.put("JPY", 0.038);
				exchangeValues.put("COP", 1.0);
				exchangeValues.put("MXN", 0.0045);
				exchangeValues.put("CHF", 0.00023);
				exchangeValues.put("CAD", 0.00035);
				exchangeValues.put("AUD", 0.00039);
				exchangeValues.put("ARS", 0.093);
				break;
			case "Peso Mexicano":
				exchangeValues.put("USD", 0.057);
				exchangeValues.put("EUR", 0.053);
				exchangeValues.put("GBP", 0.045);
				exchangeValues.put("JPY", 8.50);
				exchangeValues.put("COP", 222.0);
				exchangeValues.put("MXN", 1.0);
				exchangeValues.put("CHF", 0.050);
				exchangeValues.put("CAD", 0.077);
				exchangeValues.put("AUD", 0.086);
				exchangeValues.put("ARS", 20.50);
				break;
			case "Libra Esterlina":
				exchangeValues.put("USD", 1.26);
				exchangeValues.put("EUR", 1.17);
				exchangeValues.put("GBP", 1.0);
				exchangeValues.put("JPY", 187.50);
				exchangeValues.put("COP", 4930.0);
				exchangeValues.put("MXN", 22.20);
				exchangeValues.put("CHF", 1.12);
				exchangeValues.put("CAD", 1.71);
				exchangeValues.put("AUD", 1.91);
				exchangeValues.put("ARS", 460.0);
				break;
			case "Yen Japonés":
				exchangeValues.put("USD", 0.0067);
				exchangeValues.put("EUR", 0.0063);
				exchangeValues.put("GBP", 0.0053);
				exchangeValues.put("JPY", 1.0);
				exchangeValues.put("COP", 26.30);
				exchangeValues.put("MXN", 0.12);
				exchangeValues.put("CHF", 0.0060);
				exchangeValues.put("CAD", 0.0091);
				exchangeValues.put("AUD", 0.010);
				exchangeValues.put("ARS", 2.45);
				break;
			case "Franco Suizo":
				exchangeValues.put("USD", 1.13);
				exchangeValues.put("EUR", 1.05);
				exchangeValues.put("GBP", 0.89);
				exchangeValues.put("JPY", 167.50);
				exchangeValues.put("COP", 4400.0);
				exchangeValues.put("MXN", 19.80);
				exchangeValues.put("CHF", 1.0);
				exchangeValues.put("CAD", 1.53);
				exchangeValues.put("AUD", 1.71);
				exchangeValues.put("ARS", 410.0);
				break;
			case "Dólar Canadiense":
				exchangeValues.put("USD", 0.74);
				exchangeValues.put("EUR", 0.69);
				exchangeValues.put("GBP", 0.58);
				exchangeValues.put("JPY", 110.50);
				exchangeValues.put("COP", 2900.0);
				exchangeValues.put("MXN", 13.0);
				exchangeValues.put("CHF", 0.65);
				exchangeValues.put("CAD", 1.0);
				exchangeValues.put("AUD", 1.12);
				exchangeValues.put("ARS", 270.0);
				break;
			case "Dólar Australiano":
				exchangeValues.put("USD", 0.66);
				exchangeValues.put("EUR", 0.61);
				exchangeValues.put("GBP", 0.52);
				exchangeValues.put("JPY", 98.50);
				exchangeValues.put("COP", 2580.0);
				exchangeValues.put("MXN", 11.60);
				exchangeValues.put("CHF", 0.58);
				exchangeValues.put("CAD", 0.89);
				exchangeValues.put("AUD", 1.0);
				exchangeValues.put("ARS", 240.0);
				break;
			case "Peso Argentino":
				exchangeValues.put("USD", 0.0027);
				exchangeValues.put("EUR", 0.0025);
				exchangeValues.put("GBP", 0.0022);
				exchangeValues.put("JPY", 0.41);
				exchangeValues.put("COP", 10.70);
				exchangeValues.put("MXN", 0.049);
				exchangeValues.put("CHF", 0.0024);
				exchangeValues.put("CAD", 0.0037);
				exchangeValues.put("AUD", 0.0042);
				exchangeValues.put("ARS", 1.0);
				break;
		}
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
		for (Currency currency : currencies) {
			currency.defaultValues();
		}
		return currencies;
	}
}