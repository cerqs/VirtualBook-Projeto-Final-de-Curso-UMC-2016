
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Virtual book</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />      
        <link rel="stylesheet" href="assets/css/main.css" />

    </head>
    <body>

        <!-- Header -->
        <header id="header">
            <h1>Bem-vindo a tela de login</h1>
            <p>Para acessar seu cadastro insira seu email e sua senha<br />
                Caso nao tenha um cadastro clique <a href="cadastrarCliente.jsp">AQUI</a>.</p>
        </header>


        <script src="assets/js/main.js"></script>

        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>

        <font color="red"> <%=msg%></font>
        <%}%>

        <form action="ControleAcesso" method="POST">
            Login: <input type="email" name="txtLogin"><br/>
            Senha: <input type="password" name="txtSenha"><br/>
            <input type="submit" value="Logar" name="acao">
        </form>

    </body>
</html>
