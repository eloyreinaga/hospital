<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>

<div style="font-size:15pt"> Administraci&oacute;n de Quirofanos</div>
<br>

<table>
    <tr>
        <td>
            <form name=forma method=post action='<c:url value="/NuevoQuirofano.do"/>'>
                <div class="agregar">
                    <a class="btn btn-success" href="javascript:document.forma.submit();" >Nuevo</a>
                    <input type=hidden name=accion value='Adicionar'>
                </div>
            </form>
        </td>
        <td>
            <form name=formas method=post action='<c:url value="/NuevoQuirofano.do"/>'>
                <div class="nota">
                    <a class="btn btn-info" href="javascript:document.formas.submit();" >Programar Quirofanos</a>
                    <input type=hidden name=accion value='Programar'>
                </div>
            </form>
        </td>
    <tr>
</table>

<table class="table table-striped table-bordered table-hover table-condensed table-responsive">
    <tr style="font-size:9pt">
        <th> NRO </th>
        <th> COD </th>
        <th> QUIROFANO </th> 
        <th> ESTADO </th>
        <th> MODIFICAR </th>
        <th> ELIMINAR </th> 
    </tr>  
    <c:forEach var="lista" items="${listarQuirofanos}" varStatus="contador">
        <tr style="font-size:9pt">
            <td align="center"><c:out value="${contador.count}"/></td>
            <td><c:out value="${lista.cod_esta}"/></td> 
            <td><c:out value="${lista.quirofano}"/></td>
            <td align="center"><c:out value="${lista.id_estado}"/></td>  
        <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/NuevoQuirofano.do"/>'>
            <td>     
                <div><a class="btn btn-warning btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Modificar</a></div>
                <input type="hidden" name="id_quirofano" value='<c:out value="${lista.id_quirofano}"/>' >
                <input type="hidden" name="accion" value='Modificar' >
                <input type="hidden" name="sw" value='1' >
            </td>
        </form>
        <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/ConfirmarQuirofano.do"/>'>
            <td>     
                <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();"> Eliminar</a></div>
                <input type="hidden" name="id_quirofano" value='<c:out value="${lista.id_quirofano}"/>' >
                <input type="hidden" name="accion" value='Eliminar' >
                <input type="hidden" name="sw1" value='1' >
            </td>
        </form>
    </tr> 
</c:forEach>
</table>