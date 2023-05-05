package com.example.regis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser == null) {
            return ResponseEntity.status(404).body("User not found. Please register.");
        } else if (!foundUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(401).body("Wrong password . You can reset your password by visiting /api/login/resetPassword");
        } else {
            return ResponseEntity.ok("Login successful");
        }
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser == null) {
            return ResponseEntity.status(404).body("User not found");
        } else {
            String password = user.getPassword();
            if (password == null || password.isEmpty() || !password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
                return ResponseEntity.status(400).body("Invalid password. Password should contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character, and should be at least 8 characters long.");
            }
            foundUser.setPassword(password);
            userRepository.save(foundUser);
            return ResponseEntity.ok("Password updated Successfully");
        }
    }

//    @PostMapping("/resetPassword")
//    public ResponseEntity<?> resetPassword(@RequestBody User user) {
//        User foundUser = userRepository.findByUsername(user.getUsername());
//        if (foundUser == null) {
//            return ResponseEntity.status(404).body("User not found");
//        } else {
//            foundUser.setPassword(user.getPassword());
//            userRepository.save(foundUser);
//            return ResponseEntity.ok("Password updated Successfully");
//        }
//    }


}
