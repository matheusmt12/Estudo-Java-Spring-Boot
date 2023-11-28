package com.matheusmt.pvd.pvd.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "O campo nome é obrigatório")
    private String name;

    @Column(length = 45,nullable = false)
    @NotBlank(message = "o campo username é necessario")
    private String username;

    @Column(length = 45,nullable = false, unique = true)
    @NotBlank(message = "O campo password é necessario")
    private String password;

    private boolean isEnabled;

    @OneToMany(mappedBy = "user")
    private List<Sale> sales;

}
