package com.study.springbootapp.service;

import com.study.springbootapp.dto.UserCreateDTO;
import com.study.springbootapp.dto.UserDTO;
import com.study.springbootapp.exceptions.DuplicateCpfException;
import com.study.springbootapp.exceptions.UserNotFoundException;
import com.study.springbootapp.model.Users;
import com.study.springbootapp.repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserCreateDTO cadastrarNovoUsuario(UserCreateDTO userCreateDTO) {
        Users user = new Users(userCreateDTO);
        try {
            user = userRepository.save(user);
            return userCreateDTO;
        } catch (DataIntegrityViolationException e) {
            // Mapear para exceções mais específicas se necessário
            if (e.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException constraintException = (ConstraintViolationException) e.getCause();

                for (ConstraintViolation<?> violation : constraintException.getConstraintViolations()) {
                    if (violation.getPropertyPath().toString().equals("cpf")) {
                        throw new DuplicateCpfException("Já existe um usuário cadatrado com este CPF");
                    }
                }
            }
            throw new UserNotFoundException("Erro ao tentar salvar o usuário: " + e.getMessage());
        }
    }
    public List<UserDTO> obterTodosUsuarios(){
        try {
            List<Users> users = userRepository.findAll();
            return  users.stream()
                    .map(user -> new UserDTO(user)) // Converte cada Users para UserDTO
                    .collect(Collectors.toList()); // Coleta o resultado em uma lista
        }catch (UserNotFoundException ex){
            throw new UserNotFoundException("Não existem usuários cadastrados");
        }
    }
    public UserDTO obterUsuarioPorId (Long id){
        Users user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Nao existe usuário com id " + id + " cadastrado."));
        return new UserDTO(user);
    }

    public UserDTO atualizarUsuario (Long id, UserDTO userDTO){
        Optional<Users> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("Usuário(a) não encontrado(a) com id " + id);
        }
        var updatedUser = new Users(userDTO);
        updatedUser.setId(id);
        userRepository.save(updatedUser);
        return new UserDTO(updatedUser);
    }

    public UserDTO removerUsuario(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário(a) não encontrado(a) com id " + id));
        userRepository.deleteById(id);
        return new UserDTO(user);
    }
}
