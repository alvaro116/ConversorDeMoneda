package currencyConverter;

import java.awt.EventQueue;
import javax.swing.UIManager;

public class CurrencyConverter {
	public static void main(String[] args) {
		System.out.println("Iniciando programa..."); // Mensaje de depuración
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("Creando ventana principal..."); // Mensaje de depuración
					MainWindow mainWindow = new MainWindow();
					mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
