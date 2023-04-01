package com.demo;

import com.demo.experiment.router.TestRouter;
import com.demo.ifless.annotations.Marker;
import com.demo.ifless.scanner.SuperScanner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FindAnnoClasses {

    @Test
    public void testFindAnything(){
        var ss = SuperScanner.ALL_CLASSES.getAllClasses();

        for (Class<?> s : ss) {
            var anno = s.getAnnotation(Marker.class);
            log.info("{}", anno);

            if(anno != null) {
                var flag = anno.router() == TestRouter.class;
                log.info("{}", flag);
            }

        }
    }
}
