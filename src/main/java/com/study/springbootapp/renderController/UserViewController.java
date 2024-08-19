package com.study.springbootapp.renderController;

import com.study.springbootapp.dto.UserCreateDTO;
import com.study.springbootapp.dto.UserDTO;
import com.study.springbootapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        model.addAttribute("userCreateDTO", new UserCreateDTO());
        return "create-user";
    }

    @PostMapping
    public String cadastrarNovoUsuario(@ModelAttribute @Valid UserCreateDTO userCreateDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Se houver erros de validação, retorna o formulário com erros
            return "create-user";
        }
        // Se a validação for bem-sucedida, chama o serviço para salvar o usuário
        userService.cadastrarNovoUsuario(userCreateDTO);
        return "redirect:/user/list";
    }
}
