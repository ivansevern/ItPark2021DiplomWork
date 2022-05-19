package ElectricalCalculatesForCables;

import ElectricalCalculatesForCables.jdbc.CableDb;

public class CableRunner {
    public static void main(String[] args) {
        CableDb.dropCableIfExists();
        CableDb.createCable();
    }
}
