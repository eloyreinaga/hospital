<%@ include file="../Superior.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />
<script language = 'JavaScript' SRC="./validar.js">  </script>

<form name="actualizar" method=post action='<c:url value="/ListaNProforma.do"/>'>

<table class="table table-striped table-hover table-condensed table-responsive">
  <tr><c:if test="${swb!='PROFORMA'}">
         <th colspan="3" style="font-size:25pt"><center>DATOS PREPAGO CLIENTE</center></th>
      </c:if>
      <c:if test="${swb=='PROFORMA'}">
         <th colspan="3" style="font-size:25pt"><center>DATOS PROFORMA CLIENTE</center></th>
      </c:if>
  </tr>
  <tr>
    <td width="100%" valign="top">
     <table class="formulario" width="100%">
      <c:if test="${swb=='PROFORMA'}">
        <tr>
          <td align="right" bgcolor="#F2F2F2">Empresa::</td>    
          <td><c:out value = "${datos.nombre}"/></td>        
        </tr> 
      </c:if>
      <tr>
        <td align="right" bgcolor="#F2F2F2">Nombres::</td>    
        <td><c:out value = "${datos.nombres}"/></td>
      </tr>
      <tr>    
        <td align="right" bgcolor="#F2F2F2">No Documento::</td>    	      
        <td><c:out value = "${datos.nit}"/></td>
      </tr>
      <tr style="font-size:12pt">    
        <td align="right" bgcolor="#F2F2F2"">Monto a Cancelar::</td>    	      
        <td><fmt:formatNumber value="${datos.precio_total}" maxFractionDigits="2"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <c:if test="${swb=='PROFORMA'}">
                <font color="blue" size="4">Nº. Proforma : P<c:out value = "${num_prof}"/></font>
            </c:if>
        </td>
      </tr>  
   </table>
  </td>
  </tr>
</table>
<center>
  <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> NRO </th>
    <th bgcolor="#F2F2F2"> FECHA</th>
    <th bgcolor="#F2F2F2"> MEDICAMENTO </th>
    <th bgcolor="#F2F2F2"> Forma Far </th>
    <th bgcolor="#F2F2F2"> Concentra </th>
    <th bgcolor="#F2F2F2"> CANTIDAD </th>
    <th bgcolor="#F2F2F2"> COSTO </th>
    <th bgcolor="#F2F2F2"> TOTAL </th>
    </tr>  
   <c:forEach var="listado" items="${listarKardex}" varStatus="contador">
     <tr style="font-size:9pt">
       <td align="center"><c:out value="${contador.count}"/></td>
       <td><fmt:formatDate value="${listado.fecha}" pattern='dd/MM/yyyy HH:mm'/></td>
       <td><c:out value="${listado.medicamento}"/></td>
       <td><c:out value="${listado.forma_far}"/></td>
       <td><c:out value="${listado.concentra}"/></td>
       <td><c:out value="${listado.salida}"/></td>             
       <td><c:out value="${listado.precio_venta}"/></td>                    
       <td><c:out value="${listado.precio_total}"/></td>                           
   </c:forEach>
</table>
 </center>
 <center>
    <input type="submit" class="btn btn-primary btn-lg" value='Siguiente' onclick="document.adicionacat.accion.value='Guardar';
								      document.adicionacat.action='<c:url value="/ListanProforma.do"/>'">
  </center>
    <input type="hidden" name='id_pedido'       value='<c:out value="${datos.id_pedido}"/>'>  
    <input type="hidden" name='precio_total'    value='<c:out value="${datos.precio_total}"/>'>  
    <input type="hidden" name='precio'          value='<c:out value="${datos.precio_total}"/>'> 
    <input type="hidden" name='nombres2'        value='<c:out value="${nombres}"/>'>  
    <input type="hidden" name='nit'             value='<c:out value="${datos.nit}"/>'>  
    <input type="hidden" name='id_persona'      value='<c:out value="${id_persona}"/>'>
    <input type="hidden" name='ciucla'          value='<c:out value="${num_cladoc}"/>'>
    <input type="hidden" name='ciu'             value='<c:out value="${num_cladoc}"/>'>
    <input type="hidden" name='swb'             value='<c:out value="${swb}"/>'>
    <input type="hidden" name="accion"          value='prepago' >
</form>
