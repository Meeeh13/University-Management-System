// -------------------- Administrator.java --------------------
public class Administrator extends Person {
    private String adminID;

    public Administrator(String name, String email, String birthDate, String address, String adminID) {
        super(name, email, birthDate, address);
        this.adminID = adminID;
    }

    public String getAdminID() { return adminID; }

    @Override
    public void displayDetails() {
        System.out.println("Admin ID: " + adminID);
        super.displayDetails();
    }
}
