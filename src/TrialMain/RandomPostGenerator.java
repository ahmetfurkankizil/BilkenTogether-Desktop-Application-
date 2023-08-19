package TrialMain;

import Posts.ActivityPost;
import Posts.LessonPost;
import Posts.StudyPost;
import UserRelated.Student;
import UserRelated.User;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPostGenerator {
    private User user;
    private Student studentUser;
    private Random random;
    public static final String[] postDescriptions ={
        " General Description ",
            " General Description "
    };
    public static final String[] postTopics ={
            "Anthropology",
            "Archery",
            "Astronomy",
            "Ballet",
            "Basketball",
            "Biology",
            "Boxing",
            "Chemistry",
            "Computer Science",
            "Dance",
            "Design",
            "Drawing",
            "Economics",
            "English",
            "Football",
            "French",
            "Geography",
            "German",
            "Golf",
            "Gymnastics",
            "History",
            "Italian",
            "Law",
            "Linguistics",
            "Literature",
            "Mаrkeтing",
            "Martial Arts",
            "Mathematics",
            "Music",
            "Philosophy",
            "Photography",
            "Physics",
            "Pottery",
            "Psychology",
            "Sculpture",
            "Soccer",
            "Sociology",
            "Spanish",
            "Swimming",
            "Tennis",
            "Theater",
            "Turkish",
            "Yoga"
    };
    private String[] activitiesFilters = {
            "Art Gallery",
            "Basketball",
            "Camping",
            "Cinema",
            "Concert",
            "Cooking",
            "Dancing",
            "Festival",
            "Home Party",
            "Night Club",
            "Photography",
            "Playing Instrument",
            "Pilates",
            "Picnic",
            "Reading",
            "Soccer",
            "Tennis",
            "Volleyball",
            "Yoga"
    };
    ArrayList<String> postingFilters;
    public RandomPostGenerator(User user){
        this.user = user;
        random = new Random();
        initializeTopicArraylist();
        if (user instanceof Student student)
            studentUser = student;
    }
    public LessonPost generateRandomLessonPost(){
        String topic = postTopics[random.nextInt(postTopics.length)];
        return new LessonPost(0,studentUser,generateRandomDescription() + "  "+ topic,topic,generateRandomBinaryBoolean(),random.nextBoolean(),new Date().toString(),false);
    }
    public StudyPost generateRandomStudyPost(){
        String[] topics = generateRandomCollection();
        String topic = topics[0];
        return new StudyPost(0,user,"Author",generateRandomDescription() + "  " + topic,generateRandomDescription(),shouldItHaveFile(),new Date().toString(),topics,false);
    }
    public ActivityPost generateRandomActivityPost(){
        String topic = selectRandomActivityTypeFilter();
        return new ActivityPost(0,studentUser,generateRandomDescription() + "  " +topic,random.nextInt(1,16),new Date().toString(),topic,getRandomDate(),false);
    }
    public String generateRandomDescription(){
        return postDescriptions[random.nextInt(postDescriptions.length)];
    }

    public  int generateRandomBinaryBoolean(){
        return Integer.parseInt(random.nextInt(2) +""+ random.nextInt(2) +random.nextInt(2) +random.nextInt(2) +random.nextInt(2) +random.nextInt(2) +random.nextInt(2) );
    }
    public String[] generateRandomCollection(){
        ArrayList<String> randomCollection = new ArrayList<>();
        int length = postingFilters.size();
        randomCollection.add(postingFilters.get(random.nextInt(length)));
        for (int i = 0; i < 4; i++) {
            if (random.nextBoolean())
                randomCollection.add(postingFilters.get(random.nextInt(length)));
        }
        String[] generated = new String[randomCollection.size()];
        for (int i = 0; i < generated.length; i++) {
            generated[i] = randomCollection.get(i);
        }
        return generated;
    }
    private void initializeTopicArraylist() {
        postingFilters = new ArrayList<>();
        File file = new File("src/Other/topics.txt");

        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (in.hasNextLine()){
            String temp = in.nextLine();
            postingFilters.add(temp);
        }
        Collections.sort(postingFilters);
    }
    public byte[] shouldItHaveFile(){
        File file = new File("src/Other/DetailedDesign.pdf");
        if (random.nextBoolean()){
            byte[] bytes = new byte[(int) file.length()];
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            int read;
            while(true) {
                try {
                    if (!((read = fis.read(bytes)) != -1)) break;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                os.write(bytes, 0, read);
            }
            try {
                fis.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                os.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return bytes;
        }

        return null;
    }
    public String selectRandomActivityTypeFilter(){
        return activitiesFilters[random.nextInt(activitiesFilters.length)];
    }
    public static String getRandomDate() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();

        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);

        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return randomDate.format(formatter);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getRandomDate());
        }
    }
}
