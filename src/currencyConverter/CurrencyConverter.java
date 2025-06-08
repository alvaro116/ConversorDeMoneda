package currencyConverter;

import java.awt.EventQueue;
import javax.swing.UIManager;

import java.util.ArrayList; // Required for ArrayList

public class CurrencyConverter {
	public static void main(String[] args) {
		// System.out.println("Iniciando programa..."); // Removed
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		ArrayList<Currency> currencyList = Currency.init(); // Create the list
		// System.out.println("Fetching exchange rates from API..."); // Removed
		ExchangeRateService.fetchAndSetExchangeRates(currencyList); // Populate with rates
		// System.out.println("Exchange rates fetched and processed."); // Removed

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// System.out.println("Creando ventana principal..."); // Removed
					MainWindow mainWindow = new MainWindow(currencyList); // Pass the populated list
					mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
