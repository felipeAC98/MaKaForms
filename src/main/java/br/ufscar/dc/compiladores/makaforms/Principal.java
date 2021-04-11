package br.ufscar.dc.compiladores.makaforms;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
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
        
        //Variavel para armazenar a ocorrencia de erro
        Boolean ERROR = false;
        
        while((aux = lex.nextToken()).getType() != Token.EOF){  //enquanto tivermos caracteres no arquivo de entrada para serem analisados 
            System.out.println("<"+makaformsLexer.VOCABULARY.getDisplayName(aux.getType())+","+aux.getText()+">");
            
            //para formacao da parte direita do token
            String direita_token = aux.getText(); // usada para montagem do token <getText(),direita_token>
            
            // verifica se foi identificado algum identificador, cadeia, numero inteiro ou numero real definido pela gramatica
            
            switch (makaformsLexer.VOCABULARY.getDisplayName(aux.getType())) {
                case "ERRO_SIMBOLO":
                    saida.write(("Linha " + aux.getLine() + ": " + aux.getText() + " - simbolo nao identificado\n").getBytes());    //imprime a linha de erro e o tipo de erro
                    ERROR=true;
                    break;
                
                //Ainda nao definida a forma de comentarios da linguagem
                case "COMENTARIO_ERRADO":
                    saida.write(("Linha " + aux.getLine() + ": comentario nao fechado\n").getBytes());    //imprime a linha de erro e o tipo de erro
                    ERROR=true;
                    break;
                    
                case "ERRO_CADEIA":
                    saida.write(("Linha " + aux.getLine() + ": cadeia literal nao fechada\n").getBytes());    //imprime a linha de erro e o tipo de erro
                    ERROR=true;
                    break;
            }
                          
        }
        
        if(ERROR==false){
            //movendo para o inicio do arquivo novamente
            //para analisar novamente e ver a ocorrência de erros 
            cs.seek(0);
            lex.setInputStream(cs);

            //associando o errorListener criado com o lexer e com o parser
            lex.removeErrorListeners();
            lex.addErrorListener(MKEL);

            parser.removeErrorListeners();
            parser.addErrorListener(MKEL);
            parser.programa();   
           
        }
        saida.close();
    }
}