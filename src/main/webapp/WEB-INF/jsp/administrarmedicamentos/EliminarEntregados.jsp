<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>
<script language='JavaScript' SRC="./validar.js"></script>

<div style="font-size:15pt"> Eliminar Recetas Farmacia</div>

<form name="eliminar" method="POST" action='<c:url value="/ReporteEntregados.do"/>' >
    <table class="table table-striped table-bordered table-condensed table-responsive"> 
      <tr>
        <th colspan="3"><font size=2><center> DATOS DEL PEDIDO</center>  </font></th>
      </tr>
    </table>
 <center>   
 <table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> ID </th>
    <th bgcolor="#F2F2F2"> No Factura </th>
    <th bgcolor="#F2F2F2"> Fecha </th>
    <th bgcolor="#F2F2F2" align="center"> Nombre Paciente </th>
    <th bgcolor="#F2F2F2"> No Pedido</th>
    <th bgcolor="#F2F2F2"> No Doc.</th>
    <th bgcolor="#F2F2F2"> MONTO </th>    
    </tr>  
    <tr>
       <td><c:out value="${id_pedido}"/></td>
       <c:if test="${id_factura>0 and (id_estado=='E' or id_estado=='C')}"> 
           <td style="color:red"><c:out value="${id_factura}"/></td>  
       </c:if> 
       <c:if test="${!(id_factura>0 and (id_estado=='E' or id_estado=='C'))}"> 
           <td ><c:out value="${id_factura}"/></td>  
       </c:if> 
       <td><c:out value="${fecha}"/></td>      
       <td align="center"><c:out value="${nombres}"/></td>  
       <td><c:out value="${nit}"/></td>   
       <td><c:out value="${num_cladoc}"/></td>
       <td align="right"><fmt:formatNumber value="${total}" maxFractionDigits="1"/></td>
      </tr>
      
       <c:if test="${id_factura>0 and (id_estado=='E' or id_estado=='C')}"> 
           </tr>
           <td style="color:red" colspan="7" align="center">FACTURA EMITIDA</td>  
           </tr>
       </c:if>
      
      
      
     
<td colspan=7 align="center"> 
          
 <table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> Nro </th>
    <th bgcolor="#F2F2F2"> Medicamento </th>
    <th bgcolor="#F2F2F2"> Presentacion </th>
    <th bgcolor="#F2F2F2"> Concentracion </th>
    <th bgcolor="#F2F2F2"> Cant. </th>
    <th bgcolor="#F2F2F2"> Precio </th>
    <th bgcolor="#F2F2F2"> TOTAL </th>
    </tr>  
   <c:forEach var="listadox" items="${listarKardex}" varStatus="contadora">
    <tr style="font-size:9pt">
       <td align="center"><c:out value="${contadora.count}"/></td>
       <td><c:out value="${listadox.medicamento}"/></td>      
       <td><c:out value="${listadox.forma_far}"/></td>      
       <td><c:out value="${listadox.concentra}"/></td>      
       <td><fmt:formatNumber value="${listadox.salida}" maxFractionDigits="0"/></td>
       <td><fmt:formatNumber value="${listadox.precio_venta}" maxFractionDigits="1"/></td>
       <td><fmt:formatNumber value="${listadox.precio_total}" maxFractionDigits="1"/></td> 
    </tr>   
   </c:forEach>
   </table>       
      </td>     
     </tr>
    </table>
</center>  
      
<center>
  <input class="btn btn-primary btn-lg" type="submit" name='accion' class="eliminar" value='EliminarPed'>
</center>
  <input type="hidden" name='id_pedido' value='<c:out value="${id_pedido}"/>'>
  <input type="hidden" name='id_por'    value='<c:out value="${id_por}"/>'>
  <input type="hidden" name='sw'        value='<c:out value="${sw}"/>'>
  <input type="hidden" name=accion      value='EliminarPed'>
</form>
