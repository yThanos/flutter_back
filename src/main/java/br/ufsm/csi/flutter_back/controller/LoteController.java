package br.ufsm.csi.flutter_back.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.flutter_back.db.LoteDAO;
import br.ufsm.csi.flutter_back.model.Lote;

@RestController
@RequestMapping("/lote")
public class LoteController {
    @GetMapping
    public ArrayList<Lote> getLotes(){
        return new LoteDAO().getLotes();
    }

    @PostMapping
    public void addLote(@RequestBody Lote lote){
        if(new LoteDAO().addLote(lote)){
            System.out.println("Lote adicionado com sucesso!");
        } else {
            System.out.println("Erro ao adicionar lote!");
        }
    }

    @GetMapping("/{id}")
    public Lote getLote(int codigo){
        return new LoteDAO().getLote(codigo);
    }
}
