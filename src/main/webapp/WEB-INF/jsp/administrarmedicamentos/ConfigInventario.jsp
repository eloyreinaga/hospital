<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>
<script language='JavaScript' SRC="./validar.js"></script>

<table>
   <tr>
     <td>   
        <form name=formamv method=post action='<c:url value="/ListarMedicamentos.do"/>'>
           <a class="btn btn-success" href="javascript:document.formamv.submit();" >Volver</a>
        </form>
     </td>
   </tr>
</table> 


<form name="adicionacat" action='<c:url value="/NuevoMedicamento2.do"/>' > 
  <table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
    <tr>
    <th colspan="2"><center>CONFIGURAR VISTA ITEMS INVENTARIO</center></th>
     
    <c:if test="${accion != 'Adicionar'}">
    <tr style="font-size:9pt">
      <td align="right" ><b> <c:out value="${datoItem.medicamento}"/> </b></td>
      <td style="font-size:14pt;"><b><input type="text" name="medicamento" maxlength="80" size="60" value="<c:out value="${buscarMedicamento.medicamento}"/>"></b></td>
    </tr>      
       <c:if test="${datoItem.suma1== '1' }">
          <tr style="font-size:9pt">
             <td align="right"> <c:out value="${datoItem.cadena1}"/></td>
             <td><input type="text" name="forma_far" size="20" maxlength="50" value="<c:out value="${buscarMedicamento.forma_far}"/>"></td>
          </tr> 
       </c:if>
       <c:if test="${datoItem.suma2== '1' }">
          <tr style="font-size:9pt">
             <td align="right"> <c:out value="${datoItem.cadena2}"/></td>
             <td><input type="text" name="concentra" size="20" maxlength="50" value="<c:out value="${buscarMedicamento.forma_far}"/>"></td>
          </tr> 
        </c:if>
        <c:if test="${datoItem.suma3== '1' }">
           <tr style="font-size:9pt">
             <td align="right"> <c:out value="${datoItem.cadena3}"/></td>
             <td><input type="text" name="nro_lote" maxlength="20" size="20" value="0"></td>
         </tr>
         </c:if>
         <c:if test="${datoItem.suma4== '1' }">
             <tr style="font-size:9pt">
                 <td align="right"> <c:out value="${datoItem.cadena4}"/></td>
                 <td><input type="text" name="ubicacion" maxlength="20" size="20" value="0"></td>
             </tr>
         </c:if>
  
      <!--  
      <tr style="font-size:9pt">
        <td align="right"> Costo Unitario </td>
        <td><input type="text" name="costo_unit" size="20" maxlength="15" onblur='validar(costo_unit,"9")' value="0"></td>
      </tr>     
      <tr style="font-size:9pt">
        <td align="right"> Precio de Venta </td>
        <td><input type="text" name="precio_venta" size="20" maxlength="15" onblur='validar(precio_venta,"9")' value="0"></td>
      </tr>     
      <tr style="font-size:9pt">
        <td align="right"> Stock Venta </td>
        <td><input type="text" name="stockv" size="20" maxlength="15" onblur='validar(stockv,"9")' value="0"></td>
      </tr>     
      <tr style="font-size:9pt">
        <td align="right"> Stock SIIS</td>
        <td><input type="text" name="stocks" size="20" maxlength="15" onblur='validar(stocks,"9")' value="0"></td>
      </tr>     
      <tr style="font-size:9pt">
        <td align="right"> Stock Programa</td>
        <td><input type="text" name="stockp" size="20" maxlength="15" onblur='validar(stockp,"9")' value="0"></td>
      </tr>           
      <tr style="font-size:9pt">
        <td align="right"> Stock </td>
        <td><input type="text" name="stock" size="20" maxlength="15" onblur='validar(stock,"9")' value="0"></td>
      </tr>     
    
      -->
   </c:if>
      
      
      
   <c:if test="${accion == 'Modificar'}">
      
   </c:if>
    
      
      
      
      
  </table>
  <center>
    <input type="submit" class="btn btn-primary" value='Siguiente' onclick="document.adicionacat.accion1.value='Guardar';
								      document.adicionacat.action='<c:url value="/ConfirmarMedicamento.do"/>'">
  </center>
    <input type="hidden" name='id_medicamento' value='<c:out value="${buscarMedicamento.id_medicamento}"/>'>
    <input type="hidden" name='id_medicamento' value='<c:out value="${id_medicamento}"/>'>        
    <input type="hidden" name='recargado' value='Si'>
    <input type="hidden" name='accion1' value=''>
</form>
