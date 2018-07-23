/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theam.Rest.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import theam.Rest.models.Greeting;

/**
 *
 * @author equipo
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        Greeting greed = new Greeting(counter.incrementAndGet(), String.format(template, name));
        greed.toString();
        return greed;
    }
    @RequestMapping("/bye")
    public Greeting goodBye(){
        Greeting bye = new Greeting(counter.incrementAndGet(), "Good Bye");
        return bye;
    }
}
