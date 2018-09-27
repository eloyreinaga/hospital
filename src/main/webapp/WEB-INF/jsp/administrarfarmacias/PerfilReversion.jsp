<%@ include file="../Superior.jsp" %>
<script language='JavaScript' SRC="./validar.js"></script>

<div style="font-size:15pt"> Lista de Medicamentos a ser Revertidos</div>

<table>
  <tr>
  <form name=formaMRev<c:out value="${contador.count}"/> method=post action='<c:url value="/RecetasInternados.do"/>'>
           <td>     
             <div><a class="btn btn-primary" href="javascript:document.formaMRev<c:out value="${contador.count}"/>.submit();">Revertir</a></div>
             <input type="hidden" name="id_historial"    value=<c:out value="${id_historial}"/> > 
             <input type="hidden" name="id_paciente"     value=<c:out value="${id_paciente}"/> > 
             <input type="hidden" name="accion"          value='Perfil'>
           </td>
       </form>
  <tr>
</table>

<table class="table table-striped table-bordered table-hover table-condensed table-responsive">
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> No </th>
    <th bgcolor="#F2F2F2"> FECHA </th>
    <th bgcolor="#F2F2F2"> NOMBRE PACIENTE </th>    
    <th bgcolor="#F2F2F2"> CODIGO </th>
    <th bgcolor="#F2F2F2"> MEDICAMENTO </th>
    <th bgcolor="#F2F2F2"> FORMA_FAR </th>    
    <th bgcolor="#F2F2F2"> Cantidad<br>Prescrita </th>
    <th bgcolor="#F2F2F2"> Cantidad<br>Revertir </th>
    <th bgcolor="#F2F2F2"> Medico Tratante </th>    
    
    </tr>  
   <c:forEach var="listaI" items="${listarPerfilRev}" varStatus="contador">
     <tr style="font-size:9pt">
       <td align="center"><c:out value="${contador.count}"/></td>
       <td><fmt:formatDate value="${listaI.fecha}" pattern='dd/MM/yyyy'/>&nbsp;<b><fmt:formatDate value="${listaI.fecha}" pattern='HH:mm'/></b></td>
       <td><b><c:out value="${listaI.cadena2}"/><b></td>    
       <td><c:out value="${listaI.codsumi}"/></td>  
       <td><c:out value="${listaI.medicamento}"/></td>  
       <td><c:out value="${listaI.forma_far}"/></td>  
       <td align="center"><c:out value="${listaI.suma1}"/></td>  
       <td style="color:blue;font-size:14pt" align="center"><b><c:out value="${listaI.suma4}"/><b></td>  
       <td><c:out value="${fn:substring(listaI.cadena3,0,30)}"/></td>   
      </tr>  
   </c:forEach>
</table>

