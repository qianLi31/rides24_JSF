package eredua.bean;
import java.io.Serializable;
import java.util.Date;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Ride;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("createRideBean")
@SessionScoped
public class CreateRideBean implements Serializable {
	private Ride ride;
	private String origin;
	private String destination;
	private Date date;
	private Integer seats;
	private float price;
	private String datua; 
	private BLFacade facadeBL;
	private String driverEmail = "driver1@gmail.com";

	public CreateRideBean() {
		this.date = new Date();
	}
	
	public String getDriverEmail() { return driverEmail; }
	public void setDriverEmail(String driverEmail) { this.driverEmail = driverEmail; }

	public Integer getSeats() { return seats; }
	public void setSeats(Integer seats) { this.seats = seats; }

	public float getPrice() { return price; }
	public void setPrice(float price) { this.price = price; }

	public String getOrigin() { return origin; }
	public void setOrigin(String origin) { this.origin = origin; }

	public String getDestination() { return destination; }
	public void setDestination(String destination) { this.destination = destination; }

	public Date getDate() { 
		if (date == null) {
			date = new Date();
		}
		return date; 
	}
	public void setDate(Date date) { this.date = date; }

	public String getDatua() { return datua; }
	public void setDatua(String datua) { this.datua = datua; }

	public void createRide() {
		try {
			// Lortu BLFacade instantzia FacadeBean bidez
			this.facadeBL = FacadeBean.getBusinessLogic();

			// Sortu ride-a BLFacade erabiliz
			Ride newRide = facadeBL.createRide(origin, destination, date, seats, price, driverEmail);

			// Ride-a ondo sortu bada, mezu positiboa erakutsi
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, 
							"Ride successfully created!", 
							"Ride from " + origin + " to " + destination + " on " + date + " has been created."));

			// Datuak erakutsi
			this.datua = "Ride successfully created! " + origin + " --> " + destination + 
					", date: " + date + ", number of seats: " + seats + ", price: " + price;

			// Formularioa garbitu (aukerakoa)
			// clearForm();
		} catch (RideMustBeLaterThanTodayException e) {
			// Data gaizki dagoen kasurako
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							"Error creating ride", 
							"Ride date must be later than today."));
			this.datua = "Error: Ride date must be later than today.";

		} catch (RideAlreadyExistException e) {
			// Ride-a existitzen den kasurako
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							"Error creating ride", 
							"A ride with the same details already exists for this driver."));
			this.datua = "Error: A ride with the same details already exists for this driver.";

		} catch (Exception e) {
			// Beste errore batzuk
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							"Error creating ride", 
							"An unexpected error occurred: " + e.getMessage()));
			this.datua = "Error: An unexpected error occurred while creating the ride.";
			e.printStackTrace();
		}
	}

	public String close() {
		// Itxi logikoa hemen
		return "main"; // edo nabigazio emaitza egokia
	}

	public void test() {
		String datuak = "Ride is Created. " + origin + " --> " + destination + 
				",   date: " + date + ", number of seats: " + seats + ",  price: " + price;
		System.out.println(datuak);

		// Datuak gorde datua atributuan
		this.datua = datuak;
	}

	public void onDateSelect(SelectEvent event) {
		this.date = (Date) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Data aukeratua: "+ this.date));
	}

	// Formularioa garbitzeko metodoa (aukerakoa)
	public void clearForm() {
		this.origin = null;
		this.destination = null;
		this.seats = null;
		this.price = (Float) null;
		this.date = new Date();
		this.datua = null;
	}
}