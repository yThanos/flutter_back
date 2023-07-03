package br.ufsm.csi.flutter_back.service;

import br.ufsm.csi.flutter_back.db.UsuarioDAO;
import br.ufsm.csi.flutter_back.model.Usuario;

public class LoginService {
    public boolean isAthenticated(Usuario user){
        Usuario userDB = new UsuarioDAO().getUser(user.getEmail());
        if(userDB != null){
            if(userDB.getSenha().equals(user.getSenha())){
                return true;
            } 
        }
        return false;
    }
}
