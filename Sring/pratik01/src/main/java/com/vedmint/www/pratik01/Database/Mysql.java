package com.vedmint.www.pratik01.Database;
import com.vedmint.www.pratik01.InterFace.Database;
import org.springframework.stereotype.Component;
@Component
public class Mysql implements Database {
    @Override
    public  String connect() {
            return "Connected to MySQL";
    }
}
