<%-- 
    Document   : testandoooooo
    Created on : 01/04/2016, 16:46:24
    Author     : 11131103404
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
        <script src="js/validacaoLivroCADASTRAR.js"type="text/javascript"></script>

        <!-- Stylesheets -->
        <link href="css/BarraNavegar.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/CadastroDeLivro2.css" media="all">
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
                margin-top: 50px;
            }
        </style>
        
        
        
        <ul class="w3-navbar w3-black w3-card-2 w3-top w3-left-align w3-large">

        <li class="w3-hide-medium w3-hide-large w3-opennav w3-right">    
        </li>

        <li><a href="AdmTelaPrincipal.jsp" class="w3-padding-large w3-white">HOME</a></li>             
        <li class="w3-hide-small"><a href="AdmRelatorios.jsp" class="w3-padding-large w3-hover-white">Gerar Relatorios</a></li>
        <li class="w3-hide-small"><a href="AdmGerenciarLivros.jsp" class="w3-padding-large w3-hover-white">Gerenciar livros</a></li>          
        <li class="w3-hide-small"><a href="ControleAcesso?acao=Sair" class="w3-padding-large w3-hover-white">Sair</a></li>
    </ul>



    <%
        // Recupera os produtos do request
        ArrayList<Genero> generos = (ArrayList<Genero>) request.getAttribute("generos");
        if (generos == null) {
            //envia requisição para a servlet 
            request.getRequestDispatcher("/ControleProduto?acao=listarGenero").forward(request, response);
        }
    %>

</head>

<body id="background-layout">




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
                <h2 id="login">Cadastrando livro</h2>
                <br>
                <div id="box-inputs">

                    <table>

                        <tr> 

                            <td><input type="text" class="input" placeholder="Titulo" required="required" name="txtTitulo" onfocus="AparecerTitulo()" onfocusout="SumirTitulo()" > </td> 
                            <td><p id="ajudaTitulo" style="display:none">Dite um TITULO para o livro</p></td>

                        </tr>

                        <tr>

                            <td><input type="text" class="input" placeholder="Autor" required="required" name="txtAutor"  onfocus="AparecerAutor()" onfocusout="SumirAutor()"> </td>   
                            <td><p id="ajudaAutor" style="display:none">Digite o nome do AUTOR do livro!</p></td>
                        </tr>



                        <tr>

                            <td><input type="text" class="input" placeholder="Preço" required="required" name="txtPreco" onchange="alteraPonto($(this))" onfocus="AparecerPreco()" onfocusout="SumirPreco()"> </td>   
                            <td><p id="ajudaPreco" style="display:none">Digite o valor que custara o livro ex:18.62 </p></td>
                        

                        </tr>

                        <tr>  
                            <td>

                                <select  class="input" name="txtGenero" onfocus="AparecerGenero()" onfocusout="SumirGenero()">
                                    <%
                                        for (Genero genero : generos) {

                                    %>
                                    <option  value="<%=genero.getId_genero()%>">


                                        <%=genero.getDesc_genero()%>

                                        <%}%>

                                    </option>
                                </select>                       
                            </td>
                            <td><p id="ajudaGenero" style="display:none">Selecione o genero o livro</p></td>
                        </tr>  



                        <tr>

                            <td><input type="date" class="input" required="required" name="txtLancamento" onfocus="AparecerLancamento()" onfocusout="SumirLancamento()"> </td>   
                            <td><p id="ajudaLancamento" style="display:none">Selecione a data que o livro foi lançado</p></td>
                        </tr>


                        <tr>

                            <td><input type="text" class="input" placeholder="Isbn" name="txtIsbn" onfocus="AparecerIsbn()" onfocusout="SumirIsbn()"> </td>   
                            <td><p id="ajudaIsbn" style="display:none">Digite o isbn do livro</p></td>
                        </tr>


                        <tr>

                            <td> <textarea name="txtSinopse" rows="10" cols="30" class="input3" placeholder="Sinopse" onfocus="AparecerSinopse()" onfocusout="SumirSinopse()"></textarea>  </td>   
                            <td><p id="ajudaSinopse" style="display:none">Digite a sinopse aonde os usuarios poderao ler</p></td>
                        </tr>





                    </table>

                    <input type="submit" value="Cadastrar" class="submit" name="acao">


                </div>

            </form>

        </div>

    </div>


</body>
</html>