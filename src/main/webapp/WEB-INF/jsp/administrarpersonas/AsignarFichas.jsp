<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>
<script language='JavaScript' SRC="./validar.js"></script>

<table class="formulario">
  <tr>
    <td width="50%" valign="top">
      
  <table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
   <tr>
      <th colspan="5" style="font-size:14pt">Lista Personal Fichas Internet</th>
   </tr>   
  <tr style="font-size:9pt">
    <th> No </th>
    <th> Nombres </th>
    <th> Consultorio </th>                
    <th> NumPac </th>        
    <th> Eliminar </th>
    </tr>  
   <c:forEach var="listado" items="${listarPersonasInter}" varStatus="contador">
     <tr style="font-size:9pt">
       <td align="center"><c:out value="${contador.count}"/></td>
       <td><c:out value="${listado.nombres}"/></td>  
       <td style="color:blue"><c:out value="${fn:substring(listado.consultorio,0,20)}"/></td>
       <td align="center"><c:out value="${listado.nropac}"/></td>  
     <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/AsignarFichas.do"/>'>
       <td>     
         <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();">Eliminar</a></div>           
  	 <input type="hidden" name="id_persona"    value=<c:out value="${listado.id_persona}"/> >
         <input type="hidden" name="id_ficha"      value=<c:out value="${listado.id_medico}"/> >
         <input type="hidden" name="accion"        value='eliminar' >
	 <input type="hidden" name="sw"            value='1'>
       </td>
     </form>
    </tr> 
   </c:forEach>
    
</table>
    </td>

    <td width="50%" valign="top">
         <form name=forma action="<c:url value="/AsignarFichas.do"/>" method="POST">        
         <table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
          <tr>    
            <td align=right>Nombre Profecional</td>    	
            <td ><input type="text" name="nombre"  value="<c:out value="${nombres}"/>"  maxlength=20 onblur='validar=(nombres,"A ")'/></td>            
            <td coslpan=3><input class="btn btn-primary" type="submit" value="Buscar"></td>
          </tr>  
         </table>
  <input type="hidden" name="accion" value='Buscar' >
</form>   
      
 <table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
  <tr style="font-size:9pt">
    <th> No </th>
    <th> Nombres </th>
    <th> Consultorio </th>                
    <th> Estado </th>            
    <th> Agregar </th>
    </tr>  
   <c:forEach var="lista" items="${listarPersonas}" varStatus="contador">
    <tr style="font-size:9pt">
       <td align="center"><c:out value="${contador.count}"/></td>
       <td><c:out value="${lista.nombres}"/></td>  
       <td style="color:blue"><c:out value="${fn:substring(lista.consultorio,0,20)}"/></td>      
       <td align="center"><c:out value="${lista.id_estado}"/></td>   
     <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/AsignarFichas.do"/>'>
       <td>     
         <div><a class="btn btn-success btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Añadir</a></div>
         <input type="hidden" name="id_persona"     value=<c:out value="${lista.id_persona}"/> >
         <input type="hidden" name="id_consultorio" value=<c:out value="${lista.id_consultorio}"/> >
         <input type="hidden" name="accion"         value='adicion' >
	 <input type="hidden" name="sw"             value='1' >
       </td>
     </form>
    </tr> 
   </c:forEach>

</table>
 
    </td>
  </tr>

</table>
