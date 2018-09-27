<%@ include file="../Superior.jsp" %>
<script language = 'JavaScript' SRC="./validar.js"></script>

<c:if test="${accion == 'Modificar'}">
    <div style="font-size:15pt"> Modificando cliente</div>
</c:if>
<c:if test="${accion == 'Adicionar'}">
    <div style="font-size:15pt"> Agregando cliente</div>
</c:if>
<c:if test="${accion == 'Eliminar'}">
    <div style="font-size:15pt"> Eliminando cliente</div>
</c:if>

<div><a class="btn btn-success" href='ListarCientes.do'>Volver</a></div>


<form name="adicionarusu" method="POST" action='<c:url value="/GrabarCliente.do"/>' >
    <center>   
        <table class="formulario">
            <tr>
                <th colspan="3">CONFIRME LOS DATOS CLIENTE</th>
            </tr>
            <tr>
                <td bgcolor="#F2F2F2" align="right"> Razon Social </td>
                <td><c:out value="${dato.razonsocial}"/></td>
            </tr> 
            <tr>
                <td bgcolor="#F2F2F2" align="right"> Encargado </td>
                <td><c:out value="${dato.encargado}"/></td>
            </tr> 
            <tr>
                <td bgcolor="#F2F2F2" align="right"> Direccion </td>
                <td><c:out value="${dato.direccion}"/></td>
            </tr> 
            <tr>
                <td bgcolor="#F2F2F2" align="right"> Telefonos </td>
                <td><c:out value="${dato.fonos}"/></td>
            </tr> 
            <tr>
                <td bgcolor="#F2F2F2" align="right"> NIT </td>
                <td><c:out value="${dato.nit}"/></td>
            </tr> 
            <tr>
                <td bgcolor="#F2F2F2" align="right"> Email </td>
                <td><c:out value="${dato.email}"/></td>
            </tr> 
            <tr>
                <td bgcolor="#F2F2F2" align="right"> Ciudad </td>
                <td><c:out value="${dato.ciudad}"/></td>
            </tr>
            <c:if test="${sw == 1}">       
                <c:if test="${dato.id_estado == null}">       
                    <tr>
                        <td> Estado </td>
                        <td>B</td>
                    </tr>        
                </c:if>    
                <c:if test="${dato.id_estado == 'A'}">       
                    <tr>
                        <td bgcolor="#F2F2F2" align="right"> Estado </td>
                        <td><c:out value="${dato.id_estado}"/></td>
                    </tr>        
                </c:if>          
            </c:if>    
            <c:if test="${sw1 == 1}">       
                <tr>
                    <td bgcolor="#F2F2F2" align="right"> Estado </td>
                    <td><c:out value="${dato.id_estado}"/></td>
                </tr>        
            </c:if>        
        </table>
    </center>   
    <center>
        <input type="submit" class="btn btn-primary btn-lg" name='accion1' value='Aceptar'>
    </center>  
    <input type="hidden" name='id_cliente'     value='<c:out value="${dato.id_cliente}"/>'>
    <input type="hidden" name='razonsocial'    value='<c:out value="${dato.razonsocial}"/>'>
    <input type="hidden" name='encargado'      value='<c:out value="${dato.encargado}"/>'>
    <input type="hidden" name='direccion'      value='<c:out value="${dato.direccion}"/>'>
    <input type="hidden" name='fonos'          value='<c:out value="${dato.fonos}"/>'>
    <input type="hidden" name='nit'            value='<c:out value="${dato.nit}"/>'>
    <input type="hidden" name='email'          value='<c:out value="${dato.email}"/>'>
    <input type="hidden" name='ciudad'         value='<c:out value="${dato.ciudad}"/>'>
    <input type="hidden" name='id_estado'      value='<c:out value="${dato.id_estado}"/>'>
    <input type="hidden" name='accion'         value='<c:out value="${accion}"/>'>
</form>



