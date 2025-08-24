package main.java;

import java.util.Map;
import java.util.HashMap;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    /**
     * Adds a new contact. Contact IDs must be unique.
     * @throws IllegalArgumentException if ID already exists
     */
    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getId())) {
            throw new IllegalArgumentException("Contact with this ID already exists");
        }
        contacts.put(contact.getId(), contact);
    }

    /**
     * Deletes the contact with the given ID.
     * @throws IllegalArgumentException if no contact with that ID exists
     */
    public void deleteContact(String id) {
        if (!contacts.containsKey(id)) {
            throw new IllegalArgumentException("No contact with ID: " + id);
        }
        contacts.remove(id);
    }

    public void updateFirstName(String id, String newFirstName) {
        Contact c = getExistingContact(id);
        c.setFirstName(newFirstName);
    }

    public void updateLastName(String id, String newLastName) {
        Contact c = getExistingContact(id);
        c.setLastName(newLastName);
    }

    public void updatePhone(String id, String newPhone) {
        Contact c = getExistingContact(id);
        c.setPhone(newPhone);
    }

    public void updateAddress(String id, String newAddress) {
        Contact c = getExistingContact(id);
        c.setAddress(newAddress);
    }

    private Contact getExistingContact(String id) {
        Contact c = contacts.get(id);
        if (c == null) {
            throw new IllegalArgumentException("No contact with ID: " + id);
        }
        return c;
    }
}
