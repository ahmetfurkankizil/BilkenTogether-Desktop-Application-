package UserRelated;

import java.util.ArrayList;
import java.util.Date;

import javax.management.Notification;

import MessagesServer.MessageConnection;
import Posts.*;
import CommentsRelated.*;

/**
 * Abstract User class implements DatabaseHandler interface
 * also parent of Student and FacultyMember classes
 * 
 * @author Ufuk Baran Guler
 * @since 8/2/2023
 * @version v1
 */
public abstract class User  implements DatabaseHandler {
    // Properties (Instance Variables)
    private int ID;
    private Date dateOfBirth;
    private byte[] profilePhoto;
    private String name;
    private String password;
    private String mail;
    private String department;
    private String gender;
    private String biography;
    private ArrayList<String> researchInterests;
    private ArrayList<StudyPost> studyPostCollection;
    private ArrayList<Notification> notificationCollection;
    private ArrayList<MessageConnection> messageCollection;

    // Constructor
    public User() {
        studyPostCollection = new ArrayList<>();
        researchInterests = new ArrayList<>();
        notificationCollection = new ArrayList<>();
        messageCollection = new ArrayList<>();
    }

    // METHODS
    /**
     * Adds researchInterest into researchInterests ArrayList.
     * Will be used by both Student and Faculty Member
     * 
     *   indicates research interest of
     *                          a student or faculty member
     */
    public void addResearchInterest(String researchInterest) {
        researchInterests.add(researchInterest);
    }

    /**
     * This method will be used in both Student and FacultyMember classes.
     * The method receives a StudyPost object and adds it to the studyPostCollection
     * 
     * @param studyPost is the study post that both student and faculty member can
     *                  share
     */
    public void postStudyPost(StudyPost studyPost) {
        studyPostCollection.add(studyPost);
    }

    /**
     * This method will first check if the passed StudyPost exists in the
     * studyPostCollection.
     * If it exists, it will delete the respective notification from the
     * studyPostCollection
     * 
     * @param studyPost is the post that will be deleted in the collection
     */
    public void deleteStudyPost(StudyPost studyPost) {

        // Checking Process
        boolean isExist = false;
        for (StudyPost stuPost : studyPostCollection) {
            if (stuPost.equals(studyPost)) {
                isExist = true;
            }
        }

        if (isExist)
            studyPostCollection.remove(studyPost);
    }

    /**
     * The method receives a Notification object and adds it to the
     * notificationCollection
     * and the Notification Table of the SQL Database
     * 
     * @param notification
     */
    public void addNotification(Notification notification) {
        notificationCollection.add(notification);
        // Cannot done adding it Notification Table of the SQL Database
    }

    /**
     * This method will first check if the passed Notification exists and if it
     * exists,
     * it will delete the respective notification from the NotificationCollection
     * and the Notifications Table of the SQL Database
     * 
     * @param notification
     */
    public void deleteNotification(Notification notification) {
        
        // Checking Process
        boolean isExist = false;
        for (Notification not : notificationCollection) {
            if (not.equals(notification)) {
                isExist = true;
            }
        }

        if (isExist)
            notificationCollection.remove(notification);

        // Cannot do the second part again
    }

    /**
     * This method will receive a Comment and adds it to the respective
     * Post’s commentCollection array and Comment Table in SQL Database
     * 
     * @param post
     * @param comment
     */
    public void commentToPost(Post post, Comment comment) {
        for (StudyPost stuPost : studyPostCollection) {
            if (stuPost.equals(post)) {
                post.getCommentCollection().addCommnet(comment);
            }
        }

        // Cannot do second part;
    }

    /**
     * @see Comment class
     * @param mainComment       // Cannot finished
     * @param comment
     */
    public void commentToComment(Comment mainComment, Comment comment) {
        mainComment.getCommentCollection().addCommnet(comment);
    }

    /**
     * @see Comment class 
     * @param comment
     */
    public void likeComment(Comment comment) {
        comment.incrementLikeCount(this);
    }

    /**
     * listens to notifications constantly until the app is closed
     */
    public void checkNotifications() {
        // Don't know how to do
    }

    //Interface Methods
    /*
     * createStudiesTable(): void
     * 
     * addToStudiesTable(StudyPost): void
     * 
     * addTopicToStudiesTable(StudyPost): void
     * 
     * removeFromStudiesTable(StudyPost) : void
     * 
     * createNotificationsTable() : void
     * 
     * addToNotificationsTable(Notification) : void
     * 
     * deleteFromNotificationsTable(Notification) : void
     * 
     * createMessagesTable()
     * 
     * addToMessagesTable(Message)
     */

    // Getters
    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public String getDepartment() {
        return department;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public String getGender() {
        return gender;
    }

    public String getBiography() {
        return biography;
    }

    public ArrayList<String> getResearchInterests() {
        return researchInterests;
    }

    // Setters
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}