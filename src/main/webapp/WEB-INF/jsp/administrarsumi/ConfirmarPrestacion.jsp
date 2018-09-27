<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>

<script language = 'JavaScript' SRC="./validar.js"></script>

<c:if test="${accion == 'Modificar'}">
    <div style="font-size:15pt"> Modificando Prestacion</div>
</c:if>
<c:if test="${accion == 'Adicionar'}">
    <div style="font-size:15pt"> Agregando Prestacion</div>
</c:if>
<c:if test="${accion == 'Eliminar'}">
    <div style="font-size:15pt"> Eliminando Prestacion</div>
</c:if>

<div><a class="btn btn-success" href='ListarPrestacionSumi.do'>Volver</a></div>
<br>
<center>
    <form name="adicionarusu" method="POST" action='<c:url value="/GrabarPrestacion.do"/>' >
        <table class="table table-striped table-bordered table-condensed table-responsive">
            <tr align="right" bgcolor="#F2F2F2">
                <th colspan="3"><center>CONFIRME LOS DATOS</center></th>
            </tr>
            <c:if test="${sw != 1}">       
                <tr>
                    <td  align="right" bgcolor="#F2F2F2"> C&oacute;digo Prestacion </td>
                    <td><c:out value="${dato.id_prestacion}"/></td>
                </tr>  
            </c:if>  
            <tr >
                <td align="right" bgcolor="#F2F2F2"> Prestacion </td>
                <td><c:out value="${dato.descripcion}"/></td>
            </tr>        
            <tr>
                <td align="right" bgcolor="#F2F2F2"> Costo </td>
                <td><c:out value="${dato.costo}"/></td>
            </tr>        
            <tr>
                <td align="right" bgcolor="#F2F2F2"> Paquete </td>
                <td><c:out value="${dato.paquete}"/></td>
            </tr> 
            <tr>
                <td align="right" bgcolor="#F2F2F2"> Internado </td>
                <td><c:out value="${dato.suma1}"/></td>
            </tr>
            <tr>
                <td align="right" bgcolor="#F2F2F2"> veces </td>
                <td><c:out value="${dato.suma2}"/></td>
            </tr>
        </table>
        <center>
            <input type="submit" class="btn btn-primary" name='accion1' value='Aceptar'>
        </center>  
        <input type="hidden" name='id_prestacion'  value='<c:out value="${dato.id_prestacion}"/>'>
        <input type="hidden" name='descripcion'    value='<c:out value="${dato.descripcion}"/>'>
        <input type="hidden" name='internado'      value='<c:out value="${dato.suma1}"/>'>
        <input type="hidden" name='veces'          value='<c:out value="${dato.suma2}"/>'>
        <input type="hidden" name='costo'          value='<c:out value="${dato.costo}"/>'>
        <input type="hidden" name='paquete'        value='<c:out value="${dato.paquete}"/>'>  
        <input type="hidden" name='accion'         value='<c:out value="${accion}"/>'>
    </form>
</center>


