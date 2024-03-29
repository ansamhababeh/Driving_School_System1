import java.util.Date;

public class Test {
    private int tid;
    private String tresult;
    private Date pdate;
    private int studentId;

    // Constructor
    public Test(int tid, String tresult, Date pdate, int studentId) {
        this.tid = tid;
        this.tresult = tresult;
        this.pdate = pdate;
        this.studentId = studentId;
    }

    // Getters and Setters
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTresult() {
        return tresult;
    }

    public void setTresult(String tresult) {
        this.tresult = tresult;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
