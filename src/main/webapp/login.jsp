<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h2>Aplicação web (MVC) com Banco de Dados - Login</h2>
        <form action="Autenticar">
            Código <input type="text" name="codigo" /><br />
            Senha  <input type="password" name="senha" /><br />
            <input type="submit" value="Entrar" />
            <input type="reset" value="Limpar" />
        </form>
        <p style="color: red">${param.msg}</p>
    </body>
</html>

