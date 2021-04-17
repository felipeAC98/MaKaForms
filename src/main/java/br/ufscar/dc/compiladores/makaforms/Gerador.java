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
    
    public Gerador(){
        saida = new StringBuilder();
    }
    
    String placeholder = "";
    
    @Override
    public Void visitPlaceHolder(makaformsParser.PlaceHolderContext ctx) {
        if (ctx.CADEIA() != null)
        {
            placeholder = ctx.CADEIA().getText();
        }
        return null;
    }
    
    @Override
    public Void visitCmp(makaformsParser.CmpContext ctx) {
        if(ctx.placeHolder() != null)
            visitPlaceHolder(ctx.placeHolder());
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
        saida.append("<!DOCTYPE html>\n");
        saida.append("<html>\n");
        
        saida.append("\t<head>\n");
        
        saida.append("\t\t\t<style>\n");
        
        saida.append("\t\t\tbody {\n");
        
        visitCorBackground(ctx.corBackground());
        visitCorFonte(ctx.corFonte());
        
        saida.append("\t\t\t}\n");
        
        saida.append("\t\t\t</style>\n");
        
        saida.append("\t</head>\n");
        
        saida.append("\t<body>\n");
        saida.append("\t\t<form>\n");
        
        visitTitulo(ctx.titulo());
        visitCorpo(ctx.corpo());
        visitBotao(ctx.botao());
        
        saida.append("\t\t</form>\n");
        saida.append("\t</body>\n");
        saida.append("</html>\n");
        return null;
    }
    
    @Override
    public Void visitCorBackground(makaformsParser.CorBackgroundContext ctx)
    {
        saida.append("\t\t\t\tbackground-color: ");
        saida.append(ctx.corHexa().getText());
        saida.append(";\n");
        return null;
    }
    
    @Override
    public Void visitCorFonte(makaformsParser.CorFonteContext ctx)
    {
        saida.append("\t\t\t\tcolor: ");
        saida.append(ctx.corHexa().getText());
        saida.append(";\n");
        return null;
    }
    
    @Override
    public Void visitTitulo(makaformsParser.TituloContext ctx)
    {
        String [] nome = ctx.CADEIA().getText().split("\"");
        
        saida.append("\t\t\t<h1>" + nome[1] + "</h1>\n\n");
        return null;
    }
    
    @Override
    public Void visitCorpo(makaformsParser.CorpoContext ctx)
    {
        for(Integer i = 0; i < ctx.cmp().size(); i ++)
        {
            placeholder = "";
            visitCmp(ctx.cmp(i));
            saida.append("\t\t\t<br><br>\n");
        }
        return null;
    }
    
    @Override
    public Void visitCmpTexto(makaformsParser.CmpTextoContext ctx)
    {
        String [] nome = ctx.identCampo().expressao().CADEIA().getText().split("\"");
        
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
        String [] nome = ctx.identCampo().expressao().CADEIA().getText().split("\"");
        saida.append("\t\t\t" + nome[1] + "<br>\n");
        
        for(Integer i = 0; i < ctx.item().size(); i++)
        {
            saida.append("\t\t\t<input type=\"radio\" id=\"");
            saida.append(ctx.item(i).identCampo().identificador().getText());
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
        String [] nome = ctx.identCampo().expressao().CADEIA().getText().split("\"");
        saida.append("\t\t\t" + nome[1] + "<br>\n");
        
        for(Integer i = 0; i < ctx.item().size(); i++)
        {
            saida.append("\t\t\t<input type=\"checkbox\" id=\"");
            saida.append(ctx.item(i).identCampo().identificador().getText());
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
        String [] nome = ctx.identCampo().expressao().CADEIA().getText().split("\"");
        saida.append("\t\t\t" + nome[1] + "<br>\n");
        
        saida.append("\t\t\t<input type=\"file\"");
        if(ctx.foto == null)
        {
            saida.append(" accept=\".pdf\" ");
        }
        else
        {
            saida.append(" accept=\"image/*\" ");
        }
        saida.append(">\n");
        return null;
    }
    
    @Override
    public Void visitCmpCaixaTexto(makaformsParser.CmpCaixaTextoContext ctx)
    {
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
        saida.append("\t\t\t<button>");
        String [] nome = ctx.CADEIA().getText().split("\"");
        saida.append(nome[1]);
        saida.append("</button>\n\n");
        return null;
    }
}
