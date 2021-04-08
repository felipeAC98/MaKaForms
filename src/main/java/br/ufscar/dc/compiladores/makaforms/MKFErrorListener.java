/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.compiladores.makaforms;
import java.io.FileOutputStream;
import java.io.IOException;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.RecognitionException;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 *
 * @author felip
 */
public class MKFErrorListener extends BaseErrorListener{
    FileOutputStream saida;     //arquivo onde imprimirá a saída
    int maxErrosEsperados;      //quantos erros serão imprimidos na saída (no caso é 1)
    int nErrosObtidos;          //quantos erros foram efetivamente encontrados
    
    public MKFErrorListener(FileOutputStream saida, int maxErrosEsperados){
        //ao ser chamado recebe o arquivo onde imprimirá a saída e o máximo de erros a serem impressos
        //o construtor atribui os parâmetros recebidos aos atributos
        this.saida=saida;
        this.maxErrosEsperados=maxErrosEsperados;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        //pega uma string com o erro
        String erroEncontrado = ((Token) offendingSymbol).getText();
        
        // verificação para apresentar apenas a primeira mensagem de erro
        //pois uma vez que é encontrado um erro o resto torna-se irrelevante
        if (nErrosObtidos < maxErrosEsperados){
            try {
                //quando o erro está próximo a EOF, o erro é pegado como <EOF>
                //então modificamos a mensagem para ficar correta
                this.saida.write(("Linha " + line + ": erro sintatico proximo a " + ("<EOF>".equals(erroEncontrado) ? "EOF" : erroEncontrado) + "\n").getBytes());
            } catch (IOException ex) {
                Logger.getLogger(MKFErrorListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            nErrosObtidos=nErrosObtidos+1;
        }
    }   
}
