<%@ include file="../Superior.jsp" %>
<script language='JavaScript' SRC="./validar.js"></script>

<div><a class="btn btn-success" href='ListarPacientesFar.do'>Volver</a></div>

<form name="adicionarpaciente" method="POST">

    <table class="formulario" width="100%">
      <tr>
        <th colspan="3" align="right" bgcolor="#F2F2F2"><font size=2>DATOS PERSONALES </font></th>
      </tr>    
  
      <tr>    
        <td  align="right" bgcolor="#F2F2F2">Nombres  </td>    
        <td><input type="text" name="nombres" value="<c:out value = "${nombres}"/>" size="50" onblur='validar(nombres,"A")'/></td>
      </tr>    
 
       <tr>    
        <td  align="right" bgcolor="#F2F2F2">Nit </td>         
        <td><input type="text" name="nit" value="" maxlength=60/></td>
      </tr>        

    </table>

<center>
  <input type="submit" class="btn btn-primary btn-lg" value='Siguiente' onclick="document.adicionarpaciente.action='<c:url value="/ListaRecetaFar.do"/>'"></td>
</center>
  <input type="hidden" name='accion' value='<c:out value="entregarya"/>'>
  <input type="hidden" name='id_historial' value='<c:out value="${id_historial}"/>'>
  <input type="hidden" name='id_paciente'  value='<c:out value="${id_paciente}"/>'>
</form>
