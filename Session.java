public class Session {
    private int sessionId;
    private int studentID;
    private int plateNumber;
    private int trainerID;
    private int sessionCost;
    private String sessionTime;
    private String sessionDate;
    private String sessionStatus;

    // Constructor
    public Session(int sessionId, int studentID, int plateNumber, int trainerID, int sessionCost, String sessionTime, String sessionDate, String sessionStatus) {
        this.sessionId = sessionId;
        this.studentID = studentID;
        this.plateNumber = plateNumber;
        this.trainerID = trainerID;
        this.sessionCost = sessionCost;
        this.sessionTime = sessionTime;
        this.sessionDate = sessionDate;
        this.sessionStatus = sessionStatus;
    }

    // Getters
    public int getSessionId() {
        return sessionId;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getPlateNumber() {
        return plateNumber;
    }

    public int getTrainerID() {
        return trainerID;
    }

    public int getSessionCost() {
        return sessionCost;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    // Setters
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setPlateNumber(int plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public void setSessionCost(int sessionCost) {
        this.sessionCost = sessionCost;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}
