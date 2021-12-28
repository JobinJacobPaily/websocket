package com.jobs.websocket.springwebsocket.controller;

import com.jobs.websocket.springwebsocket.pojos.BroadcastMessage;
import com.jobs.websocket.springwebsocket.pojos.ReceivedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;

@EnableScheduling
@Controller
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/send")
    @SendTo("/broker/receive")
public BroadcastMessage recivedMessage(ReceivedMessage receivedMessage)  {

    return new BroadcastMessage(HtmlUtils.htmlEscape(receivedMessage.getMessage()));

}

@Scheduled(fixedRate = 5000)
public void sheduledMessage() {
        BroadcastMessage broadcastMessage = new BroadcastMessage();
        String date = new Date().toString();
        broadcastMessage.setMessage("Now the time is "+date);
        simpMessagingTemplate.convertAndSend("/broker/receive",broadcastMessage);
}
}
