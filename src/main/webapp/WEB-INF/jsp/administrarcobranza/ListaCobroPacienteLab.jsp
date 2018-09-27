<%@ include file="../Superior.jsp" %>


<div style="font-size:15pt"> <center>Cobros del Paciente con Laboratorios e Imagenologia</center></div>
<form name=formaRegO method=post action='<c:url value="/CobrarLab.do"/>'>
    <div><a class="btn btn-primary" ref="javascript:document.formaRegO.submit();">Terminar</a></div>
    <input type="hidden" name="id_paciente"     value='<c:out value="${id_paciente}"/>'>
    <input type="hidden" name="id_pedido"       value='<c:out value="${id_pedido}"/>'>
    <input type="hidden" name="id_pedidolab"    value='<c:out value="${id_pedidolab}"/>'>
    <input type="hidden" name='id_reservacion'  value='<c:out value="${id_reservacion}"/>'> 
    <input type="hidden" name='id_persona'      value='<c:out value="${id_persona}"/>'>
    <input type="hidden" name='nombres'         value='<c:out value="${nombres}"/>'>
    <input type="hidden" name='accion'          value='Retornar'>
</form>

<table class="table table-striped table-bordered table-condensed table-responsive">
    <tr>
        <td width="50%" valign="top">
            <table class="formulario" width="100%"> 
                <tr>    
                    <td bgcolor="#F2F2F2" align="right">Nombres Completo :</td>    
                    <td><c:out value = "${nombres}"/></td>
                </tr> 
                <tr>    
                    <td bgcolor="#F2F2F2" align="right">Monto a Cancelar :</td>    	      
                    <td style="font-size:12pt"><c:out value = "${datos.precio_total}"/></td>
                </tr>
            </table>

            <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
                <tr style="font-size:9pt">
                    <th bgcolor="#F2F2F2"> No </th>
                    <th bgcolor="#F2F2F2"> Detalle </th>
                    <th bgcolor="#F2F2F2"> Indicacion </th>
                    <th bgcolor="#F2F2F2"> Precio </th>
                    <th bgcolor="#F2F2F2"> Eliminar </th>
                </tr> 
                <c:forEach var="estado" items="${listarcobros}" varStatus="contador">
                    <tr style="font-size:9pt">
                        <td><c:out value="${contador.count}"/></td>
                        <td><c:out value="${estado.costo}"/></td>  
                        <td><c:out value="${estado.indicacion}"/></td>  
                        <td><c:out value="${estado.entrada}"/></td>   
                    <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/CobrarLab.do"/>'>
                        <td>  
                            <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();">Eliminar</a></div>
                            <input type="hidden" name="id_cuaderno"     value='<c:out value="${lista.id_cuaderno}"/>'>
                            <input type="hidden" name="id_paciente"     value='<c:out value="${id_paciente}"/>'>
                            <input type="hidden" name="id_pedido"       value='<c:out value="${id_pedido}"/>'>
                            <input type="hidden" name="id_costo"        value='<c:out value="${estado.id_costo}"/>'>
                            <input type="hidden" name="id_rubro"        value='<c:out value="${estado.id_rubro}"/>'>
                            <input type="hidden" name='id_reservacion'  value='<c:out value="${id_reservacion}"/>'> 
                            <input type="hidden" name="id_detalle"      value='<c:out value="${estado.id_detalle}"/>'>
                            <input type="hidden" name='id_persona'      value='<c:out value="${id_persona}"/>'>
                            <input type="hidden" name='id_costo'        value='<c:out value="${lista.primera}"/>'>  
                            <input type="hidden" name='cantidad'        value='<c:out value="${lista.cm}"/>'> 
                            <input type="hidden" name='id_rubro'        value='<c:out value="${lista.suma1}"/>'> 
                            <input type="hidden" name='expedido'        value='<c:out value="${expedido}"/>'>
                            <input type="hidden" name='nombres'         value='<c:out value="${nombres}"/>'>
                            <input type="hidden" name="accion"          value='eliminar' >
                        </td>
                    </form>
                </c:forEach>
            </table>       
        </td>
        <td width="50%" valign="top">

            <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
                <tr style="font-size:9pt">
                    <th bgcolor="#F2F2F2"> NRO </th>
                    <th bgcolor="#F2F2F2"> ID </th>
                    <th bgcolor="#F2F2F2"> IDlab </th>
                    <th bgcolor="#F2F2F2"> Fecha<br>Pedido </th>
                    <th bgcolor="#F2F2F2"> LABORATORIO </th>
                    <th bgcolor="#F2F2F2"> Indicacion </th>
                    <th bgcolor="#F2F2F2"> Costo </th>
                    <th bgcolor="#F2F2F2"> Cobrar </th> 
                </tr>  
                <c:forEach var="lista" items="${listalabcob}" varStatus="contador">
                    <tr style="font-size:9pt">
                        <td align="center"><c:out value="${contador.count}"/></td>
                        <td align="center" style="font-size:8px"><c:out value="${lista.id_cuaderno}"/></td>
                        <td align="center" style="font-size:8px"><c:out value="${lista.id_costo}"/></td>
                        <td><fmt:formatDate value="${lista.fechap}" pattern='dd/MM/yy HH:mm'/></td>
                        <td align="center"><c:out value="${lista.laboratorio}"/></td>
                        <td align="center"><c:out value="${lista.tipoconsulta}"/></td>

                    <form name=formaEc<c:out value="${contador.count}"/> method=post action='<c:url value="/CobrarLab.do"/>'>
                        <td><input type="text" name="cantidad" value="<fmt:formatNumber value="${lista.cm}" groupingUsed="false" maxFractionDigits="0"/>" size=5 maxlength=5 onblur='validar(cantidad, "9")'/></td>
                        <td><div ><a class="btn btn-info btn-xs" href="javascript:document.formaEc<c:out value="${contador.count}"/>.submit();"> Cobrar</a></div>
                            <input type="hidden" name="id_cuaderno"     value='<c:out value="${lista.id_cuaderno}"/>'>
                            <input type="hidden" name="id_paciente"     value='<c:out value="${id_paciente}"/>'>
                            <input type="hidden" name="id_pedidol"      value='<c:out value="${lista.id_pedido}"/>'>
                            <input type="hidden" name="id_pedido"       value='<c:out value="${id_pedido}"/>'>
                            <input type="hidden" name="id_costo"        value='<c:out value="${lista.id_costo}"/>'>
                            <input type="hidden" name='id_reservacion'  value='<c:out value="${id_reservacion}"/>'> 
                            <input type="hidden" name='id_persona'      value='<c:out value="${id_persona}"/>'>
                            <input type="hidden" name='id_costo'        value='<c:out value="${lista.primera}"/>'>  
                            <input type="hidden" name='id_rubro'        value='<c:out value="${lista.suma1}"/>'> 
                            <input type="hidden" name='expedido'        value='<c:out value="${expedido}"/>'>
                            <input type="hidden" name='nombres'         value='<c:out value="${nombres}"/>'>
                            <input type="hidden" name="accion"          value ='Cobrar'>
                            <input type="hidden" name="sw1"             value='1'>
                        </td> 
                    </form>
                </c:forEach>
    </tr> 
</table>
</td>
</tr>
</table>