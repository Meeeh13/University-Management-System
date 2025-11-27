// -------------------- Person.java --------------------
public class Person extends UserRole {
    public Person(String name, String email, String birthDate, String address) {
        super(name, email, birthDate, address);
    }

    @Override
    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Birth Date: " + birthDate);
        System.out.println("Address: " + address);
    }
}
