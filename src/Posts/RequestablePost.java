package Posts;
import UserRelated.*;
import java.util.*;

public abstract class RequestablePost extends Post {
    private String typeFilter;
    private ArrayList<Student> requestCollection;
    private ArrayList<Student> deniedCollection;
    private ArrayList<Student> agreementCollection;

    public RequestablePost(int postId, User sender, String description, String typeFilter, String dateOfPost) {
        super(postId, sender, description, dateOfPost);
        this.typeFilter = typeFilter;
        /*
         * Date filter LessonPost ve ActivityPost için ayrı ayrı uygulandı
         */
        requestCollection = new ArrayList<Student>();
        deniedCollection = new ArrayList<Student>();
        agreementCollection = new ArrayList<Student>();
        System.out.println();
    }
    public String getTypeFilter() {
        return typeFilter;
    }

    public void setTypeFilter(String typeFilter) {
        this.typeFilter = typeFilter;
    }

    public void addRequest(Student student) {
        if (!isItInRequests(student)) {
            requestCollection.add(student);
        }
        // (This method receives a student object and adds it to the requestCollection.
        // From this
        // collection, the provider of the lesson will be able to see the Students which
        // requested to the
        // specific lesson)
    }

    public void acceptRequest(Student student) {
        if (isItInRequests(student)) {
            agreementCollection.add(student);

        }
        // (This method will first check whether the request is in the requestCollection
        // or not. If it's
        // there, then the passed Student will be added to the agreementCollection.)
    }

    public void denyRequest(Student student) {
        if (isItInRequests(student)) {
            deniedCollection.add(student);
        }
    }

    public boolean isItInRequests(Student student) {
        /*
         * GUI Yazarken de kullanılır diye public yaptım
         */
        return requestCollection.contains(student);
    }
    public void withdrawRequest(Student student){
        if (isItInRequests(student)) {
            requestCollection.remove(student);
        }
    }

    public Student getTheRequestSender() {
        return null;
    }
}
