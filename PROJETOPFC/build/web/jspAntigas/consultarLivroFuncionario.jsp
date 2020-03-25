

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
                                <td><input type="text" id="id" name="txtID"/></td>
                                <td></td>
                            </tr>  

                            <tr>
                                <td></td>                        
                        </table>
                        <input type="submit" name="acao" value="consultar">
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
