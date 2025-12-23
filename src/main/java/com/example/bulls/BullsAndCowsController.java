package com.example.bulls;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.random.RandomGenerator;

@RestController
@Data
public class BullsAndCowsController {
    private int randomNumber;



    @PostMapping("/startNewGame")
    public ResponseEntity<Void> startGame(){
        Random random = new Random();
        int min = 1000;
        int max = 10000;
        setRandomNumber(random.nextInt(max - min + 1) + min);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/try/{num}")
    public ResponseEntity<String> tryToFind(@PathVariable int num){
        if(randomNumber==num){
            return ResponseEntity.ok("Ты победил");
        }
        int bullsNum = 0;
        if(randomNumber/1000==num/1000){
            bullsNum+=1;
        }
        if(randomNumber%1000/100==num%1000/100){
            bullsNum+=1;
        }
        if(randomNumber%100/10==num%100/10){
            bullsNum+=1;
        }
        if(randomNumber%10==num%10){
            bullsNum+=1;
        }
        int temp = randomNumber;
        int cowsNum = 0;
        for (int i = 0; i < 4; i++) {
            temp/=10;
            String tempString = temp+"";
            int tempNum = num;
            for (int j = 0; j < 4; j++) {
                String currentSymb = ""+tempNum%10;
                long count = tempString.chars().filter(ch -> ch == currentSymb.toCharArray()[0]).count();
                if(count!=0){
                    cowsNum+=1;
                    break;
                }
                tempNum /=10;
            }
        }
        return ResponseEntity.ok("Быков: "+bullsNum+ ".Коров: "+cowsNum);
    }

    @GetMapping("/resultNum")
    public ResponseEntity<Integer> getResultNum(){
        return ResponseEntity.ok(randomNumber);
    }

}
