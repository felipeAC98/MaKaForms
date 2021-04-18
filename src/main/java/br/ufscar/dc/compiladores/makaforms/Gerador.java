/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.compiladores.makaforms;

/**
 *
 * @author mariz
 */
public class Gerador extends makaformsBaseVisitor<Void>{
    
    StringBuilder saida;
    
    /*construtor*/
    public Gerador(){
        saida = new StringBuilder();
    }
    
    /*variavel que armazena o placeholder, se houver*/
    String placeholder = "";
    
    @Override
    public Void visitPlaceHolder(makaformsParser.PlaceHolderContext ctx) {
        /*pega  o placeholder se houver e armazena na variavel*/
        if (ctx.CADEIA() != null)
        {
            placeholder = ctx.CADEIA().getText();
        }
        return null;
    }
    
    @Override
    public Void visitCmp(makaformsParser.CmpContext ctx) {
        /*visita o placeholder, se houver*/
        if(ctx.placeHolder() != null)
            visitPlaceHolder(ctx.placeHolder());
        
        /*entra no comando do campo em questao
        verificamos qual dos campos nao e nulo para isso*/
        if(ctx.cmpTexto() != null)
            visitCmpTexto(ctx.cmpTexto());
        else if(ctx.cmpSenha() != null)
            visitCmpSenha(ctx.cmpSenha());
        else if(ctx.cmpData() != null)
            visitCmpData(ctx.cmpData());
        else if(ctx.cmpEmail() != null)
            visitCmpEmail(ctx.cmpEmail());
        else if(ctx.cmpEUnica() != null)
            visitCmpEUnica(ctx.cmpEUnica());
        else if(ctx.cmpEMultipla() != null)
            visitCmpEMultipla(ctx.cmpEMultipla());
        else if(ctx.cmpArquivo() != null)
            visitCmpArquivo(ctx.cmpArquivo());
        else if(ctx.cmpCaixaTexto() != null)
            visitCmpCaixaTexto(ctx.cmpCaixaTexto());
        
        return null;
    }
    
    @Override
    public Void visitPrograma(makaformsParser.ProgramaContext ctx) {
        /*imprime o esqueleto HTML*/
        saida.append("<!DOCTYPE html>\n");
        saida.append("<html>\n");
        
        saida.append("\t<head>\n");
        
        saida.append("\t\t\t<style>\n");
        
        saida.append("\t\t\tbody {\n");
        
        /*codigo CSS para modificar a cor do fundo e da fonte*/
        visitCorBackground(ctx.corBackground());
        visitCorFonte(ctx.corFonte());
        
        saida.append("\t\t\t}\n");
        
        saida.append("\t\t\t</style>\n");
        
        saida.append("\t</head>\n");
        
        saida.append("\t<body>\n");
        saida.append("\t\t<form>\n");
        
        /*visita titulo, corpo e botao, para imprimi-los*/
        visitTitulo(ctx.titulo());
        visitCorpo(ctx.corpo());
        visitBotao(ctx.botao());
        
        /*finaliza o esqueleto HTML*/
        saida.append("\t\t</form>\n");
        saida.append("\t</body>\n");
        saida.append("</html>\n");
        
        return null;
    }
    
    @Override
    public Void visitCorBackground(makaformsParser.CorBackgroundContext ctx)
    {
        /*imprime o codigo CSS para mudar a cor do fundo, junto com a cor em hexa*/
        saida.append("\t\t\t\tbackground-color: ");
        saida.append(ctx.corHexa().getText());
        saida.append(";\n");
        return null;
    }
    
    @Override
    public Void visitCorFonte(makaformsParser.CorFonteContext ctx)
    {
        /*imprime o código css para mudar a cor da fonte, junto com a cor em hexa*/
        saida.append("\t\t\t\tcolor: ");
        saida.append(ctx.corHexa().getText());
        saida.append(";\n");
        return null;
    }
    
    @Override
    public Void visitTitulo(makaformsParser.TituloContext ctx)
    {
        /*tira as aspas do titulo*/
        String [] nome = ctx.CADEIA().getText().split("\"");
        
        /*imprime o titulo com a tag <h1>, para titulos*/
        saida.append("\t\t\t<h1>" + nome[1] + "</h1>\n\n");
        return null;
    }
    
    @Override
    public Void visitCorpo(makaformsParser.CorpoContext ctx)
    {
        /*visita todos os comandos de imprimir campos*/
        for(Integer i = 0; i < ctx.cmp().size(); i ++)
        {
            /*deixa a variavel placeholder como nula, pois se houver ela sera preenchida*/
            placeholder = "";
            visitCmp(ctx.cmp(i));
            saida.append("\t\t\t<br><br>\n");
        }
        return null;
    }
    
    @Override
    public Void visitCmpTexto(makaformsParser.CmpTextoContext ctx)
    {
        /*tira as aspas do nome do campo*/
        String [] nome = ctx.identCampo().expressao().CADEIA().getText().split("\"");
        
        /*imprime o codigo html, um label para o input,
        o input com seu tipo específico e o ID, e o placeholder, que fica
        uma cadeia vazia se não houver
        isso é similar para todos os tipos de campo, por isso
        não vamos comentar todos particularmente*/
        saida.append("\t\t\t<label for=\"");
        saida.append(ctx.identCampo().identificador().IDENT().getText());
        saida.append("\">" + nome[1] + "</label><br>\n");
        saida.append("\t\t\t<input type=\"text\" id=\"");
        saida.append(ctx.identCampo().identificador().IDENT().getText());
        saida.append("\" placeholder=");
        saida.append(placeholder);
        saida.append(">\n\n");
        return null;
    }
    
    @Override
    public Void visitCmpSenha(makaformsParser.CmpSenhaContext ctx)
    {
        /*input do tipo senha*/
        String [] nome = ctx.identCampo().expressao().CADEIA().getText().split("\"");
        
        saida.append("\t\t\t<label for=\"");
        saida.append(ctx.identCampo().identificador().IDENT().getText());
        saida.append("\">" + nome[1] + "</label><br>\n");
        saida.append("\t\t\t<input type=\"password\" id=\"");
        saida.append(ctx.identCampo().identificador().IDENT().getText());
        saida.append("\">\n\n");
        return null;
    }
    
    @Override
    public Void visitCmpData(makaformsParser.CmpDataContext ctx)
    {
        /*input do tipo date*/
        String [] nome = ctx.identCampo().expressao().CADEIA().getText().split("\"");
        
        saida.append("\t\t\t<label for=\"");
        saida.append(ctx.identCampo().identificador().IDENT().getText());
        saida.append("\">" + nome[1] + "</label><br>\n");
        saida.append("\t\t\t<input type=\"date\" id=\"");
        saida.append(ctx.identCampo().identificador().IDENT().getText());
        saida.append("\">\n\n");
        return null;
    }
    
    @Override
    public Void visitCmpEmail(makaformsParser.CmpEmailContext ctx)
    {
        /*input do tipo email*/
        String [] nome = ctx.identCampo().expressao().CADEIA().getText().split("\"");
        
        saida.append("\t\t\t<label for=\"");
        saida.append(ctx.identCampo().identificador().IDENT().getText());
        saida.append("\">" + nome[1] + "</label><br>\n");
        saida.append("\t\t\t<input type=\"email\" id=\"");
        saida.append(ctx.identCampo().identificador().IDENT().getText());
        saida.append("\" placeholder=");
        saida.append(placeholder);
        saida.append(">\n\n");
        return null;
    }
    
    @Override
    public Void visitCmpEUnica(makaformsParser.CmpEUnicaContext ctx)
    {
        /*imprime um input do tipo radio*/
        String [] nome = ctx.identCampo().expressao().CADEIA().getText().split("\"");
        saida.append("\t\t\t" + nome[1] + "<br>\n");
        
        /*precisa imprimir cada opção*/
        for(Integer i = 0; i < ctx.item().size(); i++)
        {
            saida.append("\t\t\t<input type=\"radio\" id=\"");
            saida.append(ctx.item(i).identCampo().identificador().getText());
            saida.append("_");
            saida.append(ctx.identCampo().identificador().IDENT().getText());
            saida.append("\" name=\"");
            saida.append(ctx.identCampo().identificador().IDENT().getText());
            saida.append("\" value=\"");
            saida.append(ctx.item(i).identCampo().identificador().getText());
            saida.append("\">\n");
            saida.append("\t\t\t<label for=\"");
            saida.append(ctx.item(i).identCampo().identificador().getText());
            saida.append("\">");
            saida.append(ctx.item(i).identCampo().expressao().getText().split("\"")[1]);
            saida.append("</label><br>\n");
        }
        saida.append("\n");
        
        return null;
    }
    
    @Override
    public Void visitCmpEMultipla(makaformsParser.CmpEMultiplaContext ctx)
    {
        /*imprime um campo do tipo checkbox, onde é possivel escolher mais de uma opcao*/
        String [] nome = ctx.identCampo().expressao().CADEIA().getText().split("\"");
        saida.append("\t\t\t" + nome[1] + "<br>\n");
        
        for(Integer i = 0; i < ctx.item().size(); i++)
        {
            /*imprime cada uma das opcoes*/
            saida.append("\t\t\t<input type=\"checkbox\" id=\"");
            saida.append(ctx.item(i).identCampo().identificador().getText());
            saida.append("_");
            saida.append(ctx.identCampo().identificador().IDENT().getText());
            saida.append("\" name=\"");
            saida.append(ctx.identCampo().identificador().IDENT().getText());
            saida.append("\" value=\"");
            saida.append(ctx.item(i).identCampo().identificador().getText());
            saida.append("\">\n");
            saida.append("\t\t\t<label for=\"");
            saida.append(ctx.item(i).identCampo().identificador().getText());
            saida.append("\">");
            saida.append(ctx.item(i).identCampo().expressao().getText().split("\"")[1]);
            saida.append("</label><br>\n");
        }
        saida.append("\n");
        
        return null;
    }
    
    @Override
    public Void visitCmpArquivo(makaformsParser.CmpArquivoContext ctx)
    {
        /*imprime um campo do tipo file*/
        String [] nome = ctx.identCampo().expressao().CADEIA().getText().split("\"");
        saida.append("\t\t\t" + nome[1] + "<br>\n");
        
        saida.append("\t\t\t<input type=\"file\"");
        if(ctx.foto == null)
        {
            /*se for para pdf, aceita so essa extensao*/
            saida.append(" accept=\".pdf\" ");
        }
        else
        {
            /*aceita qualquer extensao de foto*/
            saida.append(" accept=\"image/*\" ");
        }
        if(ctx.mult != null)
        {
            /*se puder escolher mais de um arquivo, imprimimos esse atributo*/
            saida.append("multiple");
        }
        saida.append(">\n");
        return null;
    }
    
    @Override
    public Void visitCmpCaixaTexto(makaformsParser.CmpCaixaTextoContext ctx)
    {
        /*imprime um textarea, escolhendo a quantidade de linhas e colunas*/
        String [] nome = ctx.identCampo().expressao().CADEIA().getText().split("\"");
        saida.append("\t\t\t" + nome[1] + "<br>\n");
        
        saida.append("\t\t\t<textarea ");
        saida.append("rows = \"");
        saida.append(ctx.tamanhoVertical().NUM_INT().getText());
        saida.append("\" cols = \"");
        saida.append(ctx.tamanhoHorizontal().NUM_INT().getText());
        saida.append("\">");
        if(placeholder != "")
        {
            String [] texto;
            texto = placeholder.split("\"");
            saida.append(texto[1]);
        }
        saida.append("</textarea>\n\n");
        return null;
    }
    
    @Override
    public Void visitBotao(makaformsParser.BotaoContext ctx)
    {
        /*imprime um botao, com o texto dentro*/
        saida.append("\t\t\t<button>");
        String [] nome = ctx.CADEIA().getText().split("\"");
        saida.append(nome[1]);
        saida.append("</button>\n\n");
        return null;
    }
}
