package com.infinitelambda.disruptorexample.provider.queue;

import ch.qos.logback.core.util.ExecutorServiceUtil;
import com.infinitelambda.disruptorexample.event.LongEvent;
import com.infinitelambda.disruptorexample.handler.LongEventHandler;
import com.infinitelambda.disruptorexample.provider.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class QueuePublisher implements Publisher {
    private final BlockingQueue<LongEvent> blockingQueue = new LinkedBlockingQueue<>();
    private final LongEventHandler longEventHandler;

    @PostConstruct
    public void setup(){
        ExecutorService executorService = ExecutorServiceUtil.newExecutorService();
        executorService.submit(()->{
            while (true){
                LongEvent longEvent = blockingQueue.poll(1, TimeUnit.MINUTES);
                if (longEvent!= null) {
                    longEventHandler.onEvent(longEvent, 0, false);
                }
            }

        });

    }

    @Override
    public void publish(long l) {
        blockingQueue.add(new LongEvent(l));
    }
}
