package org.example;

import com.example.Foo;
import com.example.Bar;

@Scannable
public class Main {
    private final String mainField = "something";

    public static void main(String[] args) {
        System.out.println("I can access: " + new Foo());
        System.out.println("I can access Bar too: " + new Bar());
    }
}
