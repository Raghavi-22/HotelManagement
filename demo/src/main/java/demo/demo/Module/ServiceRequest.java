package demo.demo.Module;



import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

public class ServiceRequest {
    private UUID requestServiceId;
    private UUID serviceNo;
    private Date date;
    private Time time;
    private UUID serviceRequestedBy;
    private String AvailabilityStatus;

    public ServiceRequest(UUID requestServiceId, UUID serviceNo, Date date, Time time, UUID serviceRequestedBy, String availabilityStatus) {
        this.requestServiceId = requestServiceId;
        this.serviceNo = serviceNo;
        this.date = date;
        this.time = time;
        this.serviceRequestedBy = serviceRequestedBy;
        AvailabilityStatus = availabilityStatus;
    }

    public ServiceRequest() {
    }

    public UUID getRequestServiceId() {
        return requestServiceId;
    }

    public void setRequestServiceId(UUID requestServiceId) {
        this.requestServiceId = requestServiceId;
    }

    public UUID getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(UUID serviceNo) {
        this.serviceNo = serviceNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public UUID getServiceRequestedBy() {
        return serviceRequestedBy;
    }

    public void setServiceRequestedBy(UUID serviceRequestedBy) {
        this.serviceRequestedBy = serviceRequestedBy;
    }

    public String getAvailabilityStatus() {
        return AvailabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        AvailabilityStatus = availabilityStatus;
    }
}
