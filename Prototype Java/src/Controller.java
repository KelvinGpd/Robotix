public class Controller {
    public Controller (){

    }

    public int choice(int numChoices){
        Scanner reader = new Scanner(System.in);
        while(true) {
            String choice = reader.next();
            if (isValid(choice, numChoices)) {
                return Integer.parseInt(choice);
            }
            System.out.print("Veuillez saisir un choix valide: ");
        }
    }

    private boolean isValid(String input, int max){
        int n;
        if (input == null) {
            return false;
        }
        try {
            n = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return n > 0 && n <= max;
    }
}