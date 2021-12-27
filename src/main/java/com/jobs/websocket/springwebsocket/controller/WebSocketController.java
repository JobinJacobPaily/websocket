package com.jobs.websocket.springwebsocket.controller;

import com.jobs.websocket.springwebsocket.pojos.BroadcastMessage;
import com.jobs.websocket.springwebsocket.pojos.ReceivedMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketController {

    @MessageMapping("/send")
    @SendTo("/broker/receive")
public BroadcastMessage recivedMessage(ReceivedMessage receivedMessage)  {
    return new BroadcastMessage(HtmlUtils.htmlEscape(receivedMessage.getMessage()));
}
}
