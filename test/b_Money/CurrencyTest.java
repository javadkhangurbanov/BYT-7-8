package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
    Currency SEK, DKK, EUR;
    
    @Before
    public void setUp() throws Exception {
        /* Setup currencies with exchange rates */
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
    }

    @Test
    public void testGetName() {
        assertEquals("SEK", SEK.getName());
        assertEquals("DKK", DKK.getName());
        assertEquals("EUR", EUR.getName());
    }
    
    @Test
    public void testGetRate() {
        assertEquals(0.15, SEK.getRate(), 0.00001);
        assertEquals(0.20, DKK.getRate(), 0.00001);
        assertEquals(1.5, EUR.getRate(), 0.00001);
    }
    
    @Test
    public void testSetRate() {
        SEK.setRate(0.16);
        assertEquals(0.16, SEK.getRate(), 0.00001);
        // Reset the rate after the test
        SEK.setRate(0.15);
    }
    
    @Test
    public void testUniversalValue() {
        // Assuming the amount is already in smallest currency unit (like cents)
        // Therefore, 100 SEK should be equal to 100 * 0.15 in the universal currency
        assertEquals((int)(100 * 0.15), (int)SEK.universalValue(100));
    }
    
    @Test
    public void testValueInThisCurrency() {
        // Convert 100 DKK to SEK. Since 1 DKK = 0.20 universal, 100 DKK = 20 universal
        // 20 universal in SEK = 20 / 0.15 = 133.33 SEK, which when cast to int becomes 133
        assertEquals((int) (100 * 0.20 / 0.15), SEK.valueInThisCurrency(100, DKK).intValue());
        
        // Convert 100 EUR to DKK. Since 1 EUR = 1.5 universal, 100 EUR = 150 universal
        // 150 universal in DKK = 150 / 0.20 = 750 DKK
        assertEquals((int) (100 * 1.5 / 0.20), DKK.valueInThisCurrency(100, EUR).intValue());
    }

}
