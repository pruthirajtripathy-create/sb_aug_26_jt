package com.jt;

import org.springframework.stereotype.Component;

@Component
public class Greeting {
    public Greeting(){
        System.out.println("Greeting obj is Construted");
    }
    public void sayHii(){
        System.out.println("hiiiiiiiiiiiiiiii");
    }
}
