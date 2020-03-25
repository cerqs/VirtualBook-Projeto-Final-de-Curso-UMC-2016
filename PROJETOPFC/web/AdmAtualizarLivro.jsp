<%-- 
    Document   : testandoAtualizar
    Created on : 03/04/2016, 02:53:52
    Author     : guilherme
--%>

<%@page import="modelos.Genero"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Produto"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<!-- HTML5 Boilerplate -->
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <title>Virtual Book</title>

        <meta http-equiv="cleartype" content="on">

        <link rel="shortcut icon" href="/favicon.ico">
        <meta name="MobileOptimized" content="320">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <script src="js/jquery-1.7.2.js" type="text/javascript"></script>
        <script src="js/jquery.validate.js"type="text/javascript"></script>
        <script src="js/validacaoLivroATUALIZAR.js"type="text/javascript"></script>
        <link href="js/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/completion.js" type="text/javascript"></script>

        <!-- Stylesheets -->
        <link href="scripts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/BarraNavegar.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/AtualizarLivro.css" media="all">
        <link rel="stylesheet" href="css/html5reset.css" media="all">
        <link rel="stylesheet" href="css/col.css" media="all">
        <link rel="stylesheet" href="css/2cols.css" media="all">
        <link rel="stylesheet" href="css/3cols.css" media="all">
        <link rel="stylesheet" href="css/4cols.css" media="all">
        <link rel="stylesheet" href="css/5cols.css" media="all">
        <link rel="stylesheet" href="css/6cols.css" media="all">
        <link rel="stylesheet" href="css/7cols.css" media="all">
        <link rel="stylesheet" href="css/8cols.css" media="all">
        <link rel="stylesheet" href="css/9cols.css" media="all">
        <link rel="stylesheet" href="css/10cols.css" media="all">
        <link rel="stylesheet" href="css/11cols.css" media="all">
        <link rel="stylesheet" href="css/12cols.css" media="all">
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <style type="text/css">


            body { font : 100%/1.4 'Helvetica Neue', arial, helvetica, helve, sans-serif; 	
            }
            h1 { font-size:2.2em; padding:0 0 .5em 0; }
            h2 { font-size:1.5em; }
            .header { padding:1em 0; }
            /*
            .col { background: #ccc; padding:1em 0; text-align:center;}
            */
            #background-layout{background:url(image/livros.jpg);
                               background-size:110%;}
            #formCadastrarLivro{
                background:url(image/textura-input.jpg);
                background-size:100%;
                opacity:0.8;
                border-radius:30px 30px 30px 30px;
            }
        </style>

    <ul class="w3-navbar w3-black w3-card-2 w3-top w3-left-align w3-large">

        <li class="w3-hide-medium w3-hide-large w3-opennav w3-right">    
        </li>

        <li><a href="AdmTelaPrincipal.jsp" class="w3-padding-large w3-white">HOME</a></li>
        <li class="w3-hide-small"><a href="AdmCadastrarLivro.jsp" class="w3-padding-large w3-hover-white">Cadastrar livros</a></li>
        <li class="w3-hide-small"><a href="AdmGerenciarLivros.jsp" class="w3-padding-large w3-hover-white">Gerenciar livros</a></li>          
        <li class="w3-hide-small"><a href="ControleAcesso?acao=Sair" class="w3-padding-large w3-hover-white">Sair</a></li>
    </ul>


</head>

<body id="background-layout">



    <%
        ArrayList<Genero> generos = (ArrayList<Genero>) request.getAttribute("generos");
        ArrayList<Produto> dadosRecuperado = (ArrayList<Produto>) request.getAttribute("dadosAtualizar");
        if (dadosRecuperado == null) {
            return;
        }
    %>


    <form action="ControleProduto" method="post">
        <div id="retornoAjax" class="ui-widget">
        <input type="text" autocomplete="on" id="search" class="input2" placeholder="Consultar Livro pelo isbn" required="required" name="isbn">        
        <button type="submit" class="submitCadastrarLivroPesquisar" title="pesquisar" name="acao" value="RecuperaLivroParaAtualizar"><i class="fa fa-search" aria-hidden="true" style="font-size:25px"></i></button>
   </div>
    </form>

    <!--- Sistema de Grid, não alterar --->

    <div class="section group">
        <div class="col span_1_of_2">



        </div>
        <div class="col span_1_of_2">


        </div>
    </div>

    <!--    <span id="msg"></span>-->






    <div class="section group">
        <div class="col span_1_of_2">



            <form id="formCadastrarLivro" action="ControleProduto" method="POST">


                <br>
                <h2 id="login">Atualizar Livro.</h2>
                <br>
                <div id="box-inputs">

                    <table>

                        <% for (Produto dadosAtualizar : dadosRecuperado) {%>


                        <tr>
                            <td><input type="text" class="input" value="<%=dadosAtualizar.getIsbn()%>" disabled></td> 
                            <td></td>
                        </tr>

                        <tr>
                            <td><input type="hidden" class="input" name="txtIsbn" value="<%=dadosAtualizar.getIsbn()%>"></td> 
                            <td></td>
                        </tr>


                        <tr>

                            <td><input type="text" class="input" placeholder="Titulo" required="required" name="txtTitulo" value="<%=dadosAtualizar.getTitulo()%>"> </td>   
                            <td></td>
                        </tr>



                        <tr>

                            <td><input type="text" class="input" placeholder="Autor" required="required" name="txtAutor" value="<%=dadosAtualizar.getAutor()%>"> </td>   
                            <td></td>
                        </tr>


                        <tr>

                            <td><input type="text" class="input" placeholder="Preço" required="required" name="txtPreco" value="<%=dadosAtualizar.getPreco()%>"> </td>   
                            <td></td>
                        </tr>



                        <tr>

                            <td><input type="date" class="input" placeholder="Data de lançamento" required="required" name="txtLancamento" value="<%=dadosAtualizar.getLancamento()%>"> </td>   
                            <td></td>
                        </tr>


                        <tr>  
                            <td>

                                <select  class="input" name="txtGenero">
                                    <%
                                        for (Genero genero : generos) {

                                    %>

                                    <option  value="<%=genero.getId_genero()%>" <% if (genero.getId_genero() == dadosAtualizar.getGenero().getId_genero()) {%>selected <%}%>>


                                        <%=genero.getDesc_genero()%>

                                        <%}%>

                                    </option>
                                </select>                       
                            </td>
                        </tr>  

                        <tr>

                            <td> <textarea name="txtSinopse" rows="10" cols="30" class="input3" placeholder="Sinopse"> <%=dadosAtualizar.getSinopse()%> </textarea>  </td>   
                            <td></td>
                        </tr>

                        <td><input type="submit" value="Atualizar" class="submit" name="acao"> </td>



                        <% if (dadosAtualizar.getStatus().equals("ativo")) {%>

                        <td><input type="submit" value="Inativar" class="submit2" name="acao"></td>
                            <%} else {%>

                        <td><input type="submit" value="Ativar" class="submit2" name="acao"></td>
                            <%}%>    



                        <% }%>






                    </table>



                </div>

            </form>

        </div>

    </div>


</body>
</html>