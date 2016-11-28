/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.interact.web;

import java.util.Date;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Root
 */
@Controller
public class LiveTimelineController {
    @MessageMapping("/greeting")
    public String handle(String greeting) {
        return "[" + (new Date()).getTime() + ": " + greeting;
    }
}
