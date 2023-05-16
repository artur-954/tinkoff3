package ru.tinkoff.edu.java.scrapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tgchats")
public class TgChatController {
    private final TgChatService chatService;

    public TgChatController(TgChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/{tgChatId}")
    public ResponseEntity<Void> registerChat(@PathVariable long tgChatId) {
        chatService.register(tgChatId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{tgChatId}")
    public ResponseEntity<Void> unregisterChat(@PathVariable long tgChatId) {
        chatService.unregister(tgChatId);
        return ResponseEntity.ok().build();
    }
}