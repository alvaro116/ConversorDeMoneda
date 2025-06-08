package currencyConverter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

public class MainWindowTest {

    private MainWindow mainWindow;
    private ArrayList<Currency> testCurrencies;

    @BeforeEach
    public void setUp() throws InvocationTargetException, InterruptedException {
        testCurrencies = Currency.init(); // Use the same init as the app
        // For UI tests, it's crucial to create and manipulate Swing components on the EDT
        SwingUtilities.invokeAndWait(() -> {
            mainWindow = new MainWindow(testCurrencies);
            mainWindow.setVisible(false); // Keep it invisible during tests
        });
    }

    @AfterEach
    public void tearDown() throws InvocationTargetException, InterruptedException {
        if (mainWindow != null) {
            SwingUtilities.invokeAndWait(() -> {
                mainWindow.dispose();
            });
        }
    }

    @Test
    public void testMainWindowComponentsNotNull() {
        assertNotNull(mainWindow, "MainWindow instance should not be null.");

        // Accessing components needs to be done carefully, often via reflection if they are private,
        // or by making them package-private/protected or adding getters for testing.
        // For this test, let's assume we can access them if they are package-private or via getters.
        // If they are private, this test would need the source code of MainWindow to be modified
        // or use more complex reflection. Let's write the test assuming they are accessible.

        // To access private fields, we would use reflection like:
        // JTextField fieldAmount = (JTextField) getField(mainWindow, "fieldAmount");
        // For now, let's assume a helper method 'getField' or that fields are made accessible.
        // If direct access is not possible, the subtask should note this.
        // For simplicity, the subtask may need to modify MainWindow to make fields package-private for tests.

        assertNotNull(getPrivateField(mainWindow, "fieldAmount"), "Amount field should not be null.");
        // comboBoxCountry1 and comboBoxCountry2 are local variables in constructor, not fields.
        // This part of the test will fail unless they are made fields.
        // For now, we will comment them out as per the current MainWindow structure.
        // assertNotNull(getPrivateField(mainWindow, "comboBoxCountry1"), "ComboBox Country 1 should not be null.");
        // assertNotNull(getPrivateField(mainWindow, "comboBoxCountry2"), "ComboBox Country 2 should not be null.");
        assertNotNull(getPrivateField(mainWindow, "btnConvert"), "Convert button should not be null.");
        assertNotNull(getPrivateField(mainWindow, "lblResult"), "Result label should not be null.");

        JMenuBar menuBar = mainWindow.getJMenuBar();
        assertNotNull(menuBar, "Menu bar should not be null.");

        boolean fileMenuFound = false;
        boolean helpMenuFound = false;
        boolean languageMenuFound = false;

        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            // The menu names are now fetched from resource bundle.
            // We can check against known keys or just check for presence of 3 top-level menus.
            // For this basic test, let's assume there are specific menus we expect.
            // This part is tricky without knowing the exact keys used or having access to the ResourceBundle in test.
            // Let's assume the fields mnFile, mnHelp, mnLanguage exist and are accessible.
            if (menu == getPrivateField(mainWindow, "mnFile")) fileMenuFound = true;
            if (menu == getPrivateField(mainWindow, "mnHelp")) helpMenuFound = true;
            if (menu == getPrivateField(mainWindow, "mnLanguage")) languageMenuFound = true;
        }

        // A simpler check if direct field comparison is hard:
        // Given the structure, we expect File, Language, Help, Converter menus.
        // The original test assumed 3, but there are 4 top-level menus now.
        assertTrue(menuBar.getMenuCount() >= 4,
            "Should have at least File, Language, Help, and Converter menus. Found: " + menuBar.getMenuCount());

        // More robust check based on fields:
        assertTrue(fileMenuFound, "File menu (mnFile) was not found in the menu bar.");
        assertTrue(helpMenuFound, "Help menu (mnHelp) was not found in the menu bar.");
        assertTrue(languageMenuFound, "Language menu (mnLanguage) was not found in the menu bar.");
        assertNotNull(getPrivateField(mainWindow, "mnConverter"), "Converter menu (mnConverter) should not be null and be in menubar.");

    }

    // Helper method to access private fields for testing - this is common in Swing testing.
    // Add this helper method to MainWindowTest.java
    @SuppressWarnings("unchecked")
    private <T> T getPrivateField(Object obj, String fieldName) {
        try {
            java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // It's better to print the stack trace for debugging in tests
            e.printStackTrace();
            fail("Failed to access private field '" + fieldName + "' on class " + obj.getClass().getName() + ": " + e.getMessage());
            return null; // Should not reach here due to fail()
        }
    }
}
