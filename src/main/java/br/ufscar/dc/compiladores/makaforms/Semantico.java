/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.compiladores.makaforms;

import java.io.FileOutputStream;

/**
 *
 * @author mariz
 */
public class Semantico extends makaformsBaseVisitor{
    public TabelaDeSimbolos tabela;
    FileOutputStream saida;
    
    public TabelaDeSimbolos getTabela() {
        return tabela;
    }
    
    @Override 
    public Object visitPrograma(makaformsParser.ProgramaContext ctx) { 
        //Inicializando tabela de simbolos
        
        tabela= new TabelaDeSimbolos();
        return visitChildren(ctx); 
    }
    
     @Override
     public Object visitIdentCampo(makaformsParser.IdentCampoContext ctx) { 
         
         String identificador=ctx.identificador().getText();
         
         System.out.println("identificador: "+identificador); 
         
         //Verificando se identificador ja consta na tabela
         if(tabela.existe(identificador)==false){
             tabela.adicionar(identificador);
         }
         else{
            String mensagem="identificador " + identificador  + " ja declarado anteriormente";
            SemanticoUtils.adicionarErroSemantico(ctx.identificador().getStart(), mensagem); 
         }
         return visitChildren(ctx); 
     }
    
}

