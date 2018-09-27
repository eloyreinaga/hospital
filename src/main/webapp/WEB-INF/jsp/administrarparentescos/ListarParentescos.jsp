<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>

<div style="font-size:15pt"> Administraci&oacute;n de Parentescos</div>
<br>
<table>
    <tr>
    <form name=forma method=post action='<c:url value="/NuevoParentesco.do"/>'>
        <td colspan="2">
            <div class="agregar">
                <a class="btn btn-primary btn-lg" href="javascript:document.forma.submit();" >Nuevo</a>
                <input type=hidden name=accion value='Adicionar'>
            </div></td>
    </form>
    <tr>
</table>

<table class="table table-striped table-bordered table-hover table-condensed table-responsive">
    <tr>
        <th bgcolor="#F2F2F2"> NRO </th>
        <th bgcolor="#F2F2F2"> SEXO </th>
        <th bgcolor="#F2F2F2"> ESTADO </th>            
        <th bgcolor="#F2F2F2"> MODIFICAR </th>
        <th bgcolor="#F2F2F2"> ELIMINAR </th> 
    </tr>  
    <c:forEach var="lista" items="${listarParentescos}" varStatus="contador">
        <tr style="font-size:9pt">
            <td align="center"><c:out value="${contador.count}"/></td>
            <td><c:out value="${lista.parentesco}"/></td>      
            <td><c:out value="${lista.id_estado}"/></td>   
        <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/NuevoParentesco.do"/>'>
            <td>     
                <div><a class="btn btn-warning btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Modificar</a></div>
                <input type="hidden" name="id_parentesco" value=<c:out value="${lista.id_parentesco}"/> >
                <input type="hidden" name="accion" value='Modificar' >
                <input type="hidden" name="sw" value='1' >
            </td>
        </form>
        <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/ConfirmarParentesco.do"/>'>
            <td>     
                <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();"> Eliminar</a></div>
                <input type="hidden" name="id_parentesco" value=<c:out value="${lista.id_parentesco}"/> >
                <input type="hidden" name="accion" value='Eliminar' >
                <input type="hidden" name="sw1" value='1' >
            </td>
        </form>
      </tr>    
    </c:forEach>
</table>