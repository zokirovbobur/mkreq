package bbro.mkreq.webUrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("webUrl")
public class WebUrlController {

    @Autowired
    WebUrlService webUrlService;

    @GetMapping
    public WebUrlForUser sampleUrl(){
        return new WebUrlForUser();
    }

    @PostMapping
    public String saveUrl(@RequestBody WebUrl webUrl){
        return webUrlService.saveUrl(webUrl);
    }
    @GetMapping("/all")
    public List<WebUrl> getAll(){
        return webUrlService.getAll();
    }
    @DeleteMapping
    public boolean deleteUrl(@RequestBody WebUrl webUrl){
        webUrlService.deleteUrl(webUrl);
        return true;
    }


}
