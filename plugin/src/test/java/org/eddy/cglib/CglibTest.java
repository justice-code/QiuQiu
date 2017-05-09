package org.eddy.cglib;

import org.junit.Test;

/**
 * Created by eddy on 2017/5/9.
 */
public class CglibTest {

    @Test
    public void test() {
        CglibProxy cglibProxy = new CglibProxy();
        User user = (User) cglibProxy.createProxy(new User());
        System.out.println(user.getName());
        System.out.println(user.getAge());
        user.setAge(29);
        int age = user.setAndGetAge(29);
        System.out.println(age);
    }
}
