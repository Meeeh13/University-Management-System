// -------------------- UserRole.java --------------------
public abstract class UserRole {
    protected String name;
    protected String email;
    protected String birthDate;
    protected String address;

    public UserRole(String name, String email, String birthDate, String address) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getBirthDate() { return birthDate; }
    public String getAddress() { return address; }

    public abstract void displayDetails();
}
