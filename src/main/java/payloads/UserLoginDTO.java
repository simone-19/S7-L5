package payloads;

public record UserLoginDTO(
        @NotNull(message = "email cannot be null")
        @NotEmpty(message = "email cannot be empty")
        @Pattern(regexp = "^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email not valid")
        String email,
        @NotNull(message = "Password cannot be null")
        @NotEmpty(message = "Password cannot be empty")
        @Size(min = 6, max = 30, message = "Password must be between 6 e 30 chars")
        String password
) {
}
