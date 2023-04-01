package com.demo;

import com.demo.experiment.classes.Daughter;
import com.demo.experiment.classes.Son2;
import com.demo.experiment.router.TestRouter2;
import com.demo.ifless.exeptions.NoDefaultObjectException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class TestRouter2Run {

    static TestRouter2 rout;

    @BeforeAll
    public static void init() {
        rout = new TestRouter2();
    }

    @Test
    public void testSon() {
        var name = Son2.class.getName();
        var res = rout.getObject(name).get();

        log.info("{}", res);
        assertThat(res)
                .isEqualTo(name);
    }

    @Test
    public void testDaughter() {
        var name = Daughter.class.getName();
        var res = rout.getObject(name).get();

        log.info("{}", res);
        assertThat(res)
                .isEqualTo(name);
    }

    @Test
    public void testDefault() {
        assertThatThrownBy(() -> rout.getObject("Not exist").get())
                .isInstanceOf(NoDefaultObjectException.class);
    }
}
