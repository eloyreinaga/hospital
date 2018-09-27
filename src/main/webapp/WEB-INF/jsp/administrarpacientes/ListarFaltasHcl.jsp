<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>

<jsp:useBean id="now" class="java.util.Date" />
<div class=titulo> Ultimas 10000 Historias Clinicas Faltantes o Saltadas</div>
<br>

<form name=formaRegO method=post action='<c:url value="/ListarAfiliadosSumi.do"/>'>
    <div class="modificar"><a href="javascript:document.formaRegO.submit();">Ascendente</a></div>
    <input type="hidden" name='id_reservacion'  value='<c:out value="${id_reservacion}"/>'>  
    <input type="hidden" name='boton'          value='HCL vacias Des'>
</form>

<table class="tabla">
    <tr>
        <th> No </th>
        <th> Fecha </th>
        <th> HCL </th>
    </tr>  
    <c:forEach var="lista" items="${listaf}" varStatus="contador">
        <tr style="font-size:9pt">
            <td><c:out value="${contador.count}"/></td> 
            <td><fmt:formatDate value="${lista.fec_registro}" pattern='dd/MM/yyyy'/></td>
            <td><c:out value="${lista.hcl}"/></td>
        </c:forEach>

</table>
<%@ include file="../Inferior.jsp" %>