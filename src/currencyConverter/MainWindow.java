package currencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainWindow extends JFrame {
	private JPanel contentPane;
	private JTextField fieldAmount;
	private ArrayList<Currency> currencies = Currency.init();
	private JLabel lblResult; // Declarar el JLabel como atributo de la clase

	public MainWindow() {
		setTitle("Conversor de Moneda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400); // Tamaño inicial de la ventana
		setLocationRelativeTo(null);
		setResizable(true); // Permitir redimensionar la ventana

		// Crear barra de menú
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// Menú "Archivo"
		JMenu mnFile = new JMenu("Archivo");
		menuBar.add(mnFile);

		// Elemento de menú "Cerrar"
		JMenuItem mntmQuit = new JMenuItem("Cerrar");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0); // Cierra la aplicación
			}
		});
		mnFile.add(mntmQuit);

		// Menú "Ayuda"
		JMenu mnHelp = new JMenu("Ayuda");
		menuBar.add(mnHelp);

		// Elemento de menú "Explicación de componentes"
		JMenuItem mntmComponents = new JMenuItem("Explicación de componentes");
		mntmComponents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(
						MainWindow.this,
						"1. **Cantidad**: Ingrese el monto que desea convertir.\n" +
								"2. **Convertido a**: Seleccione la moneda a la que desea convertir.\n" +
								"3. **Botón Convertir**: Haga clic para realizar la conversión.",
						"Explicación de componentes",
						JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		mnHelp.add(mntmComponents);

		// Menú "Conversor"
		JMenu mnConverter = new JMenu("Conversor");
		menuBar.add(mnConverter);

		// Elemento de menú "Funcionamiento del programa"
		JMenuItem mntmFunctionality = new JMenuItem("Funcionamiento del programa");
		mntmFunctionality.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(
						MainWindow.this,
						"Este programa permite convertir una cantidad de una moneda a otra.\n\n" +
								"1. Seleccione la moneda de origen en el primer menú desplegable.\n" +
								"2. Seleccione la moneda de destino en el segundo menú desplegable.\n" +
								"3. Ingrese la cantidad que desea convertir.\n" +
								"4. Haga clic en el botón 'Convertir' para ver el resultado.\n\n" +
								"El resultado se mostrará debajo del botón.",
						"Funcionamiento del programa",
						JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		mnConverter.add(mntmFunctionality);

		// Componentes de la ventana
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout()); // Usar GridBagLayout para una interfaz responsiva

		// Configuración de GridBagConstraints
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Botón "Cantidad"
		JButton btnAmount = new JButton("Cantidad");
		btnAmount.setBackground(new Color(173, 216, 230)); // Azul claro
		btnAmount.setForeground(Color.BLACK);
		btnAmount.setFont(new Font("Arial", Font.BOLD, 12));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		contentPane.add(btnAmount, gbc);

		// Campo de texto para la cantidad
		fieldAmount = new JTextField();
		fieldAmount.setText("0.00");
		fieldAmount.setFont(new Font("Arial", Font.PLAIN, 12));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0; // Hacer que el campo de texto ocupe el espacio restante
		contentPane.add(fieldAmount, gbc);

		// Botón "Convertido a"
		JButton btnConvertedTo = new JButton("Convertido a");
		btnConvertedTo.setBackground(new Color(173, 216, 230)); // Azul claro
		btnConvertedTo.setForeground(Color.BLACK);
		btnConvertedTo.setFont(new Font("Arial", Font.BOLD, 12));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; // Reiniciar weightx
		contentPane.add(btnConvertedTo, gbc);

		// ComboBox de la primera moneda
		JComboBox<String> comboBoxCountry1 = new JComboBox<>();
		populate(comboBoxCountry1, currencies);
		comboBoxCountry1.setFont(new Font("Arial", Font.PLAIN, 12));
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		contentPane.add(comboBoxCountry1, gbc);

		// ComboBox de la segunda moneda
		JComboBox<String> comboBoxCountry2 = new JComboBox<>();
		populate(comboBoxCountry2, currencies);
		comboBoxCountry2.setFont(new Font("Arial", Font.PLAIN, 12));
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		contentPane.add(comboBoxCountry2, gbc);

		// Botón "Convertir"
		JButton btnConvert = new JButton("Convertir");
		btnConvert.setBackground(new Color(0, 0, 139)); // Azul oscuro
		btnConvert.setForeground(Color.WHITE);
		btnConvert.setFont(new Font("Arial", Font.BOLD, 14));
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		gbc.weightx = 1.0;
		contentPane.add(btnConvert, gbc);

		// Etiqueta para mostrar el resultado
		lblResult = new JLabel("");
		lblResult.setFont(new Font("Arial", Font.BOLD, 14));
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 3;
		gbc.weightx = 1.0;
		contentPane.add(lblResult, gbc);

		// Acción del botón "Convertir"
		btnConvert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nameCurrency1 = comboBoxCountry1.getSelectedItem().toString();
				String nameCurrency2 = comboBoxCountry2.getSelectedItem().toString();
				Double amount = Double.parseDouble(fieldAmount.getText());

				Double result = convert(nameCurrency1, nameCurrency2, currencies, amount);

				DecimalFormat format = new DecimalFormat("#0.00");
				String formattedResult = format.format(result);

				// Actualizar el texto del JLabel existente
				lblResult.setText(amount + " " + nameCurrency1 + " = " + formattedResult + " " + nameCurrency2);
			}
		});
	}

	// Rellenar comboBox con el nombre de las monedas
	public static void populate(JComboBox<String> comboBox, ArrayList<Currency> currencies) {
		for (Currency currency : currencies) {
			comboBox.addItem(currency.getName());
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