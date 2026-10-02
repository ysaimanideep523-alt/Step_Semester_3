public class LibraryMember {

    private String membershipId;
    private String name;
    private boolean premiumMember;

    // Stores only transformed security answer
    private String securityAnswerHash;

    // Public no-argument constructor
    public LibraryMember() {
        membershipId = null;
        name = null;
        premiumMember = false;
        securityAnswerHash = null;
    }

    // Write-once membershipId
    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {

        if (membershipId == null) {
            membershipId = id;
        }
    }

    // Name property
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Premium membership property
    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    // Write-only security answer
    public void setSecurityAnswer(String answer) {

        if (answer != null) {
            securityAnswerHash = Integer.toHexString(
                answer.hashCode()
            );
        }
    }

    public static void main(String[] args) {

        LibraryMember m = new LibraryMember();

        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);

        System.out.println(m.getMembershipId());
        System.out.println(m.getName());
        System.out.println(m.isPremiumMember());

        // Second attempt is ignored
        m.setMembershipId("FAKE-0000");

        System.out.println(m.getMembershipId());

        // Security answer can be set
        // but there is no getter for it
        m.setSecurityAnswer("BlueMountain");
    }
}