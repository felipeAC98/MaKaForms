/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.compiladores.makaforms;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author felip
 */
public class TabelaDeSimbolos {
    
    public enum TipoMKF{

    }
    
    //Definicao do construtor
    class EntradaTabelaDeSimbolos{
        String ID, conteudo;
    
        //A tabela de simbolos inicialmente ira guardar os identificadores do HTML
        private EntradaTabelaDeSimbolos(String ID){

            this.ID=ID; 
            
        }
    }

    //Inicializando tabela de simbolos
    private final Map<String, EntradaTabelaDeSimbolos> tabela;
     
    public TabelaDeSimbolos()
    {
        this.tabela = new HashMap<>();

    }
    
    //Inserindo identificador na tabela
    public void adicionar(String nome)
    {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome));
    }
    
   //Verificando se identificador existe na tabela
    public boolean existe(String nome)
    {
        return tabela.containsKey(nome);
    }
}
