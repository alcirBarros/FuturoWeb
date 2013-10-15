<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>FuturoWeb</title>
        <!-- link type="text/css" rel="stylesheet" href="http://fonts.googleapis.com/css?family=Magra:400,700"></link -->
        <link type="text/css" rel="stylesheet" href="resources/css/home.css"/>
        <script type="text/javascript" src="resources/js/util.js"></script>
    </head>

    <body>

        <div style="width: 100%; margin:0 auto;">

            <div style="position: absolute; left:40%; top: 200px;">

                <form id="formLogin" method="post" action="login">

                    <table width="200">
                        <tbody>
                            <tr>
                                <td class="left"><img src="resources/images/home/login.png" width="76" height="30"/></td>
                                <td class="right"><img src="resources/images/home/login_chave.png" width="66" height="33"></td>
                            </tr>
                        </tbody>
                    </table>

                    <table style="margin-top: 4px" width="200">
                        <tbody>
                            <tr>
                                <td class="right">

                                    <input id="identificador" type="text" name="identificador" class="inputtext"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="right">
                                    <input id="senha" type="password" name="senha" style="margin-top: 4px; margin-bottom: 4px;" class="inputtext"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="right">
                                    <a id="buttonEntrar" href="#" onclick="document.getElementById('formLogin').submit()">
                                        <img src="resources/images/home/botao_entrar.jpg" width="50" height="23"></a><br />
                                    <span style="color: red; font-size: 12px;"><%= (request.getParameter("error") != null) ? "Identificador ou senha inv&aacute;lidos" : ""%></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>