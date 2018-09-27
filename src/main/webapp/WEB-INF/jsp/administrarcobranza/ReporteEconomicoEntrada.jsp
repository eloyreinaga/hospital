<%@ include file="../Superior.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />

<form name="forma" method="POST" action='<c:url value="/ReporteEconomico.do"/>' >
    <center>
        <table class="table table-striped table-bordered table-condensed table-responsive"> 
            <tr>
                <th bgcolor="#F2F2F2"><center>BUSQUEDA DE DATOS COBRANZA POR FECHAS</center></th>
            </tr>
            <tr>
                <td>
                    <fieldset> 
                        <table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 	     
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
                                    <td  align="right" bgcolor="#F2F2F2">Rubro de Cobro::  </td>	      
                                    <td>
                                        <SELECT NAME="id_rubro" onchange="javascript:document.actualizar.submit();">
                                            <option value="0">-- T O D O S --</option>
                                        <c:forEach var="estado" items="${listarRubros}">
                                            <c:if test="${estado.id_rubro!=0 }">   
                                                <OPTION value="<c:out value="${estado.id_rubro}"/>" <c:if test="${estado.id_rubro == id_rubro}">selected</c:if>> 
                                                    <c:out value="${estado.rubro}"/>
                                                </option>
                                            </c:if> 
                                        </c:forEach>
                                    </SELECT>	
                                </td>       
                            </tr>      
                        </table>
                    </fieldset>
                </td>
            </tr>
        </table>
    </center>

    <c:if test="${tipo_consul!=3}">
        <center>
            <input type="submit" name='boton' class="btn btn-primary btn-lg" value='Buscar' onclick="document.forma.action = '<c:url value="/ReporteEconomico.do"/>';">      
        </center>
    </c:if>           

    <br>
    <center>
        <div style="font-size:15pt" bgcolor="#F2F2F2"><center> REPORTES DETALLADOS SEGUN RUBROS PDF</center></div>

        <c:forEach var="listaRubros" items="${listarRubros}">
            <c:if test="${tipo_consul==3 and listaRubros.id_rubro==3}"> 
                <input type="submit" name='accion' class="btn btn-info" value='<c:out value="${listaRubros.rubro}"/>' onclick="document.forma.action = '<c:url value="/ReporteEconomico.do"/>';">    
                <input type="hidden" name='id_rubro1'    value='<c:out value="${listaRubros.id_rubro}"/>' >
            </c:if> 
            <c:if test="${tipo_consul!=3 }"> 
                <input type="submit" name='accion' class="btn btn-info" value='<c:out value="${listaRubros.rubro}"/>' onclick="document.forma.action = '<c:url value="/ReporteEconomico.do"/>';">    
                <input type="hidden" name='id_rubro1'    value='<c:out value="${listaRubros.id_rubro}"/>' >
            </c:if> 
        </c:forEach>
        <br>
        <div style="font-size:15pt" bgcolor="#F2F2F2"><center> REPORTES DETALLADOS SEGUN RUBROS XLS</center></div>

        <c:forEach var="listaRubros" items="${listarRubros}">
            <c:if test="${tipo_consul==3 and listaRubros.id_rubro==3}"> 
                <input type="submit" name='accion' class="btn btn-success" value='<c:out value="${listaRubros.rubro}"/>.xls' onclick="document.forma.action = '<c:url value="/ReporteEconomico.do"/>';">      
                <input type="hidden" name='id_rubro1'    value='<c:out value="${listaRubros.id_rubro}"/>' >
            </c:if> 
            <c:if test="${tipo_consul!=3 }"> 
                <input type="submit" name='accion' class="btn btn-success" value='<c:out value="${listaRubros.rubro}"/>.xls' onclick="document.forma.action = '<c:url value="/ReporteEconomico.do"/>';">   
                <input type="hidden" name='id_rubro1'    value='<c:out value="${listaRubros.id_rubro}"/>' >
            </c:if> 
        </c:forEach>
    </center>           
</form>
