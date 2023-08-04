import data.*;
import data.databases.UserDb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
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
        controller.add(new Seller("Companie A", "1234 street", "company@email.com", "111-1111-1111", components, "qwerty"));

        Component[] components2 = { new Component("RapidLift 2.0", "propeller",
                "Efficacité maximale et une propulsion optimale, garantissant une vitesse et une maniabilité exceptionnelles.",
                95),
                new Component("Energizer", "batteries", "Une batterie longue durée avec une autonomie exceptionnelle",
                        45),
                new Component("Shockwave V", "speakers",
                        "Haut-parleur sans fil qui offre une connectivité sans encombrement", 89) };
        controller.add(new Seller("Companie B", "5678 street", "company2@email.com", "222-2222-2222", components2, "qwerty"));
        Component[] components3 = { new Component("Ultragrip 4", "arms",
                "bras robotique polyvalent conçu pour s'adapter à une large gamme d'applications industrielles", 60),
                new Component("VisonLens HD", "camera", "Caméra HD offrant une résolution haute définition", 210) };
        controller.add(new Seller("Companie C", "12313 street", "companysdasd@email.com", "333-1111-1111", components3,
                "qwerty"));
        Component[] components4 = {
                new Component("ImageColor", "screen",
                        "Une qualité d'image exceptionnelle avec une netteté et des détails époustouflants", 99),
                new Component("RealSound 1665-E", "speaker", "Haut-parleur compact et facilement transportable.", 70) };
        controller.add(new Seller("Companie D", "1234123 street", "company222@email.com", "333-4567-1111", components4,
                "qwerty"));
        Component[] components5 = {
                new Component("OmniCapture 3", "micro",
                        "une qualité audio exceptionnelle, capturant des sons clairs et détaillés", 50),
                new Component("Intel i7 8th Gen", "cpu", "CPU conçu pour une efficacité énergétique optimale.", 199) };
        controller.add(new Seller("Companie E", "1234 street", "companyNouvelle@email.com", "111-1111-1111", components5, "qwerty"));

        controller.add(new User("randomUser@email.com", "randomUser1", "111-1111-1111", "qwerty"));
        controller.add(new User("randomUser2@email.com", "randomUser2", "121-1111-1111", "qwerty"));
        controller.add(new User("randomUser3@email.com", "randomUser3", "131-1111-1111", "qwerty"));
        controller.add(new User("randomUser4@email.com", "randomUser4", "141-1111-1111", "qwerty"));
        controller.add(new User("randomUser5@email.com", "randomUser5", "151-1111-1111", "qwerty"));
        controller.add(new User("randomUser6@email.com", "randomUser6", "161-1111-1111", "qwerty"));
        controller.add(new User("randomUser7@email.com", "randomUser7", "171-1111-1111", "qwerty"));
        controller.add(new User("randomUser8@email.com", "randomUser8", "181-1111-1111", "qwerty"));
        controller.add(new User("randomUser9@email.com", "randomUser9", "191-1111-1111", "qwerty"));
        controller.add(new User("randomUser10@email.com", "randomUser10", "221-1111-1111", "qwerty"));
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
        Seller sell = controller.authenticateSeller("companyNouvelle@email.com", "qwerty");
        String oldName = sell.getName();
        sell.setName("Nom different");
        controller.update(sell);

        //Apres update, on retrouve le meme User et on reevalue
        Seller recoveredSeller = controller.authenticateSeller("companyNouvelle@email.com", "qwerty");
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

    // Add more test methods as needed

}
