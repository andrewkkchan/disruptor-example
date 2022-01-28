package com.infinitelambda.disruptorexample.controller;

import com.infinitelambda.disruptorexample.disruptor.DisruptorProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PublishController {
    private final DisruptorProvider disruptorProvider;
    @PostMapping("publish/disruptor")
    public void publishToDisruptor(){
        for (long l = 0; l < 100000; l++){
            disruptorProvider.publish(l);
        }

    }
}
