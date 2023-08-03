package Posts;
import UserRelated.*;
public class LessonPost extends RequestablePost {
    private int requestGiveBinaryBoolean;

    public LessonPost(User sender, String description, String typeFilter, int requestGiveBinaryBoolean) {

        super(sender, description, typeFilter);
        setRequestGiveBinaryBoolean(requestGiveBinaryBoolean);
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

    private String booleanBinaryToString(int a) {
        String ints = a + "";
        while (ints.length() < 7) {
            ints = "0" + ints;
        }
        System.out.println(ints);
        return ints;
    }
}
