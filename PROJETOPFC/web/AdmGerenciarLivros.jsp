<%--
    Document   : testandoListar
    Created on : 04/04/2016, 15:51:04
    Author     : 11131103404
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%--
    Document   : testandoExcluir
    Created on : 03/04/2016, 02:59:52
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
        <script src="js/jquery-1.7.2.js" type="text/javascript"></script>
        <script src="js/jquery.validate.js"type="text/javascript"></script>
        <script src="js/validacaoLogin.js"type="text/javascript"></script>
         <link href="js/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/completion.js" type="text/javascript"></script>

        <!-- Stylesheets -->

        <link rel="stylesheet" href="css/ListarLivro.css" media="all">
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
        <link href="scripts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

        <style type="text/css">

            th, td {
                padding: 2px;
            }

            th {
                text-align: left;
            }


            table#t01 tr:nth-child(even) {
                background-color:#c5b8fc;

            }
            table#t01 tr:nth-child(odd) {

            }

            body { padding:2em; font : 100%/1.4 'Helvetica Neue', arial, helvetica, helve, sans-serif;
            }
            h1 { font-size:2.2em; padding:0 0 .5em 0; }
            h2 { font-size:1.5em; }
            .header { padding:1em 0; }
            /*
            .col { background: #ccc; padding:1em 0; text-align:center;}
            */
            #background-layout{background:url(image/livros.jpg);
                               background-size:300%;}
            /*                            #formCadastrarLivro{
                                        background:url(image/textura-input.jpg);
                                        background-size:100%;
                                        opacity:0,9;
                                        border-radius:30px 30px 30px 30px;
                                    }*/
        </style>



    <form action="ControleProduto" method="post">
        <div id="retornoAjax" class="ui-widget">
        <input type="text" autocomplete="on" id="search" class="input2" placeholder="Consultar Livro pelo isbn" required="required" name="isbn">        
        <button type="submit" class="submitCadastrarLivroPesquisar" title="pesquisar" name="acao" value="RecuperaLivroParaAtualizar"><i class="fa fa-search" aria-hidden="true" style="font-size:25px"></i></button>
        </div>  
    </form>



</head>

<body id="background-layout">


    <%
        String msgNaoExisteIsbn = (String) request.getAttribute("msgNaoExisteIsbn");
        if (msgNaoExisteIsbn != null) {
    %>

    <font id="MsgNaoExiste" color="red">  <%=msgNaoExisteIsbn%></font>
    <%}%>

    <%
        String msgExcluido = (String) request.getAttribute("msgExcluido");
        if (msgExcluido != null) {
    %>

    <font id="MsgNaoExiste" color="red"> <%=msgExcluido%></font>
    <%}%>




    <%
        ArrayList<Produto> listaProduto = (ArrayList<Produto>) request.getAttribute("listaProduto");
        if (listaProduto == null) {

            request.getRequestDispatcher("/ControleProduto?acao=Listar").forward(request, response);
        }
    %>


    <nav id="nav">
        <ul>

            <li><form action="AdmTelaPrincipal.jsp" method="post">
                    <input type="submit" class="submitCadastrarLivro" name="acao" value="HOME">
                </form></li>

            <li class="current"><form action="AdmCadastrarLivro.jsp" method="post">
                    <input type="submit" class="submitCadastrarLivro" name="acao" value="Cadastrar">
                </form></li>
            <li>
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


    <form action="ControleProduto" method="post">
        <label>Filtrar por:</label>
        <select id="opcoes3"  name="cars">
            <option value="titulo">Título</option>
            <option value="data">Data</option>
            <option value="preco">Preço</option>
            <option value="inativos">Inativos</option>
        </select>
        <button id="opcoes3" value="Listar" name="acao" ><i class="fa fa-filter" aria-hidden="true" style="font-size:17px"></i></button>       




        <div class="section group">
            <div class="col span_1_of_2">



                <form id="formCadastrarLivro" action="ControleProduto" method="POST">


                    <div id="box-inputs">
                        <caption> <h1 id="cabecalho">Livros cadastrados: </h1> </caption>
                        <table border="1" width="1210px" class="borda" id="t01">

                            <thead>


                                <tr>
                                    <th width="250px">Isbn</th>
                                    <th width="20px"></th>
                                    <th width="400px">Titulo</th>
                                    <th width="280px">Autor</th>
                                    <th width="110px">Preço</th>
                                    <th width="150px">Lançamento</th>
                                    <th width="90px">Status</th>


                                </tr>
                            </thead>


                            <%
                                SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd/MM/yyyy");
                                String dataFormatada = null;
                                DecimalFormat df = new DecimalFormat("###,##0.00");

                                for (Produto p : listaProduto) {

                                    dataFormatada = formatoDesejado.format(p.getLancamento());

                            %>
                            <tr>

                                <td> <%= p.getIsbn()%> </td>
                                <td> </td>
                                <td> <%= p.getTitulo()%> </td>
                                <td> <%= p.getAutor()%> </td>
                                <td> <%=df.format(p.getPreco())%> </td>
                                <td> <%=dataFormatada%> </td>
                                <td> <%= p.getStatus()%> </td>

                                <td width="69px"> <a
                                        href="ControleProduto?acao=RecuperaLivroParaAtualizar&isbn=<%=p.getIsbn()%>">
                                        <button type="button" id="opcoes2" onclick="alert('Altere somente os dados necessários')" title="Editar item"><i class="fa fa-pencil-square-o" aria-hidden="true" style="font-size:19px"></i></button></td>


                                <%if (p.getStatus().equals("inativo")) {
                                %>

                                <td> 
                                   <i class="fa fa-times" style="font-size:19px"></i></td>


                                <%}%>

                            </tr>
                            <%
                                }
                            %>


                        </table>



                    </div>

                </form>

            </div>

        </div>


</body>
</html>

