<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <META HTTP-EQUIV="Refresh" CONTENT="7    ;URL= index.jsp">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <title>Seja bem-vindo | Virtual Book</title>

        <meta http-equiv="cleartype" content="on">

        <link rel="shortcut icon" href="/favicon.ico">
        <meta name="MobileOptimized" content="320">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <script src="js/jquery-1.7.2.js" type="text/javascript"></script>
        <script src="js/jquery.validate.js"type="text/javascript"></script>
        <script src="js/validacao.js"type="text/javascript"></script>

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
            #formCadastro{
                background:url(image/texturza-input.jpg);
                background-size:100%;
                opacity:100;
                border-radius:30px 0px 30px 0px;
            }
        </style>

    </head>

    <body id="background-layout">

        <%
            Usuario u = (Usuario) request.getAttribute("msgCadastrado");

        %> 


        <!--- Sistema de Grid, não alterar --->

        <div class="section group">
            <div class="col span_1_of_2">



            </div>
            <div class="col span_1_of_2">


            </div>
        </div>

        <span id="msgg"></span> 

        <div class="section group">
            <div class="col span_1_of_2">



                <form id="formCadastro" action="ControleAcesso" method="POST">                   
                    <br>

                    <div id="box-intro-cadastradosucesso">



                        <h2><i>Olá <%=u.getNome()%> Seu cadastro foi efetuado com sucesso.</i></h2>
                        <br>
                        <p> Em instantes você receberá um e-mail.</p>
                        <br>                          
                        <p>
                            Em  segundos, a página será redirecionada para a tela de login. 
                        </p>

                        <br>



                        <h4>


                        </h4>

                    </div>


                </form>
            </div>
            <div class="col span_1_of_2">
                <a href="#"><img src="image/icon-branco.png" class="icon" title="Acesse o portal"></a>

            </div>
        </div>


    </body>    

</html>