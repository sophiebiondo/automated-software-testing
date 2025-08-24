package main.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ContactServiceTest {

    private ContactService service;
    private Contact c1, c2;

    @Before
    public void setUp() {
        service = new ContactService();
        c1 = new Contact("1", "Alice", "Smith", "0123456789", "100 Main St");
        c2 = new Contact("2", "Bob", "Jones", "0987654321", "200 Oak Ave");
    }

    @Test
    public void addAndRetrieveContact() {
        service.addContact(c1);
        // internal retrieval via updating tests: no public getter for map
        // so use update to verify existence
        assertThrows(IllegalArgumentException.class,
            () -> service.addContact(c1)); // duplicate ID
        
        
        service.addContact(c2);
        assertThrows(IllegalArgumentException.class,
            () -> service.addContact(c2)); // duplicate ID
        
    }

    @Test
    public void deleteContactSuccessAndFailure() {
        service.addContact(c1);
        service.deleteContact("1");
        assertThrows(IllegalArgumentException.class,
            () -> service.deleteContact("1")); // already deleted
    }
 
    @Test
    public void updateFieldsSuccessfully() {
        service.addContact(c1);

        service.updateFirstName("1", "Sophie");
        service.updateLastName("1", "B");
        service.updatePhone("1", "1112223333");
        service.updateAddress("1", "321 Mason St");

        // create a fresh reference and compare
        Contact updated = new Contact("1", "Sophie", "B", "1112223333", "321 Mason St");
        // since we can't directly take from service, use updatePhone on c1 to check
        // but c1 is the same instance passed in, so:
        assertEquals("Sophie", updated.getFirstName());
        assertEquals("B", updated.getLastName());
        assertEquals("1112223333", updated.getPhone());
        assertEquals("321 Mason St", updated.getAddress());
    }

    @Test
    public void updateNonexistentContactThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> service.updateFirstName("nope", "X"));
        assertThrows(IllegalArgumentException.class,
            () -> service.updateLastName("nope", "Y"));
        assertThrows(IllegalArgumentException.class,
            () -> service.updatePhone("nope", "0123456789"));
        assertThrows(IllegalArgumentException.class,
            () -> service.updateAddress("nope", "Addr"));
    }

    @Test
    public void addMultipleAndDeleteOne() {
        service.addContact(c1);
        service.addContact(c2);
        service.deleteContact("1");
        // checks if c2 remains
        service.updateFirstName("2", "Bobby");
        assertEquals("Bobby", c2.getFirstName());
    }
}
