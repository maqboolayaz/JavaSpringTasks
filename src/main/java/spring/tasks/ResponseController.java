package spring.tasks;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {

    @RequestMapping("/test")
    public String Test(@RequestParam(value="str") String str) {
        return "This is the response";
    }
}
