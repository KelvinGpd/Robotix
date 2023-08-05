import data.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    private Controller controller;
    private JsonHandler jsonHandler;
    @BeforeEach
    public void setUp() {
        //Initialisations
        controller = new Controller();
        jsonHandler = new JsonHandler();

        //On met plein de test users
        Component[] components = { new Component("HyperRollers", "wheels",
                "Légère et élégante, notre roue en alliage améliore les performances et l'efficacité de votre véhicule.",
                199),
                new Component("AMD 7600X", "cpu",
                        "Puissance de traitement exceptionnelle, permettant des opérations rapides et fluides.", 350) };
        controller.add(new Seller("Company 1", "1 AB street", "company1@email.com", "111-111-1111", components, "qwerty"));

        Component[] components2 = { new Component("RapidLift 2.0", "propeller",
                "Efficacité maximale et une propulsion optimale, garantissant une vitesse et une maniabilité exceptionnelles.",
                95),
                new Component("Energizer", "batteries", "Une batterie longue durée avec une autonomie exceptionnelle",
                        45),
                new Component("Shockwave V", "speakers",
                        "Haut-parleur sans fil qui offre une connectivité sans encombrement", 89) };
        controller.add(new Seller("Company 2", "2 AB street", "company2@email.com", "211-111-1111", components2, "qwerty"));
        Component[] components3 = { new Component("Ultragrip 4", "arms",
                "bras robotique polyvalent conçu pour s'adapter à une large gamme d'applications industrielles", 60),
                new Component("VisonLens HD", "camera", "Caméra HD offrant une résolution haute définition", 210) };
        controller.add(new Seller("Company 3", "3 AB street", "company3@email.com", "311-111-1111", components3,
                "qwerty"));
        Component[] components4 = {
                new Component("ImageColor", "screen",
                        "Une qualité d'image exceptionnelle avec une netteté et des détails époustouflants", 99),
                new Component("RealSound 1665-E", "speaker", "Haut-parleur compact et facilement transportable.", 70) };
        controller.add(new Seller("Company 4", "4 AB street", "company4@email.com", "411-111-1111", components4,
                "qwerty"));
        Component[] components5 = {
                new Component("OmniCapture 3", "micro",
                        "une qualité audio exceptionnelle, capturant des sons clairs et détaillés", 50),
                new Component("Intel i7 8th Gen", "cpu", "CPU conçu pour une efficacité énergétique optimale.", 199) };
        controller.add(new Seller("Company 5", "5 AB street", "company5@email.com", "511-111-1111", components5, "qwerty"));

        controller.add(new User("randomUser1@email.com", "randomUser1", "111-111-1111", "qwerty"));
        controller.add(new User("randomUser2@email.com", "randomUser2", "121-111-1111", "qwerty"));
        controller.add(new User("randomUser3@email.com", "randomUser3", "131-111-1111", "qwerty"));
        controller.add(new User("randomUser4@email.com", "randomUser4", "141-111-1111", "qwerty"));
        controller.add(new User("randomUser5@email.com", "randomUser5", "151-111-1111", "qwerty"));
        controller.add(new User("randomUser6@email.com", "randomUser6", "161-111-1111", "qwerty"));
        controller.add(new User("randomUser7@email.com", "randomUser7", "171-111-1111", "qwerty"));
        controller.add(new User("randomUser8@email.com", "randomUser8", "181-111-1111", "qwerty"));
        controller.add(new User("randomUser9@email.com", "randomUser9", "191-111-1111", "qwerty"));
        controller.add(new User("randomUser10@email.com", "randomUser10", "221-111-1111", "qwerty"));
    }

    @AfterEach
    public void tearDown() {
        // Clean up
        controller.whipeData();
        controller = null;
        jsonHandler= null;
    }
    @Test public void testDataRetention(){
        //On cree un usager avec plusieurs donnees necessaires

        User testUser = new User("a","follower check","a","a");
        testUser.addFollower("Personne random !");
        Robot randomRobot = new Robot("Entertainer", "Micheal");
        testUser.add(randomRobot);
        controller.add(testUser);

        //On regarde si l'object recupere est le meme apres etres serialise / deserialise

        User ourUser = controller.authenticateUser("a", "a");
        //Puisque les 2 objects ne sont pas a la meme place en memoire, on compare le json string.
        assertEquals(jsonHandler.objectToJson(testUser), jsonHandler.objectToJson(ourUser));


    }


    @Test
    public void testDatabaseUpdate() {
        Seller sell = controller.authenticateSeller("company5@email.com", "qwerty");
        String oldName = sell.getName();
        sell.setName("Nom different");
        controller.update(sell);

        //Apres update, on retrouve le meme User et on reevalue
        Seller recoveredSeller = controller.authenticateSeller("company5@email.com", "qwerty");
        assertNotEquals(recoveredSeller.getName(), oldName);

    }

    //Test aussi si login fonctionne comme prevu
    @Test
    public void testDatabaseAdd() {

        User testUser = new User("a","Preuve qu'on recover le user","a","a");
        controller.add(testUser);
        User myClient = controller.authenticateUser("a", "a");
        assertEquals(myClient.getName(), "Preuve qu'on recover le user");

        //On observe qu'un utilisateur qui n'existe pas / mauvais password est bel et bien null
        myClient = controller.authenticateUser("a", "b");
        assertEquals(myClient, null);

        myClient = controller.authenticateUser("not a user", "a");
        assertEquals(myClient, null);

    }
    @Test
    public void testClearFile(){
        controller.whipeData();
        try{
            String path = "src/main/resources/Json/Sellers.json";
            String json = new String(Files.readAllBytes(Paths.get(path)));
            assertEquals(json, "null");
        }catch (Exception e){
            e.printStackTrace();
            assert(false);
        }
    }

    @Test
    public void testIsValid(){
        assertFalse(controller.isValid("a", 1));
        assertTrue(controller.isValid("5", 5));
        assertFalse(controller.isValid("0", 3));
    }

    @Test
    public void testGetUsers(){
        List<User> list = controller.getUsers();
        int i=1;
        for(User user:list){
            assertEquals(user.getEmail(), "randomUser"+i+"@email.com");
            assertEquals(user.getName(), "randomUser"+i);
            assertEquals(user.getPassword(), "qwerty");
            if(i!=10){
                assertEquals(user.getPhone(), "1"+i+"1-111-1111");
            }
            else{
                assertEquals(user.getPhone(), "221-111-1111");
            }
            i++;
        }
    }

    @Test
    public void testQueryUsers(){
        assertEquals(controller.queryUser(""), null);
        assertEquals(controller.queryUser("randomUser3").getEmail(), "randomUser3@email.com");
        assertEquals(controller.queryUser("randomUser11"), null);
    }

    @Test
    public void getSellers(){
        List<Seller> list = controller.getSellers();
        int i=1;
        for(Seller seller:list){
            assertEquals(seller.getEmail(), "company"+i+"@email.com");
            assertEquals(seller.getName(), "Company "+i);
            assertEquals(seller.getAddress(), i+" AB street");
            assertEquals(seller.getPassword(), "qwerty");
            assertEquals(seller.getPhone(), i+"11-111-1111");
            i++;
        }
    }

    @Test
    public void testQuerySeller(){
        assertEquals(controller.querySeller("", "1"), null);
        assertEquals(controller.querySeller("", "2"), null);
        assertEquals(controller.querySeller("Company 1", "1").getEmail(), "company1@email.com");
        assertEquals(controller.querySeller("company3@email.com", "2").getName(), "Company 3");
        assertEquals(controller.querySeller("Company 6", "1"), null);
        assertEquals(controller.querySeller("company6@email.com", "2"), null);
    }
}
