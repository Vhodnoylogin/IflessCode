package com.demo;

import com.demo.ifless.runtime.exeptions.CreateObjectException;
import com.demo.ifless.runtime.router.Router;
import com.demo.ifless.runtime.scanner.SuperScanner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TestFindClasses {

    @Test
    public void testFindAnything() {
        var res = SuperScanner.ALL_CLASSES.getAllClasses();

        // не работает, почему-то бросает экмепшн. Хотя запускается.
//        assertThatThrownBy(SuperScanner.ALL_CLASSES::getAllClasses)
//                .doesNotThrowAnyException();

        assertThat(res)
                .contains(Router.class)
                .contains(CreateObjectException.class);
    }
}
