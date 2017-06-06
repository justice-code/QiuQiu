package org.eddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by eddy on 2017/6/6.
 */
@SpringBootApplication
@EnableTransactionManagement
public class ApplicationStart {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ApplicationStart.class);
        springApplication.addListeners(new ApplicationPidFileWriter("qiuqiu.pid"));
        springApplication.run(args);
    }
}
