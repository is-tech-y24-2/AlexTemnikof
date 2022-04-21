import objects.CentralBank;
import services.ConsoleInteraction;
import tools.BanksException;

import java.io.IOException;
import java.util.HashMap;

public class Program {
    public static void main(String[] args) throws BanksException, IOException {
        var centralBank = new CentralBank("Russian");
        var depositPercents = new HashMap<Integer, Double>();
        centralBank.registerNewBank("Sbebrank", 300.0, 3.5, 300.0, depositPercents);
        ConsoleInteraction.mainWork(centralBank);
    }
}