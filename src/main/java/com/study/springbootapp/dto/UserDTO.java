package com.study.springbootapp.dto;

import com.study.springbootapp.enumerators.Sexo;
import com.study.springbootapp.model.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

public class UserDTO {

    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotNull
    @UniqueElements
    private String cpf;
    @NotNull
    private int idade;
    @NotNull
    private Sexo sexo;

    // Construtores
    public UserDTO() {
    }

    public UserDTO(Users user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.idade = user.getIdade();
        this.sexo = user.getSexo();
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
