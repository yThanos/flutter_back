package br.ufsm.csi.flutter_back.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.flutter_back.db.ProdutoDAO;
import br.ufsm.csi.flutter_back.model.Produto;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @CrossOrigin(origins = "*")
    @GetMapping
    public ArrayList<Produto> getProdutos(){
        return new ProdutoDAO().getProdutos();
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public void addProduto(Produto produto){
        new ProdutoDAO().addProduto(produto);
    }
}
