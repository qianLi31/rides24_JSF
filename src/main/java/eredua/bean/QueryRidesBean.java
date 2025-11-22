package eredua.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("queryRidesBean")
@SessionScoped
public class QueryRidesBean implements Serializable {
    
    private String ridesTitle = "Find Rides";
    private String from = "Donostia";
    private String to = "Bilbo";
    private String selectedOrigin = "Donostia";
    private String selectedDestination = "Bilbo";
    private Date selectedDate = new Date(); 
    
    private List<String> origins = Arrays.asList("Donostia", "Bilbo", "Vitoria", "Irun", "Pamplona");

    public QueryRidesBean() {
        System.out.println("QueryRidesBean constructor called");
    }

    // 这是action方法，不是属性getter
    public String searchRides() {
        System.out.println("Searching rides from " + selectedOrigin + " to " + selectedDestination + " on " + selectedDate);
        return null;
    }

    // 添加searchRides属性的getter方法
    public String getSearchRides() {
        // 这里可以返回一个默认值或者根据业务逻辑返回相应值
        return "searchRides";
    }

    // 确保有所有getter方法
    public String getFrom() {
        System.out.println("getFrom called: " + from);
        return from;
    }
    
    public String getTo() {
        System.out.println("getTo called: " + to);
        return to;
    }
    
    public String getRidesTitle() {
        return ridesTitle;
    }
    
    public List<String> getOrigins() {
        return origins;
    }

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

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String close() {
        System.out.println("Closing search form");
        return null;
    }
}