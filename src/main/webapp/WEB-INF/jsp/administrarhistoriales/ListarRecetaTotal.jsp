<%@ include file="../Superior.jsp" %>


<table class="table table-striped table-bordered table-hover table-condensed table-responsive">
    <tr>
        <th colspan="3"><center>RECETAS TOTAL DEL PACIENTE</center> </th>
</tr>
<tr>
    <td width="50%" valign="top">
        <table class="table table-striped table-bordered table-condensed table-responsive">
            <tr>
                <td>HCL</td>
                <td><c:out value = "${datos.hcl}"/></td>
            </tr>
            <tr>    
                <td>Nombres</td>    
                <td><c:out value = "${datos.nombres}"/></td>
            </tr>

            <tr>    
                <td>Direcci&oacute;n</td>    	      
                <td><c:out value = "${datos.direccion}"/></td>
            </tr>  
            <tr>    
                <td>Edad</td>          
                <td><c:out value = "${datos.edad}"/> años <c:out value = "${datos.mes}"/> meses <c:out value = "${datos.dia}"/> dias</td>
            </tr>
        </table>
        <table class="tabla">

        </table>
    </td>

</tr>
<tr>  
    <td>

        <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
            <tr style="font-size:9pt">
                <th bgcolor="#F2F2F2"> Nro </th>
                <th bgcolor="#F2F2F2"> Fecha </th>
                <th bgcolor="#F2F2F2"> Prescriptor </th>
                <th bgcolor="#F2F2F2"> Medicamento </th>
                <th bgcolor="#F2F2F2"> Forma Far </th>
                <th bgcolor="#F2F2F2"> Concentra </th>
                <th bgcolor="#F2F2F2"> Indicacion </th>
                <th bgcolor="#F2F2F2"> Dosifica<br>Dias </th>
                <th bgcolor="#F2F2F2"> Tipo </th>
                <th bgcolor="#F2F2F2"> Cant.<br>Recetada </th>
                <th bgcolor="#F2F2F2"> Cant.<br>Entreg. </th>
                <th bgcolor="#F2F2F2"> Costo </th>
                <th bgcolor="#F2F2F2"> Total<br>Bs. </th>
                <th bgcolor="#F2F2F2"> Estado </th>
            </tr>  
            <c:forEach var="listadox" items="${listarRecetaTotal}" varStatus="contadora">
                <tr style="font-size:9pt">
                    <td align="center"><c:out value="${contadora.count}"/></td>
                    <td><fmt:formatDate value="${listadox.fecha_fin}" pattern='dd/MM/yy HH:mm'/></td>              
                    <td><c:out value="${listadox.medico}"/><br><c:out value="${listadox.cadena1}"/></td> 
                    <td><c:out value="${listadox.medicamento}"/></td> 
                    <td><c:out value="${listadox.forma_far}"/></td>
                    <td><c:out value="${listadox.concentra}"/></td>
                    <td><c:out value="${listadox.indicacion}"/></td> 
                    <td align="center"><c:out value="${listadox.dosifica}"/></td>
                    <td align="center"><c:out value="${listadox.expedido}"/></td>   
                    <td align="center"><c:out value="${listadox.salida}"/></td>
                    <c:forEach var="listak" items="${listarKardex}" varStatus="contadora">
                        <c:if test="${listadox.id_detalle==listak.id_receta and listak.salida>'0'}">
                            <c:if test="${listadox.salida>listak.salida}">
                                <td style="color:red"  align="center"><b><c:out value="${listak.salida}"/></b></td>
                                    </c:if>
                                    <c:if test="${listadox.salida==listak.salida}">
                                <td style="color:blue"  align="center"><c:out value="${listak.salida}"/></td>
                            </c:if>
                            <td><c:out value="${listak.precio_venta}"/></td>   
                            <td><c:out value="${listak.precio_total}"/></td> 
                            <td>Dispensado</td>
                        </c:if>
                        <c:if test="${listadox.id_detalle==listak.id_receta and listak.salida<='0'}">
                            <td style="color:red" colspan="4" align="right">No Dispensado</td>
                        </c:if>
                    </c:forEach>     

                    <c:if test="${listadox.id_estado=='A'}">
                        <td style="color:red" colspan="4" align="right"><c:out value="No entregado"/></td> 
                    </c:if>
                </tr>  
            </c:forEach>

        </table>
    </td>
</tr>
</table>