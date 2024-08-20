package com.study.springbootapp.renderController;

import com.study.springbootapp.dto.UserCreateDTO;
import com.study.springbootapp.dto.UserDTO;
import com.study.springbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserViewController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public String listarTodosUsuarios(Model model) {
        List<UserDTO> users = userService.obterTodosUsuarios();
        model.addAttribute("users", users);
        return "list-users";
    }

    @GetMapping("/create")
    public String criarNovoUsuario(Model model) {
        // Adicionar um UserCreateDTO vazio ao modelo, se necessário para o formulário
        model.addAttribute("userCreateDTO", new UserCreateDTO());
        return "create-user";
    }

    // Adicione outros métodos para renderizar páginas para editar e remover usuários
}
