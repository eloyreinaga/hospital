<%@ include file="../Superior.jsp" %>


<div style="font-size:15pt"> Resumen de prestaciones Duplicadas</div>
<br>

<table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
    <tr style="font-size:9pt">
        <th bgcolor="#F2F2F2"> No. </th>
        <th bgcolor="#F2F2F2"> ID </th>  
        <th bgcolor="#F2F2F2"> HCL </th>
        <th bgcolor="#F2F2F2"> Nombre Paciente </th>  
        <th bgcolor="#F2F2F2"> Fecha </th>
        <th bgcolor="#F2F2F2"> Prestacion </th>
        <th bgcolor="#F2F2F2"> Descripcion </th>
        <th bgcolor="#F2F2F2"> Cantidad </th>
        <th bgcolor="#F2F2F2"> Accion </th>
    </tr>  
    <c:forEach var="lista" items="${listarRecetasPres}" varStatus="contador">
        <tr style="font-size:9pt">
            <td align="center"><c:out value="${contador.count}"/></td>
        <form name=formaH<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarHistorial.do"/>'>
            <td>     
                <div><center><a href="javascript:document.formaH<c:out value="${contador.count}"/>.submit();"><c:out value="${lista.id_paciente}"/></a></center></div>
                <input type="hidden" name="id_paciente"    value='<c:out value="${lista.id_paciente}"/>'>
                <input type="hidden" name="id_prestacion"  value='<c:out value="${lista.id_prestacion}"/>'>
                <input type="hidden" name="fecha"         value='<fmt:formatDate value="${lista.fecha_fin}" pattern='dd/MM/yyyy HH:mm'/>' >
                <input type="hidden" name="accion"         value='Historial'>
                <input type="hidden" name="sw"             value='1'>
            </td>
        </form>
        <td ><c:out value="${lista.hcl}"/></td> 
        <td ><c:out value="${lista.paquete}"/></td> 
        <td ><fmt:formatDate value="${lista.fecha_fin}" pattern='dd/MM/yyyy'/></td>
        <td ><c:out value="${lista.prestacion}"/></td>
        <td><c:out value="${fn:substring(lista.descripcion,0,40)}"/></td> 
        <td align="center" style="font-size:15pt"><c:out value="${lista.cantidad}"/></td>
        <form name=formaMai<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarPacientesAtendidos.do"/>'>
            <td>     
                <div><a class="btn btn-warning btn-xs" href="javascript:document.formaMai<c:out value="${contador.count}"/>.submit();">Borrar</a></div>
                <input type="hidden" name="id_historial"     value='<c:out value="${lista.id_historial}"/>'>
                <input type="hidden" name="id_paciente"      value='<c:out value="${lista.id_paciente}"/>'>
                <input type="hidden" name="id_prestacion"    value='<c:out value="${lista.id_prestacion}"/>'>
                <input type="hidden" name="diai"             value='<c:out value="${diai}"/>'>
                <input type="hidden" name="mesi"             value='<c:out value="${mesi}"/>'>
                <input type="hidden" name="anioi"            value='<c:out value="${anioi}"/>'>
                <input type="hidden" name="horai"            value='<c:out value="${horai}"/>'>
                <input type="hidden" name="minutoi"          value='<c:out value="${minutoi}"/>'>
                <input type="hidden" name="diaf"             value='<c:out value="${diaf}"/>'>
                <input type="hidden" name="mesf"             value='<c:out value="${mesf}"/>'>
                <input type="hidden" name="aniof"            value='<c:out value="${aniof}"/>'>
                <input type="hidden" name="horaf"            value='<c:out value="${horaf}"/>'>
                <input type="hidden" name="minutof"          value='<c:out value="${minutof}"/>'>
                <input type="hidden" name="valor_1"          value='<fmt:formatDate value="${lista.fecha_fin}" pattern='yyyy-MM-dd'/>' >
                <input type="hidden" name="valor_2"          value='<fmt:formatDate value="${lista.fecha_fin}" pattern='yyyy-MM-dd'/>' >
                <input type="hidden" name="valorf"           value='<c:out value="${valorf}"/>'>
                <input type="hidden" name="boton"            value='PresDuplicadas'>
                <input type="hidden" name="sw"               value='1'>
            </td>
        </form>   
    </tr>  
</c:forEach>
</table>