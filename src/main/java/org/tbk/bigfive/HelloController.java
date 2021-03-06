package org.tbk.bigfive;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @RequestMapping("/helloworld")
    public String index() {
        return Mono.just("hello world").cache().block();
    }

}