package SignupAndLogin;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

import DatabaseRelated.*;
import UserRelated.*;

//Core Class for the SignUp Process
public class SignUpHandler {

    private static final String bilkenTogetherMail = "bilkentogether@gmail.com"; //Server alınca adres değiştirilecek
    private static final String bilkenTogetherPassword = "rkdpbcwtjuvfxlhp"; //Server alınınca değiştirilecek
    private int verificationCode;
    private Random random;

    public SignUpHandler() {
    }

    //This method checks whether the user is a Bilkent University member.
    public boolean checkUserEmail(JTextField emailTextField, JLabel errorLabel) {
        if (emailTextField.getText().equals("")) {
            errorLabel.setText("You should enter your e-mail first! ");
            errorLabel.setForeground(Color.red);
            return false;
        }
        else if(checkWhetherEmailExists(emailTextField.getText())){
            errorLabel.setText("This email already has registered to BilkenTogether! Please login!");
            errorLabel.setForeground(Color.red);
            return false;
        }else {
            if (emailTextField.getText().contains("bilkent.edu.tr") && emailTextField.getText().contains("@")) {
                errorLabel.setText("✅");
                return true;
            } else {
                errorLabel.setText("Not a Bilkent Member!");
                errorLabel.setForeground(Color.red);
                return false;

            }
        }
    }

    //Idea is taken from : https://stackoverflow.com/questions/8336607/how-to-check-if-the-value-is-integer-in-java
    public static boolean integerCheck(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException n) {
            return false;
        }
    }

    public boolean checkUserID(JTextField idTextField, JLabel idErrorMessage) {
        if (!integerCheck(idTextField.getText())) {
            idErrorMessage.setText("This is not a valid ID");
            idErrorMessage.setForeground(Color.red);
            return false;
        } else if (idTextField.getText().equals("")) {
            idErrorMessage.setText("You should enter your ID first! ");
            idErrorMessage.setForeground(Color.red);
            return false;
        } else {
            if (String.valueOf(idTextField.getText()).length() == 8) {
                idErrorMessage.setText("✅");
                return true;
            } else {
                idErrorMessage.setText("ID must be 8 digits!");
                idErrorMessage.setForeground(Color.red);
                return false;
            }
        }
    }

    public boolean checkName(JTextField nameTextField, JLabel nameMessageLabel) {
        if (nameTextField.getText().equals("")) {
            nameMessageLabel.setText("You should enter your name first! ");
            nameMessageLabel.setForeground(Color.red);
            return false;}
        else if(checkWhetherNameExists(nameTextField.getText())){
                nameMessageLabel.setText("This name has already taken! Please chose another one.");
                nameMessageLabel.setForeground(Color.red);
                return false;
        } else {
            nameMessageLabel.setText("✅");
            return true;
        }
    }

    public boolean checkPassword(JPasswordField passwordTextField, JLabel passwordMessageLabel) {
        if (passwordTextField.getText().equals("")) {
            passwordMessageLabel.setText("You should enter your password first! ");
            passwordMessageLabel.setForeground(Color.red);
            return false;
        } else {
            if (String.valueOf(passwordTextField.getText()).length() > 5) {
                passwordMessageLabel.setText("✅");
                return true;
            } else {
                passwordMessageLabel.setText("Chose a stronger password!");
                passwordMessageLabel.setForeground(Color.red);
                return false;
            }
        }
    }

    public boolean checkDateOfBirth(JComboBox day, JComboBox month, JComboBox year, JLabel dateMessage) {
        if (day.getSelectedItem().equals("Day:") || month.getSelectedItem().equals("Month:") || year.getSelectedItem().equals("Year:")) {
            dateMessage.setText("You should enter your date of birth first! ");
            dateMessage.setForeground(Color.red);
            return false;
        } else {
            dateMessage.setText("✅");
            return true;
        }
    }

    public boolean checkGender(JComboBox genderCombobox, JLabel messageLabel) {
        if (genderCombobox.getSelectedItem().equals("Select:")) {
            messageLabel.setText("Please select department!");
            messageLabel.setForeground(Color.red);
            return false;
        } else {
            messageLabel.setText("✅");
            return true;
        }
    }

    public boolean checkDepartment(JComboBox departmentCombobox, JLabel errorMessage) {
        if (departmentCombobox.getSelectedItem().equals("Department:")) {
            errorMessage.setText("Please select department!");
            errorMessage.setForeground(Color.red);
            return false;
        } else {
            errorMessage.setText("✅");
            return true;
        }
    }

    public boolean checkRole(JComboBox roleComboBox, JLabel errorMessage) {
        if (roleComboBox.getSelectedItem().equals("Role:")) {
            errorMessage.setText("Please select role!");
            errorMessage.setForeground(Color.red);
            return false;
        } else {
            errorMessage.setText("✅");
            return true;
        }
    }

    public void addToUltimateTable(User newUser) {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        String insertQuery = "INSERT INTO userInformationTable (role, nameAndSurname, email, id, gender, department, password, dateOfBirth) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            // Set parameter values
            if (newUser instanceof Student) {
                statement.setString(1, "Student");
            } else {
                statement.setString(1, "Faculty Member");
            }
            statement.setString(2, newUser.getName());
            statement.setString(3, newUser.getEmail());
            statement.setInt(4, newUser.getId());
            statement.setString(5, newUser.getGender());
            statement.setString(6, newUser.getDepartment());
            statement.setString(7, newUser.getPassword());
            statement.setString(8, newUser.getDateOfBirth());

            // Execute the statement
            statement.executeUpdate();

            System.out.println("The information are imported successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student createStudent(String name, String email, int id, String gender, String department, String password, String dateOfBirth) {
        Student student = new Student(name, email, id, gender, department, password, dateOfBirth);
        return student;
    }

    public FacultyMember createFacultyMember(String name, String email, int id, String gender, String department, String password, String dateOfBirth) {
        FacultyMember facultyMember = new FacultyMember(name, email, id, gender, department, password, dateOfBirth);
        return facultyMember;
    }

    // The code for the EmailSender is taken from "https://netcorecloud.com/tutorials/send-email-in-java-using-gmail-smtp/"
    public boolean sendVerificationEmail(String toEmail) {
        InternetAddress[] adresses = new InternetAddress[1];
        String[] emailAddresses = new String[1];
        emailAddresses[0] = toEmail;
        InternetAddress[] internetAddresses = new InternetAddress[1];
        this.random = new Random();
        this.verificationCode = random.nextInt(1000, 9999);

        try {
            internetAddresses[0] = new InternetAddress(emailAddresses[0]);
        } catch (AddressException e) {
            System.out.println("Invalid email address: " + emailAddresses[0]);
            e.printStackTrace();
        }
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(bilkenTogetherMail, bilkenTogetherPassword);
            }
        });
        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(bilkenTogetherMail));
            message.setRecipients(Message.RecipientType.TO, internetAddresses);
            message.setSubject("Verification Code");
            message.setText("Verification Code: " + String.valueOf(verificationCode));

            Transport.send(message);
            System.out.println("Email sent successfully!");
            return true;
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
            return false;
        }
    }

    public boolean checkVerificationCode(int verificationCode) {
        if (this.verificationCode == verificationCode) {
            return true;
        } else {
            return false;
        }
    }

    public boolean codeIsInteger(JTextField codeTextField) {
        try {
            int codeEntered = Integer.parseInt(codeTextField.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkVerificationCodeTextField(JTextField enterTextField, JLabel messageLabel) {
        if (!enterTextField.getText().equals("")) {
            if (codeIsInteger(enterTextField)) {
                if (checkVerificationCode(Integer.valueOf(enterTextField.getText()))) {
                    return true;
                } else {
                    messageLabel.setText("The code is wrong, verification fails!");
                    messageLabel.setForeground(Color.red);
                    return false;
                }
            } else {
                messageLabel.setText("The code is not in integer format, verification fails!");
                messageLabel.setForeground(Color.red);
                return false;
            }
        } else {
            messageLabel.setText("The verification code can't be blank'");
            return false;
        }
    }

    public boolean checkWhetherEmailExists(String email) {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        String insertQuery = "SELECT * FROM userInformationTable WHERE email=?;";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, email);

            ResultSet resultSetOfUser = preparedStatement.executeQuery();

            if (resultSetOfUser.next()) {
                return true;

            }
            else{
                return false;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkWhetherNameExists(String name) {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        String insertQuery = "SELECT * FROM userInformationTable WHERE nameAndSurname=?;";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, name);

            ResultSet resultSetOfUser = preparedStatement.executeQuery();

            if (resultSetOfUser.next()) {
                return true;
            }
            else{
                return false;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkUserRegisteredFromDB(String email, String password) {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        String insertQuery = "SELECT * FROM userInformationTable WHERE email=? AND password=?;";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSetOfUser = preparedStatement.executeQuery();

            if (resultSetOfUser.next()) {
                return true;
            }
            else{
                return false;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public User getUserRegisteredFromDB(String email, String password) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        User newUser = null;

        String insertQuery = "SELECT * FROM userInformationTable WHERE email=? AND password=?;";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSetOfUser = preparedStatement.executeQuery();

            if (resultSetOfUser.next()) {

                //Checking the role
                String role = resultSetOfUser.getString("role");
                if ("Student".equals(role)) {
                    //create student
                    newUser = new Student(
                            resultSetOfUser.getString("nameAndSurname"),
                            resultSetOfUser.getString("email"),
                            Integer.parseInt(resultSetOfUser.getString("id")),
                            resultSetOfUser.getString("gender"),
                            resultSetOfUser.getString("department"),
                            resultSetOfUser.getString("password"),
                            resultSetOfUser.getString("dateOfBirth"));
                    //The other attributes must be added with set methods (like profile picture)
                } else {
                    //create facultyMember
                    newUser = new FacultyMember(
                            resultSetOfUser.getString("nameAndSurname"),
                            resultSetOfUser.getString("email"),
                            Integer.parseInt(resultSetOfUser.getString("id")),
                            resultSetOfUser.getString("gender"),
                            resultSetOfUser.getString("department"),
                            resultSetOfUser.getString("password"),
                            resultSetOfUser.getString("dateOfBirth"));
                    //The other attributes must be added with set methods (like profile picture)
                }
            } else {
                return null;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newUser;
    }

    public boolean checkWhetherEmailBlank(JTextField emailTextField, JLabel emailErrorLabel) {
        if (emailTextField.getText().equals("")) {
            emailErrorLabel.setText("Email can't be blank!");
            emailErrorLabel.setForeground(Color.red);
            return false;
        } else {
            emailErrorLabel.setText("");
            return true;
        }
    }

    public boolean checkWhetherPasswordBlank(JPasswordField passwordField, JLabel passwordErrorLabel) {
        String temp = "";
        String password = temp.valueOf(passwordField.getPassword());
        if (password.equals("")) {
            passwordErrorLabel.setText("Password can't be blank!");
            passwordErrorLabel.setForeground(Color.red);
            return false;
        } else {
            passwordErrorLabel.setText("");
            return true;
        }
    }
}
