<%-- 
    Document   : newjsp
    Created on : 23/03/2016, 15:05:11
    Author     : 11131103404
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- HTML5 Boilerplate -->
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <title>Seja bem-vindo | Virtual Book</title>

        <meta http-equiv="cleartype" content="on">

        <link rel="shortcut icon" href="/favicon.ico">

        <meta name="MobileOptimized" content="320">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <script src="js/jquery-1.7.2.js" type="text/javascript"></script>
        <script src="js/jquery.validate.js"type="text/javascript"></script>
        <script src="js/validacaoLogin.js"type="text/javascript"></script>

        <!-- Stylesheets -->
        <link rel="stylesheet" href="css/style.css" media="all">
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
            #background-layout{background:url(image/background-fundo.jpg);
                               background-size:110%;}
            #formLogin{
                background:url(image/textura-input.jpg);
                background-size:100%;
                opacity:0.8;
                border-radius:30px 0px 30px 0px;
            }
        </style>

    </head>

    <body id="background-layout">



        <!--- Sistema de Grid, não alterar --->

        <div class="section group">
            <div class="col span_1_of_2">



            </div>
            <div class="col span_1_of_2">


            </div>
        </div>

        
           <%
            String RecuperacaoOk = (String) request.getAttribute("recuperacao");
            if (RecuperacaoOk != null) {
        %>



        <font color="yellow"> <%=RecuperacaoOk%></font>
        <%}%>


        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>



        <font color="red"> <%=msg%></font>
        <%}%>

        <span id="msg"></span>

        <div class="section group">
            <div class="col span_1_of_2">



                <form id="formLogin" action="ControleAcesso" method="POST">



                    <br>
                    <h2 id="login">Login</h2>
                    <br>
                    <div id="box-inputs">

                        <table>


                            <tr>
                                <td><input type="email" class="input" placeholder="email@email.com" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required="required" name="txtLogin">   </td> 
                                <td></td>
                            </tr>

                            <tr>

                                <td><input type="password" class="input" placeholder="Senha" required="required" name="txtSenha"> </td>   
                                <td></td>
                            </tr>

                        </table>

                        <input type="submit" value="Logar" class="submit" name="acao">


                    </div>
                </form>
            </div>
            <div class="col span_1_of_2">
                <a href="#"><img src="image/icon-branco.png" class="icon" title="Acesse o portal"></a>


                <div id="box-intro">

                    <h2><i>Virtual Book</i></h2>
                    <br>

                    <h4>
                        No Virtual Book você tem acesso a um ótimo acervo. Um site projetado com os melhores livros da atualidade. Acesse já sua conta e aproveite! 

                    </h4>

                </div>
            </div>
        </div>

        <div class="section group">
            <div class="col span_1_of_2">
                <p class="links-avulsos">
                    <a href="RecuperarSenha.jsp">Esqueceu sua senha?</a> 
                </p>	

                <p class="links-avulsos">
                    <a href="cadastrarCliente.jsp">Criar uma conta</a>
                </p>	
            </div>
            <div class="col span_1_of_2">

                <h2 id="virtual-linkdireto"><a href="PaginaDeLivros.jsp">Ir para VirtualBook.com</a></h2>

            </div>
        </div>


    </body>
</html>
