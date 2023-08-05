package DatabaseRelated;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        
        SignUpTable l1 = new SignUpTable();
        l1.addSignUpInformation(22203112, "furkan.kizil@ug.bilkent.edu.tr");
        l1.addSignUpInformation(22203113, "mehmet.kilic@ug.bilkent.edu.tr");
        l1.addSignUpInformation(22203110, "ahmet.kaya@ug.bilkent.edu.tr");
        l1.addSignUpInformation(22203112, "furkan.kizil@ug.bilkent.edu.tr");
        l1.addSignUpInformation(22203113, "mehmet.kilic@ug.bilkent.edu.tr");
        l1.addSignUpInformation(22203110, "ahmet.kaya@ug.bilkent.edu.tr");
        l1.addSignUpInformation(22203112, "furkan.kizil@ug.bilkent.edu.tr");
        l1.addSignUpInformation(22203113, "mehmet.kilic@ug.bilkent.edu.tr");
        l1.addSignUpInformation(22203110, "ahmet.kaya@ug.bilkent.edu.tr");

    }
}
