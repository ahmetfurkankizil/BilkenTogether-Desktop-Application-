package UserRelated;

public class FacultyMember extends User{
    // Properties (Instance Variables)
    private String officeLocation;
    private String officeHours;
    private String researchInterests;// array or arrayList ?

    public FacultyMember(String name, String email, int id, String gender, String department, String password, String dateOfBirth) {
        setName(name);
        setMail(email);
        setId(id);
        setGender(gender);
        setDepartment(department);
        setPassword(password);
        setDateOfBirth(dateOfBirth);
    }

    // Faculty Member methods
    public String getOfficeLocation() {
        return officeLocation;
    }
    public String getOfficeHours() {
        return officeHours;
    }

    // Setters
    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }
    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }
}
