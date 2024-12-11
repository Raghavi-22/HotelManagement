package demo.demo.Module;

import java.util.UUID;

public class Service {
    private UUID serviceID;
    private int belongsto;
    private String serviceName;
    private String description;
    private double price;
    private String availabilityStatus;

    // Getters and Setters
    public UUID getServiceID() {
        return serviceID;
    }

    public void setServiceID(UUID serviceID) {
        this.serviceID = serviceID;
    }

    public Service() {
    }

    public int getBelongsto() {
        return belongsto;
    }

    public void setBelongsto(int belongsto) {
        this.belongsto = belongsto;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    // Constructor
    public Service(UUID serviceID, int belongsto, String serviceName, String description, double price, String availabilityStatus) {
        this.serviceID = serviceID;
        this.belongsto = belongsto;
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        this.availabilityStatus = availabilityStatus;
    }
}
