package br.ufsm.csi.flutter_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.flutter_back.model.Usuario;
import br.ufsm.csi.flutter_back.service.LoginService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario user){
        if(new LoginService().isAthenticated(user)){
            user.setToken("token");
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
