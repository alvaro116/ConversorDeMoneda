package currencyConverter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AboutWindow extends JFrame {
	private JPanel contentPane;
	private static AboutWindow windowInstance = null;

	private AboutWindow() {
		setTitle("Acerca de");
		setBounds(100, 1000, 1000, 1000);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Conversor de Moneda");
		lblTitle.setFont(new Font("Noto Sans", Font.BOLD, 15));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(65, 12, 219, 33);
		contentPane.add(lblTitle);

		JLabel lblVersion = new JLabel("Versión 1.0");
		lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersion.setBounds(65, 45, 219, 33);
		contentPane.add(lblVersion);

		JLabel lblLicenceMit = new JLabel("");
		lblLicenceMit.setHorizontalAlignment(SwingConstants.CENTER);
		lblLicenceMit.setBounds(65, 77, 219, 33);
		contentPane.add(lblLicenceMit);

		JLabel lblAuthor = new JLabel("");
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthor.setBounds(65, 122, 219, 33);
		contentPane.add(lblAuthor);

		JLabel lblLink = new JLabel("");
		lblLink.setForeground(Color.BLUE);
		lblLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblLink.setHorizontalAlignment(SwingConstants.CENTER);
		lblLink.setBounds(65, 40, 25, 33);
		lblLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(""));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		contentPane.add(lblLink);
	}

	public static AboutWindow getInstance() {
		if (windowInstance == null) {
			windowInstance = new AboutWindow();
		}
		return windowInstance;
	}
}
