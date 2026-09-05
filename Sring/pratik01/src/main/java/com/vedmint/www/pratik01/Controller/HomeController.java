package com.vedmint.www.pratik01.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vedmint.www.pratik01.Database.Mysql;
@RestController
public class HomeController {
    private final Mysql mysql;
    public HomeController(Mysql mysql) {
        this.mysql = mysql;
    }
    @GetMapping("/")
    public String home() {
       
        return mysql.connect();
    }
}