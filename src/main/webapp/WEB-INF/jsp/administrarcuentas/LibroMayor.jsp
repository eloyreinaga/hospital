<%@ include file="../Superior.jsp" %>

<jsp:useBean id="now" class="java.util.Date" />
<div class=titulo> Buscar libro por fecha</div>
<br>
<form name="forma" method="POST" action='<c:url value="/LibroMayor.do"/>' >
    <table class="formulario">
        <tr>
            <th colspan="3">CONFIRME LOS DATOS</th>
        </tr>
        <tr>
            <td> Tipo Cuenta </td>
            <td >::
            <td><c:out value="${dato.tipo_cuenta}"/></td>
        </tr>  
        <tr>
            <td> Cuenta </td>
            <td>::
            <td><c:out value="${dato.cuenta}"/></td>
        </tr>        
        <tr>
            <td> Codigo </td>
            <td>::
            <td><c:out value="${dato.codigo}"/></td>
        </tr>        
        <c:if test="${sw == 1}">       
            <c:if test="${dato.id_estado == null}">       
                <tr>
                    <td> Estado </td>
                    <td>::
                    <td>B</td>
                </tr>        
            </c:if>    
            <c:if test="${dato.id_estado == 'A'}">       
                <tr>
                    <td> Estado </td>
                    <td>::
                    <td><c:out value="${dato.id_estado}"/></td>
                </tr>        
            </c:if>          
        </c:if>    
        <c:if test="${sw1 == 1}">       
            <tr>
                <td> Estado </td>
                <td>::
                <td><c:out value="${dato.id_estado}"/></td>
            </tr>        
        </c:if>        
    </table>

    <table class="formulario">
        <tr>
            <th>BUSQUEDA DE DATOS</th>
        </tr>
        <tr>
            <td>
                <fieldset> 
                    <legend>Introduzca Fechas</legend>
                    <table>	     
                        <tr>  
                            <td>Fecha inicio  </td>
                            <td> :: </td>
                            <td>
                                <input type="text" name="valor_1" size="10" value='<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>' >
                                <small><a href="javascript:showCal('valor_1')"><img src="./imagenes/formularios/calendario.jpeg" border="0" ></a></small>
                            </td>
                        </tr>
                        <tr>
                            <td>Fecha final  </td>
                            <td>::</td>  
                            <td>
                                <input type="text" name="valor_2" size="10" value='<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>' readonly>
                                <small><a href="javascript:showCal('valor_2')"><img src="./imagenes/formularios/calendario.jpeg" border="0" ></a></small>
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </td>
        </tr>
    </table>
    <center>
        <input type="submit" name="boton" class="buscar" value="Buscar">
        <input type="hidden" name='id_cuenta' value='<c:out value="${id_cuenta}"/>'>
    </center>
</form>


<%@ include file="../Inferior.jsp" %>