class Participant {
    String name;
    String teamName;
    boolean registered;

    // Constructor with name and team name
    public Participant(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.registered = true;
    }

    // Constructor for solo participants
    public Participant(String name) {
        this(name, "Unassigned");
    }

    void printStatus() {
        System.out.println(name + " | " + teamName
                + " | Registered: " + registered);
    }
}

public class problem1 {
    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya"};

        String[] teamNames = {
            "ByteBusters", "", "CodeCrafters", ""
        };

        for (int i = 0; i < names.length; i++) {
            Participant p;

            if (teamNames[i].isEmpty()) {
                p = new Participant(names[i]);
            } else {
                p = new Participant(names[i], teamNames[i]);
            }

            p.printStatus();
        }
    }
}