package br.ufsm.csi.flutter_back.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.flutter_back.db.ProdutoDAO;
import br.ufsm.csi.flutter_back.model.Produto;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @GetMapping
    public ArrayList<Produto> getProdutos(){
        return new ProdutoDAO().getProdutos();
    }

    @PostMapping
    public void addProduto(@RequestBody Produto produto){
        if(new ProdutoDAO().addProduto(produto)){
            System.out.println("Produto adicionado com sucesso!");
        } else {
            System.out.println("Erro ao adicionar produto!");
        }
    }

    @GetMapping("/{id}")
    public Produto getProduto(@PathVariable int id){
        return new ProdutoDAO().getProduto(id);
    }
}
