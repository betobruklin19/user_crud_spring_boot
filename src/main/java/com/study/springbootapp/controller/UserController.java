package com.study.springbootapp.controller;

import com.study.springbootapp.dto.UserCreateDTO;
import com.study.springbootapp.dto.UserDTO;
import com.study.springbootapp.exceptions.UserNotFoundException;
import com.study.springbootapp.model.Users;
import com.study.springbootapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserCreateDTO> cadastrarNovoUsuario(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        var user = userService.cadastrarNovoUsuario(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> listarTodosUsuarios (){
        var users = userService.obterTodosUsuarios();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obterUsuarioPorId (@PathVariable("id") Long id){
        try {
            UserDTO userDTO = userService.obterUsuarioPorId(id);
            return ResponseEntity.ok(userDTO);
        }catch (UserNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable("id") Long id, @RequestBody @Valid UserDTO userDTO){
        try {
           var updateUser = userService.atualizarUsuario(id, userDTO);
           return ResponseEntity.ok(updateUser);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerUsuario (@PathVariable("id") Long id){
        try{
            UserDTO userDTO = userService.removerUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário(a) \"" + (userDTO.getNome() + "\" removido(a) do sistema."));
        }catch (UserNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
