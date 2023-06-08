package ifma.com.jogos.locadorajogos.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteRequest(
    @NotNull
    @NotBlank
    String nome, 
    @Email
    @NotNull
    @NotBlank
    String email, 
    @NotNull
    @NotBlank
    String telefone, 
    @NotNull
    @NotBlank
    String senha) {
    
}
