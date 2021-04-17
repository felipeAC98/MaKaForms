/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.compiladores.makaforms;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author felip
 */
public class SemanticoUtils {

    public static List<String> errosSemanticos = new ArrayList<>();
    
    //Funcao responsavel por adicionar os erros semanticos em uma lista
    public static void adicionarErroSemantico(Token t, String mensagem)
    {
        int linha = t.getLine();
        int coluna = t.getCharPositionInLine();
        //System.out.println("erro adicionado"); 
        errosSemanticos.add(String.format("Linha %d: %s", linha, mensagem));
    }
    
}
