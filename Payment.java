import java.util.Date;

public class Payment {
    private int pid;
    private int studentId;
    private int amount;
    private Date pdate;

    // Constructor
    public Payment(int pid, int studentId, int amount, Date pdate) {
        this.pid = pid;
        this.studentId = studentId;
        this.amount = amount;
        this.pdate = pdate;
    }

    // Getters and Setters
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }
}
