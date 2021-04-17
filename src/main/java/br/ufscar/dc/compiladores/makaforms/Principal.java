package br.ufscar.dc.compiladores.makaforms;

import br.ufscar.dc.compiladores.makaforms.makaformsParser.ProgramaContext;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
        Token aux = null;                                       //token auxiliar para analise
        
        //Definicoes para verificacoes de sintaxe
        CommonTokenStream tokens = new CommonTokenStream(lex);   
        makaformsParser parser = new makaformsParser(tokens);
        int maxErrosEsperados =1;                                                //Maximo de erros sintaticos esperados pela linguagem para entao retornar o erro ao usuario
        MKFErrorListener MKEL = new MKFErrorListener(saida, maxErrosEsperados);  //Criando listener para erros sintaticos
        
        //Variavel para armazenar a ocorrencia de erro
        Boolean ERROR = false;
        
        while((aux = lex.nextToken()).getType() != Token.EOF){  //enquanto tivermos caracteres no arquivo de entrada para serem analisados 
            //System.out.println("<"+makaformsLexer.VOCABULARY.getDisplayName(aux.getType())+","+aux.getText()+">");
            
            //para formacao da parte direita do token
            String direita_token = aux.getText(); // usada para montagem do token <getText(),direita_token>
            
            // verificando se teve algum erro lexico
            switch (makaformsLexer.VOCABULARY.getDisplayName(aux.getType())) {
                case "ERRO_SIMBOLO":
                    saida.write(("Linha " + aux.getLine() + ": " + aux.getText() + " - simbolo nao identificado\n").getBytes());    //imprime a linha de erro e o tipo de erro
                    ERROR=true;
                    break;
                
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
            ProgramaContext arvore= parser.programa();
            Semantico MKFS= new Semantico();
            MKFS.visitPrograma(arvore);

            if(SemanticoUtils.errosSemanticos.isEmpty()==false){
                //Escrevendo os erros gravados no LASemanticoUtils para um arquivo
                List<String> errosSemanticos = SemanticoUtils.errosSemanticos;
                for (var erroSemantico : errosSemanticos) {
                    saida.write((erroSemantico + "\n").getBytes());
                }   

                saida.write(("Fim da compilacao\n").getBytes());               
            }
            else
            {
                Gerador gerador = new Gerador();
                gerador.visitPrograma(arvore);
                try(PrintWriter pw = new PrintWriter(args[1]))
                {
                    pw.print(gerador.saida.toString());
                }
            }
        }
        saida.close();
    }
}