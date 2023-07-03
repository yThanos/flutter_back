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

    public String addProduto(Produto produto){
        try(Connection connection = new ConectaDB().getConnection()){
            this.sql = "INSERT INTO produto (nome, valor) VALUES (?, ?)";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, produto.getNome());
            this.preparedStatement.setDouble(2, produto.getValor());
            this.preparedStatement.execute();
            return "Produto adicionado com sucesso!";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Erro ao adicionar produto!";
        }
    }

    public String updateProduto(Produto produto, int codigo){
        try(Connection connection = new ConectaDB().getConnection()){
            this.sql = "UPDATE produto SET nome = ?, valor = ? WHERE codigo = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, produto.getNome());
            this.preparedStatement.setDouble(2, produto.getValor());
            this.preparedStatement.setInt(3, codigo);
            this.preparedStatement.execute();
            return "Produto atualizado com sucesso!";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Erro ao atualizar produto!";
        }
    }

    public String deleteProduto(int codigo){
        try(Connection connection = new ConectaDB().getConnection()){
            this.sql = "DELETE FROM produto WHERE codigo = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, codigo);
            this.preparedStatement.execute();
            return "Produto deletado com sucesso!";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Erro ao deletar produto!";
        }
    }

    public ArrayList<Produto> getProdutos(){
        try(Connection connection = new ConectaDB().getConnection()){
            this.sql = "SELECT * FROM produto";
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
            this.sql = "SELECT * FROM produto WHERE codigo = ?";
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
