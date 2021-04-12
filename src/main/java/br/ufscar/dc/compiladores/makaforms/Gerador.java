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
    
    @Override
    public Void visitPrograma(makaformsParser.ProgramaContext ctx) {
        saida.append("<!DOCTYPE html>\n");
        saida.append("<html>\n");
        saida.append("\t<body>\n");
        saida.append("\t\t<form>\n");
        
        visitTitulo(ctx.titulo());
        visitCorpo(ctx.corpo());
        
        saida.append("\t\t</form>\n");
        saida.append("\t</body>\n");
        saida.append("</html>\n");
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
        saida.append("\">\n\n");
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
        saida.append("\">\n\n");
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
        //visitExpressao(ctx.identCampo().expressao());
        saida.append("\t\t\t<input type=\"file\">\n");
        return null;
    }
    
    @Override
    public Void visitCmpCaixaTexto(makaformsParser.CmpCaixaTextoContext ctx)
    {
        visitExpressao(ctx.identCampo().expressao());
        saida.append("\t\t\t<textarea ");
        saida.append("rows = \"");
        saida.append(ctx.tamanhoVertical().NUM_INT().getText());
        saida.append("\" cols = \"");
        saida.append(ctx.tamanhoHorizontal().NUM_INT().getText());
        saida.append("\">");
        saida.append(">\n");
        return null;
    }
}
