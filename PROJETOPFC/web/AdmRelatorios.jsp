<%-- 
    Document   : Relatorios
    Created on : 05/06/2016, 18:30:49
    Author     : guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/BarraNavegar.css" rel="stylesheet" type="text/css"/>
        <link href="scripts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/Relatorios.css" rel="stylesheet" type="text/css"/>
        <style type="text/css"> 


            table, th, td {
                margin-top: 40px;
                border: 2px solid black;
                border-collapse: collapse;
            }
            th, td {

                padding: 5px;
                text-align: left;
            }

            #background-layout{background:url(image/livros.jpg);
            background-size:110%;}

        </style>
        <title>Gerar Relatorios</title>
    </head>
    <body id="background-layout">

        <ul class="w3-navbar w3-black w3-card-2 w3-top w3-left-align w3-large">

        <li class="w3-hide-medium w3-hide-large w3-opennav w3-right">    
        </li>

        <li><a href="AdmTelaPrincipal.jsp" class="w3-padding-large w3-white">HOME</a></li>
        <li class="w3-hide-small"><a href="AdmCadastrarLivro.jsp" class="w3-padding-large w3-hover-white">Cadastrar livros</a></li>
        <li class="w3-hide-small"><a href="AdmGerenciarLivros.jsp" class="w3-padding-large w3-hover-white">Gerenciar livros</a></li>          
        <li class="w3-hide-small"><a href="ControleAcesso?acao=Sair" class="w3-padding-large w3-hover-white">Sair</a></li>
    </ul>




        <form  action="ControleRelatorios" method="POST">
            
        <table style="width:40%" class="tabela1">
            <tr>
                <th>Nome do relatorio:</th>
                <td>Vendas</td>
            </tr>
            <tr>
                <th rowspan="2">Datas:</th>
                <td>de <input type="date" name="data_de"></td>
            </tr>
            <tr>
                <td>ate <input type="date" name="data_ate"> </td>
            </tr>
        </table>

        <input class="Botao" type="submit" value="vendas" name="acao" >
        
        </form>

    <form  action="ControleRelatorios" method="POST">



        <table style="width:40%" class="tabela3">
            <tr>
                <th>Nome do relatorio:</th>
                <td>Avaliações</td>
            </tr>
            <tr>
                <th rowspan="2">Datas:</th>
                <td>de <input type="date" name="data_de"></td>
            </tr>
            <tr>
                <td>ate <input type="date" name="data_ate"> </td>
            </tr>

        </table>

        <input class="Botao" type="submit" value="Avaliacoes" name="acao" >
        

    </form>
        
        
        <table style="width:40%" class="tabela2">
        <tr>
            <th>Nome do relatorio:</th>
            <td>Usuarios cadastrados</td>
        </tr>    
    </table>        
        <button class="Botao" ><li><a href="ControleRelatorios?acao=usuarios" target="_blank">Gerar relatório de usúarios</a></li></button>




</body>
</html>
