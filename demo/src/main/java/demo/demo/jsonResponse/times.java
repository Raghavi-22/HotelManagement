package demo.demo.jsonResponse;

import java.time.LocalTime;

public class times {
    private LocalTime start;  // Change to non-final for BeanPropertyRowMapper
    private LocalTime end;
    private Double price;// Change to non-final for BeanPropertyRowMapper

    // No-argument constructor
    public times() {
    }

    public times(LocalTime start, LocalTime end, Double price) {
        this.start = start;
        this.end = end;
        this.price = price;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
