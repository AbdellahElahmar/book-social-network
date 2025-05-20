package com.abdellah.book.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {
    @NotEmpty(message = "firstName must not be null")
    @NotBlank(message = "firstName must not be null")
    private String firstName;
    @NotEmpty(message = "lastName must not be null")
    @NotBlank(message = "lastName must not be null")
    private String lastName;

    @Email(message = "Email is not well formatted !")
    @NotEmpty(message = "Email must not be null")
    @NotBlank(message = "Email must not be null")
    private String email;


    @NotEmpty(message = "Password must not be null")
    @NotBlank(message = "Password must not be null")
    @Size(min = 8, message = "Password should be 8 caracters long minimum.")
    private String password;
}
