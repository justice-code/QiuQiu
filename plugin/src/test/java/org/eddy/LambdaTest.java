package org.eddy;

import org.junit.Test;

/**
 * Created by eddy on 2017/5/5.
 */
public class LambdaTest {

    @Test
    public void test() {
        LambdaInterface lambda = () -> {
            System.out.println(this.getClass());//返回 class org.eddy.LambdaTest
            System.out.println("abc");
        };
        lambda.test();
    }
}
