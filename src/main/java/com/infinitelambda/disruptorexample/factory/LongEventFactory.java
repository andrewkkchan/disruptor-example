package com.infinitelambda.disruptorexample.factory;

import com.infinitelambda.disruptorexample.event.LongEvent;
import com.lmax.disruptor.EventFactory;
import org.springframework.stereotype.Component;

@Component
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
