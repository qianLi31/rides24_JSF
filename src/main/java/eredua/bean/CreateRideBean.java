package eredua.bean;
import java.io.Serializable;
import java.util.Date;

import domain.Ride;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("createRideBean")
@SessionScoped
public class CreateRideBean implements Serializable {
	
	private String origin;
	private String destination;
	private Date date;
	private Integer seats;
	private Double price;
	
//    private Ride ride;

    public CreateRideBean() {
    }
    
    public Integer getSeats() { return seats; }
    public void setSeats(Integer seats) { this.seats = seats; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
//    
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    
    
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String createRide() {
        // Bidaia sortzeko logikoa hemen
        System.out.println("Bidaia sortzen: " + origin + " -> " + destination);
        return "success"; // edo nabigazio emaitza egokia
    }

    public String close() {
        // Itxi logikoa hemen
        return "main"; // edo nabigazio emaitza egokia
    }
}