package main.java;


import static org.junit.Assert.*;
import org.junit.Test;

public class ContactTest {

    @Test
    public void constructorAndGettersValid() {
        Contact c = new Contact("12345", "Sophie", "Biondo", "0123456789", "36 Reading Ave");
        assertEquals("12345", c.getId());
        assertEquals("Sophie", c.getFirstName());
        assertEquals("Biondo", c.getLastName());
        assertEquals("0123456789", c.getPhone());
        assertEquals("36 Reading Ave", c.getAddress());
    }

    @Test
    public void boundaryValidLengths() {
        // id, firstName, lastName = 1 char
        new Contact("1", "A", "B", "0123456789", "Addr");
        // id, firstName, lastName = 10 chars
        new Contact("ABCDEFGHIJ", "ABCDEFGHIJ", "1234567890",
                    "1234567890",
                    "123456789012345678901234567890"); // 30-char address
    }

    @Test
    public void settersAllowValidUpdates() {
        Contact c = new Contact("1", "A", "B", "0123456789", "Addr");

        c.setFirstName("NewName");
        assertEquals("NewName", c.getFirstName());

        c.setLastName("OtherName");
        assertEquals("OtherName", c.getLastName());

        c.setPhone("9876543210");
        assertEquals("9876543210", c.getPhone());

        c.setAddress("New Address 123");
        assertEquals("New Address 123", c.getAddress());
    }

    @Test
    public void equalsAndHashCodeContract() {
        Contact a = new Contact("1", "A", "B", "0123456789", "Addr");
        Contact b = new Contact("1", "A", "B", "0123456789", "Addr");
        Contact c = new Contact("2", "A", "B", "0123456789", "Addr");

        // Reflexive
        assertTrue(a.equals(a));

        // Symmetric & consistent
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertEquals(a.hashCode(), b.hashCode());

        // Null
        assertFalse(a.equals(null));

    

        // Not equal when any field differs
        assertFalse(a.equals(c));
    }

    @Test
    public void toStringIncludesAllFields() {
        Contact c = new Contact("123", "Foo", "Bar", "0001112223", "Some Addr");
        String s = c.toString();
        assertTrue(s.contains("123"));
        assertTrue(s.contains("Foo"));
        assertTrue(s.contains("Bar"));
        assertTrue(s.contains("0001112223"));
        assertTrue(s.contains("Some Addr"));
    }

    // Existing invalid‐input tests (id, names, phone, address) go here...
    // ...
}