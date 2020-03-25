<%-- 
    Document   : testandoResultadoPesquisa
    Created on : 03/04/2016, 03:26:32
    Author     : guilherme
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Genero"%>
<%@page import="modelos.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- HTML5 Boilerplate -->
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <title>Virtual Book</title>

        <meta http-equiv="cleartype" content="on">

        <link rel="shortcut icon" href="/favicon.ico">
        <meta name="MobileOptimized" content="320">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <script src="js/jquery-1.7.2.js" type="text/javascript"></script>
        <script src="js/jquery.validate.js"type="text/javascript"></script>
        <script src="js/validacaoLogin.js"type="text/javascript"></script>

        <!-- Stylesheets -->
        <link rel="stylesheet" href="css/ConsultarLivro.css" media="all">
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


            body { padding:2em; font : 100%/1.4 'Helvetica Neue', arial, helvetica, helve, sans-serif; 	
            }
            h1 { font-size:2.2em; padding:0 0 .5em 0; }
            h2 { font-size:1.5em; }
            .header { padding:1em 0; }
            /*
            .col { background: #ccc; padding:1em 0; text-align:center;}
            */
            #background-layout{background:url(image/livros.jpg);
                               background-size:110%;}
            #formResultadoLivroPesquisa{
                background:url(image/textura-input.jpg);
                background-size:100%;
                opacity:0.8;
                border-radius:30px 30px 30px 30px;
            }
        </style>

    <form action="ControleProduto" method="post">
        <input type="text" class="inputResultadoPesquisa2" placeholder="Consultar Livro pelo ISBN" required="required" name="txtIsbn">
        <input type="submit" class="submitCadastrarLivroPesquisar" name="acao" value="consultar">
    </form>



</head>

<body id="background-layout">


    <nav id="nav">
        <ul>

            <li><form action="testandoUmaTelaPrincipal.jsp" method="post">
                    <input type="submit" class="submitResultadoPesquisa" name="acao" value="principal">
                </form></li>

            <li class="current"><form action="InserirPdf.jsp" method="post">
                    <input type="submit" class="submitResultadoPesquisa" name="acao" value="Cadastrar">
                </form></li>
            <li>
            <li><form action="testandoListar.jsp" method="post">
                    <input type="submit" class="submitResultadoPesquisa" name="acao" value="Gereciar Livros">
                </form></li>            


        </ul>
    </nav>



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



            <form id="formResultadoLivroPesquisa" action="ControleProduto" method="POST">

                <%                    
                    Produto p = (Produto) request.getAttribute("Consulta");
                    ArrayList<Genero> generos = (ArrayList<Genero>) request.getAttribute("generos");
                %> 

                <br>
                <h2 id="login">Resultado da Pesquisa</h2>
                <br>
                <div id="box-inputs">

                    <table>

                        <tr>

                            <td><input type="text" class="inputResultadoPesquisa" placeholder="Titulo" required="required" name="txtTitulo" value="<%=p.getTitulo()%>"><label id="TextoConsultarLivro">Titulo</label></td> 
                            <td></td>
                        </tr>

                        <tr>

                            <td><input type="text" class="inputResultadoPesquisa" placeholder="Autor" required="required" name="txtAutor" value="<%=p.getAutor()%>"> <label id="TextoConsultarLivro">Autor</label> </td>   
                            <td></td>
                        </tr>



                        <tr>

                            <td><input type="text" class="inputResultadoPesquisa" placeholder="Preço" required="required" name="txtPreco" value="<%=p.getPreco()%>"><label id="TextoConsultarLivro">Preço</label> </td>   
                            <td></td>
                        </tr>


                        <tr>

                            <td><input type="date" class="inputResultadoPesquisa" required="required" name="txtLancamento" value="<%=p.getLancamento()%>"><label id="TextoConsultarLivro">Data de Lançamento</label> </td>   
                            <td></td>
                        </tr>


                        <tr>

                            <td><input type="text" class="inputResultadoPesquisa" placeholder="Isbn" required="required" name="txtIsbn" value="<%=p.getIsbn()%>" disabled><label id="TextoConsultarLivro">Isbn</label></td>   
                            <td></td>
                        </tr>



                        <tr>

                            <td><input type="hidden"  name="txtIsbn" value=<%=p.getIsbn()%>></td>   
                            <td></td>
                        </tr>
                        
                        
                                 <tr>  
                            <td>
                               
                                <select  class="inputResultadoPesquisa" name="txtGenero">
                                    <%
                                        for (Genero genero : generos) {

                                    %>
                                    <option  value="<%=genero.getId_genero()%>">


                                        <%=genero.getDesc_genero()%>

                                        <%}%>

                                    </option>
                                </select>
                                        <label id="TextoConsultarLivro">Genero</label>
                            </td>
                        </tr> 

                        
                        <tr>

                            <td> <textarea name="txtSinopse" rows="10" cols="30" class="inputSinopse" placeholder="Sinopse"> <%=p.getSinopse()%> </textarea></td>   
                            <td></td>
                        </tr>



                    </table>
                    <input type="submit" value="Atualizar" class="submit" name="acao">
                    <input type="submit" value="Excluir" class="submit" name="acao">

                </div>

            </form>


        </div>
        <div class="col span_1_of_2">
            <a href="principal.jsp"><img src="image/icon-branco.png" class="icon" title="Acesse o portal"></a>


            <div id="box-intro">

                <h2><i>Virtual Book</i></h2>
                <br>

            </div>
        </div>
    </div>


</body>
</html>
