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
     <td>   
        <form name=formam method=post action='<c:url value="/NuevoMedicamento.do"/>'>
           <a class="btn btn-warning" href="javascript:document.formam.submit();" >Configurar</a>
           <input type="hidden" name=accion value='Configurar'>
           <input type="hidden" name=accion2 value='Configurar'>
        </form>
     </td>
   </tr>
   
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
</table> 


<form name="adicionacat" action='<c:url value="/NuevoMedicamento2.do"/>' > 
  <table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
    <tr>
    <th colspan="2"><center>INTRODUZCA DATOS ITEMS</center></th>
     
    <c:if test="${accion == 'Adicionar'}">
       <c:if test="${datoItem.suma16== '1' }">
         <tr style="font-size:9pt">
             <td align="right"> <c:out value="${datoItem.cadena16}"/></td>
             <td><SELECT NAME="id_grupo" onchange="javascript:document.adicionacat.submit();">  
                  <option value="0">--Elegir--</option>
                  <c:forEach var="grupo" items="${listarGrupo}">
                       <option value="<c:out value="${grupo.id_medicamento}"/>"<c:if test="${grupo.id_medicamento == id_grupo}">selected</c:if>> 
                           <c:out value="${grupo.cadena1}"/>_<c:out value="${grupo.id_medicamento}"/>
                       </option>
                  </c:forEach>
              </SELECT>       
              <input type="hidden" name='accion' value='<c:out value="${accion}"/>'>
            </td>
         </tr>
       </c:if>  
       <c:if test="${datoItem.suma17== '1' }">
         <tr style="font-size:9pt">
             <td align="right"> <c:out value="${datoItem.cadena17}"/></td>
             <td><SELECT NAME="id_subgrupo">
                  <option value="0">--Elegir--</option>
                  <c:forEach var="subgrupo" items="${listarSGrupo}">
                       <option value="<c:out value="${subgrupo.id_programa}"/>"<c:if test="${subgrupo.id_programa == id_subgrupo}">selected</c:if>> 
                           <c:out value="${subgrupo.cadena2}"/>_<c:out value="${subgrupo.id_programa}"/>
                       </option>
                  </c:forEach>
              </SELECT>       
            </td>
         </tr>
       </c:if> 
       <c:if test="${datoItem.suma18== '1' }">
         <tr style="font-size:9pt">
             <td align="right"> <c:out value="${datoItem.cadena18}"/></td>
             <td><SELECT NAME="id_partida">
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
       <c:if test="${datoItem.suma5== '1' }">
         <tr style="font-size:9pt">
             <td align="right"> <c:out value="${datoItem.cadena5}"/></td>
             <td><input type="text" name="codsumi" maxlength="20" size="20" value="0"></td>
         </tr>
       </c:if>  
     <tr style="font-size:9pt">
        <td align="right"> Codigo Barra </td>
        <td><input type="text" name="barra" size="30" maxlength="30" value="0"></td>
      </tr>  
    <tr style="font-size:9pt">
      <td align="right" ><b> <c:out value="${datoItem.medicamento}"/> </b></td>
      <td style="font-size:14pt;"><b><input type="text" name="medicamento" maxlength="80" size="80" value="<c:out value="${buscarMedicamento.medicamento}"/>"></b></td>
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
             <td><input type="text" name="concentra" size="20" maxlength="50" value="<c:out value="${buscarMedicamento.concentra}"/>"></td>
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

      </c:if>
      
   <c:if test="${accion == 'Modificar'}">
        <c:if test="${datoItem.suma16== '1' }">
         <tr style="font-size:9pt">
             <td align="right"> <c:out value="${datoItem.cadena16}"/></td>
             <td><SELECT NAME="id_grupo" onchange="javascript:document.adicionacat.submit();">  
                  <option value="0">--Elegir--</option>
                  <c:forEach var="grupo" items="${listarGrupo}">
                       <option value="<c:out value="${grupo.id_medicamento}"/>"<c:if test="${grupo.id_medicamento == id_grupo}">selected</c:if>> 
                           <c:out value="${grupo.cadena1}"/>_<c:out value="${grupo.id_medicamento}"/>
                       </option>
                  </c:forEach>
              </SELECT>       
            </td>
         </tr>
       </c:if>  
       <c:if test="${datoItem.suma17== '1' }">
         <tr style="font-size:9pt">
             <td align="right"> <c:out value="${datoItem.cadena17}"/></td>
             <td><SELECT NAME="id_subgrupo">
                  <option value="0">--Elegir--</option>
                  <c:forEach var="subgrupo" items="${listarSGrupo}">
                       <option value="<c:out value="${subgrupo.id_programa}"/>"<c:if test="${subgrupo.id_programa == id_subgrupo}">selected</c:if>> 
                           <c:out value="${subgrupo.cadena2}"/>_<c:out value="${subgrupo.id_programa}"/>
                       </option>
                  </c:forEach>
              </SELECT>       
            </td>
         </tr>
       </c:if> 
       <c:if test="${datoItem.suma18== '1' }">
         <tr style="font-size:9pt">
             <td align="right"> <c:out value="${datoItem.cadena18}"/></td>
             <td><SELECT NAME="id_partida">
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
       <c:if test="${datoItem.suma5== '1' }">
         <tr style="font-size:9pt">
             <td align="right"> <c:out value="${datoItem.cadena5}"/></td>
             <td><input type="text" name="codsumi" maxlength="20" size="20" value="0"></td>
         </tr>
       </c:if>    
       
       <tr style="font-size:9pt">
      <td align="right"> Codigo </td>
      <td><input type="text" name="codsumi" maxlength="20" size="20" readonly value="<c:out value="${buscarMedicamento.codsumi}"/>"><font size="1"><c:out value="${buscarMedicamento.id_medicamento}"/></font></td>
    </tr>
    <tr style="font-size:9pt">
        <td align="right"> Codigo Barra </td>
        <td><input type="text" name="barra" size="30" maxlength="30" value="0"></td>
      </tr> 
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
             <td><input type="text" name="concentra" size="20" maxlength="50" value="<c:out value="${buscarMedicamento.concentra}"/>"></td>
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
     
       <tr style="font-size:9pt">
        <td align="right"> Costo Unitario </td>
        <td><input type="text" name="costo_unit" size="20" maxlength="15" onblur='validar(costo_unit,"9")' value="<c:out value="${buscarMedicamento.costo_unit}"/>"></td>
      </tr>     
      <tr style="font-size:9pt">
        <td align="right"> Precio de Venta </td>
        <td><input type="text" name="precio_venta" size="20" maxlength="15" onblur='validar(precio_venta,"9")' value="<c:out value="${buscarMedicamento.precio_venta}"/>"></td>
      </tr>  
      <tr style="font-size:9pt">
        <td align="right"> Stock Venta </td>
        <td><input type="text" name="stockv" size="20" maxlength="15" readonly onblur='validar(stockv,"9")' value="<c:out value="${buscarMedicamento.stockv}"/>" ></td>
      </tr>     
      <tr style="font-size:9pt">
        <td align="right"> Stock Ley475</td>
        <td><input type="text" name="stocks" size="20" maxlength="15" readonly onblur='validar(stocks,"9")' value="<c:out value="${buscarMedicamento.stocks}"/>" ></td>
      </tr>     
      <tr style="font-size:9pt">
        <td align="right"> Stock Programa</td>
        <td><input type="text" name="stockp" size="20" maxlength="15" readonly onblur='validar(stockp,"9")' value="<c:out value="${buscarMedicamento.stockp}"/>" ></td>
      </tr>           
      <tr style="font-size:9pt">
        <td align="right"> Stock </td>
        <td><input type="text" name="stock" size="20" maxlength="15" onblur='validar(stock,"9")' value="<c:out value="${buscarMedicamento.stock}"/>"></td>
      </tr>

      <tr style="font-size:9pt">
        <td align="right">Fecha de Vencimiiento </td>    
        <td ><SELECT NAME="diai">
                     <c:forEach var="dias" items="${dias}">
                       <option value="<c:out value="${dias}"/>" <c:if test="${dias == dia}">selected</c:if>> 
                         <c:out value="${dias}"/></option></c:forEach></SELECT>
                <SELECT NAME="mesi">
                     <c:forEach var="meses" items="${meses}">
                       <option value="<c:out value="${meses}"/>" <c:if test="${meses == mes}">selected</c:if>> 
                         <c:out value="${meses}"/></option></c:forEach></SELECT>
                 <SELECT NAME="anioi">
                     <c:forEach var="anios" items="${anios}">
                       <option value="<c:out value="${anios}"/>" <c:if test="${anios == anio}">selected</c:if>> 
                         <c:out value="${anios}"/></option></c:forEach></SELECT>
                 <br><font size="2" color="blue">&nbsp;&nbsp;&nbsp;Dia&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     Mes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Año</font>        
               </td>             
      </tr> 
      
      <tr style="font-size:9pt">
        <td align="right"> Numero de Lote </td>
        <td><input type="text" name="nro_lote" maxlength="20" size="20" value="<c:out value="${buscarMedicamento.nro_lote}"/>"></td>
      </tr> 
        <tr style="font-size:9pt">    
             <td align="right">Tipo </td>    	
             <td>
                 <SELECT NAME="tipo">
                  <c:forEach var="listatipo" items="${listarTipo}">
                       <option value="<c:out value="${listatipo.cadena1}"/>"<c:if test="${listatipo.cadena1 == buscarMedicamento.tipo}">selected</c:if>> 
                           <c:out value="${listatipo.cadena1}"/>_<c:out value="${listatipo.cadena2}"/>
                       </option>
                  </c:forEach>
              </SELECT>                      
            </td>
         </tr>  
       <tr style="font-size:9pt">    
             <td align="right">Restringido </td>    	
             <td>
                <c:if test="${buscarMedicamento.restringido=='0'}">
                    <SELECT NAME="restringido">
                    <option value="0" selected> NO </option>
                    <option value="1" > SI</option>
                </c:if>
                <c:if test="${buscarMedicamento.restringido=='1'}">
                    <SELECT NAME="restringido">
                    <option value="1" selected> SI </option>
                    <option value="0" > NO </option>
                </c:if>
              </SELEC          
            </td>
         </tr>    
      <tr style="font-size:9pt">
        <td align="right"> Stock Maximo <br>a Dispensar </td>
        <td><font size="1"><b>Emerg:</b></font><input type="text" name="max_emerg" maxlength="3" size="3" value="<c:out value="${buscarMedicamento.max_emerg}"/>">
            <font size="1"><b>C.Ext:</b></font><input type="text" name="max_exter" maxlength="3" size="3" value="<c:out value="${buscarMedicamento.max_exter}"/>">
                           <b>C.Inter.:</b></font><input type="text" name="max_inter" maxlength="3" size="3" value="<c:out value="${buscarMedicamento.max_inter}"/>">
        </td>
      </tr> 
      <tr style="font-size:9pt">
        <td align="right"> Stock Minimo <br>a Dispensar </td>
        <td><font size="1"><b>Emerg:</b></font><input type="text" name="min_emerg" maxlength="3" size="3" value="<c:out value="${buscarMedicamento.min_emerg}"/>">
            <font size="1"><b>C.Ext:</b></font><input type="text" name="min_exter" maxlength="3" size="3" value="<c:out value="${buscarMedicamento.min_exter}"/>">
            <font size="1"><b>C.Inter.:</b></font><input type="text" name="min_inter" maxlength="3" size="3" value="<c:out value="${buscarMedicamento.min_inter}"/>">
        </td>
      </tr>
        <tr style="font-size:9pt">
          <td align="right">Estado </td>
          <td><input type=checkbox name="codigo" value="<c:out value="${buscarMedicamento.codigo}"/>" <c:if test="${buscarMedicamento.codigo == -1}">checked</c:if>>
          </td>
        </tr>
  </c:if>
    
  </table>
  <center>
    <input type="submit" class="btn btn-primary" value='Siguiente' onclick="document.adicionacat.accion1.value='Guardar';
								      document.adicionacat.action='<c:url value="/ConfirmarMedicamento.do"/>'">
  </center>
    <input type="hidden" name='id_medicamento' value='<c:out value="${buscarMedicamento.id_medicamento}"/>'>
    <input type="hidden" name='id_medicamento' value='<c:out value="${id_medicamento}"/>'>    
    <input type="hidden" name='accion' value='<c:out value="${accion}"/>'>
    <input type="hidden" name='sw' value='<c:out value="${sw}"/>'>    
    <input type="hidden" name='recargado' value='Si'>
    <input type="hidden" name='accion1' value=''>
</form>
