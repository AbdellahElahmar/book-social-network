package com.abdellah.book.auth;


import com.abdellah.book.email.EmailService;
import com.abdellah.book.email.EmailTemplateName;
import com.abdellah.book.role.repository.RoleRepository;
import com.abdellah.book.user.Repository.TokenRepository;
import com.abdellah.book.user.Repository.UserRepository;
import com.abdellah.book.user.domain.Token;
import com.abdellah.book.user.domain.User;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;

    @Value("${spring.application.mailing.frontend.activation-url}")
    private String activationCode;


    public void register(RegistrationRequest request) throws MessagingException {
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("User was not initialized."));

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);

    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        emailService.sendMail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationCode,
                newToken,
                "Account activation"

        );
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationToken(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDate.from(LocalDateTime.now()))
                .expiresAt(LocalDate.from(LocalDateTime.now().plusMinutes(15)))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationToken(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i< length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));


        }
        return codeBuilder.toString();
    }
}
