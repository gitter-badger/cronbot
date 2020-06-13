package io.outofbox.cronbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The entry point of the application
 *
 * @author ahelmy
 */
@SpringBootApplication
@EnableAutoConfiguration
public class CronbotApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(CronbotApplication.class, args);
    }

    @Autowired
    BCryptPasswordEncoder encoder;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(encoder.encode("password"));
    }
}
