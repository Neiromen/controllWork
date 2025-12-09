package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class MessageController {

    private final List<Message> messageList = new ArrayList<>();

    @PostMapping("")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        message.setId(messageList.size());
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
    public ResponseEntity<Message> updateMessage(@PathVariable int messageId,@RequestBody Message message){
        message.setId(messageId);
        messageList.set(messageId,message);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable int messageId){
        messageList.remove(messageId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteAllMessages(){
        messageList.clear();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countMessagesLength(){
        return ResponseEntity.ok(messageList.size());
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Message>> getAllAuthorMessages(@PathVariable String author){
        List<Message> authorMessages = new ArrayList<>();
        for (Message message:messageList){
            if (message.getAuthor().equals(author)){
                authorMessages.add(message);
            }
        }
        return ResponseEntity.ok(authorMessages);
    }
}
