package currencyConverter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class ExchangeRateServiceTest {

    @Test
    public void testFetchAndSetExchangeRates_PopulatesCurrencies() {
        // This is an integration test as it calls the actual API.
        // It assumes network connectivity and API availability.
        ArrayList<Currency> currencies = Currency.init(); // Gets the list of defined currencies

        // Ensure exchange values are initially empty (or non-existent for some pairs)
        // as per the refactoring in previous steps.
        // Currencies are initialized with empty exchangeValues maps.
        if (!currencies.isEmpty()) {
            Currency firstCurrency = currencies.get(0);
            assertTrue(firstCurrency.getExchangeValues().isEmpty(),
                       "Exchange values should be empty before fetching for " + firstCurrency.getShortName());
        } else {
            fail("Currency.init() returned an empty list. Cannot perform test.");
        }


        ExchangeRateService.fetchAndSetExchangeRates(currencies);

        // Check if exchange rates have been populated for some known currency pairs.
        // We can't know the exact rates, but they should exist and be positive.
        boolean ratesPopulatedAtLeastForOneCurrency = false;
        for (Currency baseCurrency : currencies) {
            if (!baseCurrency.getExchangeValues().isEmpty()) {
                ratesPopulatedAtLeastForOneCurrency = true;
                // Check a few rates for plausibility (greater than 0 or 1.0 for self)
                for (String targetCurrencyShortName : baseCurrency.getExchangeValues().keySet()) {
                    Double rate = baseCurrency.getExchangeValues().get(targetCurrencyShortName);
                    assertNotNull(rate, "Rate for " + baseCurrency.getShortName() + " to " + targetCurrencyShortName + " should not be null");

                    if (baseCurrency.getShortName().equals(targetCurrencyShortName)) {
                         assertEquals(1.0, rate, 0.00001, "Rate for " + baseCurrency.getShortName() + " to itself should be 1.0");
                    } else {
                         assertTrue(rate > 0,
                                   "Rate for " + baseCurrency.getShortName() + " to " + targetCurrencyShortName + " should be positive. Found: " + rate);
                    }
                }
            }
        }
        assertTrue(ratesPopulatedAtLeastForOneCurrency, "Exchange rates should be populated for at least one currency after calling fetchAndSetExchangeRates.");

        // Specifically check if USD has rates for EUR (as an example)
        Currency usd = null;
        for(Currency c : currencies) {
            if("USD".equals(c.getShortName())) {
                usd = c;
                break;
            }
        }
        assertNotNull(usd, "USD currency not found in the list.");
        assertFalse(usd.getExchangeValues().isEmpty(), "USD should have its exchange rates populated.");
        assertNotNull(usd.getExchangeValues().get("EUR"), "USD to EUR rate should be populated.");
        assertTrue(usd.getExchangeValues().get("EUR") > 0, "USD to EUR rate should be positive.");
        assertEquals(1.0, usd.getExchangeValues().get("USD"), 0.00001, "USD to USD rate should be 1.0");
    }
}
