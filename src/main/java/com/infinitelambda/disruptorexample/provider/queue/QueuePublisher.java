package com.infinitelambda.disruptorexample.provider.queue;

import com.infinitelambda.disruptorexample.event.LongEvent;
import com.infinitelambda.disruptorexample.handler.LongEventHandler;
import com.infinitelambda.disruptorexample.provider.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@RequiredArgsConstructor
public class QueuePublisher implements Publisher {
    private final BlockingQueue<LongEvent> blockingQueue = new LinkedBlockingQueue<>();
    private final LongEventHandler longEventHandler;

    @PostConstruct
    public void setup(){

    }

    @Override
    public void publish(long l) {
        blockingQueue.add(new LongEvent(l));
    }
}
