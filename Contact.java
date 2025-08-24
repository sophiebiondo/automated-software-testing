package main.java;

import java.util.Objects;

public class Contact {
    private final String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String id, String firstName, String lastName, String phone, String address) {
        validateId(id);
        validateFirstName(firstName);
        validateLastName(lastName);
        validatePhone(phone);
        validateAddress(address);

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    // Getters
    public String getId()       { return id; }
    public String getFirstName(){ return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone()    { return phone; }
    public String getAddress()  { return address; }

    // Setters for updatable fields
    public void setFirstName(String firstName) {
        validateFirstName(firstName);
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        validateLastName(lastName);
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    public void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }

    // Validation helpers
    private void validateId(String id) {
        if (id == null || id.length() > 10) {
            throw new IllegalArgumentException("ID must be non-null and at most 10 characters");
        }
    }
    private void validateFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("First name must be non-null and at most 10 characters");
        }
    }
    private void validateLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Last name must be non-null and at most 10 characters");
        }
    }
    private void validatePhone(String phone) {
        if (phone == null || phone.length() != 10 || !phone.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("Phone must be exactly 10 digits");
        }
    }
    private void validateAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Address must be non-null and at most 30 characters");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact c = (Contact) o;
        return id.equals(c.id)
            && firstName.equals(c.firstName)
            && lastName.equals(c.lastName)
            && phone.equals(c.phone)
            && address.equals(c.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phone, address);
    }

    @Override
    public String toString() {
        return String.format(
            "Contact[id=%s, firstName=%s, lastName=%s, phone=%s, address=%s]",
            id, firstName, lastName, phone, address
        );
    }
}
