<%-- 
    Document   : InserirUpload
    Created on : 23/03/2016, 16:43:39
    Author     : 11131103404
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Virtual book</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="assets/css/main_1.css" />

    </head>
    <body class="homepage">
        <div id="page-wrapper">

            <!-- Header -->
            <div id="header-wrapper">
                <header id="header" class="container">

                    <!-- Logo -->
                    <div id="logo">
                        <h1><a href="principal.jsp">Virtual Book</a></h1>

                    </div>

                    <!-- Nav -->
                    <nav id="nav">
                        <ul>
                            <li class="current"><form action="InserirUpload.jsp" method="post">
                                    <input type="submit" name="acao" value="Cadastrar">
                                </form></li>
                            <li>
                            <li><form action="ControleProduto" method="post">
                                    <input type="submit" name="acao" value="Listar">
                                </form></li>
                            <li><form action="atualizarLivro.jsp" method="post">
                                    <input type="submit" name="acao" value="Atualizar">
                                </form></li>
                            <li><form action="excluirLivroFuncionario.jsp" method="post">
                                    <input type="submit" name="acao" value="Excluir">
                                </form></li>
                            <li><form action="consultarLivroFuncionario.jsp" method="post">
                                    <input type="submit" name="acao" value="Consultar">
                                </form></li>
                        </ul>
                    </nav>


                </header>
            </div>
    </body>



    <div id="banner-wrapper">
        <div id="banner" class="box container">
            <div class="row">
                <div class="7u 12u(medium)">


                    <form id="form" action="CriarUpload" method="post" enctype="multipart/form-data">

                        <table>
                            <tr>

                                <td><label for="Foto" ></label>Selecione Uma foto para o livro :</td>
                                <td><input type="file" id="foto" name="filetoupload"/></td>
                                <td></td></br>
                            </tr>

                            <br> </br>
                        </table>
                        <input type="submit" value ="Proxima Etapa" name="acao">                        
                    </form> 




                </div>
                <span id="msg"></span>
                </body>


            </div>
            <div class="5u 12u(medium)">
                -
            </div>
        </div>

    </div>



</div>



<script src="js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="js/jquery.validate.js"type="text/javascript"></script>
<script src="js/val.js"type="text/javascript"></script>
<link href="css/css.css" rel="stylesheet" type="text/css"/>

</html>
