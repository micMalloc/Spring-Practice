package kr.heesu.practice.spring.core.advanced.application.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    public void save(String itemId) {
        // 저장 로직
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
