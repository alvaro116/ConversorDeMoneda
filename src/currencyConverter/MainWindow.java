package currencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.awt.Color.black;

public class MainWindow extends JFrame {
	private JPanel contentPane;
	private JTextField fieldAmount;
	// private ArrayList<Currency> currencies = Currency.init(); // Replaced by constructor injection
	private ArrayList<Currency> currencies;
	private JLabel lblResult;
	private ResourceBundle messages;
	private Locale currentLocale;

	// UI components that need text updates
	private JMenu mnFile, mnHelp, mnConverter, mnLanguage;
	private JMenuItem mntmQuit, mntmComponents, mntmFunctionality, mntmAbout;
	private JMenuItem mntmSpanish, mntmEnglish, mntmFrench;
	private JButton btnAmount, btnConvertedTo, btnConvertTo, btnConvert;
	// fieldAmount is already a field
	// lblResult is already a field

	// public MainWindow() { // Old constructor - comment out or delete
	//	setTitle("Conversor de Moneda");
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setBounds(100, 100, 600, 400); // Tamaño inicial de la ventana
	//	setLocationRelativeTo(null);
	//	setResizable(true); // Permitir redimensionar la ventana
	//
	//	// ... rest of the old constructor code
	// }

	public MainWindow(ArrayList<Currency> initialCurrencies) {
		this.currencies = initialCurrencies; // Set the currencies list

		// Load ResourceBundle
		currentLocale = new Locale("es", "ES"); // Default to Spanish
		messages = ResourceBundle.getBundle("localization.translation", currentLocale);

		setTitle(messages.getString("window.title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400); // Tamaño inicial de la ventana
		setLocationRelativeTo(null);
		setResizable(true); // Permitir redimensionar la ventana

		// Crear barra de menú
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// Menú "Archivo"
		mnFile = new JMenu(messages.getString("menu.file"));
		menuBar.add(mnFile);

		// Elemento de menú "Cerrar"
		mntmQuit = new JMenuItem(messages.getString("menu.file.quit"));
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0); // Cierra la aplicación
			}
		});
		mnFile.add(mntmQuit);

		// Menú "Idioma"
		mnLanguage = new JMenu(messages.getString("menu.language"));
		menuBar.add(mnLanguage);

		mntmSpanish = new JMenuItem(messages.getString("menu.language.spanish"));
		mntmSpanish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentLocale = new Locale("es", "ES");
				messages = ResourceBundle.getBundle("localization.translation", currentLocale);
				updateUITexts();
			}
		});
		mnLanguage.add(mntmSpanish);

		mntmEnglish = new JMenuItem(messages.getString("menu.language.english"));
		mntmEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentLocale = new Locale("en", "US");
				messages = ResourceBundle.getBundle("localization.translation", currentLocale);
				updateUITexts();
			}
		});
		mnLanguage.add(mntmEnglish);

		mntmFrench = new JMenuItem(messages.getString("menu.language.french"));
		mntmFrench.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentLocale = new Locale("fr", "FR");
				messages = ResourceBundle.getBundle("localization.translation", currentLocale);
				updateUITexts();
			}
		});
		mnLanguage.add(mntmFrench);

		// Menú "Ayuda"
		mnHelp = new JMenu(messages.getString("menu.help"));
		menuBar.add(mnHelp);

		// Elemento de menú "Explicación de componentes"
		mntmComponents = new JMenuItem(messages.getString("menu.help.components"));
		mntmComponents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(
						MainWindow.this,
						messages.getString("dialog.components.message"),
						messages.getString("dialog.components.title"),
						JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		mnHelp.add(mntmComponents);

		// Elemento de menú "Acerca de"
		mntmAbout = new JMenuItem(messages.getString("menu.help.about"));
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AboutWindow.getInstance(currentLocale).setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);

		// Menú "Conversor"
		mnConverter = new JMenu(messages.getString("menu.converter"));
		menuBar.add(mnConverter);

		// Elemento de menú "Funcionamiento del programa"
		mntmFunctionality = new JMenuItem(messages.getString("menu.converter.functionality"));
		mntmFunctionality.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(
						MainWindow.this,
						messages.getString("dialog.functionality.message"),
						messages.getString("dialog.functionality.title"),
						JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		mnConverter.add(mntmFunctionality);

		// Componentes de la ventana
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Aumentar el espacio alrededor del contenido
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout()); // Usar GridBagLayout para una interfaz responsiva

		// Configuración de GridBagConstraints
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10); // Aumentar el espaciado entre componentes
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Botón "Ingresar Cantidad"
		btnAmount = new JButton(messages.getString("button.enterAmount"));
		btnAmount.setBackground(new Color(52, 152, 219)); // Azul #3498db
		btnAmount.setForeground(black);
		btnAmount.setFont(new Font("Arial", Font.BOLD, 12));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		contentPane.add(btnAmount, gbc);

		// Campo de texto para la cantidad
		fieldAmount = new JTextField(); // Already a field
		// fieldAmount.setText("0.00"); // Keep default or localize if necessary - not specified for this field text
		fieldAmount.setText("0.00");
		fieldAmount.setFont(new Font("Arial", Font.PLAIN, 12));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0; // Hacer que el campo de texto ocupe el espacio restante
		contentPane.add(fieldAmount, gbc);

		// Botón "Seleccione Moneda"
		btnConvertedTo = new JButton(messages.getString("button.selectCurrency"));
		btnConvertedTo.setBackground(new Color(52, 152, 219)); // Azul #3498db
		btnConvertedTo.setForeground(black);
		btnConvertedTo.setFont(new Font("Arial", Font.BOLD, 12));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; // Reiniciar weightx
		contentPane.add(btnConvertedTo, gbc);

		// ComboBox de la primera moneda
		JComboBox<String> comboBoxCountry1 = new JComboBox<>(); // Not making this a field for now, content doesn't change
		populate(comboBoxCountry1, currencies); // Populates with currency names, not UI strings from bundle
		comboBoxCountry1.setFont(new Font("Arial", Font.PLAIN, 12));
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		contentPane.add(comboBoxCountry1, gbc);

		// Botón "Convertir a"
		btnConvertTo = new JButton(messages.getString("button.convertTo"));
		btnConvertTo.setBackground(new Color(52, 152, 219)); // Azul #3498db
		btnConvertedTo.setForeground(black);
		btnConvertTo.setFont(new Font("Arial", Font.BOLD, 12));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		contentPane.add(btnConvertTo, gbc);

		// ComboBox de la segunda moneda
		JComboBox<String> comboBoxCountry2 = new JComboBox<>(); // Not making this a field for now, content doesn't change
		populate(comboBoxCountry2, currencies); // Populates with currency names, not UI strings from bundle
		comboBoxCountry2.setFont(new Font("Arial", Font.PLAIN, 12));
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		contentPane.add(comboBoxCountry2, gbc);

		// Botón "Aceptar"
		btnConvert = new JButton(messages.getString("button.accept"));
		btnConvert.setBackground(new Color(52, 152, 219)); // Azul #3498db
		btnConvertedTo.setForeground(black);
		btnConvert.setFont(new Font("Arial", Font.BOLD, 14));
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		gbc.weightx = 1.0;
		contentPane.add(btnConvert, gbc);

		// Etiqueta para mostrar el resultado
		lblResult = new JLabel(""); // Result is dynamically set
		lblResult.setFont(new Font("Arial", Font.BOLD, 14));
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 3;
		gbc.weightx = 1.0;
		contentPane.add(lblResult, gbc);

		// Acción del botón "Aceptar"
		btnConvert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nameCurrency1 = comboBoxCountry1.getSelectedItem().toString();
				String nameCurrency2 = comboBoxCountry2.getSelectedItem().toString();
				double amount;

				try {
					amount = Double.parseDouble(fieldAmount.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(MainWindow.this,
							messages.getString("error.invalidAmount.message"),
							messages.getString("error.invalidAmount.title"), JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Double result = convert(nameCurrency1, nameCurrency2, currencies, amount);
					DecimalFormat format = new DecimalFormat("#0.00");
					String formattedResult = format.format(result);
					// Actualizar el texto del JLabel existente
					lblResult.setText(String.format(messages.getString("conversion.result"), amount, nameCurrency1, formattedResult, nameCurrency2));
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(MainWindow.this,
							messages.getString("error.conversionError.message"),
							messages.getString("error.conversionError.title"), JOptionPane.ERROR_MESSAGE);
					lblResult.setText(""); // Limpiar el resultado anterior en caso de error
				}
			}
		});
	}

	private void updateUITexts() {
		setTitle(messages.getString("window.title"));

		mnFile.setText(messages.getString("menu.file"));
		mntmQuit.setText(messages.getString("menu.file.quit"));

		mnLanguage.setText(messages.getString("menu.language"));
		mntmSpanish.setText(messages.getString("menu.language.spanish"));
		mntmEnglish.setText(messages.getString("menu.language.english"));
		mntmFrench.setText(messages.getString("menu.language.french"));

		mnHelp.setText(messages.getString("menu.help"));
		mntmComponents.setText(messages.getString("menu.help.components"));
		mntmAbout.setText(messages.getString("menu.help.about"));

		mnConverter.setText(messages.getString("menu.converter"));
		mntmFunctionality.setText(messages.getString("menu.converter.functionality"));

		btnAmount.setText(messages.getString("button.enterAmount"));
		btnConvertedTo.setText(messages.getString("button.selectCurrency"));
		btnConvertTo.setText(messages.getString("button.convertTo"));
		btnConvert.setText(messages.getString("button.accept"));

		// For JOptionPane dialogs, the titles and messages are fetched when they are shown,
		// so they will use the updated `messages` bundle automatically.
		// lblResult text is dynamic and will use the correct "conversion.result" format string
		// when a conversion is performed.
		// fieldAmount text is user input, so not changing it.
	}

	// Rellenar comboBox con el nombre de las monedas
	public static void populate(JComboBox<String> comboBox, ArrayList<Currency> currencies) {
		// If currency names were localized, this would need to clear and re-populate the JComboBox
		// For now, currency names are not localized.
		if (comboBox.getItemCount() == 0) { // Populate only if empty
			for (Currency currency : currencies) {
				comboBox.addItem(currency.getName());
			}
		}
	}

	// Método para convertir una moneda a otra
	public static Double convert(String currency1, String currency2, ArrayList<Currency> currencies, Double amount) {
		String shortNameCurrency2 = null;
		Double exchangeValue = null;

		// Encontrar el nombre corto de la segunda moneda
		for (Currency currency : currencies) {
			if (currency.getName().equals(currency2)) {
				shortNameCurrency2 = currency.getShortName();
				break;
			}
		}

		// Encontrar el valor de cambio y realizar la conversión
		if (shortNameCurrency2 != null) {
			for (Currency currency : currencies) {
				if (currency.getName().equals(currency1)) {
					exchangeValue = currency.getExchangeValues().get(shortNameCurrency2);
					break;
				}
			}
		}

		if (exchangeValue != null) {
			return Currency.convert(amount, exchangeValue);
		} else {
			throw new IllegalArgumentException("No se encontró el valor de cambio para las monedas seleccionadas.");
		}
	}
}