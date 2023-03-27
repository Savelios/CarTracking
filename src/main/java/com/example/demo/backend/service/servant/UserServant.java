package com.example.demo.backend.service.servant;

import com.example.demo.backend.domain.User;
import com.example.demo.backend.domain.enums.Roles;
import com.example.demo.backend.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServant {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServant(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser (String username, String password, String email, String phone, Roles role) throws Exception {

        if (username == null || username.isEmpty()) {
            throw new Exception("Поле username не может быть пустым!");
        }
        var userOptional = userRepository.findFirstByUsername(username);
        if (userOptional.isPresent()) {
            throw new Exception("Такой пользователь уже существует! Введите другой username.");
        }
        if (password == null || password.isEmpty()) {
            throw new Exception("Поле password не может быть пустым!");
        }
        checkPasswordFormat(password);

        if (email == null || email.isEmpty()) {
            throw new Exception("Поле email не может быть пустым!");
        }
        var emailOptional = userRepository.findFirstByEmail(email);
        if (emailOptional.isPresent()) {
            throw new Exception("Такой пользователь уже существует! Введите другой email.");
        }
        checkEmailFormat(email);
        if (phone == null || phone.isEmpty()) {
            throw new Exception("Поле номера телефона не может быть пустым!");
        }
        var phoneOptional = userRepository.findFirstByPhone(phone);
        if (phoneOptional.isPresent()) {
            throw new Exception("Такой пользователь уже существует! Введите другой номер телефона!.");
        }
        checkPhoneFormat(phone);

        var encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, email, phone, role);
        userRepository.save(user);
    }
    public void checkEmailFormat(String email) throws Exception {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        if (!email.matches(regex)) {
            throw new Exception("Не верный формат электронной почты!");
        }
    }
    public void checkPhoneFormat(String phone) throws Exception {
        String regex = "(^\\s*?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}\\s*?$)";
        if (!phone.matches(regex)) {
            throw new Exception("Не верный формат номера телефона!");
        }
    }
    public void checkPasswordFormat(String password) throws Exception {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$";
        if (!password.matches(passwordRegex)) {
            if (!password.matches(".*[0-9].*")) {
                throw new Exception("Пароль должен содержать хотя бы одну цифру, ");
            }
            if (!password.matches(".*[a-z].*")) {
                throw new Exception("Пароль должен содержать хотя бы одну строчную букву, ");
            }
            if (!password.matches(".*[A-Z].*")) {
                throw new Exception("Пароль должен содержать хотя бы одну заглавную букву, ");
            }
            if (!password.matches(".*[@#$%^&+=!*()].*")) {
                throw new Exception("Пароль должен содержать хотя бы один специальный символ (@#$%^&+=!*()), ");
            }
            if (password.length() < 7) {
                throw new Exception("Пароль должен содержать быть не менее 7 символов длиной, ");
            }
        }
    }
}