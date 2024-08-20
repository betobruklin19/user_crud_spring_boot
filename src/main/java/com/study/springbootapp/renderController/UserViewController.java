package com.study.springbootapp.renderController;

import com.study.springbootapp.dto.UserCreateDTO;
import com.study.springbootapp.dto.UserDTO;
import com.study.springbootapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/delete-multiple")
    public String excluirMultiplosUsuarios(@RequestParam("selectedUsers") List<Long> selectedUsers,
                                             @RequestParam("action") String action,
                                             Model model) {
        if ("delete".equals(action)) {
            for (Long id : selectedUsers) {
                userService.removerUsuario(id);
            }
            return "redirect:/user/list";
        }

        return "redirect:/user/list";
    }
    @GetMapping("/edit/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model) {
        UserDTO userDTO = userService.obterUsuarioPorId(id);
        model.addAttribute("userDTO", userDTO);
        return "edit-user"; // Crie uma página para editar o usuário
    }

    @PostMapping("/edit")
    public String atualizarUsuario(@ModelAttribute @Valid UserDTO userDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-user"; // Retorna o formulário com erros
        }
        userService.atualizarUsuario(userDTO.getId(), userDTO);
        return "redirect:/user/list";
    }

    @GetMapping("/delete/{id}")
    public String excluirUsuario(@PathVariable("id") Long id) {
        userService.removerUsuario(id);
        return "redirect:/user/list";
    }

    @PostMapping("/update")
    public String atualizarUsuarios(@ModelAttribute List<UserDTO> userDTOs, Model model) {
        userService.atualizarMultiplosUsuarios(userDTOs);
        return "redirect:/user/list";
    }

}
