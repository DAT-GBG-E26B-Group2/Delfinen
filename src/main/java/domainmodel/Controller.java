package domainmodel;

import datasource.FileHandler;

import java.io.FileNotFoundException;

public class Controller {

    private SwimmingClub swimmingClub;
    private FileHandler fileHandler;

    public Controller() {
        swimmingClub = new SwimmingClub();
        fileHandler = new FileHandler(
                "data/members.csv"
        );
    }

    // Loads member data from the file
    public void loadData() throws FileNotFoundException {
        fileHandler.loadMembers(swimmingClub);
    }

    // Saves member data to the file
    public void saveData() throws FileNotFoundException {
        fileHandler.saveMembers(swimmingClub);
    }
}
