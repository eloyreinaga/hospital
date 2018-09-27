<%@ include file="../Superior.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />

<form name="forma" method="POST" action='<c:url value="/RecepcionMed.do"/>' >
<table> 
 <tr>
   <td width="20%"><td>
 <td width="70%">  
 <center>
  <table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
    <tr>
        <th colspan="3" bgcolor="#F2F2F2"><font size=4><center>Recepcion / Compra de Insumos - Items </center></font></th>
      </tr>    
      <tr bgcolor="#F2F2F2">
          <td align="right" bgcolor="#F2F2F2"><b> Fecha Adquisicion</b></td>	
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
          
      <tr bgcolor="#F2F2F2">    
        <td align="right" >Nombres  </td>    
        <td><input class="form-control" type="text" name="nombres" value="<c:out value = "${nombres}"/>" size="50" placeholder="NOMBRE DISTRIBUIDORA/ TIPO AJUSTES, ETC..."/></td>
      </tr>
      <tr bgcolor="#F2F2F2">   
             <td align="right">Proveedor</td>    
	     <td>
               <SELECT NAME="id_proveedor">
                   <option value="0">--Sin Proveedor--</option>
                  <c:forEach var="prove" items="${listarprov}">
                       <option value="<c:out value="${prove.id_proveedor}"/>"<c:if test="${prove.id_proveedor == id_proveedor}">selected</c:if>> 
                           <c:out value="${prove.razonsocial}"/>_[<c:out value="${prove.encargado}"/>]
                       </option>
                  </c:forEach>
              </SELECT>           
             </td>
          </tr> 
      <tr bgcolor="#F2F2F2">    
             <td align="right">Tipo Transaccion</td>    
             <td>
                <c:if test="${tipodato=='0'}">
                    <SELECT NAME="tipo" onchange="javascript:document.forma.submit();">
                        <option value="0" selected> Recepcion </option>
                        <c:if test="${area!='I'}">
                            <option value="-1" > Ajuste (-) </option>
                            <option value="1" > Ajuste (+) </option>
                        </c:if>
                            <option value="4" > Traspaso Almacen </option>
                            <c:if test="${area!='I'}">
                                <option value="6" > Caja Chica </option>
                            </c:if>

                     </SELECT>    
                </c:if>
                <c:if test="${tipodato=='1'}">
                    <SELECT NAME="tipo" onchange="javascript:document.forma.submit();">
                        <option value="1" selected> Ajuste (+) </option>
                        <option value="-1" > Ajuste (-) </option>
                        <option value="0" > Recepcion </option>
                    </SELECT>
                </c:if>
                <c:if test="${tipodato=='-1'}">
                    <SELECT NAME="tipo" onchange="javascript:document.forma.submit();">
                        <option value="-1" selected> Ajuste (-) </option>
                        <option value="1" > Ajuste (+) </option>
                        <option value="0" > Recepcion </option>
                    </SELECT>
                </c:if>
                <c:if test="${tipodato=='4'}">
                    <SELECT NAME="tipo" onchange="javascript:document.forma.submit();">
                        <option value="4" selected> Traspaso Almacen </option>
                        <c:if test="${area!='I'}">
                                <option value="6" > Caja Chica </option>
                                <option value="-1" > Ajuste (-) </option>
                                <option value="1" > Ajuste (+) </option>
                        </c:if>
                        
                        <option value="0" > Recepcion </option>
                    </SELECT>
                </c:if>   
                <c:if test="${tipodato=='6'}">
                    <SELECT NAME="tipo" onchange="javascript:document.forma.submit();">
                        <option value="6" selected> Caja Chica </option>
                        <option value="4" > Traspaso Almacen </option>
                        <option value="-1" > Ajuste (-) </option>
                        <option value="1" > Ajuste (+) </option>
                        <option value="0" > Recepcion </option>
                    </SELECT>
                </c:if>
            </td>
         </tr>  
         <c:if test="${(rol=='30' or rol=='61' or  rol=='32' or rol=='7') and tipodato=='4'}">
            <tr bgcolor="#F2F2F2">   
             <td align="right">Traspaso</td>    
	     <td>
               <SELECT NAME="id_farmacia">
                  <c:forEach var="farma" items="${listafarAsig}">
                      <c:if test="${(id_farmaciauser!=farma.id_farmacia ) }">
                          <option value="<c:out value="${farma.id_farmacia}"/>"<c:if test="${farma.id_farmacia == id_farmacia}">selected</c:if>> 
                              <c:out value="${farma.farmacia}"/>
                          </option>
                      </c:if> 
                  </c:forEach>
              </SELECT>           
             </td>
          </tr> 
         </c:if> 
        <tr bgcolor="#F2F2F2">    
             <td align="right">Expedido</td>    
	     <td>
             
                <SELECT NAME="expedido" onchange="javascript:document.forma.submit();">
                  <c:if test="${expedido=='V'}">  
                      <option value="V" selected> Venta </option>
                      <c:if test="${datoItem.suma6== '1' }">  <!-- Ley475 -->
                          <option value="S" > Ley475(exSUMI) </option>
                      </c:if>
                      <c:if test="${datoItem.suma7== '1' }">  <!-- Programa -->
                          <option value="P" > Programa </option>
                      </c:if>
                      
                  </c:if>
                  <c:if test="${expedido=='S'}">  
                      <option value="S" selected> Ley475(exSUMI) </option>
                      <c:if test="${datoItem.suma7== '1' }">  <!-- Programa -->
                          <option value="P" > Programa </option>
                      </c:if>
                      <c:if test="${datoItem.suma8== '1' }">  <!-- Ley475 -->
                          <option value="V" > Venta </option>
                      </c:if>
                  </c:if>
                  <c:if test="${expedido=='P'}">  
                      <option value="P" selected> Programa </option>
                      <c:if test="${datoItem.suma6== '1' }">  <!-- Ley475 -->
                          <option value="S" > Ley475(exSUMI) </option>
                      </c:if>
                      <c:if test="${datoItem.suma8== '1' }">  <!-- Venta -->
                          <option value="V" > Venta </option>
                      </c:if>
                  </c:if>
                  <c:if test="${expedido!='V' and expedido!='S' and expedido!='P'}">  
                      <option value="V" selected> Venta </option>
                      <c:if test="${datoItem.suma6== '1' }">  <!-- Ley475 -->
                          <option value="S" > Ley475(exSUMI) </option>
                      </c:if>
                      <c:if test="${datoItem.suma7== '1' }">  <!-- Programa -->
                          <option value="P" > Programa </option>
                      </c:if>
                  </c:if>
                  <input type="hidden" name='accion' value='<c:out value="entregarya2"/>'>
                </SELECT>  
              <c:if test="${programa == 'progra' and expedido=='P'}">
          
                <SELECT NAME="id_programa">
	        <c:forEach var="prog" items="${listarProg}">
                   <c:if test="${prog.id_programa>5}">  
                      <option value="<c:out value="${prog.id_programa}"/>"<c:if test="${prog.id_programa == id_programa}">selected</c:if>> 
                          <c:out value="${prog.id_programa}"/>.<c:out value="${prog.concentra}"/>
                      </option>
                   </c:if>
                </c:forEach>
              </SELECT>           
         </c:if> 
            </td>
          </tr>

    <tr bgcolor="#F2F2F2">    
        <td align="right">Numero Documento(NIT)  </td>    	      
        <td><input class="form-control" type="text" name="orden" value="<c:out value = "${orden}"/>" size="50"/></td>
     </tr>   
     <tr bgcolor="#F2F2F2">    
        <td align="right">Nº Orden de Compra  </td>          
        <td><input class="form-control" type="text" name="num_cladoc" value="<c:out value = "${num_cladoc}"/>" size="50" onblur='validar(num_cladoc,"9");'/></td>
     </tr>    
  </tr>
  </table>
  </center>
  </td>
  <td width="10%"></td>
  </tr>
  </table>
  <center>
        <input class="btn btn-primary btn-lg" type="submit" name='accion2' value='Siguiente'> 
  </center>
    <input type="hidden" name='accion' value='<c:out value="entregarya2"/>'>
    <input type="hidden" name='id_historial' value='<c:out value="${id_historial}"/>'>
 </form>

<%@ include file="../Inferior.jsp" %>


