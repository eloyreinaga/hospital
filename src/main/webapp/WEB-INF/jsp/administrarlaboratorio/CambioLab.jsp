<%@ include file="../Superior.jsp" %>

<form name="adicionar" method="POST" action='<c:url value="/LabRealizado.do"/>' >
    <table class="table table-striped table-bordered table-condensed table-responsive">
        <tr>
            <th colspan="3" bgcolor="#F2F2F2"><font size=3><center>CAMBIAR DATOS LABORATORIO</center></font></th>
        </tr>
        <tr>    
            <td align="right" bgcolor="#F2F2F2">Nº HCL</td>    
            <td><c:out value = "${hcl}"/></td>
        </tr>  
        <tr>    
            <td align="right" bgcolor="#F2F2F2">Nombres</td>    
            <td><c:out value = "${nombres}"/></td>
        </tr>   
        <tr>
            <td align="right" bgcolor="#F2F2F2">Fecha Realizacion </td>    
            <td><input type="text" name="dia" value="<c:out value="${dia}"/>" maxlength=2 size=2 onblur=validarNota(dia, 1, 31)>-
                <input type="text" name="mes" value="<c:out value="${mes}"/>" maxlength=2 size=2 onblur=validarNota(mes, 1, 12)>-
                <input type="text" name="anio" value="<c:out value="${anio}"/>" maxlength=4 size=4 onblur=validarNota(anio, 1900, <c:out value="${anioy}"/>)' />dd-mm-aaaa
            </td>	                 
        </tr>
        <tr>
            <td align="right" bgcolor="#F2F2F2">Laboratorio (Realizado B,No Realizado A)</td>
            <td><SELECT NAME="id_estado">
                    <OPTION value="B" <c:if test="${id_estado=='B'}">selected</c:if>> 
                            Entregada (B)
                        </option>
                        <OPTION value="A" <c:if test="${id_estado=='A'}">selected</c:if>> 
                            No Entregada (A)
                        </option>
                    </SELECT></td>
            </tr>
            <tr>
                <td align="right" bgcolor="#F2F2F2">Tipo de Afiliacion</td>
                <td><SELECT NAME="expedido">
                        <OPTION value="S" <c:if test="${expedido=='S'}">selected</c:if>> 
                            Ley475(exSumi)(S)
                        </option>
                        <OPTION value="P" <c:if test="${expedido=='P'}">selected</c:if>> 
                            Programa (P)
                        </option>
                        <OPTION value="E" <c:if test="${expedido=='E'}">selected</c:if>> 
                            Externo (E)
                        </option>
                    </SELECT></td>
            </tr>  
        </table>
        <center>
            <input type="submit" name='accion' class="btn btn-danger btn-lg" value='Eliminar'>
            <input type="submit" name='accion' class="btn btn-primary btn-lg" value='Modificar'>
        </center>
        <input type="hidden" name='sw' value='<c:out value="${sw}"/>'>
    <input type="hidden" name=accion value='Modificar'>
    <input type="hidden" name='id_historial' value='<c:out value="${id_historial}"/>'>
    <input type="hidden" name='id_pedido' value='<c:out value="${id_pedido}"/>'>
</form>
