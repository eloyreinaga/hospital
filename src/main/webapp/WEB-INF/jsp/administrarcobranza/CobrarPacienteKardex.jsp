<%@ include file="../Superior.jsp" %>


<div style="font-size:15pt"> Kardex de Pagos del Paciente</div>

<div><a class="volver" href='ListarCobroReserva.do'>Volver</a></div>
<br>
<table class="formulario" width="50%">
    <tr>
        <th colspan="3"><font size=2>DATOS PERSONALES </font></th>
    </tr>
    <tr>    
        <td>Nombres Completo</td>    
        <td>::</td>
        <td><c:out value = "${nombres}"/></td>
    </tr>
</table>
<table class="formulario" width="50%">
    <tr>
        <th> Fecha </th>
        <th> Detalle </th>
        <th> Deuda </th>
        <th> A cuenta </th>       
    </tr> 
    <c:forEach var="estado" items="${listarcobroskardex}" varStatus="contador">
        <tr style="font-size:9pt">
            <td><fmt:formatDate value="${estado.fecha}" pattern='dd/MM/yyyy HH:mm:ss'/></td>           
            <td><c:out value="${estado.costo}"/></td>
            <td><c:out value="${estado.entrada}"/></td>                                             
            <td><c:out value="${estado.salida}"/></td>                                   
        </c:forEach>
        <c:forEach var="estadosaldo" items="${listarcobrosaldo}" varStatus="contador">
        <tr bgColor="#FFFFFF">
            <td colspan="2" align="center" style="color:blue">Totales</td>           
            <td style="color:blue"><c:out value="${estadosaldo.entrada}"/></td>
            <td style="color:blue"><c:out value="${estadosaldo.salida}"/></td>
        </tr>
        <tr bgColor="#FFDDD9">
            <td colspan="3" align="right" style="font_size:12pt"><b>DEUDA TOTAL (Bs.)</b></td>           
            <td style="font_size:13pt"><b><c:out value="${estadosaldo.saldo}"/></b></td>
        </tr>
    </c:forEach> 
</table> 