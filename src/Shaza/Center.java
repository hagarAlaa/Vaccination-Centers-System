package Shaza;

/**
 *
 * @author shatha
 */
public class Center {

    static int size;

    private int centerID;
    private Practitioner head;

    public Center() {
    }

    public Center(int centerID) {
        this.centerID = centerID;
    }

    public void setHead(Practitioner head) {
        this.head = head;
    }

    public int getcenterID() {
        return centerID;
    }
    // For return head.

    public Practitioner getHead() {
        return head;
    }

    public void addPractitioner(Practitioner Practitioner) {

        if (IsEmpty()) {
            this.head = Practitioner;
            return;
        }
        Practitioner helptr = head;
        while (helptr.getnext() != null) {
            helptr = helptr.getnext();
        }
        helptr.setnext(Practitioner);
    }

// This method for search id of student.
    public Practitioner searchByID(String ID) {

        if (IsEmpty()) {
            return null;
        }

        Practitioner helptr = this.head;
        Practitioner Practitioner = null;

        while (helptr != null) {
            if (helptr.getparctID().equals(ID)) {

                Practitioner = helptr;
            }
            helptr = helptr.getnext();
        }
        return Practitioner;
    }

    public void deletePractitionersBasedOnStatus(String Status) {

        if (head != null && head.getStatus().equals(Status)) {
            head = head.getnext();
            return;
        }

        Practitioner helptr = head;

        while (helptr.getnext() != null) {

            if (helptr.getnext().getStatus().equals(Status)) {
                // set next.
                helptr.setnext(helptr.getnext().getnext());
                return;
            }

            helptr = helptr.getnext();
        }
    }

    public void deletePractitionerById(String id) {

        if (head != null && head.getparctID().equals(id)) {
            head = head.getnext();
            return;
        }

        Practitioner helptr = head;

        while (helptr.getnext() != null) {

            if (helptr.getnext().getparctID().equals(id)) {

                helptr.setnext(helptr.getnext().getnext());
                return;
            }

            helptr = helptr.getnext();
        }

    }

    private boolean IsEmpty() {
        return head == null;
    }

}
