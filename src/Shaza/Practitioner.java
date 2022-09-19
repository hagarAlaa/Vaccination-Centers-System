package Shaza;

/**
 *
 * @author shatha
 */
public class Practitioner {

    private String parctID;
    private String Fname;
    private String lName;
    private String Status;
    private int center;
    private Practitioner next;

// Constructor.
    public Practitioner(String parctID, String Fname, String lName, String Status, int center) {
        this.parctID = parctID;
        this.Fname = Fname;
        this.lName = lName;
        this.center = center;
        this.Status = Status;
    }

    public Practitioner(String parctID, String Fname, String lName, int center, String Status, Practitioner next) {
        this.parctID = parctID;
        this.Fname = Fname;
        this.lName = lName;
        this.center = center;
        this.Status = Status;
        this.next = next;
    }
// For return id of student.

    public String getparctID() {
        return parctID;
    }

// For return first name of student.
    public String getFname() {
        return Fname;
    }

    public String getlName() {
        return lName;
    }

    public String getStatus() {
        return Status;
    }

    public int getCenter() {
        return center;
    }

    public Practitioner getnext() {
        return next;
    }

    public void setparctID(String parctID) {
        this.parctID = parctID;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setCenter(int section) {
        this.center = center;
    }

    public void setnext(Practitioner next) {
        this.next = next;
    }
// For print information of student as string .

    @Override
    public String toString() {
        return parctID + " - " + Fname + " " + lName + " - " + Status;
    }

}
