<%@ include file="../Superior.jsp" %>
<script language = 'JavaScript' SRC="./validar.js"></script>

<div><a class="btn btn-success" href='ListarCostos.do'>Volver</a></div>

<form name="adicionarusu" method="POST" action='<c:url value="/GrabarCosto.do"/>' >
    <center>
        <table class="table table-striped table-bordered table-condensed table-responsive">
            <tr>
                <th colspan="2" bgcolor="#F2F2F2"><center>CONFIRME LOS DATOS 
                <c:if test="${accion == 'Modificar'}">
                    MODIFICANDO COSTO
                </c:if>
                <c:if test="${accion == 'Adicionar'}">
                    AGREGANDO COSTO
                </c:if>
                <c:if test="${accion == 'Eliminar'}">
                    ELIMINANDO COSTO
                </c:if>
            </center></th>
            </tr>
            <tr>
                <td align="right" bgcolor="#F2F2F2"> Rubros </td>
                <td><c:out value="${dato.rubro}"/></td>
            </tr>
            <tr>
                <td align="right" bgcolor="#F2F2F2"> Nombre Costo </td>
                <td><c:out value="${dato.costo}"/></td>
            </tr>   
            <tr>
                <td align="right" bgcolor="#F2F2F2"> Costo </td>
                <td><c:out value="${dato.costo_unit}"/></td>
            </tr>        
            <c:if test="${id_rubro=='6'}">
                <tr>    
                    <td align="right" bgcolor="#F2F2F2"> Seguro  </td>
                    <td><c:out value="${dato.id_estado}"/></td>     
                </tr>    
                <tr>    
                    <td align="right" bgcolor="#F2F2F2"> Valores Normales  </td>
                    <td><c:out value="${dato.normales}"/></td>     
                </tr>
                <tr>    
                    <td align="right" bgcolor="#F2F2F2"> Valores Defecto  </td>
                    <td><c:out value="${dato.defecto}"/></td>     
                </tr>
                <tr>    
                    <td align="right" bgcolor="#F2F2F2"> Valores Muestra  </td>
                    <td><c:out value="${dato.muestra}"/></td>     
                </tr>
                <tr>    
                    <td align="right" bgcolor="#F2F2F2"> Tipo</td>
                    <td><c:out value="${dato.tipo}"/></td>     
                </tr>
            </c:if>  
        </table>
    </center>
    <center>
        <input type="submit" class="btn btn-primary btn-lg" name='accion1' value='Aceptar'>
    </center>  
    <input type="hidden" name='id_rubro'       value='<c:out value="${dato.id_rubro}"/>'>
    <input type="hidden" name='id_estado'      value='<c:out value="${dato.id_estado}"/>'>
    <input type="hidden" name='rubro'          value='<c:out value="${dato.rubro}"/>'>
    <input type="hidden" name='id_costo'       value='<c:out value="${dato.id_costo}"/>'>
    <input type="hidden" name='costo'          value='<c:out value="${dato.costo}"/>'>
    <input type="hidden" name='costo_unit'     value='<c:out value="${dato.costo_unit}"/>'>
    <input type="hidden" name='normales'       value='<c:out value="${dato.normales}"/>'>
    <input type="hidden" name='defecto'        value='<c:out value="${dato.defecto}"/>'>
    <input type="hidden" name='muestra'        value='<c:out value="${dato.muestra}"/>'>
    <input type="hidden" name='id_laboratorio' value='<c:out value="${dato.id_laboratorio}"/>'>
    <input type="hidden" name='id_prestacion'  value='<c:out value="${dato.id_prestacion}"/>'>
    <input type="hidden" name='id_nivel'       value='<c:out value="${dato.id_nivel}"/>'>
    <input type="hidden" name='emergencias'    value='<c:out value="${dato.emergencias}"/>'>
    <input type="hidden" name='accion'         value='<c:out value="${accion}"/>'>
</form>



