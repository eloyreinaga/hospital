<%@ include file="../Superior.jsp" %>


<div style="font-size:15pt"> Estado de Cotizaciones de Empresas</div>
<br>

<table valign=top border="0" cellspacing="0">
    <tr>   
        <td><form name=formaBN method=post action='<c:url value="/ListarEmpresasPago.do"/>'>
                <table valign=top border="0" cellspacing="0">
                    <tr>
                        <td>  
                            <fieldset>
                                <legend align=center>Datos Empresa</legend>
                                <table width=50% border=0 align=center>
                                    <tr>
                                        <td align=right class=colh>Dato Empresa</td>
                                        <td class=colh>::</td>
                                        <td ><input type=text name=nombre size="30" onblur='validar(nombre, "A9")'></td>
                                        <td coslpan=3><input type="submit" name=boton value="BuscarE"></td>
                                    </tr> 
                                    <tr >
                                        <td colspan="4">
                                            <table>  
                                                <tr>
                                                    <td><input type=radio  name="id_estado" value="C" checked >   </td>
                                                    <td>Codigo Patronal </td>
                                                    <td>::</td>
                                                    <td><input type=radio  name="id_estado" value="N" >   </td>
                                                    <td>Nombre Empresa </td>
                                                    <td>::</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                </table>
            </form></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td><form name=formaBN method=post action='<c:url value="/ListarEmpresasPago.do"/>'>
                <table valign=top border="0" cellspacing="0">
                    <tr>
                        <td>  
                            <fieldset>
                                <legend align=center>Datos Afiliado</legend>
                                <table width=50% border=0 align=center>
                                    <tr>
                                        <td align=right class=colh>Dato Afiliado</td>
                                        <td class=colh>::</td>
                                        <td ><input type=text name=nombre size="30" onblur='validar(nombre, "A9")'></td>
                                        <td coslpan=3><input type="submit" name=boton value="BuscarA"></td>
                                    </tr> 
                                    <tr >
                                        <td colspan="4">
                                            <table>  
                                                <tr>
                                                    <td><input type=radio  name="id_estado" value="C" checked >   </td>
                                                    <td>Codigo Afiliado </td>
                                                    <td>::</td>
                                                    <td><input type=radio  name="id_estado" value="N" >   </td>
                                                    <td>Nombres Afiliado </td>
                                                    <td>::</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                </table>
            </form></td>

    </tr>
</table>

<table class="table table-striped table-bordered table-hover table-condensed table-responsive">
    <tr style="font-size:9pt">
        <th> NRO </th>
        <th> Coigo <br>Patronal </th>   
        <th> RAZON SOCIAL </th>   
        <th> Estado </th>
        <th> NIT </th>
        <th> DIRECCION </th>
        <th> ESTADO </th>
        <th> MORA </th>
        <th> ESTADO<br>PAGOS </th>
    </tr>  
    <c:forEach var="lista" items="${listarEmpresas}" varStatus="contador">
        <tr style="font-size:9pt">
            <td align="center"><c:out value="${contador.count}"/></td>
            <td><c:out value="${lista.cod_patronal}"/></td>  
            <td><c:out value="${lista.empresa}"/></td>    
            <td><c:out value="${lista.id_estado}"/></td>  
            <td><c:out value="${lista.nit}"/></td>    
            <td><c:out value="${lista.zona}"/>-<c:out value="${lista.direccion}"/></td>    
            <td><c:out value="${lista.estado}"/></td>    
            <td><c:out value="${lista.mora}"/></td>   
        <form name=formaMp<c:out value="${contador.count}"/> method=post action='<c:url value="/ConfirmarEmpresa.do"/>'>
            <td>     
                <div class="agregar"><a href="javascript:document.formaMp<c:out value="${contador.count}"/>.submit();">Ver</a></div>
                <input type="hidden" name="id_empresa" value='<c:out value="${lista.id_empresa}"/>' >
                <input type="hidden" name="empresa"    value='<c:out value="${lista.empresa}"/>' >
                <input type="hidden" name="direccion"  value='<c:out value="${lista.direccion}"/>' >
                <input type="hidden" name="nit"        value='<c:out value="${lista.nit}"/>' >
                <input type="hidden" name="accion"     value='Ver Pagos'>
                <input type="hidden" name="sw"         value='1' >
            </td>
        </form>

    </c:forEach>
</table>