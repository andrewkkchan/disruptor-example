package com.infinitelambda.disruptorexample.provider.disruptor;

import com.infinitelambda.disruptorexample.event.LongEvent;
import com.infinitelambda.disruptorexample.handler.LongEventHandler;
import com.infinitelambda.disruptorexample.provider.Publisher;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.ByteBuffer;

@Component
@RequiredArgsConstructor
@Slf4j
public class DisruptorPublisher implements Publisher {
    private final Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, 1024, DaemonThreadFactory.INSTANCE);
    private final LongEventHandler longEventHandler;

    @PostConstruct
    private void setup() {
        disruptor.handleEventsWith(longEventHandler);
        disruptor.start();
    }

    @Override
    public void publish(long l) {
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.putLong(0, l);
        ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer.getLong(0)), bb);
    }

}
