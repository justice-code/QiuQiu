package org.eddy.cglib;

import org.junit.Test;

/**
 * Created by eddy on 2017/5/9.
 */
public class CglibTest {

    private CglibProxy cglibProxy = new CglibProxy();

    @Test
    public void test() {
        User user = (User) cglibProxy.createProxy(new User());
        User user2 = (User) cglibProxy.createProxy(new User());
        User user3 = (User) cglibProxy.createProxy(new User());
        User user4 = (User) cglibProxy.createProxy(new User());
        User user5 = (User) cglibProxy.createProxy(new User());
        System.out.println(user.getName());
        System.out.println(user.getAge());
        user.setAge(29);
        int age = user.setAndGetAge(29);
        System.out.println(age);
    }
}
