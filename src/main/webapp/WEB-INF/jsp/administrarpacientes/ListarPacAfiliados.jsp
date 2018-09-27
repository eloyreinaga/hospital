<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>

<div class="table-responsive">
    <table class="table table-striped table-bordered table-condensed table-responsive">  
        <tr>
        <td valign="top">
        <center> 
            <form name=formaBN method=post action='<c:url value="/ListarAsegurados.do"/>'>
                <table class="table table-striped table-bordered table-condensed table-responsive">  
                    <legend align=center style="font-size:10pt"><b><center>Buscar Asegurados SSCP </center></b> </legend>
                    <tr>
                        <td align=right style="font-size:10pt" class=colh><b>Nombre / CI</b></td>
                        <td colspan="2"><input class="form-control" type=text name=nombre value="<c:out value = "${nombres}"/>" size="50" maxlength="30" ></td>
                        <td coslpan=3><input type="submit" name=boton class="btn btn-success btn-lg" value="Buscar Asegurado"></td>
                    </tr>
                </table>
            </form>
        </center>
        </td>
       </tr> 
    </table>  
                        
    <c:if test="${listaPacientes != null}">  
    <div class="table-responsive">    
    <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
        <tr style="font-size:9pt">
            <th bgcolor="#F2F2F2"> No. </th>
            <th bgcolor="#F2F2F2"> Matricula </th>
            <th bgcolor="#F2F2F2"> NOMBRE </th>
            <th bgcolor="#F2F2F2"> Fecha<br>Nacimien.</th>
            <th bgcolor="#F2F2F2"> Sexo </th>
            <th bgcolor="#F2F2F2"> carnet </th>  
            <th bgcolor="#F2F2F2"> cod SSCP </th>
            <th bgcolor="#F2F2F2"> Seguro</th>  
            <!--<th bgcolor="#F2F2F2"> Obtener</th>-->
        </tr>  
        <c:forEach var="lista" items="${listaPacientes}" varStatus="contador">
            <tr style="font-size:12pt">
            <td style="font-size:12pt"><c:out value="${contador.count}"/></td>
            <td style="font-size:12pt"><c:out value="${lista.nro_registro}"/></td>
            <td style="font-size:12pt"><c:out value="${lista.nombres}"/></td>
            <td><fmt:formatDate value="${lista.fec_nacimiento}" pattern='dd/MM/yyyy'/></td>
            <td style="font-size:12pt"><c:out value="${lista.expedido}"/></td> 
            <td style="font-size:12pt;"><c:out value="${lista.carnet}"/></td> 
            <td style="font-size:12pt;"><c:out value="${lista.nro}"/></td> 
            <td style="font-size:13pt; color:blue"><c:out value="${lista.seguro}"/></td>  
            <!--
            <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/fichas"/>'>
                <td>     
                    <div><a class="btn btn-primary" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Desafiliar</a></div>
                    <input type="hidden" name="id_paciente"   value='<c:out value="${lista.id_paciente}"/>' >
                    <input type="hidden" name="accion"        value='Ficha'>
                </td>
            </form>
            -->
            </tr> 
        </c:forEach>  
    </table>
    </div>    
    </c:if>  
    
</div>
<%@ include file="../Inferior.jsp" %>