
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
                        <h1><a href="InserirUpload.jsp">Virtual Book</a></h1>

                    </div>  
                </header>
            </div>
    </body>






    <!-- Banner -->
    <div id="banner-wrapper">
        <div id="banner" class="box container">
            <div class="row">
                <div class="7u 12u(medium)">

                    <form id="form" action="ControleProduto" method="post">

                        <table>

                            <tr>
                                <td><label for="id" ></label>ID</td>
                                <td><input type="text" id="id" name="txtIds"/></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><label for="titulo" ></label>Titulo</td>
                                <td><input type="text" id="titulo" name="txtTitulo"/></td>
                                <td></td>
                            </tr>
                            <tr>

                                <td><label for="autor" ></label>Autor</td>
                                <td><input type="text" id="autor" name="txtAutor"/></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><label for="preco" ></label>Preço</td>
                                <td><input type="text" id="preco" name="txtPreco"/></td>
                                <td></td>
                            </tr>
                            <tr>

                                <td><label for="lancamento" ></label>Lançamento</td>
                                <td><input type="date" id="lancamento" name="txtLancamento"/></td>
                                <td></td>
                            </tr>
                            <tr>

                                <td><label for="isbn" ></label>Isbn</td>
                                <td><input type="text" id="isbn" name="txtIsbn"/></td>
                                <td></td>
                            </tr>
                            <tr>

                                <td><label for="sinopse" ></label>Sinopse</td>
                                <td><input type="text" id="sinopse" name="txtSinopse"/></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>                        
                        </table>
                        <input type="submit" name="acao" value="Atualizar">
                        </tr>

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