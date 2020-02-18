package cn.edu.hust.nlp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hust.travel.mapper")
@SpringBootApplication
public class NLPApplication {

    public static void main(String[] args) {
        SpringApplication.run(NLPApplication.class, args);
    }
}
