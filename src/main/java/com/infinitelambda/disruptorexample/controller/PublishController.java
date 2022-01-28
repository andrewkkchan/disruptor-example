package com.infinitelambda.disruptorexample.controller;

import ch.qos.logback.core.util.ExecutorServiceUtil;
import com.infinitelambda.disruptorexample.provider.disruptor.DisruptorPublisher;
import com.infinitelambda.disruptorexample.provider.queue.QueuePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;

@RestController
@RequiredArgsConstructor
public class PublishController {
    private final DisruptorPublisher disruptorPublisher;
    private final QueuePublisher queuePublisher;
    public static final int SIZE = 100000000;

    @PostMapping("publish/disruptor")
    public void publishToDisruptor() {
        ExecutorService executorService = ExecutorServiceUtil.newExecutorService();
        executorService.submit(() -> {
            for (long l = 0; l < SIZE; l++) {
                disruptorPublisher.publish(l);
            }
        });
    }

    @PostMapping("publish/queue")
    public void publishToQueue() {
        ExecutorService executorService = ExecutorServiceUtil.newExecutorService();
        executorService.submit(() -> {
            for (long l = 0; l < SIZE; l++) {
                queuePublisher.publish(l);
            }
        });
    }
}
