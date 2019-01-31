package bbro.mkreq.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping("start")
    public void start() {
        requestService.start();
    }

    @GetMapping("res")
    public String res(HttpServletRequest request){
        return requestService.res(request);
    }

    @GetMapping("threadStart")
    public void threadStart() {
        requestService.threadStart();
    }
}
