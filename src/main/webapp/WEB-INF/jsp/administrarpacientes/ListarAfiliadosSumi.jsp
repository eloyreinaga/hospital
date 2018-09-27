<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />

<form name="forma" method="POST" action='<c:url value="/ListarAfiliadosSumi.do"/>' >
    <center>
        <table class="table table-striped table-bordered table-condensed table-responsive"> 
            <tr>
                <th bgcolor="#F2F2F2"><center>Buscar Afiliados por fecha, atendidos Asegurados</center></th>
            </tr>
            <tr>
                <td>
                    <fieldset> 
                        <table class="table table-striped table-bordered table-condensed table-responsive">      
                            <tr style="font-size:12pt"><td align="right" bgcolor="#F2F2F2"><b> Fecha Inicio</b></td>	
                                <td ><SELECT NAME="diai">
                                        <c:forEach var="dias" items="${dias}">
                                            <option value="<c:out value="${dias}"/>" <c:if test="${dias == dia}">selected</c:if>> 
                                                <c:out value="${dias}"/></option></c:forEach></SELECT>
                                        <SELECT NAME="mesi">
                                        <c:forEach var="meses" items="${meses}">
                                            <option value="<c:out value="${meses}"/>" <c:if test="${meses == mes}">selected</c:if>> 
                                                <c:out value="${meses}"/></option></c:forEach></SELECT>
                                        <SELECT NAME="anioi">
                                        <c:forEach var="anios" items="${anios}">
                                            <option value="<c:out value="${anios}"/>" <c:if test="${anios == anio}">selected</c:if>> 
                                                <c:out value="${anios}"/></option></c:forEach></SELECT>
                                        <SELECT NAME="horai">
                                        <c:forEach var="horas" items="${horas}">
                                            <option value="<c:out value="${horas}"/>" <c:if test="${horas == hora}">selected</c:if>> 
                                                <c:out value="${horas}"/></option></c:forEach></SELECT>
                                        <SELECT NAME="minutoi">
                                        <c:forEach var="minutos" items="${minutos}">
                                            <option value="<c:out value="${minutos}"/>" <c:if test="${minutos == minuto}">selected</c:if>> 
                                                <c:out value="${minutos}"/></option></c:forEach></SELECT>
                                        <br><font size="2" color="blue">&nbsp;&nbsp;&nbsp;Dia&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        Mes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Año&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        Hora&nbsp;&nbsp;&nbsp;&nbsp;Minuto</font>        
                                    </td>
                                </tr>

                                <tr style="font-size:12pt"><td align="right" bgcolor="#F2F2F2"><b> Fecha Inicio</b></td>	
                                    <td ><SELECT NAME="diaf">
                                        <c:forEach var="dias" items="${dias}">
                                            <option value="<c:out value="${dias}"/>" <c:if test="${dias == dia2}">selected</c:if>> 
                                                <c:out value="${dias}"/></option></c:forEach></SELECT>
                                        <SELECT NAME="mesf">
                                        <c:forEach var="meses" items="${meses}">
                                            <option value="<c:out value="${meses}"/>" <c:if test="${meses == mes2}">selected</c:if>> 
                                                <c:out value="${meses}"/></option></c:forEach></SELECT>
                                        <SELECT NAME="aniof">
                                        <c:forEach var="anios" items="${anios}">
                                            <option value="<c:out value="${anios}"/>" <c:if test="${anios == anio2}">selected</c:if>> 
                                                <c:out value="${anios}"/></option></c:forEach></SELECT>
                                        <SELECT NAME="horaf">
                                        <c:forEach var="horas" items="${horas}">
                                            <option value="<c:out value="${horas}"/>" <c:if test="${horas == hora2}">selected</c:if>> 
                                                <c:out value="${horas}"/></option></c:forEach></SELECT>
                                        <SELECT NAME="minutof">
                                        <c:forEach var="minutos" items="${minutos}">
                                            <option value="<c:out value="${minutos}"/>" <c:if test="${minutos == minuto2}">selected</c:if>> 
                                                <c:out value="${minutos}"/></option></c:forEach></SELECT>
                                        <br><font size="2" color="blue">&nbsp;&nbsp;&nbsp;Dia&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        Mes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Año&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        Hora&nbsp;&nbsp;&nbsp;&nbsp;Minuto</font>        
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
            </table>
        </center>
        <center>
            <input type="submit" name="boton" class="btn btn-success" value="HCL vacias">
            <input type="submit" name="boton" class="btn btn-success" value="SIIS Ninio">
            <input type="submit" name="boton" class="btn btn-success" value="SIIS Mujer">
            <input type="submit" name="boton" class="btn btn-success" value="SIIS Mujer Fertil">
            <input type="submit" name="boton" class="btn btn-success" value="SIIS > 60">
            <input type="submit" name="boton" class="btn btn-success" value="SIIS Discapacidad">
            <br>
            <input type="submit" name="boton" class="btn btn-primary" value="Otros Seguros">
            <input type="submit" name='accion' class="btn btn-primary" value='Produccion/Medico' onclick="document.forma.action = '<c:url value="/ControlCalidad.do"/>';"> 
        <input type="submit" name="boton" class="btn btn-primary" value="Buscar Todos">

    </center>
</form>

<%@ include file="../Inferior.jsp" %>