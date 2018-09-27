<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>

<div style="font-size:15pt"> Administraci&oacute;n de Prestaciones Ley475 segun nombre de Prestacion</div>
<br>

<center>
    <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
        <tr>
            <TD>
        <center>
            <form name=formaNom action="<c:url value="/ListarPrestacionSumi.do"/>" method="POST">        
                <table >
                    <tr>    
                        <td align=right>Nombre Prestacion</td>    
                        <td ><input class="form-control" type="text" name="nombresPres"  value="<c:out value="${nombresPres}"/>"  size="50" maxlength=50 onblur='validar = (nombresPres, "A ")'/></td>            
                        <td coslpan=3><input class="btn btn-success" type="submit" name=boton value="BuscarNom"></td>
                    </tr>  
                </table>
                <input type="hidden" name='expedido'        value='<c:out value="${expedido}"/>'> 
                <input type="hidden" name='accion2'        value='<c:out value="${accion2}"/>'> 
            </form> 
        </center>

        <table>
            <tr>
            <form name=formax method=post action='<c:url value="/ListarPrestacionSumi.do"/>'>
                <td colspan="2">
                    <a class="btn btn-primary" href="javascript:document.formax.submit();" >Habilitar Prestacion</a>
                    <input type=hidden name=accion value='Habilitar'>
                </td>
            </form>
            <tr>
        </table>

        </TD>
        </tr>

        <tr>
            <td  valign="top">
                <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
                    <tr style="font-size:9pt">
                        <th> No </th>
                        <th> Codigo </th>    
                        <th> DESCRIPCION </th>            
                        <th> Costo </th> 
                        <th> NIVEL </th>
                        <th> Tipo </th>
                            <c:if test="${(accion != 'Habilitar')}">              
                            <th> MODIFICAR </th>
                            <th> ELIMINAR </th>
                            </c:if>
                            <c:if test="${(accion == 'Habilitar')}">              
                            <th> HABILITAR </th>
                            </c:if>
                    </tr>  
                    <c:forEach var="lista" items="${listarPrestaciones}" varStatus="contador">
                        <tr style="font-size:9pt">
                            <td align="center"><c:out value="${contador.count}"/></td>
                            <td style="font-size:11"><c:out value="${lista.prestacion}"/></td>
                            <c:if test="${(accion == 'Habilitar')}">    
                                <td><font color="blue"><c:out value="${fn:substring(lista.descripcion,0,90)}"/></font></td>         
                                </c:if>     
                                <c:if test="${(accion != 'Habilitar')}">     
                            <form name=formaMod<c:out value="${contador.count}"/> method=post action='<c:url value="/ModificarPaquete.do"/>'>
                                <td>     
                                    <div><a href="javascript:document.formaMod<c:out value="${contador.count}"/>.submit();"> <c:out value="${fn:substring(lista.descripcion,0,90)}"/> </a></div>
                                    <input type="hidden" name="id_prestacion" value=<c:out value="${lista.id_prestacion}"/> >
                                    <input type="hidden" name="accion" value='Prestacion' >
                                    <input type="hidden" name="sw" value='1' >
                                </td>
                            </form>     
                        </c:if>     

                        <td><c:out value="${lista.costo}"/></td> 
                        <td><c:out value="${lista.cadena1}"/></td>
                        <c:if test="${lista.suma1==0}">     
                            <td>Amb</td> 
                        </c:if> 
                        <c:if test="${lista.suma1==1}">     
                            <td>Int</td> 
                        </c:if>  
                        <c:if test="${(accion == 'Habilitar')}"> 
                            <c:if test="${(lista.paquete != 'N2012')}">
                                <form name=formaMH<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarPrestacionSumi.do"/>'>
                                    <td>     
                                        <div><a class="btn btn-success btn-xs" href="javascript:document.formaMH<c:out value="${contador.count}"/>.submit();">Habilitar</a></div>
                                        <input type="hidden" name="id_prestacion" value=<c:out value="${lista.id_prestacion}"/> >
                                        <input type="hidden" name="nombresPres"        value=<c:out value="${nombresPres}"/> >
                                        <input type="hidden" name='accion2'        value='<c:out value="${accion2}"/>'> 
                                        <input type="hidden" name="accion" value='habilita' >
                                        <input type="hidden" name="sw" value='1' >
                                    </td>
                                </form>     
                            </c:if>
                            <c:if test="${(lista.paquete == 'N2012')}">
                                <form name=formaMH<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarPrestacionSumi.do"/>'>
                                    <td>     
                                        <div><a class="btn btn-danger btn-xs" href="javascript:document.formaMH<c:out value="${contador.count}"/>.submit();">Deshabilitar</a></div>
                                        <input type="hidden" name="id_prestacion"    value=<c:out value="${lista.id_prestacion}"/> >
                                        <input type="hidden" name="nombresPres"      value=<c:out value="${nombresPres}"/> >
                                        <input type="hidden" name='accion2'        value='<c:out value="${accion2}"/>'> 
                                        <input type="hidden" name="accion"           value='deshabilita' >
                                        <input type="hidden" name="sw" value='1' >
                                    </td>
                                </form>    
                            </c:if>
                        </c:if>
                        <c:if test="${(accion != 'Habilitar')}">    
                            <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/NuevaPrestacion.do"/>'>
                                <td>     
                                    <div><a class="btn btn-warning btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Modificar</a></div>
                                    <input type="hidden" name="id_prestacion" value=<c:out value="${lista.id_prestacion}"/> >	 
                                    <input type="hidden" name="accion"        value='Modificar' >
                                    <input type="hidden" name="sw"            value='1' >
                                </td>
                            </form>  
                            <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/ConfirmarPrestacion.do"/>'>
                                <td>     
                                    <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();"> Eliminar</a></div>
                                    <input type="hidden" name="id_prestacion" value=<c:out value="${lista.id_prestacion}"/> >
                                    <input type="hidden" name="accion" value='Eliminar' >
                                    <input type="hidden" name="sw1" value='1' >
                                </td>
                            </form>
                        </c:if>        
                    </c:forEach>

                </table>

            </td>
        </tr>

    </table>
</center>




