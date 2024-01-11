package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
    Currency SEK, DKK, NOK, EUR;
    Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
    
    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
        SEK100 = new Money(10000, SEK);
        EUR10 = new Money(1000, EUR);
        SEK200 = new Money(20000, SEK);
        EUR20 = new Money(2000, EUR);
        SEK0 = new Money(0, SEK);
        EUR0 = new Money(0, EUR);
        SEKn100 = new Money(-10000, SEK);
    }

    @Test
    public void testGetAmount() {
        assertEquals(Integer.valueOf(10000), SEK100.getAmount());
        assertEquals(Integer.valueOf(0), SEK0.getAmount());
    }

    @Test
    public void testGetCurrency() {
        assertEquals(SEK, SEK100.getCurrency());
        assertEquals(EUR, EUR10.getCurrency());
    }

    @Test
    public void testToString() {
        assertEquals("100.00 SEK", SEK100.toString());
        assertEquals("10.00 EUR", EUR10.toString());
        assertEquals("0.00 SEK", SEK0.toString());
        assertEquals("-100.00 SEK", SEKn100.toString());
    }

    @Test
    public void testGlobalValue() {
        assertEquals(Integer.valueOf(1500), SEK100.universalValue()); // 10000 * 0.15
        assertEquals(Integer.valueOf(1500), EUR10.universalValue()); // 1000 * 1.5
    }

    @Test
    public void testEqualsMoney() {
        assertTrue(SEK200.equals(new Money(20000, SEK)));
        assertTrue(SEK100.equals(EUR10));
    }

    @Test
    public void testAdd() {
        Money sum = SEK100.add(EUR10); // 10000 SEK + 1000 EUR
        int expectedSEKAmount = 10000 + (int)(1000 * 1.5 / 0.15);
        assertEquals(Integer.valueOf(expectedSEKAmount), sum.getAmount());
    }

    @Test
    public void testSub() {
        Money difference = SEK200.sub(EUR10); // 20000 SEK - 1000 EUR
        int expectedSEKAmount = 20000 - (int)(1000 * 1.5 / 0.15);
        assertEquals(Integer.valueOf(expectedSEKAmount), difference.getAmount());
    }

    @Test
    public void testIsZero() {
        assertTrue(SEK0.isZero());
        assertFalse(SEK100.isZero());
    }

    @Test
    public void testNegate() {
        assertEquals(Integer.valueOf(-10000), SEK100.negate().getAmount());
        assertEquals(Integer.valueOf(10000), SEKn100.negate().getAmount());
    }

    @Test
    public void testCompareTo() {
        assertFalse(SEK100.compareTo(EUR10) < 0); // SEK100 is less than EUR10
        assertTrue(EUR20.compareTo(EUR10) > 0); // EUR20 is more than EUR10
        assertEquals(0, SEK0.compareTo(EUR0)); // SEK0 is equal to EUR0
    }
}
