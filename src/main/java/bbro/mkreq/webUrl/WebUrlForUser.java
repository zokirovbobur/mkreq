package bbro.mkreq.webUrl;

public class WebUrlForUser{
    private String url;
    private int reqPerHour;
    private int iterationLimit;



    public WebUrlForUser() {
        url = "http://example.com";
        reqPerHour = 12;
        iterationLimit = 12;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
