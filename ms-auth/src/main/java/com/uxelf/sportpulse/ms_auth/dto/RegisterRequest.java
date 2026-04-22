package com.uxelf.sportpulse.ms_auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Data to register a new user")
public class RegisterRequest {

    @Schema(example = "Bob")
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^\\S+$", message = "Username must not contain spaces")
    @Size(min = 3, max = 50)
    private String username;

    @Schema(example = "bob@email.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(example = "Secret123")
    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Password must be at least 8 characters, include one uppercase letter and one number"
    )
    private String password;
}
