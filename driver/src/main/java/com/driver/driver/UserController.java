package com.driver.driver;
import com.driver.driver.model.User;
import com.driver.driver.service.UserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
  //  private final PasswordEncoder passwordEncoder;
//    @PostMapping("/register")
//    public User register(@RequestBody User user) {
//        return userService.register(user);
//    }
//    @PostMapping("/user/register")
//    public String signin(@RequestBody LoginDto login){
//
//    }

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody User user) throws Exception {

        userService.register(user);
        return "register successful";
    }
    @GetMapping("/login")
    public String showLoginForm(@Valid @RequestBody LoginDto user) {
       if(userService.Login(user.getEmail(), user.getPassword()))
        {
            return "suceess";
        }else
        { return "not verified";}

    }

}
