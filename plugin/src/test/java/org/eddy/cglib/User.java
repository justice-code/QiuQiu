package org.eddy.cglib;

import lombok.Getter;
import lombok.Setter;

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
}
