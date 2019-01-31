package bbro.mkreq.request;

import bbro.mkreq.webUrl.WebUrl;
import bbro.mkreq.webUrl.WebUrlService;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private WebUrlService webUrlService;


    public void start(){

        while (true){
            List<WebUrl> urlList = webUrlService.getAll();
            for (WebUrl webUrl: urlList){
                String uri = webUrl.getUrl();

                webUrl.iterationIncrement();

                System.out.println("---------------\nclient uri: "+uri +"\niteration: "+webUrl.getIterationAmount());

                RestTemplate restTemplate = new RestTemplate();

                try {
                    String result = restTemplate.getForObject(uri, String.class);

                    webUrlService.saveUrl(webUrl);

                    System.out.println(result);
                    Thread.sleep(1000);
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                    webUrlService.deleteUrl(webUrl);
                } catch (StaleObjectStateException e){
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }


        }
    }


    public String res(HttpServletRequest request){
        System.out.println("res get -- request info:");
        System.out.println(request.getRemoteAddr());
        return "res";
    }


    public void threadStart() {

        List<WebUrl> urlList = webUrlService.getAll();
        for (WebUrl webUrl: urlList){
            System.out.println("after for id: "+ webUrl.getId());
            //threads per url
            new Thread(()->{
                System.out.println("Thread is started for url: " + webUrl.getUrl());
                for (int j = 0;j<webUrl.getIterationLimit();j++){

                    String uri = webUrl.getUrl();

                    webUrl.iterationIncrement();

                    System.out.println("---------------\nclient uri: "+uri +"\niteration: "
                            +webUrl.getIterationAmount()+"\nlimit: "+ webUrl.getIterationLimit());

                    RestTemplate restTemplate = new RestTemplate();

                    try {
                        //Main work for request--------------------------------------------------
                        String result = restTemplate.getForObject(uri, String.class);
                        System.out.println("before save id: "+ webUrl.getId());
                        webUrlService.saveUrl(webUrl);

                        System.out.println(result);

                        Thread.sleep((long) ((3600*1000)/webUrl.getReqPerHour()));

                        //end of main work-------------------------------------------------------

                    }catch (IllegalArgumentException e){
                        e.printStackTrace();
                        webUrlService.deleteUrl(webUrl);
                        break;
                    } catch (StaleObjectStateException e){
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }



            }).start();
            webUrlService.deleteUrl(webUrl);

        }
    }

}
