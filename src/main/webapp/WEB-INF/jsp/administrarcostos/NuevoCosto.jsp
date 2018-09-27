<%@ include file="../Superior.jsp" %>


<div><a  class="btn btn-success" href='ListarCostos.do'>Volver</a></div>

<form name="adicionacat" method="POST">
    <center>
        <table class="table table-striped table-bordered table-condensed table-responsive">
            <tr>
                <th colspan="5"  bgcolor="#F2F2F2"><center>INTRODUZCA DATOS &nbsp;&nbsp;<c:out value="${rubro}"/></center></th>
            </tr>

            <tr>
                <td  align="right" bgcolor="#F2F2F2"> Nombre Laboratorio/Costo </td>
                <td><input type="text" name="costo" maxlength="50" size="50" value="<c:out value="${buscarCosto.costo}"/>"></td>
                <td  align="right" bgcolor="#F2F2F2"> Costo_unit </td>
                <td ><input type="text" name="costo_unit" size="20" maxlength="20" value="<c:out value="${buscarCosto.costo_unit}"/>" onblur='validar(costo_unit, "9")'></td>
            </tr>       
            <c:if test="${id_rubro=='6'}">       
                <tr>
                    <td  align="right" bgcolor="#F2F2F2">Tipo Seguro </td>
                    <td ><SELECT NAME="id_estado">
                            <c:forEach var="lseguro" items="${lseguro}">
                                <option value="<c:out value="${lseguro}"/>" <c:if test="${lseguro == buscarCosto.id_estado}">selected</c:if>> 
                                    <c:out value="${lseguro}"/>
                                </option>  
                            </c:forEach>
                        </SELECT>
                        <c:if test="${id_color=='0'}">
                            <SELECT NAME="id_color">
                                <option value="0" > Color Negro </option>
                                <option value="1" > Color Rojo</option>
                                <option value="2" > Color Azul</option>
                                <option value="3" > Color Verde</option>
                            </SELECT>    
                        </c:if>
                        <c:if test="${id_color=='1'}">
                            <SELECT NAME="id_color">
                                <option value="1" > Color Rojo</option>
                                <option value="2" > Color Azul</option>
                                <option value="3" > Color Verde</option>
                                <option value="0" > Color Negro </option>
                            </SELECT>    
                        </c:if>
                        <c:if test="${id_color=='2'}">
                            <SELECT NAME="id_color">
                                <option value="2" > Color Azul</option>
                                <option value="3" > Color Verde</option>
                                <option value="0" > Color Negro </option>
                                <option value="1" > Color Rojo</option>
                            </SELECT>    
                        </c:if>
                        <c:if test="${id_color=='3'}">
                            <SELECT NAME="id_color">
                                <option value="3" > Color Verde</option>
                                <option value="0" > Color Negro </option>
                                <option value="1" > Color Rojo</option>
                                <option value="2" > Color Azul</option>
                            </SELECT>    
                        </c:if>
                    </td>
                    <td  align="right" bgcolor="#F2F2F2">Prestacion </td>
                    <td ><SELECT NAME="id_prestacion">
                            <option value="0">--Elegir--</option>    
                            <c:forEach var="ped" items="${listarPrestaciones}">
                                <option value="<c:out value="${ped.id_prestacion}"/>" <c:if test="${ped.id_prestacion == id_prestacion}">selected</c:if>> 
                                    <c:out value="${ped.prestacion}"/>&nbsp;:&nbsp;<c:out value="${fn:substring(ped.descripcion,0,25)}"/>
                                </option>
                            </c:forEach>
                        </SELECT>	
                    </td>
                </tr> 
                <tr>
                    <td  align="right" bgcolor="#F2F2F2">Laboratorio </td>
                    <td>
                        <SELECT NAME="id_laboratorio">
                            <c:forEach var="lab" items="${listarLaboratorios}">
                                <option value="<c:out value="${lab.id_laboratorio}"/>" <c:if test="${lab.id_laboratorio == buscarCosto.id_laboratorio}">selected</c:if>> 
                                    <c:out value="${lab.laboratorio}"/>
                                </option>
                            </c:forEach>
                        </SELECT>	
                    </td>

                    <td  align="right" bgcolor="#F2F2F2">Muestra en </td>	      
                    <td><SELECT NAME="muestra">
                            <c:forEach var="lmuestra" items="${listarmuestra}">
                                <option value="<c:out value="${lmuestra}"/>" <c:if test="${lmuestra == buscarCosto.muestra}">selected</c:if>> 
                                    <c:out value="${lmuestra}"/>
                                </option>  
                            </c:forEach>
                        </SELECT></td>
                </tr> 
                <c:if test="${cod_esta=='200010'}"> 
                    <tr>
                        <td  align="right" bgcolor="#F2F2F2">Tipo Laboratorio </td>	      
                        <td>
                            <c:if test="${id_urgencias=='0'}">
                                <SELECT NAME="urgencias">
                                    <option value="0" > Laboratorio Normal </option>
                                    <option value="1" > Laboratorio Urgencias</option>
                                </SELECT>    
                            </c:if>
                            <c:if test="${id_urgencias=='1'}">
                                <SELECT NAME="urgencias">
                                    <option value="1" > Laboratorio Urgencias</option>
                                    <option value="0" > Laboratorio Normal </option>
                                </SELECT>    
                            </c:if>
                        </td>
                    </tr>
                </c:if>    
                <tr>    
                    <td  align="right" bgcolor="#F2F2F2"> Valores Normales  </td>
                    <td><input type="text" name="normales" size="50" maxlength="50" value="<c:out value="${buscarCosto.normales}"/>"></td>   
                    <td  align="right" bgcolor="#F2F2F2"> Reactivo  </td>
                    <td><input type="text" name="reactivo" size="20" maxlength="20" value="<c:out value="${buscarCosto.normales}"/>"></td>   
                </tr>

                <tr>    
                    <td  align="right" bgcolor="#F2F2F2"> Valores po Defecto  </td>
                    <td colspan=3><textarea name="defecto" rows="1" cols="20" style="width: 100%" ><c:out value = "${fn:trim(buscarCosto.defecto)}" escapeXml="False"/></textarea></td>
                </tr>
            </c:if>
        </table>
    </center>   
    <center>
        <input type="submit"  class="btn btn-primary btn-lg" value='Siguiente' onclick="document.adicionacat.accion1.value = 'Guardar';
                document.adicionacat.action = '<c:url value="/ConfirmarCosto.do"/>'">
    </center>
    <input type="hidden" name='accion1'  value=''>
    <input type="hidden" name='id_rubro' value='<c:out value="${id_rubro}"/>'>  
    <input type="hidden" name='rubro'    value='<c:out value="${rubro}"/>'>   
    <input type="hidden" name='id_costo' value='<c:out value="${id_costo}"/>'>    
    <input type="hidden" name='sw'       value='<c:out value="${sw}"/>'>    
    <input type="hidden" name='accion'   value='<c:out value="${accion}"/>'>
    <input type="hidden" name='id_costo' value='<c:out value="${buscarCosto.id_costo}"/>'>
    <input type="hidden" name='recargado' value='Si'>
</form>


<c:if test="${id_rubro=='6'}">    
    <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
        <tr style="font-size:9pt">
            <th colspan="12" style="font-size: 14pt;" bgcolor="#F2F2F2"><center><c:out value="${rubro}"/></center></th>
</tr>
<tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> NRO </th>
    <th bgcolor="#F2F2F2"> ID </th>
    <th bgcolor="#F2F2F2"> TIPO </th>
    <th bgcolor="#F2F2F2"> DESCRIPCION </th>
    <th bgcolor="#F2F2F2"> SEGURO </th>
    <th bgcolor="#F2F2F2"> COSTO </th>
    <th bgcolor="#F2F2F2"> NORMALES </th>
    <th bgcolor="#F2F2F2"> DEFECTO </th>
    <th bgcolor="#F2F2F2"> MUESTRA </th>    
    <th bgcolor="#F2F2F2"> IdPre </th>
    <th bgcolor="#F2F2F2"> MODIFICAR </th>
    <th bgcolor="#F2F2F2"> ELIMINAR </th> 
</tr>  
<c:forEach var="lista" items="${listarCostosRubro}" varStatus="contador">
    <c:if test="${lista.id_rubro==id_lab}">   
        <tr style="font-size:9pt">
            <td align="center"><c:out value="${contador.count}"/></td>
            <td><c:out value="${lista.id_costo}"/></td>
            <c:forEach var="listaL" items="${listarLaboratorios}" >
                <c:if test="${listaL.id_laboratorio==lista.id_laboratorio}">
                    <td style="color:blue"><c:out value="${listaL.laboratorio}"/></td>   
                </c:if> 
            </c:forEach>
            <c:if test="${lista.emergencias==0}">
                <td><c:out value="${lista.costo}"/></td>
            </c:if>
            <c:if test="${lista.emergencias==1}">
                <td style="color:red"><c:out value="${lista.costo}"/></td>
            </c:if>
            <c:if test="${lista.id_estado=='A'}">
                <td align="center"><c:out value="${lista.id_estado}"/></td>
            </c:if>
            <c:if test="${lista.id_estado=='S'}">
                <td align="center" style="color:red"><c:out value="${lista.id_estado}"/></td>
            </c:if>
            <td><c:out value="${lista.costo_unit}"/></td>    
            <td><c:out value="${lista.normales}"/></td>    
            <td><c:out value="${lista.defecto}"/></td>
            <td><c:out value="${lista.muestra}"/></td>    
            <td><c:out value="${lista.id_prestacion}"/></td>
        <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/NuevoCosto.do"/>'>
            <td>     
                <div><a class="btn btn-warning btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Modificar</a></div>
                <input type="hidden" name="rubro"          value='<c:out value="${lista.rubro}"/>' >
                <input type="hidden" name="id_costo"       value='<c:out value="${lista.id_costo}"/>' >
                <input type="hidden" name="id_rubro"       value='<c:out value="${lista.id_rubro}"/>' >
                <input type="hidden" name="id_laboratorio" value='<c:out value="${lista.id_paciente}"/>' >
                <input type="hidden" name="normales"       value='<c:out value="${lista.normales}"/>' >
                <input type="hidden" name="accion"         value='Modificar' >
                <input type="hidden" name="sw"             value='1' >
            </td>
        </form>
        <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/ConfirmarCosto.do"/>'>
            <td>     
                <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();"> Eliminar</a></div>
                <input type="hidden" name="id_costo"   value='<c:out value="${lista.id_costo}"/>' >
                <input type="hidden" name="accion"     value='Eliminar' >
                <input type="hidden" name="sw1"        value='1' >
            </td>
        </form>
    </c:if>  
</c:forEach>
</table>
</c:if>

<c:if test="${(id_rubro!='6')}"> 
    <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
        <tr>
            <th colspan="11" style="font-size: 14pt;"><c:out value="${rubro}"/></th>
        </tr>
        <tr style="font-size:9pt">
            <th bgcolor="#F2F2F2"> NRO </th>
            <th bgcolor="#F2F2F2"> RUBRO </th>
            <th bgcolor="#F2F2F2"> ID </th>
            <th bgcolor="#F2F2F2"> DESCRIPCION </th>
            <th bgcolor="#F2F2F2"> COSTO </th>
            <th bgcolor="#F2F2F2"> MODIFICAR </th>
            <th bgcolor="#F2F2F2"> ELIMINAR </th> 
        </tr>  
        <c:forEach var="lista" items="${listarCostosRubro}" varStatus="contador">
            <c:if test="${lista.id_rubro==id_lab}">   
                <tr style="font-size:9pt">
                    <td align="center"><c:out value="${contador.count}"/></td>
                    <td><c:out value="${lista.rubro}"/></td>
                    <td><c:out value="${lista.id_costo}"/></td>
                    <td><c:out value="${lista.costo}"/></td>      
                    <td><c:out value="${lista.costo_unit}"/></td>    
                <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/NuevoCosto.do"/>'>
                    <td>     
                        <div><a class="btn btn-warning btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Modificar</a></div>
                        <input type="hidden" name="rubro"          value='<c:out value="${lista.rubro}"/>' >
                        <input type="hidden" name="id_costo"       value='<c:out value="${lista.id_costo}"/>' >
                        <input type="hidden" name="id_rubro"       value='<c:out value="${lista.id_rubro}"/>' >
                        <input type="hidden" name="id_laboratorio" value='<c:out value="${lista.id_paciente}"/>' >
                        <input type="hidden" name="normales"       value='<c:out value="${lista.normales}"/>' >
                        <input type="hidden" name="accion"         value='Modificar' >
                        <input type="hidden" name="sw"             value='1' >
                    </td>
                </form>
                <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/ConfirmarCosto.do"/>'>
                    <td>     
                        <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();"> Eliminar</a></div>
                        <input type="hidden" name="id_costo"   value='<c:out value="${lista.id_costo}"/>' >
                        <input type="hidden" name="accion"     value='Eliminar' >
                        <input type="hidden" name="sw1"        value='1' >
                    </td>
                </form>
            </tr> 
        </c:if>  
    </c:forEach>
</table>
</c:if>