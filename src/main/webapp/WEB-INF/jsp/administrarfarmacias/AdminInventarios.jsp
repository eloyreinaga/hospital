<%@ include file="../Superior.jsp" %>
<jsp:useBean id="now" class="java.util.Date" /> 
<script language='JavaScript' SRC="./validar.js"></script>

<table>
 <tr>
   <td>   
    <table>
      <tr>
      <form name=forma method=post action='<c:url value="/AdminInventario.do"/>'>
        <td colspan="2">
          <div >
           <a class="btn btn-primary" href="javascript:document.forma.submit();" >Nueva Venta</a>
           <input type="hidden" name='sw' value='VENTA'>
           <input type=hidden name='accion' value='adicionar'>
        </div></td>
       </form>
       
       <form name=formacp method=post action='<c:url value="/RecepcionMed.do"/>'>
        <td colspan="2">
          <div >
           <a class="btn btn-success" href="javascript:document.formacp.submit();" >Nueva Compra</a>
           <input type="hidden" name='sw' value='COMPRA'>
           <input type=hidden name='accion' value='adicionar'>
        </div></td>
       </form>
       
       <form name=formacc method=post action='<c:url value="/AdminInventario.do"/>'>
        <td colspan="2">
          <div >
           <a class="btn btn-info" href="javascript:document.formacc.submit();" >Nueva Proforma</a>
           <input type="hidden" name='sw' value='PROFORMA'>
           <input type=hidden name='accion' value='adicionar'>
        </div></td>
       </form>

      </tr>
    </table>
    </td>
 
   
 
   <form name=formatot method=post action='<c:url value="/AdminInventario.do"/>'>
   <td><table>
      <tr>
        <td colspan="1" class="form-control">
                <SELECT NAME="sig_centro"  >
                <c:forEach var="listacentro" items="${listarCentros}">
                   <option value="<c:out value="${listacentro.cod_esta}"/>" <c:if test="${listacentro.cod_esta == localidad}">selected</c:if>>
                     <c:out value="${fn:substring(listacentro.consultorio,0,25)}"/>__<c:out value="${listacentro.descripcion}"/>
                   </option>
                </c:forEach>
               </SELECT>
             <input type="hidden" name='sw' value='Gral' >       
             <input type=hidden name='accion' value='Buscar'>
        </td>
        
      </tr>
    </table>
    </td> 
    <td><table>
      <tr>
        <td colspan="1"><input class="form-control" type="text" name="nombres"  value="<c:out value="${nombres}"/>" size="25" maxlength="30" onblur='validar=(nombres,"A ")' placeholder="Buscar nombre/NroDoc"/></td>
        <td><input class="btn btn-success" type="submit" name='accion' value="Buscar"></td>
      </tr>
    </table>
    </td> 
    </form>
</tr> 
</table>


<c:if test="${listaporcancel == '1'}">  
     <br>
    <table>
      <tr>
      <form name=formapp method=post action='<c:url value="/AdminInventario.do"/>'>
        <td align="center" style="font-size:15pt">Clientes Pendientes de Pago&nbsp;&nbsp;</td>
        <td>
          <div><a class="btn btn-info btn-xs" href="javascript:document.formapp.submit();" >Mostrar Pedidos por Pagar</a>
           <input type=hidden name='accione' value='mostrarPagos'>
         </div></td>
       </form>
      </tr>
    </table>
    <br>

<table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> NRO </th>
    <th bgcolor="#F2F2F2"> FECHA </th>
    <th bgcolor="#F2F2F2"> NOMBRE CLIENTE </th>
    <th bgcolor="#F2F2F2"> NIT </th> 
    <th bgcolor="#F2F2F2"> NRO DOC </th> 
    <th bgcolor="#F2F2F2"> MONTO </th>    
    <th bgcolor="#F2F2F2"> ESTADO </th>
    <th bgcolor="#F2F2F2"> DISP </th>
    <c:if test="${seguro_estab != '1' }"> 
        <th bgcolor="#F2F2F2"> IMPRIMIR </th>
        <th bgcolor="#F2F2F2"> IMPRIMIR </th>
    </c:if> 
    
    <c:if test="${factur!='2'}">
         <th bgcolor="#F2F2F2"> COBRAR </th>
     </c:if> 
    <th bgcolor="#F2F2F2"> MODIFICAR </th>
    </tr>  
  <c:if test="${mostrarpagos == '1'}">     
   <c:forEach var="lista" items="${listapago}" varStatus="contador">
     <tr style="font-size:9pt">
       <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/ReporteEntregados.do"/>'>
              <td align="center">     
                 <div><a href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();"> <c:out value="${contador.count}"/></a></div>
                 <input type="hidden" name="id_pedido" value=<c:out value="${lista.id_pedido}"/> >
                 <input type="hidden" name="id_por"    value=<c:out value="${lista.id_dispensa}"/> >
                 <input type="hidden" name="accion"    value='Eliminar' >
                 <input type="hidden" name="sw1"       value='1' >
              </td>
           </form>
       <td><fmt:formatDate value="${lista.fec_registro}" pattern='dd/MM/yy HH:mm'/></td>
       <td><c:out value="${lista.nombres}"/></td>      
       <td><c:out value="${lista.nit}"/></td>    
       <td><c:out value="${lista.num_cladoc}"/></td> 
       <td align="center"><b><fmt:formatNumber value="${lista.precio_total}" maxFractionDigits="1"/></b></td>      
       <td><center><c:out value="${lista.id_estado}"/></center></td>
       <td><c:out value="${lista.id_dispensa}"/></td>
       
      <c:if test="${seguro_estab != '1' }"> 
        <c:if test="${cod_esta != '400010'}">  
           <form name=formaImp<c:out value="${contador.count}"/> method=post action='<c:url value="/ListaNReceta.do"/>'>
           <td>     
             <div><a class="btn btn-info btn-xs" href="javascript:document.formaImp<c:out value="${contador.count}"/>.submit();">Orden</a></div>
             <input type="hidden" name="id_pedido"   value=<c:out value="${lista.id_pedido}"/> > 
             <input type="hidden" name="precio"      value='<c:out value="${lista.precio_total}"/>' >
             <input type="hidden" name="id_persona"  value=<c:out value="${id_persona}"/> >         
             <input type="hidden" name="accion"      value='Imprimir'>
             <input type="hidden" name="sw"          value='VENTA'>
           </td>
          </form>
       </c:if> 

           <form name=formaMFR<c:out value="${contador.count}"/> method=post action='<c:url value="/CobrarFarmacia.do"/>'>
           <td>     
             <div><a class="btn btn-primary btn-xs" href="javascript:document.formaMFR<c:out value="${contador.count}"/>.submit();">RECIBO</a></div>
             <input type="hidden" name="id_pedido"   value='<c:out value="${lista.id_pedido}"/>' >   
             <input type="hidden" name="precio"      value='<c:out value="${lista.precio_total}"/>' >
             <input type="hidden" name="nombres"     value='<c:out value="${lista.nombres}"/>' >
             <input type="hidden" name="nit"         value='<c:out value="${lista.nit}"/>' >
             <input type="hidden" name="id_persona"  value='<c:out value="${id_persona}"/>' >     
             <input type="hidden" name="accion"      value='VerFacturaRec' >
             <input type="hidden" name="sw"          value='1' >
           </td>
         </form>

     </c:if>  
     

     <form name=formaMRCo<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarAtendidos.do"/>'>
           <td>     
             <div><a class="btn btn-success btn-xs" href="javascript:document.formaMRCo<c:out value="${contador.count}"/>.submit();">Coobrar</a></div>
             <input type="hidden" name="id_pedido"     value='<c:out value="${lista.id_pedido}"/>' >         
             <input type="hidden" name="id_persona"    value='<c:out value="${id_persona}"/>' >      
             <input type="hidden" name='precio_total'  value='<c:out value="${lista.precio_total}"/>'> 
             <input type="hidden" name='nombres'       value='<c:out value="${lista.nombres}"/>'> 
             <input type="hidden" name="accion"        value='pagarmed'>
             <input type="hidden" name="sw"            value='VENTA' >
           </td>
         </form>

     
     <form name=formaMR<c:out value="${contador.count}"/> method=post action='<c:url value="/ListaNProforma.do"/>'>
       <td>     
         <div><a class="btn btn-warning btn-xs" class="btn btn-info btn-xs" href="javascript:document.formaMR<c:out value="${contador.count}"/>.submit();">Ver Pedido</a></div>
  	 <input type="hidden" name="id_pedido"  value=<c:out value="${lista.id_pedido}"/> >         
         <input type="hidden" name="id_persona" value=<c:out value="${id_persona}"/> >         
	 <input type="hidden" name="accion"     value='entregar' >
	 <input type="hidden" name="sw"         value='VENTA' >
       </td>
     </form>
     </c:forEach>
  </c:if> 
</table>
</c:if>



<c:if test="${listacancelados == '1' }">
<div class="form-control" style="font-size:14pt"> Entregar Items que fueron Pagados</div>
<br>
<table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> No. </th>
    <th bgcolor="#F2F2F2"> FECHA </th>
    <th bgcolor="#F2F2F2"> NOMBRE CLIENTE</th>
    <th bgcolor="#F2F2F2"> NIT </th>    
    <th bgcolor="#F2F2F2"> CANCELADO </th>
    <th bgcolor="#F2F2F2"> DISP </th>
    <th bgcolor="#F2F2F2"> MODIFICAR </th>
    </tr>  
   <c:forEach var="lista" items="${milista}" varStatus="contador">
     <tr style="font-size:9pt">
       <td align="center"><c:out value="${contador.count}"/></td>
       <td><fmt:formatDate value="${lista.fec_registro}" pattern='dd/MM/yy HH:mm'/></td>
       <td><c:out value="${lista.nombres}"/>_<c:out value="${lista.cod_esta}"/></td>      
       <td><c:out value="${lista.nit}"/></td>    
       <td align="center"><b><c:out value="${lista.precio_total}"/></b></td>
       <td><c:out value="${lista.id_dispensa}"/></td>
     <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarAtendidos.do"/>'>
       <td>     
         <div><a class="btn btn-success btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Entregar</a></div>
  	 <input type="hidden" name="id_pedido" value='<c:out value="${lista.id_pedido}"/>' >         
         <input type="hidden" name="id_persona" value='<c:out value="${id_persona}"/>' >         
	 <input type="hidden" name="accion" value='entregarmed' >
	 <input type="hidden" name="sw" value='1' >
       </td>
     </form>
    </tr> 
   </c:forEach>
 </table>
</c:if>

<c:if test="${mostrarprof == '1' }">
<div class="form-control" style="font-size:14pt"> Lista de Proformas Realizadas</div>
<br>
<table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> No. </th>
    <th bgcolor="#F2F2F2"> FECHA </th>
    <th bgcolor="#F2F2F2"> COD. PROFOR </th>
    <th bgcolor="#F2F2F2"> EMPRESA / INSTITUCION</th>
    <th bgcolor="#F2F2F2"> NOMBRE RESPONSABLE</th>
    <th bgcolor="#F2F2F2"> NIT </th>    
    <th bgcolor="#F2F2F2"> TOTAL </th>
    <th bgcolor="#F2F2F2"> ID </th>
    <th bgcolor="#F2F2F2"> PAGAR </th>
    <th bgcolor="#F2F2F2"> MODIFICAR </th>
    <th bgcolor="#F2F2F2"> IMPRIMIR </th>
    </tr>  
   <c:forEach var="lista" items="${listaprof}" varStatus="contador">
     <tr style="font-size:9pt">
       <td align="center"><c:out value="${contador.count}"/></td>
       <td><fmt:formatDate value="${lista.fec_registro}" pattern='dd/MM/yy HH:mm'/></td>
       <td><c:out value="${lista.num_cladoc}"/></td> 
       <td><c:out value="${lista.nombre}"/></td> 
       <td><c:out value="${lista.nombres}"/></td> 
       <td><c:out value="${lista.nit}"/></td>    
       <td align="center"><b><c:out value="${lista.precio_total}"/></b></td>
       <td><c:out value="${lista.id_persona}"/></td>
       <c:if test="${lista.id_estado == 'B' }">
           <td style="color:blue">Pagado</td>
           <td style="color:blue">Pagado</td>
       </c:if>    
       <c:if test="${lista.id_estado != 'B' }">
      <form name=formaMRCo<c:out value="${contador.count}"/> method=post action='<c:url value="/ListaNProforma.do"/>'>
           <td>     
             <div><a class="btn btn-success" href="javascript:document.formaMRCo<c:out value="${contador.count}"/>.submit();">Pagar</a></div>
             <input type="hidden" name="id_pedido"     value='<c:out value="${lista.id_pedido}"/>' >         
             <input type="hidden" name="id_persona"    value='<c:out value="${id_persona}"/>' >      
             <input type="hidden" name='precio_total'  value='<c:out value="${lista.precio_total}"/>'> 
             <input type="hidden" name="accion"        value='TerminarCobroP' >
             <input type="hidden" name="sw"            value='VENTA' >
           </td>
         </form> 
       <form name=formaMR<c:out value="${contador.count}"/> method=post action='<c:url value="/ListaNProforma.do"/>'>
       <td>     
         <div><a class="btn btn-warning" class="btn btn-info btn-xs" href="javascript:document.formaMR<c:out value="${contador.count}"/>.submit();">Modificar</a></div>
  	 <input type="hidden" name="id_pedido"  value=<c:out value="${lista.id_pedido}"/> >
         <input type="hidden" name="id_persona" value=<c:out value="${id_persona}"/> >         
	 <input type="hidden" name="accion"     value='entregar' >
	 <input type="hidden" name="swb"        value='PROFORMA' >
       </td>
     </form>
     </c:if>  
     <form name=formaMp<c:out value="${contador.count}"/> method=post action='<c:url value="/ListaNProforma.do"/>'>
       <td>     
         <div><a class="btn btn-info" href="javascript:document.formaMp<c:out value="${contador.count}"/>.submit();">Imprimir</a></div>
  	 <input type="hidden" name="id_pedido"   value='<c:out value="${lista.id_pedido}"/>' >         
         <input type="hidden" name="id_persona"  value='<c:out value="${id_persona}"/>' >         
	 <input type="hidden" name="accion"      value='Imprimir' >
	 <input type="hidden" name="sw"          value='1' >
       </td>
     </form>
    </tr> 
   </c:forEach>
 </table>
</c:if>

