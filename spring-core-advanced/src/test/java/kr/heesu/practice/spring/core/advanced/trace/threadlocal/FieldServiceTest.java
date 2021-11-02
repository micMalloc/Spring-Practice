package kr.heesu.practice.spring.core.advanced.trace.threadlocal;

import kr.heesu.practice.spring.core.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();    //A실행 sleep(2000); //동시성 문제 발생X
        sleep(100);   // 1초 미만일 시 동시성 문제 발생 O 이외 발생 X
        threadB.start();   //B실행

        sleep(2000); //동시성 문제 발생O
        log.info("main exit");
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
