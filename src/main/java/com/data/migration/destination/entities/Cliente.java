package com.data.migration.destination.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "client")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private String nome;
    private String rua;
    private String complemento;
    private String numero;
    private String cidade;
    private String estado;
    private String email;
    private String telefone;

}


