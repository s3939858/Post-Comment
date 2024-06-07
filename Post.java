import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Post {
    protected int postID;
    protected String postTitle;
    protected String postBody;
    protected String[] postTags;
    protected String[] postTypes = { "Very Difficult", "Difficult", "Easy" };
    protected String[] postEmergency = { "Immediately Needed", "Highly Needed", "Ordinary" };
    protected ArrayList<String> postComments = new ArrayList<>();

    // ADDITIONAL FIELDS

    boolean hasTags;
    int minTagChars;
    int maxTagChars;
    int chosenType;
    int chosenEmergency;
    int commentNum;
    int a;
    String validComment = "This is a valid comment";

    // CONSTRUCTOR
    public Post() {
        postID = 0;
        postTitle = "";
        postBody = "";
        minTagChars = 2;
        maxTagChars = 10;
        chosenType = 0;
        chosenEmergency = 0;
        commentNum = 0;
        a = 0;

    }

    // INITIALISES ARRAY TO COUNT NUMBER OF TAGS TO STORE
    public Post(int tagCount) {
        this();
        postTags = new String[tagCount];
    }

    public Post(ArrayList<String> postComments) {
        this.postComments = postComments;
    }

    // MAIN METHOD
    public static void main(String[] args) {
        Post post = new Post();

        Random rand = new Random();
        Scanner scnr = new Scanner(System.in);

        // RANDOM 7 DIGIT ID
        post.postID = 1000000 + rand.nextInt(9000000);

        // SELECTS TITLE
        System.out.println("Please enter title" + "\n");
        post.postTitle = scnr.nextLine();

        // SELECTS BODY
        System.out.println("Please enter content" + "\n");
        post.postBody = scnr.nextLine();

        // SELECTS TAGS
        System.out.println("Please enter your number of tags: ");
        int tagCount = scnr.nextInt();
        scnr.nextLine();

        Post getTagsCount = new Post(tagCount);

        System.out.println(
                "Please enter your tags, if you don't want tags, please simply press enter to skip lines" + "\n");
        for (post.a = 0; post.a < getTagsCount.postTags.length; post.a++) {
            System.out.print("Tag number " + post.a + ": ");
            getTagsCount.postTags[post.a] = scnr.nextLine();
        }

        int difficultyMin = 0;
        int difficultyMax = 2;
        post.chosenType = -1;
        // SELECTS TYPE
        System.out.println("Select your post difficulty; 0 for Very Difficult, 1 for Difficult, 2 for Easy" + "\n");
        while (post.chosenType < difficultyMin || post.chosenType > difficultyMax) {
            System.out.println("Difficulty number (0-2): ");
            while (!scnr.hasNextInt()) {
                System.out.println("Please enter a valid integer (0-2): ");
                scnr.next();
            }
            post.chosenType = scnr.nextInt();
            if (post.chosenType < difficultyMin || post.chosenType > difficultyMax) { // VALIDATES IF USER CHOSE THE
                                                                                      // THREE AVAILABLE OPTIONS
                System.out.println("Please enter a valid integer (0-2): ");
            }
        }

        int urgencyMin = 0;
        int urgencyMax = 2;
        post.chosenEmergency = -1;
        // SELECTS EMERGENCY
        System.out.println("\nSelect your post urgency; 0 for Immediate, 1 for Highly Needed, 2 for Ordinary\n");
        while (post.chosenEmergency < urgencyMin || post.chosenEmergency > urgencyMax) {
            System.out.println("Urgency number (0-2): ");
            while (!scnr.hasNextInt()) {
                System.out.println("Please enter a valid integer (0-2): ");
                scnr.next();
            }
            post.chosenEmergency = scnr.nextInt();
            if (post.chosenEmergency < difficultyMin || post.chosenEmergency > difficultyMax) { // VALIDATES IF USER
                                                                                                // CHOSE THE THREE
                                                                                                // AVAILABLE OPTIONS
                System.out.println("Please enter a valid integer (0-2): ");
            }
        }

        // TRIES TO ADD POST AND EVALUATES CONDITIONS
        post.addPost(post.postID, post.postTitle, post.postBody, getTagsCount.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars);

        // EVALUATES RETURN CONDITION AND ADDS POST
        post.writeConfirm(post.postID, post.postTitle, post.postBody, getTagsCount.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars);

        // TRIES TO ADD COMMENT AND EVALUATES
        post.addComment(post.validComment);

        // EVALUATES RETURN CONDITION AND ADDS COMMENT
        post.writeCommentConfirm(post.chosenEmergency, post.chosenType, post.validComment);

    }

    // POST METHOD
    public boolean addPost(int postID, String postTitle, String postBody, String[] postTags, String[] postTypes,
            String[] postEmergency, int chosenType, int chosenEmergency, int minTagChars, int maxTagChars) {

        String checkUppercase;
        char chUpper;
        char chSpecial;

        if (postTitle.length() < 10 || postTitle.length() > 250) {
            System.out.println("Post condition 1 not met");
            return false;
        }

        for (int d = 0; d < 5; d++) {
            chSpecial = postTitle.charAt(d);

            if (!Character.isLetter(chSpecial)) {
                System.out.println("Post condition 1 not met");
                return false;
            }
        }

        if (postBody.length() < 250) {
            System.out.println("Post condition 2 not met");
            return false;
        }

        if ((chosenType == 0 || chosenType == 1) && postBody.length() < 300) {
            System.out.println("Post condition 4 not met");
            return false;
        }

        if ((postTags.length < 2 || postTags.length > 5)) {
            System.out.println("Post condition 3 not met");
            return false;
        }

        if (chosenType == 2 && (postTags.length < 2 || postTags.length > 3)) {
            System.out.println("Post condition 4 not met");
            return false;
        }

        if ((postTags.length >= 2 && postTags.length <= 5)
                || (chosenType != 2 && postTags.length >= 2 && postTags.length <= 3)) {

            for (String postTag : postTags) {
                if (postTag.length() < minTagChars || postTag.length() > maxTagChars) {
                    System.out.println("Post condition 3 not met");
                    return false;
                }
                checkUppercase = postTag;

                for (int c = 0; c < checkUppercase.length(); c++) {
                    chUpper = checkUppercase.charAt(c);
                    if (Character.isUpperCase(chUpper)) {
                        System.out.println("Post condition 3 not met");
                        return false;
                    }
                }
            }
        }

        if (chosenType == 2 && (chosenEmergency == 0 || chosenEmergency == 1)) {
            System.out.println("Post condition 5 not met");
            return false;
        }

        if ((chosenType == 0 || chosenType == 1) && chosenEmergency == 2) {
            System.out.println("Post condition 5 not met");
            return false;
        }

        System.out.println("Success");
        return true;
    }

    // WRITES TO FILE ONLY DEPENDING ON EVALUATION OF ADDPOST
    public void writeConfirm(int postID, String postTitle, String postBody, String[] postTags,
            String[] postTypes,
            String[] postEmergency, int chosenType, int chosenEmergency, int minTagChars, int maxTagChars) {
        if (addPost(postID, postTitle, postBody, postTags, postTypes, postEmergency, chosenType, chosenEmergency,
                minTagChars, maxTagChars)) {
            writeToFile(postID, postTitle, postBody, postTags, postTypes, postEmergency, chosenType, chosenEmergency);
        } else {
            System.out.println("Post did not meet the required conditions and will not be written to file.");
        }
    }

    // METHOD TO WRITE TO FILE
    public void writeToFile(int postID, String postTitle, String postBody, String[] postTags, String[] postTypes,
            String[] postEmergency, int chosenType, int chosenEmergency) {
        File file = new File("postdetails.txt");

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("ID: " + postID + "\n" + "\n");
            writer.write("Title: " + postTitle + "\n" + "\n");
            writer.write("Body: " + postBody + "\n" + "\n");

            writer.write("Tags :");
            for (int j = 0; j < postTags.length; j++) {
                writer.write(postTags[j] + ", ");
            }
            writer.write("\n" + "\n");

            writer.write("Type: " + postTypes[chosenType] + "\n" + "\n");
            writer.write("Emergency: " + postEmergency[chosenEmergency] + "\n" + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // WRITES TO FILE DEPENDING ON EVALUATION OF ADDCOMMENT
    public void writeCommentConfirm(int chosenEmergency, int chosenType, String validComment) {
        if (addComment(validComment)) {
            writeCommentFile(chosenEmergency, chosenType, validComment);
        } else {
            System.out.println("Comment did not meet the required conditions and will not be written to file.");
        }
    }

    // WRITES TO COMMENT FILE
    public void writeCommentFile(int chosenEmergency, int chosenType, String validComment) {
        int maxComments;
        if (chosenType == 2 || chosenEmergency == 2) {
            maxComments = 3;
        } else {
            maxComments = 5;
        }

        while (postComments.size() < maxComments) {
            postComments.add(validComment);
        }

        try (FileWriter writer = new FileWriter(postID + "comments.txt")) {
            for (int i = 0; i < postComments.size(); i++) {
                writer.write("Comment " + (i + 1) + ": " + postComments.get(i) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ADD COMMENT
    public boolean addComment(String validComment) {

        if (countWords(validComment) < 4 || countWords(validComment) > 10) {
            System.out.println("Comment conditions not met");
            return false;
        }

        if (!isFirstLetterUppercase(validComment)) {
            System.out.println("Comment conditions not met");
            return false;
        }
        return true;
    }

    // COUNT WORDS METHOD
    public static int countWords(String validComment) {

        String[] words = validComment.split("\\s+");

        return words.length;
    }

    // CHECKS FOR UPPERCASE IN FIRST LETTER
    public boolean isFirstLetterUppercase(String validComment) {
        if (validComment == null || validComment.isEmpty()) {
            return false;
        }

        validComment = validComment.trim();

        String[] words = validComment.split("\\s+");

        if (words.length == 0) {
            return false;
        }

        char firstLetter = words[0].charAt(0);

        return Character.isUpperCase(firstLetter);
    }

}
