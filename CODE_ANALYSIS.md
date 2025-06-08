# Code Analysis

## Project Structure

The project is a Java-based currency converter application. The core functionality is encapsulated in three main Java files:

- `CurrencyConverter.java`: Serves as the main entry point for the application.
- `MainWindow.java`: Defines the graphical user interface (GUI) and handles user interactions.
- `Currency.java`: Represents currency data and manages exchange rates.

## Key Java Files Functionality

### `CurrencyConverter.java`

This file likely contains the `main` method, which is the starting point of the Java application. Its primary responsibility is to initialize and launch the application, potentially by creating an instance of `MainWindow`.

### `MainWindow.java`

This file is responsible for the presentation layer of the application. It defines the layout and components of the GUI, such as input fields for amounts, dropdown menus for selecting currencies, and display areas for the converted results. It also handles user interactions, like button clicks, and orchestrates the currency conversion process by interacting with the `Currency` class.

### `Currency.java`

This file defines the data model for currencies. It likely includes attributes such as currency codes (e.g., USD, EUR, JPY) and their corresponding exchange rates. It may also contain methods for fetching and updating exchange rates, although the provided context suggests that the rates are currently hardcoded. This class is used by `MainWindow` to perform the actual currency conversion calculations.

## Potential Areas for Improvement

### Hardcoded Exchange Rates
The application currently uses exchange rates that are hardcoded within the `Currency.java` file. This means the rates are static and do not reflect real-time values. A significant improvement would be to integrate an external API to fetch up-to-date exchange rates.

### Error Handling
The error handling in `MainWindow.java`, particularly when parsing the amount field and during the conversion process, could be made more robust. For instance, provide user-friendly messages for invalid input (e.g., non-numeric text in the amount field) or if a conversion rate is not available.

### Localization Implementation
The project includes localization files (`.properties`) but their usage within the main application logic (e.g., `MainWindow.java`) is not immediately apparent from the initial code review. Further investigation is needed to confirm if localization is fully implemented and how UI elements are translated.

### Localization Status
- The `localization/` directory contains `.properties` files for different languages (e.g., `translation_en.properties`, `translation_fr.properties`).
- However, the UI strings in `MainWindow.java` (and likely other UI components) are currently hardcoded in Spanish.
- There is no apparent code in `MainWindow.java` or `CurrencyConverter.java` that loads or utilizes these resource bundles.
- Conclusion: The localization feature appears to be present in terms of resource files but is not currently implemented or used by the application.

### Unit Testing
There are no apparent unit tests in the project. Adding unit tests for the conversion logic in `Currency.java` and for UI interactions in `MainWindow.java` would improve code reliability and maintainability.

## Auxiliary Components

### `JTextFieldLimit.java`
This class is a utility for limiting the number of characters that can be entered into a `JTextField` component. It extends `PlainDocument` and overrides the `insertString` method to enforce the character limit. Additionally, it provides an option to convert all input to uppercase.

### `AboutWindow.java`
This class defines a simple "About" window for the application. It displays basic information such as the application's name and version. The design follows a singleton pattern, ensuring only one instance of the About window can exist. It includes placeholders for license and author information and features a clickable hyperlink, presumably to the project's website or a relevant resource.
