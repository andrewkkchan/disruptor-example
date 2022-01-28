package com.infinitelambda.disruptorexample.controller;

import com.infinitelambda.disruptorexample.provider.disruptor.DisruptorPublisher;
import com.infinitelambda.disruptorexample.provider.queue.QueuePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PublishController {
    private final DisruptorPublisher disruptorPublisher;
    private final QueuePublisher queuePublisher;
    @PostMapping("publish/disruptor")
    public void publishToDisruptor(){
        for (long l = 0; l < 100000000; l++){
            disruptorPublisher.publish(l);
        }
    }

    @PostMapping("publish/queue")
    public void publishToQueue(){
        for (long l = 0; l < 100000; l++){
            queuePublisher.publish(l);
        }
    }
}
