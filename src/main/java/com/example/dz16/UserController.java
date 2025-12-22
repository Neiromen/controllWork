package com.example.dz16;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final List<UserEntity> userEntities = new ArrayList<>();

    @PostMapping("/create")
    public ResponseEntity<Integer> createUser(@RequestBody UserDTOInput userDTOInput){
        for (UserEntity u: userEntities){
            if(u.getUsername().equals(userDTOInput.getUsername())){
                return ResponseEntity.status(409).build();
            }
        }
        if(!userDTOInput.getPassword().equals(userDTOInput.getRepeatPassword())){
            return ResponseEntity.status(400).build();
        }
        UserEntity userEntity = userDTOInput.toEntity();
        userEntities.add(userEntity);
        return ResponseEntity.status(201).body(userEntities.size()-1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTOOutput> getUser(@PathVariable int id){
        if (id> userEntities.size()-1){
            return ResponseEntity.status(404).build();
        }
        UserEntity userEntity = userEntities.get(id);
        UserDTOOutput userDTOOutput = userEntity.toDTOOut();
        return ResponseEntity.ok(userDTOOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        if (id> userEntities.size()-1){
            return ResponseEntity.status(404).build();
        }
        userEntities.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable int id, @RequestBody UserEntity userEntity){
        if (id> userEntities.size()-1){
            return ResponseEntity.status(404).build();
        }
        userEntities.set(id, userEntity);
        return ResponseEntity.ok(userEntity);
    }

    @GetMapping("/all")
    public List<UserDTOOutput> getAllUsers(@RequestParam int age){
        List<UserDTOOutput> userDTOOutputs = new ArrayList<>();
        for (UserEntity userEntity:userEntities){
            if(Math.abs(userEntity.getAge()-age)<=5){
                userDTOOutputs.add(userEntity.toDTOOut());
            }
        }
        return ResponseEntity.ok(userDTOOutputs).getBody();
    }


}
