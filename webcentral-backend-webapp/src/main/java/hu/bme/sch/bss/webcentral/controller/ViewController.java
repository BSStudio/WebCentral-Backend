package hu.bme.sch.bss.webcentral.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping({"", "video/{id}", "video/all"})
    public final String index() {
        return "forward:/index.html";
    }

}
