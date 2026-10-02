class GymMember {

    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null ||
            memberId.trim().isEmpty() ||
            memberId.length() < 4) {

            throw new IllegalArgumentException(
                "Invalid member ID"
            );
        }

        if (monthlyFee <= 0) {
            throw new IllegalArgumentException(
                "Monthly fee must be positive"
            );
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.print(
            "Standard | Sessions: " +
            sessionsAttended
        );
    }

    public static String batchPrint(
        GymMember[] members
    ) {

        StringBuilder result =
            new StringBuilder();

        for (GymMember member : members) {

            member.displayInfo();

            result.append(
                getDisplayString(member)
            );

            if (member instanceof PremiumMember) {

                PremiumMember premium =
                    (PremiumMember) member;

                result.append(
                    " [Trainer via downcast: "
                    + premium.getTrainerName()
                    + "]"
                );
            }

            result.append(" | ");
        }

        return result.toString();
    }

    private static String getDisplayString(
        GymMember member
    ) {

        if (member instanceof PremiumMember) {

            PremiumMember premium =
                (PremiumMember) member;

            return "Premium | Trainer: " +
                premium.getTrainerName() +
                " | Sessions: " +
                premium.getSessionsAttended();

        } else {

            return "Standard | Sessions: " +
                member.getSessionsAttended();
        }
    }
}

class PremiumMember extends GymMember {

    private String trainerName;

    public PremiumMember(
        String memberId,
        int monthlyFee,
        String trainerName
    ) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public void displayInfo() {
        System.out.print(
            "Premium | Trainer: " +
            trainerName +
            " | Sessions: " +
            sessionsAttended
        );
    }
}

public class Main {

    public static void main(String[] args) {

        GymMember[] members = {

            new GymMember(
                "MEM6",
                1000
            ),

            new PremiumMember(
                "MEM7",
                2000,
                "Coach Riya"
            )
        };

        String result =
            GymMember.batchPrint(members);

        System.out.println();
        System.out.println(result);
    }
}