package currencyConverter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AboutWindow extends JFrame {
	private JPanel contentPane;
	private static AboutWindow windowInstance = null;
	private ResourceBundle messages;
	private static Locale currentWindowLocale = null;

	private AboutWindow(Locale locale) {
		currentWindowLocale = locale; // Store the locale used to create this instance
		messages = ResourceBundle.getBundle("localization.translation", locale);

		setTitle(messages.getString("about.title"));
		setBounds(100, 100, 350, 250); // Adjusted bounds for typical about window
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15)); // Increased padding
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout()); // Using GridBagLayout for better resizing behavior

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);

		JLabel lblTitle = new JLabel(messages.getString("about.lblTitle"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle, gbc);

		JLabel lblVersion = new JLabel(messages.getString("about.lblVersion"));
		lblVersion.setFont(new Font("Arial", Font.PLAIN, 12));
		lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblVersion, gbc);

		JLabel lblLicenceMit = new JLabel(messages.getString("about.lblLicenceMit"));
		lblLicenceMit.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLicenceMit.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLicenceMit, gbc);

		JLabel lblAuthor = new JLabel(messages.getString("about.lblAuthor"));
		lblAuthor.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAuthor, gbc);

		JLabel lblLink = new JLabel(messages.getString("about.lblLink"));
		lblLink.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLink.setForeground(Color.BLUE.darker());
		lblLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblLink.setHorizontalAlignment(SwingConstants.CENTER);
		lblLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					// The actual link URL should probably be a non-localized property or constant
					Desktop.getDesktop().browse(new URI("https://github.com/your-repo")); // Kept as is per instruction
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		contentPane.add(lblLink, gbc);

		// API Attribution Label
		JLabel lblApiAttribution = new JLabel(messages.getString("about.apiAttribution"));
		lblApiAttribution.setFont(new Font("Arial", Font.PLAIN, 10)); // Smaller font for attribution
		lblApiAttribution.setForeground(Color.BLUE.darker());
		lblApiAttribution.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblApiAttribution.setHorizontalAlignment(SwingConstants.CENTER);
		lblApiAttribution.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.exchangerate-api.com"));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		// Add some top margin to separate from the link above
		gbc.insets = new Insets(10, 5, 5, 5);
		contentPane.add(lblApiAttribution, gbc);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, not exit
	}

	public static AboutWindow getInstance(Locale locale) {
		if (windowInstance == null || !locale.equals(currentWindowLocale)) {
			if (windowInstance != null) {
				windowInstance.dispose(); // Close the old instance if locale changes
			}
			windowInstance = new AboutWindow(locale);
			// currentWindowLocale is set in the constructor
		}
		return windowInstance;
	}
}
