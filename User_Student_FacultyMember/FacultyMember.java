

public class FacultyMember extends User {
    // Properties (Instance Variables)
    private String officeLocation;
    private String officeHours;
    private String researchInterests;// array or arrayList ? 

    // Constructor
    public FacultyMember() {
        super();
    }
    
    // Getters
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
