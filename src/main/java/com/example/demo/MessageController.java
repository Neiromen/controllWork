package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class MessageController {

    private final List<Message> messageList = new ArrayList<>();

    @PostMapping("")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        message.setId(messageList.size());
        message.setCreatedAt(LocalDateTime.from(LocalDateTime.now()));
        messageList.add(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageList.reversed());
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable int messageId) {
        return ResponseEntity.ok(messageList.get(messageId));
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<Message> updateMessage(@PathVariable int messageId, @RequestBody Message message) {
        message.setId(messageId);
        messageList.set(messageId, message);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable int messageId) {
        messageList.remove(messageId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteAllMessages() {
        messageList.clear();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countMessagesLength() {
        return ResponseEntity.ok(messageList.size());
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Message>> getAllAuthorMessages(@PathVariable String author) {
        List<Message> authorMessages = new ArrayList<>();
        for (Message message : messageList) {
            if (message.getAuthor().equals(author)) {
                authorMessages.add(message);
            }
        }
        return ResponseEntity.ok(authorMessages);
    }

    @GetMapping("/latest")
    public ResponseEntity<List<Message>> getLastTwentyMessages() {
        int endIndex = 20;
        if (messageList.size() < 20) {
            endIndex = messageList.size();
        }
        List<Message> messagesSortedByCreateTime = messageList.stream()
                .sorted(Comparator.comparing(Message::getCreatedAt)).toList().reversed().subList(0, endIndex);
        return ResponseEntity.ok(messagesSortedByCreateTime);
    }

    @GetMapping("/cheapest")
    public ResponseEntity<List<Message>> getCheapest() {
        List<Message> cheapest = messageList.stream().sorted(Comparator.comparing(Message::getPrice)).toList();
        return ResponseEntity.ok(cheapest);
    }

    @GetMapping("/expensive")
    public ResponseEntity<List<Message>> getExpensive() {
        List<Message> expensiveMessages = messageList.stream().sorted(Comparator.comparing(Message::getPrice)).toList().reversed();
        return ResponseEntity.ok(expensiveMessages);
    }

    @GetMapping("/today")
    public ResponseEntity<List<Message>> getTodayMessages() {
        LocalDateTime now = LocalDateTime.now();
        List<Message> todayMessages = messageList.stream().
                filter(message -> Duration.between(now, message.getCreatedAt()).toSeconds() < 86000).toList();
        return ResponseEntity.ok(todayMessages);
    }

    @GetMapping("/search/{word}")
    public ResponseEntity<List<Message>> searchMessagesByWord(@PathVariable String word) {
        List<Message> foundedMessages = new ArrayList<>();
        for (Message message:messageList){
            if(message.getMessage().contains(word) || message.getTitle().contains(word)){
                foundedMessages.add(message);
            }
        }
        return ResponseEntity.ok(foundedMessages);
    }
}
