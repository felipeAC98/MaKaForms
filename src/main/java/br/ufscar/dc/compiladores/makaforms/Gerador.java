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

        visitTitulo(ctx.titulo());
        visitCorpo(ctx.corpo());
        
        saida.append("\t</body>\n");
        saida.append("</html>\n");
        return null;
    }
    
    @Override
    public Void visitTitulo(makaformsParser.TituloContext ctx)
    {
        saida.append("\t<h1>" + ctx.CADEIA().getText() + "</h1>");
        return null;
    }
    
    @Override
    public Void visitCorpo(makaformsParser.CorpoContext ctx)
    {
        ctx.cmp().forEach(dec -> visitCmp(dec));
        return null;
    }
    
    @Override
    public Void visitCmpTexto(makaformsParser.CmpTextoContext ctx)
    {
        visitExpressao(ctx.expressao());
        saida.append("\t<input type=\"text\">\n");
        return null;
    }
    
    @Override
    public Void visitCmpSenha(makaformsParser.CmpSenhaContext ctx)
    {
        visitExpressao(ctx.expressao());
        saida.append("\t<input type=\"password\">\n");
        return null;
    }
    
    @Override
    public Void visitCmpData(makaformsParser.CmpDataContext ctx)
    {
        visitExpressao(ctx.expressao());
        saida.append("\t<input type=\"date\">\n");
        return null;
    }
    
    @Override
    public Void visitCmpEmail(makaformsParser.CmpEmailContext ctx)
    {
        visitExpressao(ctx.expressao());
        saida.append("\t<input type=\"email\">\n");
        return null;
    }
    
    @Override
    public Void visitCmpEUnica(makaformsParser.CmpEUnicaContext ctx)
    {
        //visitExpressao(ctx.expressao());
        saida.append("\t<input type=\"radio\">\n");
        return null;
    }
}
