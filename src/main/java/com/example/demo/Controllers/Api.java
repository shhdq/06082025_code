package com.example.demo.Controllers;

import com.example.demo.Models.User;
import com.example.demo.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Api {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getPage() {
        return "Sveika, pasaule!";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        userRepo.save(user);

        return "Lietotājs ir izveidots!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {

        User deleteUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Lietotājs ar ID: " + id + " nav atrasts!"));

        userRepo.delete(deleteUser);

        return "Lietotājs tika izdzēsts";

    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {

        User updateUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Lietotājs ar ID: " + id + " nav atrasts!"));

        updateUser.setUsername(user.getUsername());

        userRepo.save(updateUser);

        return "Lietotājs tika atjaunots!";

    }

}
