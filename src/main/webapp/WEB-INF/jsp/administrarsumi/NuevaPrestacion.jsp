<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>

<c:if test="${accion == 'Modificar'}">
    <div style="font-size:15pt"> Modificando Prestaciones</div>
</c:if>
<c:if test="${accion == 'Adicionar'}">
    <div style="font-size:15pt"> Agregando Pais</div>
</c:if>

<div><a class="btn btn-success" href='ListarPrestacionSumi.do'>Volver</a></div>

<form name="adicionacat" method="POST">
    <center>
        <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
            <tr>
                <th colspan="3"><center>INTRODUZCA LOS DATOS PRESTACION</center></th>
            </tr>

            <tr>
                <td align="right" bgcolor="#F2F2F2"> Prestacion  </td>
                <td><input type="text" name="descripcion" maxlength="200" size="120" value="<c:out value="${buscarPres.descripcion}"/>"></td>
            </tr>       
            <tr>
                <td align="right" bgcolor="#F2F2F2"> Costo  </td>
                <td><input type="text" name="costo" size="20" maxlength="50" value="<c:out value="${buscarPres.costo}"/>"></td>
            </tr>        
            <tr >    
                <td align="right" bgcolor="#F2F2F2">Paquete </td>    	
                <td>
                    <c:if test="${buscarPres.paquete=='N2012'}">
                        <SELECT NAME="paquete">
                            <option value="N2012" selected> HABILITADO </option>
                            <option value="N20XXX" > Deshabilitado</option>
                        </c:if>
                        <c:if test="${buscarPres.paquete!='N2012'}">
                            <SELECT NAME="paquete">
                                <option value="N20XXX" selected> DESHABILITADO </option>
                                <option value="N2012" > Habilitado </option>
                            </c:if>
                            </SELEC          
                            </td>
                            </tr>
                            <tr >    
                                <td align="right" bgcolor="#F2F2F2">Internado </td>    	
                                <td>
                                    <c:if test="${buscarPres.suma1=='0'}">
                                        <SELECT NAME="internado">
                                            <option value="0" selected> Ambulatorio </option>
                                            <option value="1" > Internado</option>
                                        </c:if>
                                        <c:if test="${buscarPres.suma1=='1'}">
                                            <SELECT NAME="internado">
                                                <option value="1" selected> Internado </option>
                                                <option value="0" > Ambulatorio </option>
                                            </c:if>
                                            </SELEC          
                                            </td>
                                            </tr>
                                            <tr >    
                                                <td align="right">Mas 1 vez </td>    	
                                                <td>
                                                    <c:if test="${buscarPres.suma2=='0'}">
                                                        <SELECT NAME="veces">
                                                            <option value="0" selected> NO </option>
                                                            <option value="1" > Si</option>
                                                        </c:if>
                                                        <c:if test="${buscarPres.suma2!='0'}">
                                                            <SELECT NAME="veces">
                                                                <option value="1" selected> SI </option>
                                                                <option value="0" > No </option>
                                                            </c:if>
                                                            </SELEC          
                                                            </td>
                                                            </tr>    
                                                            <tr>
                                                                <td align="right" bgcolor="#F2F2F2"> Nivel  </td>
                                                                <td><c:out value="${buscarPres.cadena1}"/></td>
                                                            </tr> 
                                                            </table>
                                                            </center>
                                                            <center>
                                                                <input type="submit" class="btn btn-primary" value='Siguiente' onclick="document.adicionacat.accion1.value = 'Guardar';
                                                                        document.adicionacat.action = '<c:url value="/ConfirmarPrestacion.do"/>'">
                                                            </center>
                                                            <input type="hidden" name='accion1' value=''>
                                                            <input type="hidden" name='id_prestacion' value='<c:out value="${id_prestacion}"/>'>    
                                                            <input type="hidden" name='sw' value='<c:out value="${sw}"/>'>    
                                                            <input type="hidden" name='accion' value='<c:out value="${accion}"/>'>
                                                            <input type="hidden" name='id_prestacion' value='<c:out value="${buscarPres.id_prestacion}"/>'>
                                                            <input type="hidden" name='recargado' value='Si'>
                                                            </form>
