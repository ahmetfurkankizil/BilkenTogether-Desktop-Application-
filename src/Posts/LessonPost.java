package Posts;
import UserRelated.*;
public class LessonPost extends RequestablePost {
    private int requestGiveBinaryBoolean;
    private boolean requestType;

    public LessonPost(int postID ,User sender, String description, String typeFilter, int requestGiveBinaryBoolean, boolean requestType, String dateOfPost) {

        super(postID,sender, description, typeFilter, dateOfPost);
        this.requestType = requestType;
        setRequestGiveBinaryBoolean(requestGiveBinaryBoolean);
        System.out.println();
    }

    public int getRequestGiveBinaryBoolean() {
        return requestGiveBinaryBoolean;
    }

    public void setRequestGiveBinaryBoolean(int requestGiveBinaryBoolean) {
        if (requestGiveBinaryBoolean > 0 && requestGiveBinaryBoolean < 10000000 && isBinary(requestGiveBinaryBoolean)) {
            this.requestGiveBinaryBoolean = requestGiveBinaryBoolean;
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
        String stringV = booleanBinaryToString(requestGiveBinaryBoolean);
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
