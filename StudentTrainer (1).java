public class StudentTrainer {
    private int studentId;
    private int trainerId;

    // Constructor
    public StudentTrainer(int studentId, int trainerId) {
        this.studentId = studentId;
        this.trainerId = trainerId;
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
}
