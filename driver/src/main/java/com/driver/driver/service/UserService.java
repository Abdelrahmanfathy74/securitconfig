package com.driver.driver.service;



import com.driver.driver.Repo.UserRepo;
import com.driver.driver.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    public User getUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.orElseThrow(()->new RuntimeException("User not found")) ;
    }
    public List<User> findAll(){
        return userRepo.findAll();
    }
    public User save(User user) {
        return userRepo.save(user);
    }
    public  User findByEmail(String email) {
        Optional<User> user=userRepo.findByEmail(email);

        return user.orElseThrow(()->new RuntimeException("User not found"));
    }
    public User register(User user) throws Exception {
        String email = user.getEmail();
        Optional<User> existingUser = userRepo.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new Exception(String.format("User with the email address '%s' already exists.", email));
        }
        return userRepo.save(user);}
    public Boolean Login(String email,String password) {

        if (userRepo.findByEmail(email).isPresent()&&
                userRepo.findByEmail(email).get().getPassword().equals(password)) {

            return true;
        }else
            return false;
    }
    public void delete(Long id) {
        User user = userRepo.findById(id).get();
        userRepo.deleteById(user.getId());
    }
    public User update(User user) {return userRepo.save(user);}
}

