package br.ufsm.csi.flutter_back.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.ufsm.csi.flutter_back.model.Produto;

public class ProdutoDAO {
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;

    public boolean addProduto(Produto produto){
        try(Connection connection = new ConectaDB().getConnection()){
            this.sql = "INSERT INTO produtos (nome, valor) VALUES (?, ?)";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, produto.getNome().toUpperCase());
            this.preparedStatement.setDouble(2, produto.getValor());
            this.preparedStatement.execute();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduto(Produto produto, int codigo){
        try(Connection connection = new ConectaDB().getConnection()){
            this.sql = "UPDATE produtos SET nome = ?, valor = ? WHERE codigo = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, produto.getNome().toUpperCase());
            this.preparedStatement.setDouble(2, produto.getValor());
            this.preparedStatement.setInt(3, codigo);
            this.preparedStatement.execute();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduto(int codigo){
        try(Connection connection = new ConectaDB().getConnection()){
            this.sql = "DELETE FROM produtos WHERE codigo = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, codigo);
            this.preparedStatement.execute();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Produto> getProdutos(){
        try(Connection connection = new ConectaDB().getConnection()){
            this.sql = "SELECT * FROM produtos";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();
            ArrayList<Produto> produtos = new ArrayList<>();
            while(this.resultSet.next()){
                int codigo = this.resultSet.getInt("codigo");
                String nome = this.resultSet.getString("nome");
                double valor = this.resultSet.getDouble("valor");
                produtos.add(Produto.builder().codigo(codigo).nome(nome).valor(valor).build());
            }
            return produtos;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Produto getProduto(int codigo){
        try(Connection connection = new ConectaDB().getConnection()){
            this.sql = "SELECT * FROM produtos WHERE codigo = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, codigo);
            this.resultSet = this.preparedStatement.executeQuery();
            if(this.resultSet.next()){
                String nome = this.resultSet.getString("nome");
                double valor = this.resultSet.getDouble("valor");
                return Produto.builder().codigo(codigo).nome(nome).valor(valor).build();
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
