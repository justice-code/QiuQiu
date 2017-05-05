package org.eddy;

import org.junit.Test;

/**
 * Created by eddy on 2017/5/5.
 */
public class LambdaTest {

    @Test
    public void test() {
        Lambda lambda = () -> {
            System.out.println(this.getClass());
            System.out.println("abc");
        };
        lambda.test();
    }
}
