// -------------------- Faculty.java --------------------
public class Faculty extends Person {
    private String facultyID;

    public Faculty(String name, String email, String birthDate, String address, String facultyID) {
        super(name, email, birthDate, address);
        this.facultyID = facultyID;
    }

    public String getFacultyID() { return facultyID; }

    @Override
    public void displayDetails() {
        System.out.println("Faculty ID: " + facultyID);
        super.displayDetails();
    }
}
