package com.study.springbootapp.controller;

import com.study.springbootapp.dto.UserCreateDTO;
import com.study.springbootapp.dto.UserDTO;
import com.study.springbootapp.exceptions.UserNotFoundException;
import com.study.springbootapp.model.Users;
import com.study.springbootapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/home")
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

//    @GetMapping("/list")
//    public ResponseEntity<Page<UserDTO>> listarTodosUsuarios(@RequestParam(value = "page", defaultValue = "0") int page,
//                                                             @RequestParam(value = "size", defaultValue = "5") int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        var users = userService.obterTodosUsuarios(pageable);
//        return ResponseEntity.ok(users);
//    }

    @GetMapping("/list")
    public ResponseEntity<Page<UserDTO>> listarTodosUsuarios(@RequestParam(value = "query", required = false) String query,
                                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<UserDTO> userPage;

        if (query == null || query.isEmpty()) {
            userPage = userService.obterTodosUsuarios(pageable);
        } else {
            // Busca por nome
            userPage = userService.buscarUsuarios(query, pageable);
        }
        return ResponseEntity.ok(userPage);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> obterUsuarioPorId(@PathVariable("id") Long id) {
        try {
            UserDTO userDTO = userService.obterUsuarioPorId(id);
            return ResponseEntity.ok(userDTO);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable("id") Long id, @RequestBody @Valid UserDTO userDTO) {
        try {
            var updateUser = userService.atualizarUsuario(id, userDTO);
            return ResponseEntity.ok(updateUser);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerUsuario(@PathVariable("id") Long id) {
        try {
            UserDTO userDTO = userService.removerUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário(a) \"" + (userDTO.getNome() + "\" removido(a) do sistema."));
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
