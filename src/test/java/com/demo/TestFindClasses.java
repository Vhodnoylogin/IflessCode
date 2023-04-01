package com.demo;

import com.demo.ifless.scanner.SuperScanner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestFindClasses {

    @Test
    public void testFindAnything() {
        var res = SuperScanner.ALL_CLASSES.getAllClasses();
        log.info("{}", res);

//        assertThatThrownBy(SuperScanner.ALL_CLASSES::getAllClasses)
//                .doesNotThrowAnyException();

//        assertThat(res)
//                .isIn(Router.class)
//                .isIn(CreateObjectException.class);
    }
}
