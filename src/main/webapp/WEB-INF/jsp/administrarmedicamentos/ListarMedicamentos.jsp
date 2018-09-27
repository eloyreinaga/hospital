<%@ include file="../Superior.jsp" %>
<script language='JavaScript' SRC="./validar.js"></script>
<jsp:useBean id="now" class="java.util.Date" />

<!--
<table>
    <tr><td colspan="2">
      <form name=formax3 method=post action='<c:url value="/ListarMedicamentos.do"/>'>
        <div style="font-size:14pt; color:black">
          Administraci&oacute;n <a href="javascript:document.formax3.submit();" ><font size="4"></font></a>de Items
          <input type="hidden" name=accion value='Veractua'>
        </div>
    </form>  
       </td> 
      <tr>
</table>

 <table>
  <c:if test="${rol==32}">   
   <tr>
   <td>   
   <c:if test="${datoItem.suma16== '1' }">
          <form name=forma1 method=post action='<c:url value="/NuevoMedicamento.do"/>'>
               <a class="btn btn-default" href="javascript:document.forma1.submit();" ><c:out value="${datoItem.cadena16}"/></a>
               <input type="hidden" name=accion value='Grupo'>
          </form>
      </c:if>
       </td> 
       <td>
      <c:if test="${datoItem.suma17== '1' }">
          <form name=forma2 method=post action='<c:url value="/NuevoMedicamento.do"/>'>
               <a class="btn btn-default" href="javascript:document.forma2.submit();" ><c:out value="${datoItem.cadena17}"/></a>
               <input type="hidden" name=accion value='SubGrupo'>
          </form>
      </c:if>
       </td> 
       <td>
      <c:if test="${datoItem.suma18== '1' }">
          <form name=forma3 method=post action='<c:url value="/NuevoMedicamento.do"/>'>
               <a class="btn btn-default" href="javascript:document.forma3.submit();" ><c:out value="${datoItem.cadena18}"/></a>
               <input type="hidden" name=accion value='Partida'>
          </form>
      </c:if>
       </td> 
       <td>
      <c:if test="${datoItem.suma19== '1' }">
          <form name=forma4 method=post action='<c:url value="/NuevoMedicamento.do"/>'>
               <a class="btn btn-default" href="javascript:document.forma4.submit();" ><c:out value="${datoItem.cadena19}"/></a>
               <input type="hidden" name=accion value='Subpartida'>
          </form>
      </c:if>
       
  </td> 
 </tr>
</c:if> 
</table>
-->
<form name=formax action="<c:url value="/ListarMedicamentos.do"/>" method="POST">
<table>
<tr>
<td>    
<table class="table table-striped table-bordered table-condensed table-responsive"> 
  <tr style="font-size:12pt">
        <td bgcolor="#F2F2F2" align=center colspan=6>Lista General de Items</td>
  </tr> 
  <!--
  <tr> 
        <c:if test="${datoItem.suma16== '1' }">
         <tr style="font-size:9pt">
             <td align="right" bgcolor="#E0E6F8"> <c:out value="${datoItem.cadena16}"/></td>
             <td bgcolor="#E0E6F8"><SELECT NAME="id_grupo" onchange="javascript:document.formax.submit();">  
                  <option value="0">--Elegir--</option>
                  <c:forEach var="grupo" items="${listarGrupo}">
                       <option value="<c:out value="${grupo.id_medicamento}"/>"<c:if test="${grupo.id_medicamento == id_grupo}">selected</c:if>> 
                           <c:out value="${grupo.cadena1}"/>_<c:out value="${grupo.id_medicamento}"/>
                       </option>
                  </c:forEach>
              </SELECT>       
            </td>
       </c:if>  
       <c:if test="${datoItem.suma17== '1' }">
             <td align="right" bgcolor="#E0E6F8"> <c:out value="${datoItem.cadena17}"/></td>
             <td bgcolor="#E0E6F8"><SELECT NAME="id_subgrupo">
                  <option value="0">--Elegir--</option>
                  <c:forEach var="subgrupo" items="${listarSGrupo}">
                       <option value="<c:out value="${subgrupo.id_programa}"/>"<c:if test="${subgrupo.id_programa == id_subgrupo}">selected</c:if>> 
                           <c:out value="${subgrupo.cadena2}"/>_<c:out value="${subgrupo.id_programa}"/>
                       </option>
                  </c:forEach>
              </SELECT>       
            </td>
       </c:if> 
       <c:if test="${datoItem.suma18== '1' }">
             <td align="right" bgcolor="#E0E6F8"> <c:out value="${datoItem.cadena18}"/></td>
             <td bgcolor="#E0E6F8"><SELECT NAME="id_partida">
                  <option value="0">--Elegir--</option>
                  <c:forEach var="partida" items="${listarPartida}">
                       <option value="<c:out value="${partida.id_medicamento}"/>"<c:if test="${partida.id_medicamento == id_partida}">selected</c:if>> 
                           <c:out value="${partida.cadena1}"/>_<c:out value="${partida.id_medicamento}"/>
                       </option>
                  </c:forEach>
              </SELECT>       
            </td>
         </tr>
       </c:if> 
  </tr> 
  -->
  <tr>    
        <td align=right bgcolor="#F2F2F2">Nombre</td>    	
        <td colspan="3"><input class="form-control" type="text" name="nombres"  value="<c:out value="${nombres}"/>"  maxlength=40 onblur='validar=(nombres,"A ")'/></td>            
        <td colspan="2">
            <input style="font-size:12pt" class="btn btn-success" type="submit" name='accion' value="Buscar">
              <input type="hidden" name="orden"            value='medicamento'>
            <c:if test="${rol!=31}">
              <input style="font-size:12pt" class="btn btn-default" type="submit" name='accion' value='Actualiza'> 
              <input style="font-size:12pt" class="btn btn-link" type="submit" name='accion' value='_'>
            </c:if> 
            
        </td>
      </tr>  
</table>
</form>

    <table>
      <tr>
      <c:if test="${area!='I'}">
       <form name=forma method=post action='<c:url value="/NuevoMedicamento.do"/>'>
        <td colspan="2">
          <div class="agregar">
           <a style="font-size:12pt"  class="btn btn-primary" href="javascript:document.forma.submit();" >Nuevo</a>
           <input type="hidden" name=accion value='Adicionar'>
           <input type="hidden" name=accion2 value='Adicionar2'>
        </div></td>
        </form>
        </c:if> 
        <c:if test="${area=='I'}">
           <form name=formai method=post action='<c:url value="/NuevoMedicamento2.do"/>'>
            <td colspan="2">
              <div class="agregar">
               <a style="font-size:12pt"  class="btn btn-primary" href="javascript:document.formai.submit();" >NuevoItem</a>
               <input type="hidden" name=accion value='Adicionar'>
               <input type="hidden" name=accion2 value='Adicionar2'>
            </div></td>
            </form>
        </c:if> 
      </tr>
    </table>

<table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> No </th>
    <th bgcolor="#F2F2F2"> ID <br>IT</th>
    <c:if test="${datoItem.suma16== '1' }"> 
        <form name=formagrupo<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarMedicamentos.do"/>'>
           <th bgcolor="#F2F2F2">     
             <a class="btn btn-xs" href="javascript:document.formagrupo<c:out value="${contador.count}"/>.submit();"><c:out value="${datoItem.cadena16}"/></a>
             <input type="hidden" name="nombremed"        value='<c:out value="${nombremed}"/>'>
             <input type="hidden" name="accion"           value='Ordenar'>
             <input type="hidden" name="orden"            value='grupo'>
           </th>
         </form>
       </c:if>  
    <c:if test="${datoItem.suma5== '1' }">
         <form name=formacodsumi<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarMedicamentos.do"/>'>
           <th bgcolor="#F2F2F2">     
             <a class="btn btn-xs" href="javascript:document.formacodsumi<c:out value="${contador.count}"/>.submit();"><c:out value="${datoItem.cadena5}"/></a>
             <input type="hidden" name="nombremed"        value='<c:out value="${nombremed}"/>'>
             <input type="hidden" name="accion"           value='Ordenar'>
             <input type="hidden" name="orden"            value='codsumi'>
           </th>
         </form>
    </c:if>
    <form name=formamedicamento<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarMedicamentos.do"/>'>
           <th bgcolor="#F2F2F2">     
             <a class="btn btn-xs" href="javascript:document.formamedicamento<c:out value="${contador.count}"/>.submit();"><c:out value="${datoItem.medicamento}"/></a>
             <input type="hidden" name="nombremed"        value='<c:out value="${nombremed}"/>'>
             <input type="hidden" name="accion"           value='Ordenar'>
             <input type="hidden" name="orden"            value='medicamento'>
           </th>
         </form>
    <c:if test="${datoItem.suma1== '1' }">
          <th bgcolor="#F2F2F2"> <c:out value="${datoItem.cadena1}"/></th>
    </c:if>
    <c:if test="${datoItem.suma2== '1' }"> 
          <th bgcolor="#F2F2F2"> <c:out value="${datoItem.cadena2}"/></th>
    </c:if>
    <c:if test="${datoItem.suma3== '1' }"> 
          <form name=formanro_lote<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarMedicamentos.do"/>'>
           <th bgcolor="#F2F2F2">     
             <a class="btn btn-xs" href="javascript:document.formanro_lote<c:out value="${contador.count}"/>.submit();"><c:out value="${datoItem.cadena3}"/></a>
             <input type="hidden" name="nombremed"        value='<c:out value="${nombremed}"/>'>
             <input type="hidden" name="accion"           value='Ordenar'>
             <input type="hidden" name="orden"            value='nro_lote'>
           </th>
         </form>
    </c:if>
    <c:if test="${datoItem.suma6== '1' }">  <!-- Ley475 -->
          <th bgcolor="#F2F2F2"> <c:out value="${datoItem.cadena6}"/></th>
    </c:if>
    <c:if test="${datoItem.suma7== '1' }">  <!-- Programa -->
          <th bgcolor="#F2F2F2"> <c:out value="${datoItem.cadena7}"/></th>
    </c:if>
    <c:if test="${datoItem.suma8== '1' }">  <!-- VENTA -->
          <th bgcolor="#F2F2F2"> <c:out value="${datoItem.cadena8}"/></th>
    </c:if>
    
    <form name=formastock<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarMedicamentos.do"/>'>
           <th bgcolor="#F2F2F2">     
             <a class="btn btn-xs" href="javascript:document.formastock<c:out value="${contador.count}"/>.submit();"><b>STOCK<br>TOTAL</b></a>
             <input type="hidden" name="nombremed"        value='<c:out value="${nombremed}"/>'>
             <input type="hidden" name="accion"           value='Ordenar'>
             <input type="hidden" name="orden"            value='stock'>
           </th>
         </form>
    <c:if test="${datoItem.suma20== '1' }"> 
        <form name=formacosto_unit<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarMedicamentos.do"/>'>
           <th bgcolor="#F2F2F2">     
             <a class="btn btn-xs" href="javascript:document.formacosto_unit<c:out value="${contador.count}"/>.submit();">Costo<br>Unit</a>
             <input type="hidden" name="nombremed"        value='<c:out value="${nombremed}"/>'>
             <input type="hidden" name="accion"           value='Ordenar'>
             <input type="hidden" name="orden"            value='costo_unit'>
           </th>
         </form>
    </c:if>
    <c:if test="${datoItem.suma22== '1' }"> 
          <form name=formaprecio_venta<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarMedicamentos.do"/>'>
           <th bgcolor="#F2F2F2">     
             <a class="btn btn-xs" href="javascript:document.formaprecio_venta<c:out value="${contador.count}"/>.submit();">Precio<br>Venta</a>
             <input type="hidden" name="nombremed"        value='<c:out value="${nombremed}"/>'>
             <input type="hidden" name="accion"           value='Ordenar'>
             <input type="hidden" name="orden"            value='precio_venta'>
           </th>
         </form>
    </c:if>
    <c:if test="${datoItem.suma4== '1' }"> 
         <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarMedicamentos.do"/>'>
           <th bgcolor="#F2F2F2">     
             <a class="btn btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Fecha<br>Vencim</a>
             <input type="hidden" name="nombremed"        value='<c:out value="${nombremed}"/>'>
             <input type="hidden" name="accion"           value='Ordenar'>
             <input type="hidden" name="orden"            value='fecha_ven'>
           </th>
         </form>
    </c:if>
      <th bgcolor="#F2F2F2"> MODIFICAR </th>
      <th bgcolor="#F2F2F2"> ELIMINAR </th> 
    <th bgcolor="#F2F2F2"> KARDEX </th> 
    </tr>  
    
   <c:forEach var="lista" items="${listarMedicamentos}" varStatus="contador">
     <tr style="font-size:9pt">
        <td align="center" style="font-size:6pt"><c:out value="${contador.count}"/></td>
        <form name=formaAct<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarMedicamentos.do"/>'>
              <td align="center">     
                 <div><a href="javascript:document.formaAct<c:out value="${contador.count}"/>.submit();"> <c:out value="${lista.id_medicamento}"/></a></div>
                 <input type="hidden" name="id_medicamento" value=<c:out value="${lista.id_medicamento}"/> >
                 <input type="hidden" name="accion" value='ActualizarMed' >
              </td>
           </form>
       <c:if test="${datoItem.suma16== '1' }">
           <td><c:out value="${lista.grupo}"/></td>
       </c:if>  
       <c:if test="${datoItem.suma5== '1' }">
          <c:if test="${lista.id_medicamento<=2000}">
               <td ><font color="blue"><c:out value="${lista.codsumi}"/></font></td>      
           </c:if>
           <c:if test="${(lista.id_medicamento>2000)}">
               <td ><c:out value="${lista.codsumi}"/></td>      
           </c:if>     
       </c:if>    
       
       <td><c:out value="${fn:substring(lista.medicamento,0,35)}"/></td>      
       <c:if test="${datoItem.suma1== '1' }">
          <td><c:out value="${lista.forma_far}"/></td>    
       </c:if>
       <c:if test="${datoItem.suma2== '1' }"> 
           <td><c:out value="${lista.concentra}"/></td>
       </c:if>
       <c:if test="${datoItem.suma3== '1' }"> 
           <td><c:out value="${lista.nro_lote}"/></td>
       </c:if>
       <c:if test="${datoItem.suma6== '1' }">  <!-- Ley475 -->
          <td style="font-size:10pt"><fmt:formatNumber value="${lista.stocks}" maxFractionDigits="0"/></td>
       </c:if>
       <c:if test="${datoItem.suma7== '1' }">  <!-- Programa -->
             <td style="font-size:10pt"><fmt:formatNumber value="${lista.stockp}" maxFractionDigits="0"/></td>
       </c:if>
       <c:if test="${datoItem.suma8== '1' }">  <!-- VENTA -->
            <td style="font-size:10pt"><fmt:formatNumber value="${lista.stockv}" maxFractionDigits="0"/></td>   
       </c:if>
       <c:if test="${(lista.stock>0)}">  <!-- STOCK TOTAL -->
           <td style="font-size:11pt " align="right"><b><fmt:formatNumber value="${lista.stock}" maxFractionDigits="0"/></b></td>    
       </c:if>
       <c:if test="${(lista.stock<=0)}">   <!-- STOCK TOTAL -->
           <td style="font-size:13pt; color:red" align="right"><b ><fmt:formatNumber value="${lista.stock}" maxFractionDigits="0"/></b></td>    
       </c:if>
       
       <c:if test="${datoItem.suma20== '1' }">  <!-- Costo Unit -->
          <td style="font-size:10pt" align="right"><c:out value="${lista.costo_unit}"/></td>
       </c:if>   
       <c:if test="${datoItem.suma22== '1' }">   <!-- Precio Venta -->
           <td style="font-size:10pt" align="right"><c:out value="${lista.precio_venta}"/></td>
       </c:if>
      <c:if test="${datoItem.suma4== '1' }"> 
          <c:if test="${(lista.mes==1)}">
           <td align="right"><fmt:formatDate value="${lista.fecha_ven}" pattern='MM/yyyy'/></td> 
             </c:if>
              <c:if test="${(lista.mes==0)}">
                   <td style="font-size:9pt; color:red" align="right"><b><fmt:formatDate value="${lista.fecha_ven}" pattern='MM/yyyy'/></td> 
             </c:if>
             <c:if test="${(lista.mes==2)}">
                   <td style="color:orange" align="right"><b><fmt:formatDate value="${lista.fecha_ven}" pattern='MM/yyyy'/></td> 
             </c:if>

    </c:if> 
     
      <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/NuevoMedicamento.do"/>'>
       <td>     
         <div><a class="btn btn-warning btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Modificar</a></div>
  	 <input type="hidden" name="id_medicamento"   value='<c:out value="${lista.id_medicamento}"/>' >
	 <input type="hidden" name="accion"           value='Modificar'>
	 <input type="hidden" name="sw"               value='1' >
       </td>
     </form>

     <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/ConfirmarMedicamento.do"/>'>
       <td>     
         <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();"> Eliminar</a></div>
         <input type="hidden" name="id_medicamento"   value='<c:out value="${lista.id_medicamento}"/>' >
         <input type="hidden" name="accion"           value='Eliminar'>
         <input type="hidden" name="sw1"              value='1' >
       </td>
     </form>    

     <form name=formaK<c:out value="${contador.count}"/> method=post action='<c:url value="/KardexMedicamento.do"/>'>
       <td>     
         <div><a class="btn btn-info btn-xs" href="javascript:document.formaK<c:out value="${contador.count}"/>.submit();"> Kardex</a></div>
         <input type="hidden" name="id_medicamento"  value='<c:out value="${lista.id_medicamento}"/>'>
         <input type="hidden" name="medicamento"     value='<c:out value="${lista.medicamento}"/>'>
         <input type="hidden" name="cod_esta"        value='<c:out value="${lista.cod_esta}"/>'>
         <input type="hidden" name="id_farmacia"     value='<c:out value="${lista.id_farmacia}"/>'>
         <input type="hidden" name="nombremed"       value='<c:out value="${nombremed}"/>'>
         <input type="hidden" name="accion"          value='Kardex'>
         <input type="hidden" name="sw1"             value='1' >
       </td>
     </form>
    </tr> 
   </c:forEach>
</table>
