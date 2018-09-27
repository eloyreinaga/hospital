<%@ include file="../Superior.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />

<form name="forma" method="POST" action='<c:url value="/ListarPacientesAtendidosInter.do"/>' >
    <table class="table table-striped table-bordered table-condensed table-responsive">
        <tr>
            <th><center>Mostrar Detalle de Pacientes Internados por fecha</center></th>
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
                            <tr>
                                <td align="right" bgcolor="#F2F2F2">Servicio Internacion  </td>     
                                <td>
                                    <SELECT NAME="id_sala" onchange="javascript:document.adicionarcolegio.submit();">
                                        <option value="0">-- seleccione --
                                        <c:forEach var="estado" items="${listarSalas}">
                                        <option value="<c:out value="${estado.id_sala}"/>" <c:if test="${estado.id_sala == id_sala}">selected</c:if>>
                                            <c:out value="${estado.sala}"/>
                                        </option></c:forEach></SELECT>	
                                </td>       
                            </tr>  
                        </table>
                    </fieldset>
                </td>
            </tr>
        </table>
    <c:if test="${tipo_medico == '7'}">
        <table class="formulario" align="center" >

        </table>
        <center>
            <input type="submit" name="boton" class="btn btn-primary btn-lg"  value="Resumen">
            <input type="hidden" name="id_persona"              value=<c:out value="${id_persona}"/> >         
            <input type="hidden" name="id_consultorio"          value=<c:out value="${lista.id_consultorio}"/> >         
            <input type="hidden" name="id_paciente"             value=<c:out value="${lista.id_paciente}"/> >      
        </center>
    </c:if>
    <br>

</form>


<%@ include file="../Inferior.jsp" %>