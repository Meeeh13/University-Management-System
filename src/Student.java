// -------------------- Student.java --------------------
public class Student extends Person {
    private static int studentCounter = 1000;
    private int studentID;

    public Student(String name, String email, String birthDate, String address) {
        super(name, email, birthDate, address);
        this.studentID = studentCounter++;
    }

    public Student(int studentID, String name, String email, String birthDate, String address) {
        super(name, email, birthDate, address);
        this.studentID = studentID;
    }

    public int getStudentID() { return studentID; }

    @Override
    public void displayDetails() {
        System.out.println("Student ID: " + studentID);
        super.displayDetails();
    }
}
