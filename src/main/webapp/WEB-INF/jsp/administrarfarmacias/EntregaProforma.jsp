<%@ include file="../Superior.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />
<script language='JavaScript' SRC="./validar.js"></script>

<div><form name=formaRet method=post action='<c:url value="/AdminInventario.do"/>'>
         <a class="btn btn-success" href="javascript:document.formaRet.submit();">Volver</a>
          <input type="hidden" name="sw"              value='<c:out value="${sw}"/>'> 
          <input type="hidden" name="accion"          value='terminar'>
      </form></div>

<form name="forma" method="POST" action='<c:url value="/ListaNProforma.do"/>'>
    <table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
      <tr>
        <th colspan="3" bgcolor="#F2F2F2"><font size=4><center>Crear Proforma de Items <c:out value="${swb}"/></center></font></th>
      </tr>    
      <tr bgcolor="#F2F2F2">
          <td align="right" bgcolor="#F2F2F2"><b> Fecha Entrega</b></td>	
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
          <c:if test="${swb=='PROFORMA'}">
             <tr>    
                <td align="right" bgcolor="#F2F2F2">Empresa/Institucion </td>    	      
                <td><div class="form-inline"><input class="form-control" type="text" name="institucion" value="" size="50" placeholder="Empresa / Institucion..." /></div></td>
              </tr>
          </c:if> 
          <tr>    
            <td align="right" bgcolor="#F2F2F2">Nombre Cliente: </td>    
            <td><div class="form-inline"><input class="form-control" type="text" name="nombres" value="" size="50" placeholder="Nombre / Responsable..." /></div></td>
          </tr>  
          <tr>    
            <td align="right" bgcolor="#F2F2F2">Nro Nit/Documento: </td>    	      
            <td><div class="form-inline"><input class="form-control" type="text" name="num_cladoc" value="<c:out value = "${num_cladoc}"/>" maxlength=60/></div></td>
          </tr>
    </table>

<center>
  <td><input type="submit" class="btn btn-primary btn-lg" value='Siguiente' onClick="return cambiarVentana()"></td>
</center>
   <input type="hidden" name='id_historial' value='<c:out value="${id_historial}"/>'>
   <input type="hidden" name='accion'       value='<c:out value="entregarya"/>'>
   <input type="hidden" name='accionb'      value='<c:out value="${accionb}"/>'>
   <input type="hidden" name='swb'           value='<c:out value="${swb}"/>'>
   <input type="hidden" name='sw'           value='<c:out value="${sw}"/>'>
</form>
