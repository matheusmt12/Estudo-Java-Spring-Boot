package com.matheusmt.pvd.pvd.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotBlank(message = "O campo login é obrigatório")
    private String username;
    @NotBlank(message = "O campo password é obrigatório")
    private String password;

}
