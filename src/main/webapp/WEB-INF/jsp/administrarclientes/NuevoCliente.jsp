<%@ include file="../Superior.jsp" %>


<c:if test="${accion == 'Modificar'}">
    <div style="font-size:15pt"> Modificando Cliente</div>
</c:if>
<c:if test="${accion == 'Adicionar'}">
    <div style="font-size:15pt"> Agregando Cliente</div>
</c:if>

<div><a class="btn btn-success" href='ListarClientes.do'>Volver</a></div>

<form name="adicionacat" method="POST">
    <center>
        <table class="table table-striped table-bordered table-condensed table-responsive">
            <tr>
                <th colspan="3">INTRODUZCA LOS DATOS CLIENTES</th>
            </tr>    
            <tr>
                <td bgcolor="#F2F2F2" align="right"> Razon Social  </td>
                <td><input type="text" name="razonsocial" maxlength="20" size="20" value="<c:out value="${datocliente.razonsocial}"/>"></td>
            </tr> 
            <tr>
                <td bgcolor="#F2F2F2" align="right"> Encargado  </td>
                <td><input type="text" name="encargado" maxlength="20" size="20" value="<c:out value="${datocliente.encargado}"/>"></td>
            </tr> 
            <tr>
                <td bgcolor="#F2F2F2" align="right"> Direccion  </td>
                <td><input type="text" name="direccion" maxlength="20" size="20" value="<c:out value="${datocliente.direccion}"/>"></td>
            </tr> 
            <tr>
                <td bgcolor="#F2F2F2" align="right"> Telefonos  </td>
                <td><input type="text" name="fonos" maxlength="20" size="20" value="<c:out value="${datocliente.fonos}"/>"></td>
            </tr> 
            <tr>
                <td bgcolor="#F2F2F2" align="right"> NIT  </td>
                <td><input type="text" name="nit" maxlength="20" size="20" value="<c:out value="${datocliente.nit}"/>"></td>
            </tr> 
            <tr>
                <td bgcolor="#F2F2F2" align="right"> Email  </td>
                <td><input type="text" name="email" maxlength="20" size="20" value="<c:out value="${datocliente.email}"/>"></td>
            </tr> 
            <tr>
                <td bgcolor="#F2F2F2" align="right"> Ciudad  </td>
                <td><input type="text" name="ciudad" maxlength="20" size="20" value="<c:out value="${datocliente.ciudad}"/>"></td>
            </tr> 
            <c:if test="${sw == 1}">
                <tr>
                    <td bgcolor="#F2F2F2" align="right">Estado  </td>
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
    </center>   
    <center>
        <input type="submit" class="btn btn-primary btn-lg" value='Siguiente' onclick="document.adicionacat.accion1.value = 'Guardar';
                document.adicionacat.action = '<c:url value="/ConfirmarCliente.do"/>'">
    </center>
    <input type="hidden" name='id_cliente'   value='<c:out value="${id_cliente}"/>'>
    <input type="hidden" name='accion'         value='<c:out value="${accion}"/>'>
    <input type="hidden" name='id_cliente'   value='<c:out value="${datocliente.id_cliente}"/>'>
    <input type="hidden" name='sw'             value='<c:out value="${sw}"/>'>
    <input type="hidden" name='recargado'      value='Si'>
    <input type="hidden" name='accion1'        value=''>
</form>
