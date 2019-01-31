package bbro.mkreq.webUrl;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

@Service
public class WebUrlService {
    @Autowired
    private WebUrlRepo webUrlRepo;

    public String saveUrl(WebUrl webUrl)  {
        String res;
        if(webUrl.getIterationLimit()<=0){
            res = "Error: Iteration which you need given less than 1";
        }
        else if(webUrl.getReqPerHour()<=0){
            res = "Error: Request per Hour is less than 1";
        }
        else if (webUrl.getReqPerHour() > 1000000){
            res = "Error: Request per Hour is more than required limit";
        }
        else if (webUrl.getUrl().equals("")){
            res= "Error: url is empty";
        }
        else if (!isValid(webUrl.getUrl())){
            res = "Error: url is not proper";
        }
        else{
            try {
                webUrlRepo.save(webUrl);
            }catch (StaleObjectStateException e){
                e.printStackTrace();
            }

            res= "url added successfully";
        }


        return res;
    }

    public List<WebUrl> getAll(){
        return webUrlRepo.findAll();
    }
    public void deleteUrl(WebUrl webUrl){
        webUrlRepo.delete(webUrl);
    }


    public boolean isValid(String url) {
        /* Try creating a valid URL */
        try {
            new URL(url).toURI();
            System.out.println(new URL(url).toURI());
            return true;
        }

        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }
}
