<%@ include file="Superior.jsp"%>

<br/>
<blink>
    <center>
        <div class='cuadroError'>
            <div style="font-size:15pt; color:red">�Error!</div>
            <font size="4"><b>Su sesion a caducado o Existe errores, Consulte con el Administrador...!!!!</b></font><br>
            <a class="btn btn-success" href='<c:url value="/" />' target="_top" width="89">
                Clic aqui para Iniciar Sesi&oacute;n <img src="<c:url value="/" />imagenes/dibRap/aceptar.png">
            </a>
        </div>
    </center>
</blink>

<%@ include file="Inferior.jsp"%>