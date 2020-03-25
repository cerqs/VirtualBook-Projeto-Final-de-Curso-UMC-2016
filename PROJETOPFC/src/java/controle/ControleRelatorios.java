/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.PedidoDAO;
import modelo.dao.RelatorioDeAvaliacaoDAO;
import modelo.dao.RelatorioDeUsuarioDAO;
import modelo.dao.RelatorioDeVendasDAO;
import modelos.Pedido;
import modelos.RelatorioDeAvaliacao;
import modelos.RelatorioDeUsuario;
import modelos.RelatorioDeVendas;
import modelos.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/**
 *
 * @author 11131103404
 */
public class ControleRelatorios extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        String acao = request.getParameter("acao");
        if (acao.equals("usuarios")) {
            response.setContentType("text/html;charset=UTF-8");
            response.setContentType("application/pdf");
            
            String path = getServletContext().getRealPath("/relatorios");
            
            String relJasper = path + "\\RelatorioDeTotalUsuarios.jasper";
            
            RelatorioDeUsuarioDAO relatorio = new RelatorioDeUsuarioDAO();
            
            ArrayList<RelatorioDeUsuario> listaUsuarios = relatorio.listar();
            
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaUsuarios);
            
            Map parametros = new HashMap();
            
            try {
                JasperPrint print = JasperFillManager.fillReport(relJasper, parametros, ds);
                
                byte relatorioPdf[] = JasperExportManager.exportReportToPdf(print);
                
                response.getOutputStream().write(relatorioPdf);
                
            } catch (JRException e) {
                response.getWriter().println(e.getMessage());
            }
            
        } else if (acao.equals("vendas")) {
            
            response.setContentType("text/html;charset=UTF-8");
            response.setContentType("application/pdf");
            
            String path = getServletContext().getRealPath("/relatorios");
            
            String relJasper = path + "\\relatorioPedidoFinalizado.jasper";
            
            //RECUPERA A DATA QUE O USUARIO DETERMINOU PARA O RELATORIO (data vindo do jsp)
            String de = request.getParameter("data_de");
            String ate = request.getParameter("data_ate");
            
            // CONVERTE A DATA DE STRING PARA SQL.DATE PARA QUE POSSA PASSAR PRO BD
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            
            Date parsedDe = format.parse(de);
            java.sql.Date data_de = new java.sql.Date(parsedDe.getTime());
            
            Date parsedAte = format.parse(ate);
            java.sql.Date data_ate = new java.sql.Date(parsedAte.getTime());
            
            RelatorioDeVendas relatorioDevendas = new RelatorioDeVendas();
            relatorioDevendas.setDe(data_de);
            relatorioDevendas.setAte(data_ate);
            
            // na variavel "relatorio" esta recuperando o dados do SELECT do banco
            RelatorioDeVendasDAO relatorioVendasDAO = new RelatorioDeVendasDAO();            
            ArrayList<RelatorioDeVendas> relatorio = relatorioVendasDAO.listarRelatorio(relatorioDevendas);
            
            
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(relatorio);
            Map parametros = new HashMap();
            try {
                
                JasperPrint print = JasperFillManager.fillReport(relJasper, parametros, ds);
                byte relatorioPdf[] = JasperExportManager.exportReportToPdf(print);
                response.getOutputStream().write(relatorioPdf);
                
            } catch (JRException e) {
                response.getWriter().println(e.getMessage());
            }
            
        } else if (acao.equals("Avaliacoes")) {
            
            response.setContentType("text/html;charset=UTF-8");
            response.setContentType("application/pdf");
            
            String path = getServletContext().getRealPath("/relatorios");
            String relJasper = path + "\\relatorioDeAvaliacao.jasper";
            
            //RECUPERA A DATA QUE O USUARIO DETERMINOU PARA O RELATORIO (data vindo do jsp)
            String de = request.getParameter("data_de");
            String ate = request.getParameter("data_ate");
            
            // CONVERTE A DATA DE STRING PARA SQL.DATE PARA QUE POSSA PASSAR PRO BD
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            
            Date parsedDe = format.parse(de);
            java.sql.Date data_de = new java.sql.Date(parsedDe.getTime());
            
            Date parsedAte = format.parse(ate);
            java.sql.Date data_ate = new java.sql.Date(parsedAte.getTime());
            
            
            // SET NAS DATA PARA PODER PASSAR COMO PARAMENTRO PARA DAO
            RelatorioDeAvaliacao r = new RelatorioDeAvaliacao();
            r.setDe(data_de);
            r.setAte(data_ate);
            
            //variavel RELATORIO tem a resposta da consulta do banco
            RelatorioDeAvaliacaoDAO relatorioDeAvaliacao = new RelatorioDeAvaliacaoDAO();
            ArrayList<RelatorioDeAvaliacao> relatorio = relatorioDeAvaliacao.CriarRelatorioDeAvaliacao(r);
            
            //é passado o relatorio COMPLETO com todos os dados nescessarios
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(relatorio);
            Map parametros = new HashMap();
            try {
                
                JasperPrint print = JasperFillManager.fillReport(relJasper, parametros, ds);
                byte relatorioPdf[] = JasperExportManager.exportReportToPdf(print);
                response.getOutputStream().write(relatorioPdf);
                
            } catch (JRException e) {
                response.getWriter().println(e.getMessage());
            }
        }else if (acao.equals("GerarBoleto")) {
            
            response.setContentType("text/html;charset=UTF-8");
            response.setContentType("application/pdf");
            
            String path = getServletContext().getRealPath("/relatorios");
            
            String relJasper = path + "\\BoletoBancario.jasper";
            
           HttpSession sessaoUsuario = request.getSession();
           Usuario usuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");
            
            
            
            
            Integer id_pedido = Integer.parseInt(request.getParameter("idPedido"));
            
            Pedido pedido = new Pedido();
            pedido.setId(id_pedido);
            pedido.setUsuario(usuario);
            
            PedidoDAO pdao = new PedidoDAO();
            ArrayList<Pedido> dadosBoleto = pdao.CriarDadosBoleto(pedido);
            
            if(dadosBoleto.isEmpty()){
                
                
               RequestDispatcher rd = request.getRequestDispatcher("PaginaDeLivros.jsp");
               String mensagem = "Desculpe, Esse boleto não faz parte do seu pedido";
               request.setAttribute("boletoNaoExiste", mensagem);                
               rd.forward(request, response);
                
                
                
                
            }else{
                
               
            
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dadosBoleto);
            Map parametros = new HashMap();
            try {
                
                JasperPrint print = JasperFillManager.fillReport(relJasper, parametros, ds);
                byte relatorioPdf[] = JasperExportManager.exportReportToPdf(print);
                response.getOutputStream().write(relatorioPdf);
                
            } catch (JRException e) {
                response.getWriter().println(e.getMessage());
            }
            
            }
            
            
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ControleRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ControleRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
