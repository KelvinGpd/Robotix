//UI for user

public class UserView{
    Controller controller;
    public UserView(){
        controller = new Controller();
    }

    public void run(){
        Utilisateur user = register();

        System.out.println("Bienvenue à Robotix "+ user.getName() +". Veuillez choisir une option:");
        System.out.println("1. Ajouter un robot\n2. Informations sur vos robots\n 3. Acheter des composantes\n" +
                "4. Créer un action\n5. Interagir avec vos robots");
        switch (controller.choice(5)){
            case "1" -> addRobot();
            case "2" -> robotInfo();
            case "3" -> buyComponents();
            case "4" -> createAction();
            case "5" -> interact();
        }
    }

    private Utilisateur register(){
        //TODO
        //get username, email, etc.
    }

    private void addRobot(){
        //TODO
        //ajouter robot à la flotte
    }
    private void robotInfo(){
        //TODO
        //voir état, métriques
    }

    private void buyComponents(){
        //TODO
        //trouver fournisseur, acheter composantes
    }

    private void createAction() {
        //TODO
        //créer actions, activités
    }

    private void interact() {
        //TODO
        //assigner tâches, participer à des activités
    }
}