<%@ include file="../Superior.jsp" %>


<table class="table table-striped table-bordered table-condensed table-responsive">
    <tr>
        <th colspan="3" align="center" bgcolor="#F2F2F2"><center>DATOS PERSONALES</center></th>
</tr>
<tr>
    <td width="100%" valign="top">
        <table class="table table-striped table-bordered table-condensed table-responsive">
            <tr>    
                <td  bgcolor="#F2F2F2" align="right">HCL / Nombres</td>    
                <td><c:out value = "${datos.hcl}"/>_/_<c:out value = "${datos.nombres}"/></td>
            </tr>
            <tr>    
                <td  bgcolor="#F2F2F2" align="right">Direcci&oacute;n</td>          
                <td><c:out value = "${datos.direccion}"/></td>
            </tr>  
            <tr>    
                <td  bgcolor="#F2F2F2" align="right">Edad</td>    	      
                <td style="color:blue"><b><c:out value = "${datos.edad}"/> años <c:out value = "${datos.mes}"/> meses <c:out value = "${datos.dia}"/> dias</b></td>
            </tr>   
        </table>
    </td>
</tr>

</table>
<!--
<form name=formaM method=post action='<c:url value="/AtenderPaciente.do"/>'>
         <div><a class="btn btn-warning btn-xs" href="javascript:document.formaM.submit();">Retornar</a></div>
          <input type="hidden" name='id_reservacion'  value='<c:out value="${id_reservacion}"/>'>  
          <input type="hidden" name='id_persona'      value='<c:out value="${id_persona}"/>'>
          <input type="hidden" name='id_consultorio'  value='<c:out value="${id_consultorio}"/>'>
          <input type="hidden" name='id_paciente'     value='<c:out value="${id_paciente}"/>'>
          <input type="hidden" name="expedido"        value='<c:out value="${expedido}"/>' >                   
          <input type="hidden" name='accion'          value='Ninguno'>
  </form>
-->
<table class="table table-striped table-bordered table-condensed table-responsive">
    <tr>
        <th colspan="6" bgcolor="#F2F2F2"><center>HISTORIAL IMAGENOLOGIA PACIENTE</center></th>
</tr>     
<tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> Nro </th>
    <th bgcolor="#F2F2F2"> Fecha Pedido </th>
    <th bgcolor="#F2F2F2"> Laboratorio </th>    
    <th bgcolor="#F2F2F2"> Fecha Realizacion </th>
    <th bgcolor="#F2F2F2"> Ver </th>
    <th bgcolor="#F2F2F2" Imprimir </th>
</tr>  
<c:forEach var="listadoe" items="${listalabEndo}" varStatus="contador">
    <tr style="font-size:9pt">
        <td><c:out value="${contador.count}"/></td> 
        <td><fmt:formatDate value="${listadoe.fechap}" pattern='dd/MM/yyyy HH:mm'/></td> 
        <td><c:out value="${listadoe.laboratorio}"/></td>
        <td><fmt:formatDate value="${listadoe.fechae}" pattern='dd/MM/yyyy HH:mm'/></td> 
    <form name=formaEn<c:out value="${contador.count}"/> method=post action='<c:url value="/VerLaboratorio.do"/>'>
        <td><div><a class="btn btn-info btn-xs" href="javascript:document.formaEn<c:out value="${contador.count}"/>.submit();">Pedido</a></div>
            <input type="hidden" name='id_paciente'  value='<c:out value="${id_paciente}"/>'> 
            <input type="hidden" name='id_pedido'    value='<c:out value="${listadoe.id_pedido}"/>'> 
            <input type="hidden" name='id_persona'   value='<c:out value="${id_persona}"/>'> 
            <input type="hidden" name='id_historial' value='<c:out value="${listadoe.id_historial}"/>'>     
            <input type="hidden" name='id_cuaderno'  value='<c:out value="${listadoe.id_cuaderno}"/>'> 
            <input type="hidden" name='expedido'     value='<c:out value="${expedido}"/>' > 
            <input type="hidden" name='accion'       value='VerEndo'>
            <input type="hidden" name='sw'           value='1'>
        </td>
    </form>  
    <form name=formaEnimp<c:out value="${contador.count}"/> method=post action='<c:url value="/VerLaboratorio.do"/>'>
        <td><div><a class="btn btn-success btn-xs" href="javascript:document.formaEnimp<c:out value="${contador.count}"/>.submit();">Impresion</a></div>
            <input type="hidden" name='id_paciente'  value='<c:out value="${id_paciente}"/>'> 
            <input type="hidden" name='id_pedido'    value='<c:out value="${listadoe.id_pedido}"/>'> 
            <input type="hidden" name='id_persona'   value='<c:out value="${listadoe.id_persona}"/>'> 
            <input type="hidden" name='id_historial' value='<c:out value="${listadoe.id_historial}"/>'>   
            <input type="hidden" name='id_cuaderno'  value='<c:out value="${listadoe.id_cuaderno}"/>'>   
            <input type="hidden" name='cod_esta'     value='<c:out value="${listadoe.cod_esta}"/>' > 
            <input type="hidden" name='expedido'     value='<c:out value="${expedido}"/>' > 
            <input type="hidden" name='accion'       value='imprimirEco'>
            <input type="hidden" name='accionl'      value='Ecos'>
            <!--<input type="hidden" name='accion'       value='ImpEndow'> -->
            <input type="hidden" name='sw'           value='1'>
        </td>
    </form>
</tr> 
</c:forEach>
</table>

<table class="table table-striped table-bordered table-condensed table-responsive">
    <tr>
        <th colspan="7" bgcolor="#F2F2F2"><center>HISTORIAL LABORATORIOS PACIENTE</center></th>
</tr>     
<tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> Nro </th>
    <th bgcolor="#F2F2F2"> Fecha Pedido </th>
    <th bgcolor="#F2F2F2"> Tipo </th>    
    <th bgcolor="#F2F2F2"> Medico  </th> 
    <th bgcolor="#F2F2F2"> Fecha Realizacion </th>
    <th bgcolor="#F2F2F2"> Imprimir </th>
    <th bgcolor="#F2F2F2"> Imprimir </th>
</tr>  
<c:forEach var="listado" items="${listalab}" varStatus="contador">
    <tr style="font-size:9pt">
        <td><c:out value="${contador.count}"/></td> 
        <td><fmt:formatDate value="${listado.fecha}" pattern='dd/MM/yyyy HH:mm'/></td> 
        <td><c:out value="${listado.expedido}"/></td>
        <td><c:out value="${listado.nombre}"/></td>     
        <td><fmt:formatDate value="${listado.fechae}" pattern='dd/MM/yyyy HH:mm'/></td> 
    <form name=formaImp<c:out value="${contador.count}"/> method=post action='<c:url value="/PedirLaboratorio.do"/>'>
        <td><div><a class="btn btn-info btn-xs" href="javascript:document.formaImp<c:out value="${contador.count}"/>.submit();">Pedido</a></div>
            <input type="hidden" name="id_paciente"    value=<c:out value="${listado.id_paciente}"/> >   
            <input type="hidden" name='id_pedido'      value='<c:out value="${listado.id_pedido}"/>'> 
            <input type="hidden" name='id_persona'     value='<c:out value="${listado.id_persona}"/>'>
            <input type="hidden" name="id_historial"   value=<c:out value="${listado.id_historial}"/> >         
            <input type="hidden" name="expedido"       value=<c:out value="${listado.expedido}"/> > 
            <input type="hidden" name="accion"         value='adicion' >
            <input type="hidden" name="sw"             value='1' >
        </td>
    </form>   
    <form name=formaImg<c:out value="${contador.count}"/> method=post action='<c:url value="/VerLaboratorio.do"/>'>
        <td><div><a class="btn btn-warning btn-xs" href="javascript:document.formaImg<c:out value="${contador.count}"/>.submit();">Resultado</a></div>
            <input type="hidden" name='id_paciente'  value='<c:out value="${id_paciente}"/>'> 
            <input type="hidden" name='id_pedido'    value='<c:out value="${listado.id_pedido}"/>'> 
            <input type="hidden" name='id_persona'   value='<c:out value="${id_persona}"/>'> 
            <input type="hidden" name='id_historial' value='<c:out value="${listado.id_historial}"/>'> 
            <input type="hidden" name='cod_esta'     value='<c:out value="${listado.cod_esta}"/>' >
            <input type="hidden" name='expedido'     value='<c:out value="${expedido}"/>' > 
            <input type="hidden" name='accionpred'   value='pred1'>
            <input type="hidden" name='accionl'      value='general'>
            <input type="hidden" name='sw'           value='1'>
        </td>
    </form>
</tr> 
</c:forEach>
</table>

<table class="table table-striped table-bordered table-condensed table-responsive">    
    <tr style="font-size:9pt">
        <th bgcolor="#F2F2F2"> NRO </th>
        <th bgcolor="#F2F2F2"> FECHA </th>
        <th bgcolor="#F2F2F2"> LABORATORIO </th>
        <th bgcolor="#F2F2F2"> INDICACIONES </th>    
        <th bgcolor="#F2F2F2"> RESULTADO </th>        
    </tr>  
    <c:forEach var="lista" items="${milista}" varStatus="contador">
        <tr style="font-size:9pt">
            <td align="center"><c:out value="${contador.count}"/></td>
            <td><fmt:formatDate value="${lista.fechap}" pattern='dd/MM/yyyy HH:mm'/></td>
            <td><c:out value="${lista.laboratorio}"/></td>    
            <td><c:out value="${lista.tipoconsulta}"/></td>         
            <td> <c:out value = "${lista.resultado}" escapeXml="False"/></td>     
        </tr> 
    </c:forEach>
</table>
