package UserRelated;

import java.util.ArrayList;

public class FacultyMember extends User{
    // Properties (Instance Variables)
    private String officeLocation;
    private String officeHours;

    public FacultyMember(String name, String email, int id, String gender, String department, String password, String dateOfBirth, byte[] profilePhoto, byte[] backGroundPhoto ,boolean isItNew) {
        super(name, email, id, gender, department, password, dateOfBirth, profilePhoto, backGroundPhoto,isItNew);
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
