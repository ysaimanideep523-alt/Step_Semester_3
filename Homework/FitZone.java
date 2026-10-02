interface MembershipPlan {
    double calculateFee();
    String getName();
}

class MonthlyPlan implements MembershipPlan {

    public double calculateFee() {
        return 1000;
    }

    public String getName() {
        return "Monthly";
    }
}

class QuarterlyPlan implements MembershipPlan {

    public double calculateFee() {
        return 1000 * 3 * 0.90;
    }

    public String getName() {
        return "Quarterly";
    }
}

class AnnualPlan implements MembershipPlan {

    public double calculateFee() {
        return 1000 * 12 * 0.75;
    }

    public String getName() {
        return "Annual";
    }
}

class Member {
    private String name;

    Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

enum MembershipStatus {
    ACTIVE,
    FROZEN,
    EXPIRED
}

class Membership {

    private Member member;
    private MembershipPlan plan;
    private MembershipStatus status;

    Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.status = MembershipStatus.ACTIVE;

        System.out.printf(
                "%s membership created for %s. Fee: ₹%.2f. Status: Active.%n",
                plan.getName(),
                member.getName(),
                plan.calculateFee()
        );
    }

    public void checkIn() {

        if (status == MembershipStatus.ACTIVE) {
            System.out.println(
                    member.getName() +
                    " checked in successfully."
            );
        } else {
            System.out.println(
                    "Check-in denied: " +
                    member.getName() +
                    "'s membership is " +
                    status + "."
            );
        }
    }

    public void freeze() {

        if (status == MembershipStatus.ACTIVE) {

            status = MembershipStatus.FROZEN;

            System.out.println(
                    member.getName() +
                    "'s membership frozen. Status: Frozen."
            );

        } else if (status == MembershipStatus.EXPIRED) {

            System.out.println(
                    "Cannot freeze an Expired membership."
            );

        } else {

            System.out.println(
                    "Membership is already Frozen."
            );
        }
    }

    public void unfreeze() {

        if (status == MembershipStatus.FROZEN) {

            status = MembershipStatus.ACTIVE;

            System.out.println(
                    member.getName() +
                    "'s membership unfrozen. Status: Active."
            );

        } else if (status == MembershipStatus.EXPIRED) {

            System.out.println(
                    "Cannot unfreeze an Expired membership."
            );
        }
    }

    public void expire() {

        if (status != MembershipStatus.EXPIRED) {

            status = MembershipStatus.EXPIRED;

            System.out.println(
                    member.getName() +
                    "'s membership expired. Status: Expired."
            );
        }
    }
}

public class FitZone {

    public static void main(String[] args) {

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership =
                new Membership(
                        asha,
                        new QuarterlyPlan()
                );

        Membership raviMembership =
                new Membership(
                        ravi,
                        new MonthlyPlan()
                );

        ashaMembership.checkIn();

        ashaMembership.freeze();

        ashaMembership.checkIn();

        raviMembership.expire();

        raviMembership.freeze();
    }
}