<%-- 
    Document   : testandoInputLivro
    Created on : 03/04/2016, 02:18:25
    Author     : guilherme
--%>

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
        <link href="css/BarraNavegar.css" rel="stylesheet" type="text/css"/>
        <link href="scripts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-1.7.2.js" type="text/javascript"></script>
        <script src="js/jquery.validate.js"type="text/javascript"></script>
        <script src="js/validacaoLogin.js"type="text/javascript"></script>             
        <link href="js/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/completion.js" type="text/javascript"></script>

        <!-- Stylesheets -->
        <link rel="stylesheet" href="css/PrincipalADM.css" media="all">
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


            body {font : 100%/1.4 'Helvetica Neue', arial, helvetica, helve, sans-serif; 	
            }
            h1 { font-size:2.2em; padding:0 0 .5em 0; }
            h2 { font-size:1.5em; }
            .header { padding:1em 0; }
            /*
            .col { background: #ccc; padding:1em 0; text-align:center;}
            */
            #background-layout{background:url(image/livros.jpg);                               
                               background-size:110%;}
            #formInserirLivro1{

                background-size:100%;
                opacity:1;

            }
            #formInserirLivro2{

                background-size:100%;
                opacity:1;

            }
            /*            #formInserirLivro3{
                            background: #000;
                            background-size:100%;
                            opacity:1;
                            border-radius:30px 30px 30px 30px;
                        }
                        #formInserirLivro4{
                            background: #000;
                            background-size:100%;
                            opacity:1;
                            border-radius:30px 30px 30px 30px;
                        }*/
        </style>


        <!-- Navbar -->
    <ul class="w3-navbar w3-black w3-card-2 w3-top w3-left-align w3-large">

        <li class="w3-hide-medium w3-hide-large w3-opennav w3-right">    
        </li>

        <li><a href="PaginaDeLivros.jsp" class="w3-padding-large w3-white">HOME</a></li>
        <li class="w3-hide-small"><a href="ControleAcesso?acao=Sair" class="w3-padding-large w3-hover-white">Sair</a></li>
    </ul>









    <form action="ControleProduto" method="post">
        <div id="retornoAjax" class="ui-widget">
        <input type="text" autocomplete="on" id="search" class="input2" placeholder="Consulte aqui o Livro pelo seu isbn" required="required" name="isbn">

        <button type="submit"  class="submitCadastrarLivroPesquisar" title="pesquisar" name="acao" value="RecuperaLivroParaAtualizar"><i class="fa fa-search" aria-hidden="true" style="font-size:25px"></i></button>
    </form>
 </div>
</head>

<body id="background-layout">


    <%
        String msgJaExisteIsbn = (String) request.getAttribute("msgJaExisteIsbn");
        if (msgJaExisteIsbn != null) {
    %>

    <font id="MsgNaoExiste" color="red"> <%=msgJaExisteIsbn%></font>
    <%}%>


    



    <!--- Sistema de Grid, não alterar --->

    <div class="section group">
        <div class="col span_1_of_2">



        </div>
        <div class="col span_1_of_2">


        </div>
    </div>

    <span id="msg"></span>






    <div class="section group">
        <div class="col span_1_of_2">



            <form id="formInserirLivro1" action="AdmCadastrarLivro.jsp" method="post">

                <input type="submit" class="submitInserirLivro" name="acao" value="Quer Cadastrar um livro?">

            </form>

            <form id="formInserirLivro2" action="AdmGerenciarLivros.jsp" method="post">
                <input type="submit" class="submitInserirLivro" name="acao" value="Quer Gerenciar seus livros cadastrados?">
            </form>

            <form id="formInserirLivro2" action="AdmRelatorios.jsp" method="post">
                <input type="submit" class="submitInserirLivro" name="acao" value="Quer Gerar relatórios ?">
            </form>


        </div>

    </div>


</body>
</html>