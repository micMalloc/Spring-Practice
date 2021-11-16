package kr.heesu.practice.spring.core.advanced.proxy.application.v3;

import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class OrderRepositoryV3 {

    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000);
    }

    private void sleep(int interval) {
        try {
            TimeUnit.MILLISECONDS.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
