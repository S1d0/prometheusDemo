package com.example.metryki;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HelloWorld {
    @Autowired
    private MicrometerService micrometerService;

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        micrometerService.incrementHello();
        return ResponseEntity.of(
                Optional.of("hello")
        );
    }

    @GetMapping("ciao")
    public ResponseEntity<String> ciao() {
        micrometerService.incrementCiao();
        return ResponseEntity.of(
                Optional.of("ciao")
        );
    }

    @GetMapping("hola")
    public ResponseEntity<String> hola() {
        micrometerService.incrementHola();
        return ResponseEntity.of(
                Optional.of("hola")
        );
    }
}


@Service
class MicrometerService {
    CompositeMeterRegistry registry;
    private Counter hello;
    private Counter ciao;
    private Counter hola;

    public MicrometerService(CompositeMeterRegistry registry) {
        this.registry = registry;

        hello = Counter.builder("greeting.counter")
                .tag("greeting", "hello")
                .register(registry);

        ciao = Counter.builder("greeting.counter")
                .tag("greeting", "ciao")
                .register(registry);

        hola = Counter.builder("greeting.counter")
                .tag("greeting", "hola")
                .register(registry);


    }

    void incrementHello() {
        hello.increment();
    }

    void incrementCiao() {
        ciao.increment();
    }

    void incrementHola() {
        hola.increment();
    }
}