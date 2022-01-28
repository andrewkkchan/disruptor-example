package com.infinitelambda.disruptorexample.handler;

import com.infinitelambda.disruptorexample.config.Config;
import com.infinitelambda.disruptorexample.event.LongEvent;
import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LongEventHandler implements EventHandler<LongEvent> {
    private long startTime;

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        if (event.getValue() == 0) {
            startTime = System.nanoTime();
        }
        if (event.getValue() % 1000000 == 0) {
            log.info("Event: " + event);
            log.info("Event content value: " + event.getValue());
        }
        if (event.getValue() == Config.SIZE - 1) {

            log.info("Run time for {} amount of events : {}", Config.SIZE, System.nanoTime() - startTime);

        }
    }
}
