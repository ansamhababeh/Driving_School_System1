import java.util.Objects;

public class TrainerAvailability {
    private int trainerID;
    private String availableDay;
    private String availableTime;

    // Constructor
    public TrainerAvailability(int trainerID, String availableDay, String availableTime) {
        this.trainerID = trainerID;
        this.availableDay = availableDay;
        this.availableTime = availableTime;
    }

    // Getters and setters
    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public String getAvailableDay() {
        return availableDay;
    }

    public void setAvailableDay(String availableDay) {
        this.availableDay = availableDay;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    // toString method
    @Override
    public String toString() {
        return "TrainerAvailability{" +
                "trainerID=" + trainerID +
                ", availableDay='" + availableDay + '\'' +
                ", availableTime='" + availableTime + '\'' +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainerAvailability that = (TrainerAvailability) o;
        return trainerID == that.trainerID &&
                Objects.equals(availableDay, that.availableDay) &&
                Objects.equals(availableTime, that.availableTime);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(trainerID, availableDay, availableTime);
    }
}
