package currencyConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// Attempt to import org.json
import org.json.JSONObject;
import org.json.JSONException;

public class ExchangeRateService {

    private static final String API_URL = "https://open.er-api.com/v6/latest/USD";

    public static void fetchAndSetExchangeRates(ArrayList<Currency> currencies) {
        String jsonResponse;
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // 5 seconds
            connection.setReadTimeout(5000);    // 5 seconds

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                jsonResponse = content.toString();
            } else {
                // Consider using a proper logger in a real application
                // System.err.println("HTTP GET request failed with response code: " + responseCode);
                // For now, printStackTrace or a more specific error.
                new IOException("HTTP GET request failed with response code: " + responseCode).printStackTrace();
                return;
            }
            connection.disconnect();
        } catch (IOException e) {
            // System.err.println("IOException during HTTP GET request: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        Map<String, Double> usdBasedRates = new HashMap<>();

        try {
            // Attempt to use org.json
            JSONObject jsonObject = new JSONObject(jsonResponse);
            if (jsonObject.has("rates")) {
                JSONObject ratesObject = jsonObject.getJSONObject("rates");
                Iterator<String> keys = ratesObject.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    usdBasedRates.put(key, ratesObject.getDouble(key));
                }
            } else {
                // System.err.println("JSON response does not contain 'rates' object.");
                new JSONException("JSON response does not contain 'rates' object.").printStackTrace();
                return;
            }
        } catch (JSONException e) {
            // System.err.println("JSONException parsing with org.json: " + e.getMessage());
            e.printStackTrace(); // Log the original JSONException
            // Fallback to manual parsing if org.json is not available or fails
            // System.err.println("Falling back to manual JSON parsing."); // Informative, but can be removed
            usdBasedRates = parseRatesManually(jsonResponse, currencies);
            if (usdBasedRates.isEmpty()) {
                 // System.err.println("Manual JSON parsing also failed or returned no rates.");
                 new RuntimeException("Manual JSON parsing also failed or returned no rates.").printStackTrace();
                 return;
            }
        } catch (NoClassDefFoundError e) {
            // This explicitly catches if org.json is not on the classpath
            // System.err.println("org.json library not found. Falling back to manual JSON parsing. Error: " + e.getMessage());
            e.printStackTrace(); // Log the NoClassDefFoundError
            usdBasedRates = parseRatesManually(jsonResponse, currencies);
            if (usdBasedRates.isEmpty()) {
                 // System.err.println("Manual JSON parsing also failed or returned no rates.");
                 new RuntimeException("Manual JSON parsing also failed or returned no rates after NoClassDefFoundError.").printStackTrace();
                 return;
            }
        }


        if (usdBasedRates.isEmpty()) {
            // System.err.println("Could not parse rates from API response.");
            new RuntimeException("Could not parse rates from API response.").printStackTrace();
            return;
        }

        for (Currency baseCurrency : currencies) {
            String baseShortName = baseCurrency.getShortName();
            Double usdToBaseRate = usdBasedRates.get(baseShortName);

            if (usdToBaseRate == null) {
                // System.err.println("Rate not found for " + baseShortName + " in API response (USD based). Skipping direct rates for this base.");
                // This is a common case if the API doesn't support one of our listed currencies.
                // Not necessarily an error, so removing verbose logging.
            }

            for (Currency targetCurrency : currencies) {
                String targetShortName = targetCurrency.getShortName();
                Double usdToTargetRate = usdBasedRates.get(targetShortName);

                if (usdToTargetRate == null) {
                    // System.err.println("Rate not found for " + targetShortName + " in API response (USD based). Skipping this target pair.");
                    // Common case, removing verbose logging.
                    continue;
                }

                double exchangeRate;

                if (baseShortName.equals("USD")) {
                    exchangeRate = usdToTargetRate;
                } else if (usdToBaseRate != null && usdToBaseRate != 0.0) {
                    exchangeRate = usdToTargetRate / usdToBaseRate;
                } else {
                     if (usdToBaseRate == null) {
                        // System.err.println("Skipping rate " + baseShortName + " to " + targetShortName + " because USD base rate for " + baseShortName + " is missing.");
                    } else { // usdToBaseRate is 0.0
                        // System.err.println("Skipping rate " + baseShortName + " to " + targetShortName + " due to zero USD base rate for " + baseShortName + " to prevent division by zero.");
                    }
                    continue;
                }

                exchangeRate = Math.round(exchangeRate * 1_000_000d) / 1_000_000d;
                baseCurrency.setExchangeRate(targetShortName, exchangeRate);
            }
        }
        // System.out.println("Exchange rates populated for " + currencies.size() + " currencies."); // Removed
    }

    // Basic manual JSON parser as a fallback
    private static Map<String, Double> parseRatesManually(String jsonResponse, ArrayList<Currency> relevantCurrencies) {
        Map<String, Double> rates = new HashMap<>();
        try {
            String ratesBlock = null;
            int ratesStartIndex = jsonResponse.indexOf("\"rates\":{");
            if (ratesStartIndex != -1) {
                int ratesEndIndex = jsonResponse.indexOf("}", ratesStartIndex);
                if (ratesEndIndex != -1) {
                    ratesBlock = jsonResponse.substring(ratesStartIndex + "\"rates\":{".length(), ratesEndIndex);
                }
            }

            if (ratesBlock == null) {
                // System.err.println("Manual parsing: Could not find rates block.");
                new RuntimeException("Manual parsing: Could not find rates block in JSON: " + jsonResponse.substring(0, Math.min(jsonResponse.length(), 100)) + "...").printStackTrace();
                return rates;
            }

            String[] pairs = ratesBlock.split(",");
            for (String pair : pairs) {
                String[] keyValue = pair.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].replace("\"", "").trim();
                    // Only parse rates we care about to simplify manual parsing
                    for (Currency c : relevantCurrencies) {
                        if (c.getShortName().equals(key)) {
                            try {
                                double value = Double.parseDouble(keyValue[1].trim());
                                rates.put(key, value);
                                break;
                            } catch (NumberFormatException e) {
                                // System.err.println("Manual parsing: Error parsing rate value for " + key + ": " + keyValue[1]);
                                e.printStackTrace(); // Log the parsing error for the specific rate
                            }
                        }
                    }
                }
            }
             // Ensure USD has a rate of 1.0 if it's in the list, as API is USD based
            boolean usdFound = false;
            for(Currency c : relevantCurrencies) {
                if(c.getShortName().equals("USD")) {
                    usdFound = true;
                    break;
                }
            }
            if(usdFound && !rates.containsKey("USD")){
                 // The API is USD based, so USD should always be 1.0 relative to itself.
                 // If the API doesn't explicitly list USD:1.0 (some might not), add it.
                 // However, open.er-api.com *does* list USD:1.0
                 // This check is more for robustness if API source changes
            }


        } catch (Exception e) { // Catching a broader exception here for any manual parsing issue
            // System.err.println("Exception during manual JSON parsing: " + e.getMessage());
            e.printStackTrace();
            // Return whatever was parsed, or empty if critical error
        }
        return rates;
    }
}
