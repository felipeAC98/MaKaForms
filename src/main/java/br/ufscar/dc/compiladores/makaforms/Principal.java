package br.ufscar.dc.compiladores.makaforms;

import br.ufscar.dc.compiladores.makaforms.makaforms;
import java.io.FileOutputStream;
import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author felip
 */
public class Principal {
    public static void main(String args[]) throws IOException{
        
        CharStream cs= CharStreams.fromFileName(args[0]);       //utilizado para leitura do arquivo passado como entrada contendo codigo com programa em linguagem algoritmica 
        makaforms lex = new makaforms(cs); 
        FileOutputStream saida = new FileOutputStream(args[1]); //utilizado para escrita no arquivo de saida apos analisa lexica
        Token aux = null;   //token auxiliar para analise
       
        while((aux = lex.nextToken()).getType() != Token.EOF){  //enquanto tivermos caracteres no arquivo de entrada para serem analisados 
            System.out.println("<"+makaforms.VOCABULARY.getDisplayName(aux.getType())+","+aux.getText()+">");
            
            //para formacao da parte direita do token
            String direita_token = "'" + aux.getText() + "'"; // usada para montagem do token <getText(),direita_token>
            
            // verifica se foi identificado algum identificador, cadeia, numero inteiro ou numero real definido pela gramatica
            switch (makaforms.VOCABULARY.getDisplayName(aux.getType())) {
                case "IDENT":
                    direita_token = makaforms.VOCABULARY.getDisplayName(aux.getType());       // forma token <'aux.getText()','IDENT'>
                    break;
                case "CADEIA":
                    direita_token = makaforms.VOCABULARY.getDisplayName(aux.getType());       // forma token <'aux.getText()','CADEIA'>
                    break;
                case "NUM_INT":
                    direita_token = makaforms.VOCABULARY.getDisplayName(aux.getType());       // forma token <'aux.getText()','NUM_INT'>
                    break;
                case "NUM_REAL":
                    direita_token = makaforms.VOCABULARY.getDisplayName(aux.getType());       // forma token <'aux.getText()','NUM_REAL'>
                    break;
            }
                   
       
        }
        
        saida.close();
    }
}