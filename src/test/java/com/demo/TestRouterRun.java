package com.demo;

import com.demo.experiment.classes.Daughter;
import com.demo.experiment.classes.IParent;
import com.demo.experiment.classes.Son;
import com.demo.experiment.router.TestRouter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TestRouterRun {

    static TestRouter rout;

    @BeforeAll
    public static void init() {
        rout = new TestRouter();
    }

    @Test
    public void testSon() {
        var name = Son.class.getName();

        var res = rout.getObject("Sonny").get();

        log.info("{}", res);
        assertThat(res)
                .isEqualTo(name);
    }

    @Test
    public void testSon2() {
        var name = Son.class.getName();

        var res = rout.getObject(name).get();
        log.info("{}", res);
        assertThat(res)
                .isEqualTo(IParent.Def.class.getName());

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
        var res = rout.getObject("Not exist").get();

        log.info("{}", res);
        assertThat(res)
                .isEqualTo(IParent.Def.class.getName());
    }
}
