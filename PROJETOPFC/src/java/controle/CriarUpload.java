/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guilherme
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Produto;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

public class CriarUpload extends HttpServlet {

    String saveFile = "C://Users//guilherme//Desktop//Projeto Final//PROJETOPFC//web//imagens";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {

            boolean ismultipart = ServletFileUpload.isMultipartContent(request);
            if (!ismultipart) {

            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                List items = null;

                try {

                    items = upload.parseRequest(request);

                } catch (Exception e) {

                }
                Iterator itr = items.iterator();
                while (itr.hasNext()) {
                    FileItem item = (FileItem) itr.next();
                    if (item.isFormField()) {

                    } else {
                        String itemname = item.getName();
                        if ((itemname == null) || itemname.equals("")) {
                            String mensagem = "Desculpe, é necessario adicionar uma foto!!";
                            request.setAttribute("msgsemfoto", mensagem);
                            RequestDispatcher rd = request.getRequestDispatcher("AdmInserirImagem.jsp");
                            rd.forward(request, response);
                        }
                        
                        //RECUPERA O NOME DA FOTO
                        String nomeDaFoto = FilenameUtils.getName(itemname);
                        
                        
                        StringBuffer sbb = new StringBuffer(nomeDaFoto);
                        int qtdDeCaractere = sbb.lastIndexOf(".");
                        String substring = nomeDaFoto.substring(qtdDeCaractere);
                        
                       
                        if (substring.equals(".exe")) {                            
                            String mensagem = "Desculpe, não é possivel adicionar um arquivo exe!!";
                            request.setAttribute("msgsemfoto", mensagem);
                            RequestDispatcher rd = request.getRequestDispatcher("AdmInserirPdf.jsp");
                            rd.forward(request, response);
                        }else{
                            
                                             
                        File f = new File(saveFile + "/" + nomeDaFoto);
                         
                        // CASO O ARQUIVO JÁ EXISTA COM MESMO NOME PEGA A DATA PRA COLOCAR COMO DIFERENÇA
                        if (f.exists()) {
                            StringBuffer sb = new StringBuffer(nomeDaFoto);
                            
                            //APÓS O . ADICIONA A DATA E HORA
                            sb.insert(sb.lastIndexOf("."), "-" + new Date().getTime());
                            f = new File(saveFile + "/" + sb.toString());
                            
                            //RECUPERA O NOME DO ARQUIVO JÁ COM A DATA E HORA
                            nomeDaFoto = sb.toString();
                        }
                        
                        //GRAVA NO DIRETORIO
                        item.write(f);

                        HttpSession sessaoProduto = request.getSession();
                        Produto produto = (Produto) sessaoProduto.getAttribute("cadastrarProduto");
                        produto.setImagem(nomeDaFoto);

                        response.sendRedirect("AdmConfirmarCadastroDeLivro.jsp");
                            
                            
                        }
                          
       

                    }

                }
            }

        } catch (Exception e) {

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private File checkExist(String fileName) {
        File f = new File(saveFile + "/" + fileName);

        if (f.exists()) {
            StringBuffer sb = new StringBuffer(fileName);
            sb.insert(sb.lastIndexOf("."), "-" + new Date().getTime());
            f = new File(saveFile + "/" + sb.toString());
        }

        return f;
    }

}
