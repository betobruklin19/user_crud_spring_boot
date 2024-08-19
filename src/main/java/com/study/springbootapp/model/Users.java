package com.study.springbootapp.model;

import com.study.springbootapp.dto.UserCreateDTO;
import com.study.springbootapp.dto.UserDTO;
import com.study.springbootapp.enumerators.Sexo;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;
    private String email;
    @Column(nullable = false, length = 14, unique = true)
    private String cpf;
    @Column(nullable = false, length = 2)
    private int idade;
    @Column(nullable = false)
    private Sexo sexo;

    public Users() {
    }

    public Users(Long id, String nome, String email, String cpf, int idade, Sexo sexo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.idade = idade;
        this.sexo = sexo;
    }

    public Users(UserCreateDTO userCreateDTO) {
        this.nome = userCreateDTO.getNome();
        this.email = userCreateDTO.getEmail();
        this.cpf = userCreateDTO.getCpf();
        this.idade = userCreateDTO.getIdade();
        this.sexo = userCreateDTO.getSexo();
    }

    public Users(UserDTO userDTO) {
        this.nome = userDTO.getNome();
        this.email = userDTO.getEmail();
        this.cpf = userDTO.getCpf();
        this.idade = userDTO.getIdade();
        this.sexo = userDTO.getSexo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
}
