<%@ include file="../Superior.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />
<script language='JavaScript' SRC="./validar.js"></script>

<div><a class="btn btn-success" href='ListarAtendidosSPS.do'>Volver</a></div>

<form name="forma" method="POST" action='<c:url value="/ListaNRecetaSPS.do"/>' >

    <table class="table table-striped table-bordered table-condensed table-responsive"> 
      <tr>
        <th colspan="3" align="right" bgcolor="#F2F2F2"><font size=2><center>DATOS RECETA LEY475 </center></font></th>
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
                 Mes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A�o</font>        
           </td>
          </tr>
      <tr>    
        <td  align="right" bgcolor="#F2F2F2">Nombres  </td>    
        <td><input type="text" name="nombres" value="<c:out value = "${nombres}"/>" size="50" onblur='validar(nombres,"A")'/></td>
      </tr>
      <tr>    
             <td  align="right" bgcolor="#F2F2F2">Tipo Ley475</td>    
	     <td>                   
                <SELECT NAME="id_programa">
                    <option value="1" selected> Menor de 5 a�os </option>
                    <option value="2" > Mujer Embarazada </option>
                    <option value="3" > Mujer Edad Fertil </option>     
                    <option value="4" > Mayor 60 a�os </option>     
                    <option value="5" > Discapacitado </option>     
              </SELECT>           
            </td>
       </tr>
      <tr>    
        <td  align="right" bgcolor="#F2F2F2">Numero Clave de Documento  </td>          
        <td><input type="text" name="num_cladoc" value="<c:out value = "${num_cladoc}"/>" maxlength=60/></td>
      </tr>       
    </table>
<center>
  <input type="submit" class="btn btn-primary btn-lg" value='Siguiente' onclick="document.adicionarpaciente.action='<c:url value="/ListaNRecetaSPS.do"/>'"></td>
</center>
  <input type="hidden" name='sw' value='<c:out value="${sw}"/>'>
  <input type="hidden" name='accion' value='<c:out value="entregarya"/>'>
  <input type="hidden" name='id_historial' value='<c:out value="${id_historial}"/>'>
</form>
