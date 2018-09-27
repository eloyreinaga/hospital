<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>

<form name=formax3 method=post action='<c:url value="/ListarPrestacionCodigo.do"/>'>
    <th colspan="2">
        <div style="font-size:15pt">
            <a href="javascript:document.formax3.submit();" ><font size="4">Administracion de Prestaciones SUMI segun Codigo</font></a>
            <input type="hidden" name=accion value='Pormedica'>
        </div></th>
</form>  

<br>
<center>
    <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
        <tr>
            <TD>
        <center>
            <form name=formaNom action="<c:url value="/ListarPrestacionCodigo.do"/>" method="POST">        
                <table >
                    <tr>    
                        <td align=right>Codigo Prestacion</td>    
                        <td><input class="form-control" type="text" name="nombresPres"  value="<c:out value="${nombresPres}"/>"  maxlength=20 onblur='validar = (nombresPres, "A ")'/></td>            
                        <td coslpan=3><input class="btn btn-success" type="submit" name=boton value="BuscarNom"></td>
                    </tr>  
                </table>
                <input type="hidden" name='expedido'        value='<c:out value="${expedido}"/>'>  
            </form> 
        </center>
</center>
</TD>
</tr>
<tr>
    <th colspan="3">PRESTACIONES</th>
</tr>
<tr>
    <td  valign="top">

        <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
            <tr style="font-size:9pt">
                <th> NRO </th>
                <th> PRESTACION </th>            
                <th> DESCRIPCION </th>            
                <th> COSTO </th>      
                <th> NIVEL </th>
                <th> Tipo </th>
                <th> MODIFICAR </th>
            </tr>  
            <c:forEach var="lista" items="${listarPrestaciones}" varStatus="contador">
                <tr style="font-size:9pt">
                    <td align="center"><c:out value="${contador.count}"/></td>
                    <td><c:out value="${lista.prestacion}"/></td>             
                    <td><c:out value="${fn:substring(lista.descripcion,0,90)}"/></td>   
                    <td><c:out value="${lista.costo}"/></td>   
                    <td><c:out value="${lista.cadena1}"/></td>
                    <c:if test="${lista.suma1==0}">     
                        <td>Amb</td> 
                    </c:if> 
                    <c:if test="${lista.suma1==1}">     
                        <td>Int</td> 
                    </c:if>  
                <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/ModificarPaquete.do"/>'>
                    <td>     
                        <div><a class="btn btn-warning btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Modificar</a></div>
                        <input type="hidden" name="id_prestacion" value=<c:out value="${lista.id_prestacion}"/> >
                        <input type="hidden" name="accion" value='Modificar' >
                        <input type="hidden" name="sw" value='1' >
                    </td>
                </form> 
    </tr>
</c:forEach>

</table>

</td>
</tr>
</center>
</table>
</center>
