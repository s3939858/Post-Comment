import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class PostTest {

    private Post post;
    private Random rand;

    @BeforeEach
    public void setUp() {
        post = new Post();
        rand = new Random();
        post.postID = 1000000 + rand.nextInt(9000000);
    }

    private String testBody250 = "In the heart of the bustling metropolis, a tiny café stood as an oasis of calm, where the rich aroma of freshly brewed coffee mingled with the soft hum of conversation, creating an inviting atmosphere for those seeking a moment of respite from their hectic lives.";
    private String testTitle275 = "Despite the challenges that arose during the project, the team managed to overcome every obstacle through dedication, innovation, and collaboration, ensuring that the final product not only met but exceeded expectations, setting a new standard for excellence in the industry and inspiring others.";
    private String testBody300 = "Amidst the hustle and bustle of the vibrant city, there existed a hidden gem, a serene garden where the fragrant blossoms and the gentle rustling of leaves provided a peaceful escape, inviting visitors to take leisurely strolls along winding paths, find solace in the beauty of nature, and momentarily forget their worries.";

    // POST TEST CASE 1 DATA 1
    @Test
    public void testPostTitleLengthTrue() {
        post.postTitle = "abcdefghijklm";
        post.postBody = testBody250;
        post.postTags = new String[] { "tag1", "tag2" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        assertTrue(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // POST TEST CASE 1 DATA 2
    @Test
    public void testPostTitleLengthFalse() {
        post.postTitle = "a";
        post.postBody = testBody250;
        post.postTags = new String[] { "tag1", "tag2" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        assertFalse(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // TEST TO ASSERT FALSE IF TITLE IS MORE THAN 250 CHARACTERS
    @Test
    public void testPostTitleLengthFalse2() {
        post.postTitle = testTitle275;
        post.postBody = testBody250;
        post.postTags = new String[] { "tag1", "tag2" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        assertFalse(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // POST TEST CASE 2 DATA 1
    @Test
    public void testPostBodyLengthTrue() {
        post.postTitle = "SuccessfulTitle";
        post.postBody = testBody250;
        post.postTags = new String[] { "success1", "success2" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        assertTrue(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // POST TEST CASE 2 DATA 2
    @Test
    public void testPostBodyLengthFalse() {
        post.postTitle = "ValidTitle";
        post.postBody = "definitely less than 250";
        post.postTags = new String[] { "tag1", "tag2" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        assertFalse(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // TEST TO CHECK FOR SPECIAL CHAR IN FIRST FIVE CHARACTERS
    @Test
    public void testPostTitleSpecialChar() {
        post.postTitle = "Tit!e";
        post.postBody = testBody250;
        post.postTags = new String[] { "tag1", "tag2" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        assertFalse(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // TEST TO CHECK IF TAGS ARRAY CONTAINS LESS THAN 2 TAGS
    @Test
    public void testPostTagsArrayLength1() {
        post.postTitle = "ValidTitle";
        post.postBody = testBody250;
        post.postTags = new String[] { "tag1" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        assertFalse(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // TEST TO CHECK IF TAGS ARRAY CONTAINS MORE THAN 5 CHARACTERS
    @Test
    public void testPostTagsArrayLength2() {
        post.postTitle = "ValidTitle";
        post.postBody = testBody250;
        post.postTags = new String[] { "tag1", "tag2", "tag3", "tag4", "tag5", "tag6" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        assertFalse(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // POST TEST CASE 3 DATA 1
    @Test
    public void testPostTagLengthTrue() {
        post.postTitle = "ValidTitle";
        post.postBody = testBody250;
        post.postTags = new String[] { "ab", "bc" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        assertTrue(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // {PST TEST CASE 3 DATA 2
    @Test
    public void testPostTagLengthFalse1() {
        post.postTitle = "ValidTitle";
        post.postBody = testBody250;
        post.postTags = new String[] { "1", "2" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        assertFalse(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // TEST TO CHECK IF TAG HAS MORE THAN 10 CHARACTERS
    @Test
    public void testPostTagLengthFalse2() {
        post.postTitle = "ValidTitle";
        post.postBody = testBody250;
        post.postTags = new String[] { "123456789011", "2" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        assertFalse(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // TEST TO CHECK IF THE TAG ARRAY HAS A TAG WHICH CONTAINS AN UPPERCASE
    // CHARACTER
    @Test
    public void testPostTagUppercase() {
        post.postTitle = "ValidTitle";
        post.postBody = testBody250;
        post.postTags = new String[] { "Tag1", "tag2" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        assertFalse(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // POST TEST CASE 4 DATA 1
    @Test
    public void testPostTypeEmergencyMismatchFalse1() {
        post.postTitle = "ValidTitle";
        post.postBody = testBody250;
        post.postTags = new String[] { "tag1", "tag2" };
        post.chosenType = 2;
        post.chosenEmergency = 0;
        assertFalse(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // POST TEST CASE 4 DATA 2
    @Test
    public void testPostTypeEmergencyMismatchFalse2() {
        post.postTitle = "ValidTitle";
        post.postBody = testBody250;
        post.postTags = new String[] { "tag1", "tag2" };
        post.chosenType = 2;
        post.chosenEmergency = 1;
        assertFalse(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // POST TEST CASE 5 DATA 1
    @Test
    public void testPostTypeEmergencyMismatchFalse3() {
        post.postTitle = "ValidTitle";
        post.postBody = testBody250;
        post.postTags = new String[] { "tag1", "tag2" };
        post.chosenType = 0;
        post.chosenEmergency = 2;
        assertFalse(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // POST TEST CASE 5 DATA 2
    public void testPostTypeEmergencyMismatchTrue() {
        post.postTitle = "ValidTitle";
        post.postBody = testBody250;
        post.postTags = new String[] { "tag1", "tag2" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        assertTrue(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // POST TEST CASE 6 DATA 1
    @Test
    public void test3TagsFalse() {
        post.postTitle = "ValidTitle";
        post.postBody = testBody250;
        post.postTags = new String[] { "one", "two", "three", "four" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        assertFalse(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // POST TEST CASE 6 DATA 2
    @Test
    public void test3TagsTrue() {
        post.postTitle = "ValidTitle";
        post.postBody = testBody250;
        post.postTags = new String[] { "one", "two", "three" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        assertTrue(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // TEST TO CHECK IF DIFFICULT POST WILL NOT ALLOW LESS THAN 300 CHARS FOR BODY
    @Test
    public void testBody300DifficultFalse() {
        post.postTitle = "ValidTitle";
        post.postBody = testBody250;
        post.postTags = new String[] { "one", "two", "three" };
        post.chosenType = 0;
        post.chosenEmergency = 0;
        assertFalse(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // TEST TO CHECK IF DIFFICULT POST WILL ALLOW BODY OF 300 CHARS
    @Test
    public void testBody300DifficultTrue() {
        post.postTitle = "ValidTitle";
        post.postBody = testBody300;
        post.postTags = new String[] { "one", "two", "three" };
        post.chosenType = 0;
        post.chosenEmergency = 0;
        assertTrue(post.addPost(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars));
    }

    // COMMENT TEST CASE 1 DATA 1
    @Test
    public void count4Words1() {
        assertFalse(post.addComment("Not valid"));
    }

    // COMMENT TEST CASE 1 DATA 2
    @Test
    public void count4Words2() {
        assertFalse(post.addComment("Definitely not valid"));
    }

    // COMMENT TEST CASE 2 DATA 1
    @Test
    public void count10Words1() {
        assertFalse(post.addComment("I tried my best to come up with ten words and it worked"));
    }

    // COMMENT TEST CASE 2 DATA 2
    @Test
    public void count10Words2() {
        assertFalse(post.addComment("I tried my best to come up with ten words and it definitely worked"));
    }

    // COMMENT TEST CASE 3 DATA 1
    @Test
    public void countFourToTen1() {
        assertTrue(post.addComment("This has four words"));
    }

    // COMMENT TEST CASE 3 DATA 2
    @Test
    public void countFourToTen2() {
        assertTrue(post.addComment("This has five words"));
    }

    // COMMENT TEST CASE 4 DATA 1
    @Test
    public void checkUppercase1() {
        assertTrue(post.addComment("This is a valid comment"));
    }

    // COMMENT TEST CASE 4 DATA 2
    @Test
    public void checkUppercase2() {
        assertFalse(post.addComment("this is not a valid comment"));
    }

    // COMMENT TEST CASE 5 DATA 1
    @Test
    public void checkWhitespace1() {
        assertTrue(post.addComment("   This is a valid comment"));
    }

    // COMMENT TEST CASE 5 DATA 2
    @Test
    public void checkWhitespace2() {
        assertTrue(post.addComment(" This is a valid comment"));
    }

    // COMMENT TEST CASE 6 DATA 1
    @Test
    public void checkUppercaseWhitespace1() {
        assertTrue(post.addComment("   This is a valid comment"));
    }

    // COMMENT TEST CASE 6 DATA 2
    @Test
    public void checkUppercaseWhitespace2() {
        assertFalse(post.addComment("   this is a valid comment"));
    }

    // ONLY TEST THAT WRITES TO FILE WITH ALL SUCCESSFUL FIELDS
    @Test
    public void testSuccessfulPost() {
        post.postTitle = "TestSuccessfulPost";
        post.postBody = testBody250;
        post.postTags = new String[] { "success1", "success2" };
        post.chosenType = 2;
        post.chosenEmergency = 2;
        post.writeConfirm(post.postID, post.postTitle, post.postBody, post.postTags, post.postTypes,
                post.postEmergency, post.chosenType, post.chosenEmergency, post.minTagChars, post.maxTagChars);
    }

}
