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
         
         //System.out.println("identificador: "+identificador); 
         
         //Insercao de regra para verificacao de underscore nos identificadores
         //Um identificador nao pode ter um underscore devido a logica de criacao de identificadores vinculados a seus campos no gerador de HTML
         if(identificador.contains("_")){
            String mensagem=identificador+" - identificadores nao podem possuir underscore '_' ";
            SemanticoUtils.adicionarErroSemantico(ctx.identificador().getStart(), mensagem); 
         }
         
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
     
     /*@Override 
     public Object visitBotao(makaformsParser.BotaoContext ctx) { 
          
         System.out.println("aqui: ");
         //Verificando se identificador ja consta na tabela
         if(tabela.existe("botao")==false){
             tabela.adicionar("botao");
         }
         else{
            String mensagem=" botao ja adicionado no formulario";
            SemanticoUtils.adicionarErroSemantico(ctx.getStart(), mensagem); 
         }        
         
         return visitChildren(ctx);      
     }
     
     @Override 
     public Object visitCorpo(makaformsParser.CorpoContext ctx) { 
     
         if(ctx.botao()==null){
            String mensagem=" nenhum botao adicionado ao formulario";
            SemanticoUtils.adicionarErroSemantico(ctx.getStart(), mensagem);           
         }
         return visitChildren(ctx); 
     
     }*/
    
}

