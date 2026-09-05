package com.vedmint.www.pratik01.Database;
import com.vedmint.www.pratik01.InterFace.Database;
public class Pqsql implements Database {
    @Override
    public String connect() {
        return "Connected to PostgreSQL";
    }
}
