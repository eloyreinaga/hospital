<%@ include file="../Superior.jsp" %>


<c:if test="${accion == 'Modificar'}">
    <div style="font-size:15pt"> Modificando Departamento</div>
</c:if>
<c:if test="${accion == 'Adicionar'}">
    <div style="font-size:15pt"> Agregando Departamento</div>
</c:if>

<div><a class="volver" href='ListarDepartamentos.do'>Volver</a></div>
<br>

<form name="adicionarEnlace" method="POST">
    <table class="formulario">
        <tr>
            <th colspan="3">INTRODUZCA LOS DATOS </th>
        </tr>
        <c:if test="${sw != 1}">
            <tr>
                <td> C&oacute;digo Departamento  </td>
                <td>::</td>
                <td><input type="text" name="id_departamento" size="20" maxlength="3" onblur='validar(id_pais, "A9")' value="<c:out value="${datos.id_departamento}"/>"></td>
            </tr>     
        </c:if>  
        <tr>
            <td> Pais  </td>
            <td>::</td>	      
            <td>
                <SELECT NAME="id_pais">
                    <option value="0">-- seleccione --
                        <c:forEach var="cat" items="${listarPaises}">
                        <option value="<c:out value="${cat.id_pais}"/>" <c:if test="${cat.id_pais == datos.id_pais}"> selected </c:if>>
                            <c:out value="${cat.pais}"/>
                        </option>
                    </c:forEach>
                </SELECT>
            </td>
        </tr>	
        <tr>
            <td> Nombre del Departamento  </td>
            <td>::</td>
            <td><input type="text" name="departamento" size="20" maxlength="40" value="<c:out value="${datos.departamento}"/>"></td>
        </tr>       
        <c:if test="${sw == 1}">
            <tr>
                <td>Estado  </td>
                <td>::</td>
                <td>
                    <c:if test="${id_estado == 'A'}">
                        <input type=checkbox name="id_estado" value="A" <c:if test="${id_estado == 'A'}">checked</c:if>>
                            Activo
                    </c:if> 
                    <c:if test="${id_estado == 'B'}">
                        <input type=checkbox name="id_estado" value="A" <c:if test="${id_estado == 'A'}">checked</c:if>>
                            Bloqueado
                    </c:if> 
                </td>     
            </tr>
        </c:if>     
    </table>
    <center>
        <input type="submit" class="siguiente" value='Siguiente' onclick="document.adicionarEnlace.accion1.value = 'Guardar';
                document.adicionarEnlace.action = '<c:url value="/ConfirmarDepartamento.do"/>'">
    </center>
    <input type="hidden" name='accion1' value=''>
    <input type="hidden" name='id_departamento' value='<c:out value="${id_departamento}"/>'>    
    <input type="hidden" name='sw' value='<c:out value="${sw}"/>'>    
    <input type="hidden" name='accion' value='<c:out value="${accion}"/>'>
    <input type="hidden" name='id_departamento' value='<c:out value="${datos.id_departamento}"/>'>
    <input type="hidden" name='recargado' value='Si'>
</form>
