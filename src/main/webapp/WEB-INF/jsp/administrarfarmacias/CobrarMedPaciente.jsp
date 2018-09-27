<%@ include file="../Superior.jsp" %>
<script language = 'JavaScript' SRC="./validar.js">  </script>

<form name="adicionacat" method="POST" action='<c:url value="/CobrarFarmacia.do"/>'>
<table class="table table-striped table-bordered table-condensed table-responsive">
  <tr>
    <th colspan="3" bgcolor="#F2F2F2"><center>Cobrar Items al Cliente</center></th>
  </tr>
  <tr>
    <td width="100%" valign="top">
    <table class="formulario" width="100%">
  
     <tr>    
        <td align="right" bgcolor="#F2F2F2">Nombres :</td>    
        <td><c:out value = "${datos.nombres}"/></td>
      </tr>
      <tr>    
        <td align="right" bgcolor="#F2F2F2">Nit :</td>    	      
        <td><c:out value = "${datos.nit}"/></td>
      </tr>  
      <tr>    
        <td align="right" bgcolor="#F2F2F2">Costo Total :</td>    	      
        <td><c:out value = "${datos.precio_total}"/></td>
       </tr>  
       <tr>    
         <td align="right" bgcolor="#F2F2F2">Numero de Clave de documento</td>    	      
         <td><c:out value = "${datos.num_cladoc}"/></td>
       </tr>   
   </table>
  </td>
  </tr>
</table>
 <center>
     
 <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> NRO </th>
    <th bgcolor="#F2F2F2"> <c:out value="${datoItem.medicamento}"/></th>
    <c:if test="${datoItem.suma1== '1' }">
          <th bgcolor="#F2F2F2"> <c:out value="${datoItem.cadena1}"/></th>
    </c:if>
    <c:if test="${datoItem.suma2== '1' }"> 
          <th bgcolor="#F2F2F2"> <c:out value="${datoItem.cadena2}"/></th>
    </c:if>
    <th bgcolor="#F2F2F2"> CANTIDAD </th>
    <th bgcolor="#F2F2F2"> COSTO </th>
    <th bgcolor="#F2F2F2"> TOTAL </th>
    </tr>  
   <c:forEach var="listado" items="${listarKardex}" varStatus="contador">
     <tr style="font-size:9pt">
       <td align="center"><c:out value="${contador.count}"/></td>
       <td><c:out value="${listado.medicamento}"/></td>      
       <td><c:out value="${listado.forma_far}"/></td>      
       <td><c:out value="${listado.concentra}"/></td>      
       <td align="center"><fmt:formatNumber value="${listado.salida}" maxFractionDigits="0"/></td>             
       <td align="right"><c:out value="${listado.precio_venta}"/></td>                    
       <td align="right"><b><fmt:formatNumber value="${listado.precio_total}" maxFractionDigits="2"/></b></td>                           
   </c:forEach>

</table>
 </center>
 <input type="submit" name='accion' class="btn btn-success" value='Pre Pago' onclick="document.forma.action = '<c:url value="/CobrarFarmacia.do"/>';">
 

 <center>
   <input type="submit" name='accion' class="btn btn-primary btn-lg" value='Cobrar Items' onclick="document.forma.action = '<c:url value="/CobrarFarmacia.do"/>';">
 
 </center>
    <input type="hidden" name='id_pedido'     value='<c:out value="${datos.id_pedido}"/>'>    
    <input type="hidden" name='id_persona'    value='<c:out value="${id_persona}"/>'>
    <input type="hidden" name='num_recibo'    value='<c:out value="${datos.num_cladoc}"/>'>
    <input type="hidden" name='sumatot1'      value='<c:out value="${datos.precio_total}"/>'>
    <input type="hidden" name='nombres'       value='<c:out value="${datos.nombres}"/>'>
    <input  type="hidden" name='accion1'       value='TerminarFar'>
</form>
