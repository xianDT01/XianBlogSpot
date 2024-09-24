package com.example.BlogSpot.XianBlogSpot.service;

import com.example.BlogSpot.XianBlogSpot.model.User;
import com.example.BlogSpot.XianBlogSpot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Método para obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Método para guardar un nuevo usuario
    public User saveUser(User user) {
        return userRepository.save(user);
    }


}
