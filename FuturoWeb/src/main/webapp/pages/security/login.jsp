<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core">
    <h:head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </h:head>

    <body id="body">
        <form id="formLogin" method="post" action="j_security_check" style="position:absolute;left:40%;top:40%;" >
            <div style="margin-top: 5px;"><input type="text" id="identificador" name="j_username" placeholder="seu login" onkeydown="if (event.keyCode == 13)
                        document.getElementById('buttonEntrar').click()"/></div>
            <div style="margin-top: 5px;"><input type="password" id="senha" name="j_password" placeholder="sua senha" onkeydown="if (event.keyCode == 13)
                        document.getElementById('buttonEntrar').click()"/></div>
            <div style="margin-top: 5px;">
                <div style="float: right;">
                    <a id="buttonEntrar" href="#" onclick="document.getElementById('formLogin').submit();">
                        <img src="${pageContext.request.contextPath}/resources/images/home/botao_entrar.jpg" width="50" height="23"/>
                    </a>
                </div>
                <div style="clear: both"></div>
            </div>
        </form>
    </body>
</html>