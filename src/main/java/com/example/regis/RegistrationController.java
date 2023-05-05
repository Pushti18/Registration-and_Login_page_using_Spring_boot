//package com.example.regis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/user/")
//public class RegistrationController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/check")
//    public String check() {
//        return "worked...";
//    }
//
//    @GetMapping("/register")
//    public List<User> showRegistrationForm() {
//        return userRepository.findAll();
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody User user) {
//        User existingUser = userRepository.findByUsername(user.getUsername());
//        if(existingUser!=null){
//            return ResponseEntity.status(402).body("Username alredy exists. You can Login");
//        }
//        userRepository.save(user);
//        return ResponseEntity.ok("Regitered Successfully");
//    }
//}
//
package com.example.regis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/check")
    public String check() {
        return "worked...";
    }

    @GetMapping("/register")
    public List<User> showRegistrationForm() {
        return userRepository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if(existingUser!=null){
            return ResponseEntity.status(402).body("Username already exists. You can Login");
        }

        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if (!user.getPassword().matches(passwordRegex)) {
            return ResponseEntity.status(400).body("Password must contain at least 8 characters, one lowercase letter, one uppercase letter, one digit and one special character.");
        }

        userRepository.save(user);
        return ResponseEntity.ok("Registered Successfully");
    }
}

