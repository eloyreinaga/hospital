<%@ include file="../Superior.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />
<script language = 'JavaScript' SRC="./validar.js"></script>

<form name="actualizar" method=post action='<c:url value="/CobrarFarmacia.do"/>'>

    <table class="table table-striped table-bordered table-condensed table-responsive">
        <tr>
            <c:if test="${dato.cod_esta != 400010}">
                <th colspan="3" style="font-size:25pt"><center>IMPRIMIR FACTURA CLIENTE</center></th>
            </c:if> 
            <c:if test="${dato.cod_esta == 400010}">
            <th colspan="3" style="font-size:25pt"><center>IMPRIMIR RECIBO CLIENTE</center></th>
            </c:if>

        </tr>
        <tr>
            <td width="100%" valign="top">
                <table class="formulario" width="100%">      
                    <tr>
                        <td bgcolor="#F2F2F2" align="right">Numero autorizacion :</td>
                        <td><c:out value = "${numauto}"/></td>            
                    </tr> 
                    <tr>
                        <td bgcolor="#F2F2F2" align="right">Numero Factura :</td>
                        <td><c:out value = "${numfact}"/></td>            
                    </tr>
                    <tr>    
                        <td bgcolor="#F2F2F2" align="right">Nombres Cliente :</td>    
                        <td><input type="text" name="nombres"  value='<c:out value="${nombres}"/>' size="40" maxlength="40" onblur='validar(nombres, "A9")' readonly></td>
                    </tr>
                    <tr>    
                        <td bgcolor="#F2F2F2" align="right">N.I.T./C.I. Cliente :</td>    
                        <td><input type="text" name="ciu"  value='<c:out value="${carnet}"/>' size="20" maxlength="20" onblur='validar(ciu, "9")' readonly></td>
                    </tr>

                    <tr>
                        <td bgcolor="#F2F2F2" align="right"> Monto a Cobrar :  </td>
                        <td><input readonly type="text" name="precio" size="20" maxlength="15" onblur='validar(precio, "9")' value=<c:out value="${precio}"/> ></td>
                    </tr> 
                </table>
            </td>
        </tr>
    </table>
    <center>
        <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
            <tr style="font-size:9pt">
                <th bgcolor="#F2F2F2"> NRO </th>
                <th bgcolor="#F2F2F2"> MEDICAMENTO </th>
                <th bgcolor="#F2F2F2"> Forma Far </th>
                <th bgcolor="#F2F2F2"> Concentra </th>
                <th bgcolor="#F2F2F2"> CANTIDAD </th>
                <th bgcolor="#F2F2F2"> COSTO </th>
                <th bgcolor="#F2F2F2"> TOTAL </th>
            </tr>  
            <c:forEach var="listado" items="${listarKardex}" varStatus="contador">
                <tr style="font-size:9pt">
                    <td align="center"><c:out value="${contador.count}"/></td>
                    <td><c:out value="${listado.medicamento}"/></td>      
                    <td><c:out value="${listado.forma_far}"/></td>
                    <td><c:out value="${listado.concentra}"/></td>
                    <td align="center"><c:out value="${listado.salida}"/></td>           
                    <td><c:out value="${listado.precio_venta}"/></td>                    
                    <td><c:out value="${listado.precio_total}"/></td>                           
                </c:forEach>
        </table>
    </center>
    <center>
        <input type="submit" class="btn btn-primary btn-lg" value='Impresora/Inyeccion' onclick="document.actualizar.accion.value = 'Impresora/Inyeccion'">
        <!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" class="siguiente" value='Impresora/Matricial' onclick="document.actualizar.accion.value='Impresora/Matricial'"> -->
    </center>
    <input type="hidden" name='id_persona'      value='<c:out value="${id_persona}"/>'>
    <input type="hidden" name='id_pedido'       value='<c:out value="${id_pedido}"/>'>    
    <input type="hidden" name='id_costo'        value='<c:out value="${id_costo}"/>'>  
    <input type="hidden" name='id_rubro'        value='<c:out value="${id_rubro}"/>'>  
    <input type="hidden" name='id_paciente'     value='<c:out value="${id_paciente}"/>'>
    <input type="hidden" name='id_reservacion'  value='<c:out value="${id_reservacion}"/>'>    
    <input type="hidden" name='nombres'         value='<c:out value="${nombres}"/>'>
    <input type="hidden" name='nombres2'         value='<c:out value="${nombres2}"/>'>
    <input type="hidden" name='num_cladoc'      value='<c:out value="${num_cladoc}"/>'>
    <input type="hidden" name='nit'             value='<c:out value="${nit}"/>'>
    <input type="hidden" name="accion"          value='Continua'>
</form>