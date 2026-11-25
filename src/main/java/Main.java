import domainmodel.Controller;
import domainmodel.SwimmingClub;
import ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        SwimmingClub club = new SwimmingClub();
        Controller controller = new Controller(club);
        UserInterface ui = new UserInterface(controller);

        ui.start();
    }
}