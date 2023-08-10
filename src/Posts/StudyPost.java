package Posts;
import UserRelated.*;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class StudyPost extends Post {
    

    private String author;
    private String studyPostHeading;
    private String[] topicCollection;
    private int numOfTopics;
    private String dateOfPost;
    private byte[] pdfFile;
    public StudyPost(int postId, User sender, String author, String heading, String description, byte[] studyFile, String dateOfPost, String[] topicCollection) {
        super(postId, sender, description, dateOfPost);
        this.author = author;
        this.studyPostHeading = heading;
        this.topicCollection = topicCollection;
        this.pdfFile = studyFile;
        sender.addStudyPost(this);
    }

    public byte[] getStudyFile() {
        return this.pdfFile;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStudyPostHeading() {
        return studyPostHeading;
    }

    public void setStudyPostHeading(String studyPostHeading) {
        this.studyPostHeading = studyPostHeading;
    }

    public void addTopic(String topic) {
        if (numOfTopics != topicCollection.length) {
            topicCollection[numOfTopics] = topic;
        }
        /*
         * This method first checks if there is an empty (null) space in topicCollection
         * and if there is a
         * space, adds the String (topic) to the topicCollection.
         */
    }

    

  public String getTopicByIndex(int index) {
    if (index >-1 && index < numOfTopics){
        return topicCollection[index];
        /*
         * This method will check if the index is less than or equal to 5 first. 
         * Then it returns the
         * specified String (topic) from the topicCollection Array 
         * with the use of the passed integer
         * index.
         */         

    }
    return null;
  }


    public String[] getTopicCollection() {
        return this.topicCollection;
    }


    private int numOfTopics() {
        for (int i = 0; i < topicCollection.length; i++) {
            if (topicCollection[i] == null) {
                return i;
            }
        }
        return topicCollection.length;
    }



    public boolean matchesFilter(String filter) {
        for (String topic : topicCollection)
        {
            if (topic != null && topic.equalsIgnoreCase(filter))
            {
                return true;
            }
        }
        return false;
    }
}
