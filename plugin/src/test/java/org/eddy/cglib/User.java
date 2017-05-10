package org.eddy.cglib;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by eddy on 2017/5/9.
 */
@Getter
@Setter
public class User {

    private String name = "eddy";

    private int age = 28;


    public int setAndGetAge(int age) {
        this.age = age;
        return age;
    }

    private void test() {

    }

    public static void main(String[] args) {
        Method[] methods =  User.class.getMethods();
        Arrays.stream(methods).forEach(m -> {
            System.out.println(m.getName());
        });
    }
}
