package bbro.mkreq.webUrl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class WebUrl {
    @Id
    @GeneratedValue
    private int id;

    private String url;
    private int iterationAmount;
    private int reqPerHour;
    private int iterationLimit;

    public WebUrl() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIterationAmount() {
        return iterationAmount;
    }

    public void setIterationAmount(int iterationAmount) {
        this.iterationAmount = iterationAmount;
    }

    public int getReqPerHour() {
        return reqPerHour;
    }

    public void setReqPerHour(int reqPerHour) {
        this.reqPerHour = reqPerHour;
    }

    public int getIterationLimit() {
        return iterationLimit;
    }

    public void setIterationLimit(int iterationLimit) {
        this.iterationLimit = iterationLimit;
    }

    public void iterationIncrement(){
        iterationAmount++;
    }
}
