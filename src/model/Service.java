package model;

public class Service {
    private int serviceId;
    private String name;
    private String description;
    private double basePrice;
// constructor
    public Service() {}

    public Service(int serviceId, String name, String description, double basePrice) {
        this.serviceId = serviceId;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
    }
// getters and setters
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId=" + serviceId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
