package br.ufscar.dc.compiladores.makaforms;

import java.io.FileOutputStream;
import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author felip
 */
public class Principal {
    public static void main(String args[]) throws IOException{
        
        CharStream cs= CharStreams.fromFileName(args[0]);       //utilizado para leitura do arquivo passado como entrada contendo codigo com programa em linguagem algoritmica 
        makaformsLexer lex = new makaformsLexer(cs); 
        FileOutputStream saida = new FileOutputStream(args[1]); //utilizado para escrita no arquivo de saida apos analisa lexica
        Token aux = null;   //token auxiliar para analise
        CommonTokenStream tokens = new CommonTokenStream(lex);   
        makaformsParser parser = new makaformsParser(tokens);
        int maxErrosEsperados =1;
        MKFErrorListener MKEL = new MKFErrorListener(saida, maxErrosEsperados); 
        
        
        while((aux = lex.nextToken()).getType() != Token.EOF){  //enquanto tivermos caracteres no arquivo de entrada para serem analisados 
            System.out.println("<"+makaformsLexer.VOCABULARY.getDisplayName(aux.getType())+","+aux.getText()+">");
            
            //para formacao da parte direita do token
            String direita_token = aux.getText(); // usada para montagem do token <getText(),direita_token>
            
            // verifica se foi identificado algum identificador, cadeia, numero inteiro ou numero real definido pela gramatica
            switch (makaformsLexer.VOCABULARY.getDisplayName(aux.getType())) {
                case "IDENT":
                    direita_token = makaformsLexer.VOCABULARY.getDisplayName(aux.getType());       // forma token <'aux.getText()','IDENT'>
                    break;
                case "CADEIA":
                    direita_token = makaformsLexer.VOCABULARY.getDisplayName(aux.getType());       // forma token <'aux.getText()','CADEIA'>
                    break;
                case "NUM_INT":
                    direita_token = makaformsLexer.VOCABULARY.getDisplayName(aux.getType());       // forma token <'aux.getText()','NUM_INT'>
                    break;
                case "NUM_REAL":
                    direita_token = makaformsLexer.VOCABULARY.getDisplayName(aux.getType());       // forma token <'aux.getText()','NUM_REAL'>
                    break;
            }
                   
       
        }
        
        //movendo para o inicio do arquivo novamente
        //para analisar novamente e ver a ocorrência de erros 
        /*cs.seek(0);
        lex.setInputStream(cs);

        //associando o errorListener criado com o lexer e com o parser
        lex.removeErrorListeners();
        lex.addErrorListener(MKEL);

        parser.removeErrorListeners();
        parser.addErrorListener(MKEL);
        parser.programa();       */

        saida.close();
    }
}