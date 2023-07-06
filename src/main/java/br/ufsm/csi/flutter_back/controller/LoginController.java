package br.ufsm.csi.flutter_back.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.flutter_back.model.Usuario;

@RestController
@RequestMapping
public class LoginController {
    @PostMapping("/login")
    public String login(Usuario user){
        if(user.getEmail() == "teste" && user.getSenha() == "1234"){
            return "token";
        }
        else{
            return "Erro ao efetuar login!";
        }
    }
}
