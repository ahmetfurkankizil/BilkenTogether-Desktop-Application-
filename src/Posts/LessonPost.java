package Posts;
import UserRelated.*;

import java.util.ArrayList;

public class LessonPost extends RequestablePost {
    private int dateBinaryBoolean;
    private boolean requestType;
    private ArrayList<String> lessonPost;

    public LessonPost(int postID , User sender, String description, String typeFilter, int dateBinaryBoolean, boolean requestType, String dateOfPost) {

        super(postID,sender, description, typeFilter, dateOfPost);
        this.requestType = requestType;
        setDateBinaryBoolean(dateBinaryBoolean);
        ((Student) sender).addLessonPost(this);
    }
    public ArrayList<String> getLessonPost(){
        return lessonPost;
    }
    public int getDateBinaryBoolean() {
        return dateBinaryBoolean;
    }

    public void setDateBinaryBoolean(int dateBinaryBoolean) {
        if (dateBinaryBoolean > 0 && dateBinaryBoolean < 10000000 && isBinary(dateBinaryBoolean)) {
            this.dateBinaryBoolean = dateBinaryBoolean;
        }
    }

    public static boolean isBinary(int requestGiveBinaryBoolean) {
        String stringVersion = Integer.toString(requestGiveBinaryBoolean);
        for (int i = 0; i < stringVersion.length(); i++) {
            if (Integer.parseInt(stringVersion.charAt(i) + "") > 1) {
                return false;
            }
        }
        return true;
    }

    public boolean[] getDaysAvailable() {
        boolean[] returned = new boolean[7];
        String stringV = booleanBinaryToString(dateBinaryBoolean);
        for (int i = 0; i < 7; i++) {
            returned[i] = Integer.parseInt(stringV.charAt(i) + "") == 1 ? true : false;
        }
        return returned;
    }

    public String booleanBinaryToString(int a) {
        String ints = a + "";
        while (ints.length() < 7) {
            ints = "0" + ints;
        }
        return ints;
    }
    public Student getSender(){
        return (Student) super.getSender();
    }
    public boolean getRequestType(){
        return requestType;
    }
}
