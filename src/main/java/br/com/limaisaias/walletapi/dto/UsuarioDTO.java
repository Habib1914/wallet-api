package br.com.limaisaias.walletapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    @Size(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres")
    private String name;
    @Email(message = "Email Inválido")
    private String email;
    @NotNull
    @Size(min = 6, message = "O password deve ter no minimo 6 caracteres")
    private String password;
}
