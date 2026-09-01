package com.vedmint.com.pratik.entity;

import com.vedmint.com.pratik.Interface.Body;
import org.springframework.stereotype.Component;

@Component
public class Human implements Body {
    @Override
    public void move() {
        System.out.println("Human is moving");
    }
    @Override
    public void breathe() {
        System.out.println("Human is breathing");
    }
    @Override
    public void think() {
        System.out.println("Human is thinking");
    }
    @Override
    public void feel() {
        System.out.println("Human is feeling");
    }
}
