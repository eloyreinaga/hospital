<%@ include file="../Superior.jsp" %>
<script language='JavaScript' SRC="./validar.js"></script>

 <div style="font-size:15pt"> Entregar Medicamentos al Paciente Internado</div>
 <c:if test="${dispensar=='dispensar' }">
     <div><a  class="btn btn-success" href='ListarInternadosFar.do'>Volver</a></div>
 </c:if> 
 <c:if test="${entragarint=='entragarint' }">
     <div><a  class="btn btn-success" href='ListarAtendidos.do'>Volver</a></div>
 </c:if>
 <br>

 <form name="adicionarpaciente" method="POST">
    <table class="table table-striped table-bordered table-condensed table-responsive">  
      <tr>
        <th colspan="3"><font size=2>DATOS PERSONALEES INTERNADO</font></th>
      </tr>    
      <tr>    
        <td>Nombres </td>    
        <td><input type="text" name="nombres" value="<c:out value = "${nombres}"/>" size="50" onblur='validar(nombres,"A")'/></td>
      </tr>    
     <tr>    
        <td>Nit / CI</td>    	      
        <td><div class="form-inline"><input class="form-control" type="text" name="nit" value="<c:out value = "${carnet}"/>" maxlength=60/></div></td>
      </tr>        
    </table>
 <c:if test="${dispensar=='dispensar' }">
     <center>
       <input type="submit" class="btn btn-primary btn-lg" value='Siguiente' onclick="document.adicionarpaciente.action='<c:url value="/ListaNRecetaI.do"/>'"></td>
       <input type="hidden" name='id_historial' value='<c:out value="${id_historial}"/>'>
       <input type="hidden" name="expedido"     value='<c:out value="${expedido}"/>' >
       <input type="hidden" name='id_paciente'  value='<c:out value="${id_paciente}"/>'>
       <input type="hidden" name='sw'           value='<c:out value="${expedido}"/>'>
       <input type="hidden" name='accion'       value='<c:out value="entregarya"/>'>
     </center>
 </c:if>
 <c:if test="${entragarint=='entragarint' }">  
    <center>
     <input type="submit"  class="btn btn-primary btn-lg" value='Siguiente' onclick="document.adicionarpaciente.action='<c:url value="/ListaRecetaI.do"/>'"></td>
    </center>
 </c:if>   
  <input type="hidden" name='accion' value='<c:out value="entregarya"/>'>
  <input type="hidden" name='id_historial' value='<c:out value="${id_historial}"/>'>
  <input type="hidden" name='id_historia' value='<c:out value="${id_historia}"/>'>
   <input type="hidden" name="expedido"     value='<c:out value="${expedido}"/>' >
  <input type="hidden" name='id_paciente'  value='<c:out value="${id_paciente}"/>'>
  <input type="hidden" name='id_doctor' value='<c:out value="${id_doctor}"/>'>
</form>
