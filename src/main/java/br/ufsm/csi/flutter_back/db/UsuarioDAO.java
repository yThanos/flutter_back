package br.ufsm.csi.flutter_back.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import br.ufsm.csi.flutter_back.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioDAO {
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;

    public Usuario getUser(String login){
        try(Connection connection = new ConectaDB().getConnection()){
            this.sql = "SELECT * FROM usuarios WHERE email = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, login.toUpperCase());
            this.resultSet = this.preparedStatement.executeQuery();
            if(this.resultSet.next()){
                int codigo = this.resultSet.getInt("codigo");
                String nome = this.resultSet.getString("nome");
                String email = this.resultSet.getString("email");
                String senha = new BCryptPasswordEncoder().encode(this.resultSet.getString("senha"));
                return Usuario.builder().codigo(codigo).nome(nome).email(email).senha(senha).build();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
