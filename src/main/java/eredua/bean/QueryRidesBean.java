package eredua.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import businessLogic.BLFacade;
import domain.Ride;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("queryRidesBean")
@SessionScoped
public class QueryRidesBean implements Serializable {
    private String departCity;
    private String arrivalCity;
    private Date date;
    private List<Ride> rides;
    private List<String> departCities;
    private List<String> arrivalCities;

    public QueryRidesBean() {
        this.date = new Date();
        loadDepartCities();
    }

    public void loadDepartCities() {
        try {
            BLFacade facadeBL = FacadeBean.getBusinessLogic();
            this.departCities = facadeBL.getDepartCities();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error loading depart cities"));
            e.printStackTrace();
        }
    }

    public void onDepartCityChange() {
        try {
            if (departCity != null && !departCity.isEmpty()) {
                BLFacade facadeBL = FacadeBean.getBusinessLogic();
                this.arrivalCities = facadeBL.getDestinationCities(departCity);
            } else {
                this.arrivalCities = null;
            }
            this.rides = null; // Garbitu ride-ak
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error loading arrival cities"));
            e.printStackTrace();
        }
    }

    public void searchRides() {
        try {
            if (departCity == null || departCity.isEmpty() || 
                arrivalCity == null || arrivalCity.isEmpty() || 
                date == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Please select all fields"));
                return;
            }

            BLFacade facadeBL = FacadeBean.getBusinessLogic();
            this.rides = facadeBL.getRides(departCity, arrivalCity, date);
            
            if (rides.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "No rides found for the selected criteria"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error searching rides"));
            e.printStackTrace();
        }
    }

    // Getter eta Setter metodoak
    public String getDepartCity() { return departCity; }
    public void setDepartCity(String departCity) { 
        this.departCity = departCity;
        this.onDepartCityChange();
    }

    public String getArrivalCity() { return arrivalCity; }
    public void setArrivalCity(String arrivalCity) { this.arrivalCity = arrivalCity; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public List<Ride> getRides() { return rides; }
    public void setRides(List<Ride> rides) { this.rides = rides; }

    public List<String> getDepartCities() { return departCities; }
    public void setDepartCities(List<String> departCities) { this.departCities = departCities; }

    public List<String> getArrivalCities() { return arrivalCities; }
    public void setArrivalCities(List<String> arrivalCities) { this.arrivalCities = arrivalCities; }
}