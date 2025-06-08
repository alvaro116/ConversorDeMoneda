package currencyConverter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class CurrencyTest {

    private Currency usd;
    private Currency eur;

    @BeforeEach
    public void setUp() {
        usd = new Currency("Dólar estadounidense", "USD");
        eur = new Currency("Euro", "EUR");
    }

    @Test
    public void testConvert() {
        // Test static convert method
        assertEquals(150.0, Currency.convert(100.0, 1.5), 0.001);
        assertEquals(75.0, Currency.convert(50.0, 1.5), 0.001);
        assertEquals(0.0, Currency.convert(0.0, 1.5), 0.001);
    }

    @Test
    public void testConvert_handlesNullAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Currency.convert(null, 1.5);
        });
        assertEquals("Amount and exchange value must not be null", exception.getMessage());
    }

    @Test
    public void testConvert_handlesNullExchangeValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Currency.convert(100.0, null);
        });
        assertEquals("Amount and exchange value must not be null", exception.getMessage());
    }

    @Test
    public void testSetAndGetExchangeRate() {
        usd.setExchangeRate("EUR", 0.92);
        HashMap<String, Double> rates = usd.getExchangeValues();
        assertNotNull(rates);
        assertEquals(0.92, rates.get("EUR"), 0.001);
    }

    @Test
    public void testSetMultipleExchangeRates() {
        usd.setExchangeRate("EUR", 0.92);
        usd.setExchangeRate("JPY", 150.5);
        HashMap<String, Double> rates = usd.getExchangeValues();
        assertNotNull(rates);
        assertEquals(0.92, rates.get("EUR"), 0.001);
        assertEquals(150.5, rates.get("JPY"), 0.001);
        assertEquals(2, rates.size());
    }

    @Test
    public void testGetShortNameAndName() {
        assertEquals("USD", usd.getShortName());
        assertEquals("Dólar estadounidense", usd.getName());
    }
}
