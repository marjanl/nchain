package com.nchain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {
        "spring.liquibase.enabled=true"
})
class ApplicationTests {

    @Test
    void applicationContextLoads() {
        System.out.println("application loads");
    }

}