<%@ include file="../Superior.jsp" %>


<div style="font-size:15pt"> Administraci&oacute;n de Asegurados por Empresas</div>
<br>

<table class="formulario">
    <tr>
        <th colspan="3">DATOS DE LA FAMILIA </th>
    </tr>
    <tr>
        <td width="50%" valign="top">
            <table class="formulario" width="100%">
                <tr>
                    <th colspan="3"> JEFE DE FAMILIA </th>
                </tr>    
                <tr>    
                    <td>Numero de Carpeta</td>    
                    <td>::</td>	      
                    <td ><c:out value = "${id_carpeta}"/></td>
                </tr> 
                <tr>    
                    <td>Sector</td>    
                    <td>::</td>	      
                    <td ><c:out value = "${Carpeta.carpeta}"/></td>
                </tr> 
                <tr>
                    <td>HCL</td>
                    <td>::</td>
                    <td><c:out value = "${datos.hcl}"/></td>
                </tr>

                <tr>    
                    <td>Nombre Completo</td>    
                    <td>::</td>
                    <td><c:out value = "${datos.nombre}"/>&nbsp;&nbsp;<c:out value = "${datos.paterno}"/>&nbsp;&nbsp;<c:out value = "${datos.materno}"/></td>
                </tr>
                <tr>    
                    <td>Direcci&oacute;n</td>    
                    <td>::</td>	      
                    <td><c:out value = "${datos.direccion}"/></td>
                </tr> 
                <c:if test="${fn:length(datos.factor_riesgo)>2 }">
                    <tr>    
                        <td>Factores de Riesgo</td>    
                        <td>::</td>	      
                        <td style="font-size:20pt;color:red"><c:out value = "${datos.factor_riesgo}"/></td>
                    </tr>  
                </c:if>
            </table>
        </td>
        <!-- ********** Empieza otra Tabla ************ -->
        <td width="50%" valign="top">
            <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
                <tr style="font-size:9pt">
                    <th colspan="4"> COMPONENTES DE LA FAMILIA </th>
                </tr>    
                <tr>
                    <th> HCL </th>
                    <th> NOMBRE </th>
                    <th> FECHA NACIM </th>                
                    <th> PARENTESCO </th>                
                </tr>  
                <c:forEach var="lista" items="${listaPacientesD}" varStatus="contador">
                    <tr style="font-size:9pt">
                        <td style="font-size: 10pt;"><c:out value="${lista.hcl}"/></td>  
                    <form name=formaRd<c:out value="${contador.count}"/> method=post action='<c:url value="/ConfirmarPaciente.do"/>'>
                        <td>     
                            <div class="aceptar"><a href="javascript:document.formaRd<c:out value="${contador.count}"/>.submit();"><c:out value="${lista.nombres}"/></a></div>
                            <input type="hidden" name="id_paciente" value=<c:out value="${lista.id_paciente}"/> >
                            <input type="hidden" name="accion" value='Reservar' >
                            <input type="hidden" name="sw" value='1' >
                            <input type="hidden" name="id_pacientej" value=<c:out value="${id_pacientej}"/> >   
                            <input type="hidden" name="id_carpeta" value=<c:out value="${id_carpeta}"/> >         
                        </td>
                    </form>
                    <td style="font-size: 10pt;" align="center"><fmt:formatDate value="${lista.fec_nacimiento}" pattern='dd/MM/yyyy'/></td>
                    <td style="font-size: 10pt;"><c:out value="${lista.tipo}"/></td>       
                </c:forEach> 
            </table>
        </td>

    </tr>
</table>

<table class="formulario">
    <tr>
        <th colspan="3">AFILIACIONES A EMPRESAS</th>
    </tr>
    <tr>
        <td width="50%" valign="top">

            <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
                <tr style="font-size:9pt">
                    <th> Nro.</th>
                    <th> Fecha <br>Registro</th>
                    <th> EMPRESA </th> 
                    <th> NIT </th>
                    <th> Estado </th>                
                    <th> Tipo </th>                
                    <th> Fecha <br>Baja</th>
                    <th> Eliminar </th>                
                </tr>  
                <c:forEach var="lista" items="${listaEmpresa}" varStatus="contador">
                    <tr style="font-size:9pt">
                        <td align="center"><c:out value="${contador.count}"/></td>
                        <td><fmt:formatDate value="${lista.fec_registro}" pattern='dd/MM/yyyy'/></td>
                        <td><c:out value="${lista.empresa}"/></td>
                        <td><c:out value="${lista.nit}"/></td>
                        <td><c:out value="${lista.id_estado}"/></td>
                        <td><c:out value="${lista.tipo}"/></td>
                        <td><fmt:formatDate value="${lista.fec_baja}" pattern='dd/MM/yyyy'/></td>      
                    <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarPacientesD.do"/>'>
                        <td>     
                            <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();"> Eliminar</a></div>
                            <input type="hidden" name="id_empresa" value=<c:out value="${lista.id_empresa}"/> >
                            <input type="hidden" name="id_carpeta" value=<c:out value="${id_carpeta}"/> >    
                            <input type="hidden" name="id_pacientej" value=<c:out value="${id_pacientej}"/> >                  
                            <input type="hidden" name="accion" value='EliminarEmpresa' >
                            <input type="hidden" name="sw1" value='1' >
                        </td>
                    </form>
                </c:forEach> 
            </table>
        </td>
        <td width="50%" valign="top">
            <form name=formaBEmp method=post action='<c:url value="/ListarPacientesD.do"/>'>
                <table valign=top border="0" cellspacing="0">
                    <tr>
                        <td>  
                            <fieldset>
                                <legend align=center>Nombre de Empresa</legend>
                                <table width=50% border=0 align=center>
                                    <tr>
                                        <td align=right class=colh>Nonbre Empresa
                                        <td class=colh>::
                                        <td ><input type=text name=empresa onblur='validar(nombre, "A")'>
                                        <td coslpan=3><input type="submit" name=boton value="Buscar"></td>
                                    </tr>  
                                </table>
                            </fieldset>
                        </td>
                        </td>
                    </tr>
                </table>
                <input type="hidden" name="id_empresa" value=<c:out value="${id_paciente}"/> >  
                <input type="hidden" name="id_carpeta" value=<c:out value="${id_carpeta}"/> >       
            </form>

            <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
                <tr style="font-size:9pt">
                    <th> Patronal </th>
                    <th> EMPRESA </th>
                    <th> NIT </th>                        
                    <th> AGREGAR </th> 
                </tr>  
                <c:forEach var="listaE" items="${listaEmp}" varStatus="contador">
                    <tr style="font-size:9pt">
                        <td><c:out value="${listaE.cod_patronal}"/></td>   
                        <td><c:out value="${listaE.empresa}"/></td>         
                        <td><c:out value="${listaE.nit}"/></td>         
                    <form name=formaAE<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarPacientesD.do"/>'>
                        <td>     
                            <div class="agregar"><a href="javascript:document.formaAE<c:out value="${contador.count}"/>.submit();"> Agregar</a></div>
                            <input type="hidden" name="id_empresa" value=<c:out value="${listaE.id_empresa}"/> >
                            <input type="hidden" name="id_carpeta" value=<c:out value="${id_carpeta}"/> > 
                            <input type="hidden" name="accion" value='AgregarEmpresa' >
                            <input type="hidden" name="sw1" value='1' >
                        </td>
                    </form>
                </c:forEach>
            </table>

        </td>
    </tr>

</table>




