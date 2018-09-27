<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>

<div style="font-size:15pt"> Administraci&oacute;n de Prestaciones SUMI segun Medicamentos</div>
<center>
    <table class="table table-striped table-border table-condensed table-responsive">
        <tr>
            <TD>
        <center>
            <form name=formaNom action="<c:url value="/ListarPrestacionCodigo.do"/>" method="POST">        
                <table class="table table-striped table-bordered table-condensed table-responsive">
                    <tr>    
                        <td class="colh" align=right>Por Medicamento</td>    
                        <td class="colh">::</td>	
                        <td class="colb"><input input class="form-control" type="text" name="nombremed"  value="<c:out value="${nombremed}"/>"  maxlength=20 onblur='validar = (nombresPres, "A ")'/></td>            
                        <td coslpan=3><input class="btn btn-primary" type="submit" name=boton value="BuscarMed"></td>
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

                <th> MODIFICAR </th>
            </tr>  
            <c:forEach var="lista" items="${listarPrestaciones}" varStatus="contador">
                <tr style="font-size:9pt">
                    <td align="center"><c:out value="${contador.count}"/></td>
                    <td><c:out value="${lista.prestacion}"/></td>             
                    <td><c:out value="${lista.descripcion}"/></td>   
                    <td><c:out value="${lista.costo}"/></td>   

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
