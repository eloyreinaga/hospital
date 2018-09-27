<%@ include file="../Superior.jsp" %>
<script language='JavaScript' SRC="./validar.js"></script>

<form name=formaRet method=post action='<c:url value="/ListaNRecetaP.do"/>'>
         <div><a class="btn btn-primary" href="javascript:document.formaRet.submit();">Finalizar</a></div>
          <input type="hidden" name='id_persona'      value='<c:out value="${id_persona}"/>'>
          <input type="hidden" name='id_consultorio'  value='<c:out value="${id_consultorio}"/>'>
          <input type="hidden" name='id_pedido'       value='<c:out value="${id_pedido}"/>'>
          <input type="hidden" name="sw"              value='<c:out value="${sw}"/>' >  
          <input type="hidden" name="valor_1"         value='<c:out value="${valor_1}"/>' >
          <input type="hidden" name="accion"          value='terminar' >
</form>
<table class="formulario">
  <tr>
    <th colspan="3">RECETA DEL PACIENTE SOLO (<font color="blue"><c:out value = "${programa}"/></font>)</th>
  </tr>
  <tr>
    <td width="50%" valign="top">
      <table class="table table-striped table-bordered table-condensed table-responsive"> 
  
      <tr>    
        <td bgcolor="#F2F2F2" align="right">Nombres</td>    
        <td><c:out value = "${datos.nombres}"/></td>
      </tr>
 
      <tr>    
        <td bgcolor="#F2F2F2" align="right">No Documento</td>    	      
        <td><c:out value = "${datos.nit}"/></td>
      </tr>  
      <tr>    
        <td bgcolor="#F2F2F2" align="right">Monto a Cancelar</td>    	      
        <td><fmt:formatNumber value="${datos.precio_total}" maxFractionDigits="2"/></td>
      </tr>  
   </table>
   
  <table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> Medicamento </th>
    <th bgcolor="#F2F2F2"> Forma</th>
    <th bgcolor="#F2F2F2"> Concentra </th>
    <th bgcolor="#F2F2F2"> Cantidad </th>
    <th bgcolor="#F2F2F2"> Costo </th>
    <th bgcolor="#F2F2F2"> Total </th>
    <th bgcolor="#F2F2F2"> Eliminar </th>
    </tr>  
   <c:forEach var="listado" items="${listarKardex}" varStatus="contador">
     <tr style="font-size:9pt">
       <td><c:out value="${listado.medicamento}"/></td>
      <td><c:out value="${listado.forma_far}"/></td>             
       <td><c:out value="${listado.concentra}"/></td>
       <td style="font-size: 10pt"><b><c:out value="${listado.salida}"/></b></td>             
       <td><c:out value="${listado.precio_venta}"/></td>                    
       <td><c:out value="${listado.precio_total}"/></td>                           
     <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/ListaNRecetaP.do"/>'>
       <td>     
         <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();">Eliminar</a></div>
  	 <input type="hidden" name="id_medicamento"  value='<c:out value="${listado.id_medicamento}"/>' >
         <input type="hidden" name="salida"          value='<c:out value="${listado.salida}"/>' >
         <input type="hidden" name="id_kardex"       value='<c:out value="${listado.id_factura}"/>' >
         <input type="hidden" name="expedido"        value='<c:out value="${listado.expedido}"/>' >                  
         <input type="hidden" name='id_persona'      value='<c:out value="${id_persona}"/>'>
         <input type="hidden" name='id_programa'     value='<c:out value="${id_programa}"/>'>
         <input type="hidden" name='id_consultorio'  value='<c:out value="${id_consultorio}"/>'>
         <input type="hidden" name='id_pedido'       value='<c:out value="${id_pedido}"/>'>
         <input type="hidden" name="valor_1"         value='<c:out value="${valor_1}"/>' >
	 <input type="hidden" name="accion" value='eliminar' >
	 <input type="hidden" name="sw" value='<c:out value="${sw}"/>' >
       </td>
     </form>
   </c:forEach>

</table>      
    </td>
    <td width="50%" valign="top">
     <form name=forma action="<c:url value="/ListaNRecetaP.do"/>" method="POST">        
         <table class="table table-striped table-bordered table-condensed table-responsive"> 
          <tr>    
            <td align=right>Nombre Medicamento</td>    	
            <td><input type="text" name="nombremed"  value="<c:out value="${nombremed}"/>"  maxlength=20 onblur='validar=(nombremed,"A ")'/></td>            
            <td coslpan=3><input class="btn btn-primary" type="submit" value="Buscar"></td>
          </tr>  
         </table>
      <input type="hidden" name="sw" value='<c:out value="${sw}"/>' >         
      <input type="hidden" name='id_pedido'      value='<c:out value="${id_pedido}"/>'>
      <input type="hidden" name='id_programa'     value='<c:out value="${id_programa}"/>'>
      <input type="hidden" name="valor_1"         value='<c:out value="${valor_1}"/>' >
     </form>
    
<table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> Medicamento </th>
    <th bgcolor="#F2F2F2"> Forma</th>
    <th bgcolor="#F2F2F2"> Concentra </th>    
    <th bgcolor="#F2F2F2"> Stock </th>     
    <th bgcolor="#F2F2F2"> Cantidad</th>
    <th bgcolor="#F2F2F2"> Entregar </th>
    </tr>  
   <c:forEach var="listado" items="${listarMedicamentos}" varStatus="contador">
     <tr style="font-size:9pt">
       <td><c:out value="${listado.medicamento}"/></td>      
       <td><c:out value="${listado.forma_far}"/></td>             
       <td><c:out value="${listado.concentra}"/></td>      
       <td style="font-size: 10pt"><b><c:out value="${listado.stockp}"/></b></td>   
                   
     <form name=formaEmp<c:out value="${contador.count}"/> method=post action='<c:url value="/ListaNRecetaP.do"/>'>
      <c:if test="${listado.stockp > 0}">
          <td align="center" bgcolor="blue" s><input type="text" name="cantidad" value="1" size=3 maxlength=6 onblur='validarNota(cantidad,1,<c:out value="${listado.stock}"/>)'/></td>                 
      </c:if>     
      <c:if test="${listado.stockp <= 0}">
          <td>No Existe</td>                 
      </c:if>     

       <td>     
         <div><a class="btn btn-success btn-xs" href="javascript:document.formaEmp<c:out value="${contador.count}"/>.submit();">Entregar</a></div>
  	 <input type="hidden" name="id_medicamento"  value='<c:out value="${listado.id_medicamento}"/>' >
         <input type="hidden" name='id_persona'      value='<c:out value="${id_persona}"/>'>
         <input type="hidden" name='id_consultorio'  value='<c:out value="${id_consultorio}"/>'>
         <input type="hidden" name='id_pedido'       value='<c:out value="${id_pedido}"/>'>
         <input type="hidden" name='id_programa'     value='<c:out value="${id_programa}"/>'>
         <input type="hidden" name="valor_1"         value='<c:out value="${valor_1}"/>' >
	 <input type="hidden" name="accion"          value='adicion' >
         <input type="hidden" name="sw"              value='<c:out value="${sw}"/>' >
       </td>
     </form>
    </tr>   
   </c:forEach>
</table> 

  <table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> Medicamento </th>
    <th bgcolor="#F2F2F2"> Forma</th>
    <th bgcolor="#F2F2F2"> Concentra</th>    
    <th bgcolor="#F2F2F2"> Stock </th>   
    <th bgcolor="#F2F2F2"> Cantidad</th>
    <th bgcolor="#F2F2F2"> Entregar </th>
    </tr>  
   <c:forEach var="listado" items="${listarMedicamentosCot}" varStatus="contador">
     <tr style="font-size:9pt">
       <td><c:out value="${listado.medicamento}"/></td>      
       <td><c:out value="${listado.forma_far}"/></td>             
       <td><c:out value="${listado.concentra}"/></td>      
       <td style="font-size: 10pt"><b><c:out value="${listado.stockp}"/></b></td>                 
    <form name=formaEnp<c:out value="${contador.count}"/> method=post action='<c:url value="/ListaNRecetaP.do"/>'>
        <c:if test="${listado.stockp > 0}">
              <td align="center" bgcolor="blue" s><input type="text" name="cantidad" value="1" size=3 maxlength=6 onblur='validarNota(cantidad,1,<c:out value="${listado.stock}"/>)'/></td>                 
          </c:if>     
          <c:if test="${listado.stockp <= 0}">
              <td>No Existe</td>                 
          </c:if> 
       <td>     
         <div><a class="btn btn-success btn-xs" href="javascript:document.formaEnp<c:out value="${contador.count}"/>.submit();">Entregar</a></div>
  	 <input type="hidden" name="id_medicamento" value='<c:out value="${listado.id_medicamento}"/>' >
         <input type="hidden" name='id_persona'      value='<c:out value="${id_persona}"/>'>
         <input type="hidden" name='id_consultorio'  value='<c:out value="${id_consultorio}"/>'>
         <input type="hidden" name='id_programa'     value='<c:out value="${id_programa}"/>'>
         <input type="hidden" name='id_pedido'       value='<c:out value="${id_pedido}"/>'>
         <input type="hidden" name="valor_1"         value='<c:out value="${valor_1}"/>' >
	 <input type="hidden" name="accion" value='adicion' >
         <input type="hidden" name="sw" value='<c:out value="${sw}"/>' >
       </td>
     </form>
    </tr>   
   </c:forEach>
</table>
    </td>
  </tr>

</table>