package com.infinitelambda.disruptorexample.controller;

import ch.qos.logback.core.util.ExecutorServiceUtil;
import com.infinitelambda.disruptorexample.provider.disruptor.DisruptorPublisher;
import com.infinitelambda.disruptorexample.provider.queue.QueuePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;

import static com.infinitelambda.disruptorexample.config.Config.SIZE;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PublishController {
    private final DisruptorPublisher disruptorPublisher;
    private final QueuePublisher queuePublisher;

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
