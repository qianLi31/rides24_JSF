package eredua.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import domain.Ride;

import java.util.ArrayList;
import java.util.Arrays;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("queryRidesBean")
@SessionScoped
public class QueryRidesBean implements Serializable {
	private String selectedOrigin;
	private String selectedDestination;
	private static List<Ride> rides=new ArrayList<Ride>();
	
	
    public String getSelectedOrigin() {
		return selectedOrigin;
	}


	public void setSelectedOrigin(String selectedOrigin) {
		this.selectedOrigin = selectedOrigin;
	}


	public String getSelectedDestination() {
		return selectedDestination;
	}


	public void setSelectedDestination(String selectedDestination) {
		this.selectedDestination = selectedDestination;
	}


	public static List<Ride> getRides() {
		return rides;
	}


	public static void setRides(List<Ride> rides) {
		QueryRidesBean.rides = rides;
	}


	public QueryRidesBean() {
    }

	
}