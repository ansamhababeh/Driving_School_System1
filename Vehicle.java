import java.util.Date;

public class Vehicle {
    private int plateNumber;
    private int trainerID;
    private String vehicleModel;
    private int productionYear;
    private int mileage;
    private int engineSize;
    private String transmissionType;
    private String fuelType;
    private Date insuranceDate;
    private Date licenseDate;

    // Constructors
    public Vehicle() {
    }

    public Vehicle(int plateNumber, int trainerID, String vehicleModel, int productionYear, int mileage, int engineSize, String transmissionType, String fuelType, Date insuranceDate, Date licenseDate) {
        this.plateNumber = plateNumber;
        this.trainerID = trainerID;
        this.vehicleModel = vehicleModel;
        this.productionYear = productionYear;
        this.mileage = mileage;
        this.engineSize = engineSize;
        this.transmissionType = transmissionType;
        this.fuelType = fuelType;
        this.insuranceDate = insuranceDate;
        this.licenseDate = licenseDate;
    }

    // Getters and setters
    public int getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(int plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Date getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(Date insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public Date getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(Date licenseDate) {
        this.licenseDate = licenseDate;
    }
}
