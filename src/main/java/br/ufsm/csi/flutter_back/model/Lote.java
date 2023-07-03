package br.ufsm.csi.flutter_back.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lote {
    private int codigo;
    private int id_produto;
    private long lote;
    private String validade;
    private int quantidade;
    
}
