package br.ufsm.csi.flutter_back.db;

import br.ufsm.csi.flutter_back.model.Lote;
import br.ufsm.csi.flutter_back.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoteDAO {
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;

    public ArrayList<Lote> getLotes() {
        ArrayList<Lote> lotes = new ArrayList<>();
        try(Connection con = new ConectaDB().getConnection()){
            this.sql= "SELECT * FROM lotes";
            this.preparedStatement = con.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                int codigo = this.resultSet.getInt("codigo");
                Produto produto = new ProdutoDAO().getProduto(this.resultSet.getInt("codigo_produto"));
                long lote = this.resultSet.getLong("lote");
                String validade = this.resultSet.getString("validade");
                int quantidade = this.resultSet.getInt("quantidade");
                lotes.add(Lote.builder().codigo(codigo).produto(produto).lote(lote).validade(validade).quantidade(quantidade).build());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lotes;
    }
}
