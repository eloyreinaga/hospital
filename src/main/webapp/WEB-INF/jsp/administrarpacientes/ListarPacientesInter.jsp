<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>

<div class="table-responsive">
    <table class="table table-striped table-bordered table-condensed table-responsive">  
        <tr>
        <td valign="top">
        <center> 
            <form name=formaBN method=post action='<c:url value="/fichas"/>'>
                <table class="table table-striped table-bordered table-condensed table-responsive">  
                    <legend align=center style="font-size:10pt"><b><center>Buscar Paciente Internet </center></b> </legend>
                    <tr>
                        <td align=right style="font-size:10pt" ><b>Matricula :</b></td>
                        <td ><input class="form-control" type=text name=nombre value="" size="10" maxlength="10" placeholder="Ej.: 711201RCE..."/></td>
                     </tr>
                     <tr>    
                        <td align=right style="font-size:10pt" ><b>C.I. :</b></td>
                        <td ><input class="form-control" type=text name=carnet value="" size="10" maxlength="10" placeholder="Ej.: 1532668..."/></td>
                     </tr> 
                     <tr>
                        <td></td> 
                        <td ><input type="submit" name=boton class="btn btn-success btn-lg" value="Buscar Paciente"></td>
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
            <th bgcolor="#F2F2F2"> HCL </th>
            <th bgcolor="#F2F2F2"> NOMBRE </th>
            <th bgcolor="#F2F2F2"> Fecha<br>Nacimien.</th>
            <th bgcolor="#F2F2F2"> Sexo </th>
            <th bgcolor="#F2F2F2"> Matricula </th>  
            <th bgcolor="#F2F2F2"> Cod</th>  
            <th bgcolor="#F2F2F2"> C.I.</th> 
            <th bgcolor="#F2F2F2"> Obtener</th>
        </tr>  
        <c:forEach var="lista" items="${listaPacientes}" varStatus="contador">
            <tr style="font-size:9pt">
            <td style="font-size:8pt"><c:out value="${contador.count}"/></td>
            <td style="font-size:8pt"><c:out value="${lista.hcl}"/></td>
            <td style="font-size:8pt"><c:out value="${lista.nombres}"/></td>
            <td><fmt:formatDate value="${lista.fec_nacimiento}" pattern='dd/MM/yyyy'/></td>
            <c:if test="${lista.id_tipo_sexo == '2' }">
                <td align="center" style="font-size:10pt">M</td>
            </c:if>
            <c:if test="${lista.id_tipo_sexo == '1' }">
                <td align="center" style="font-size:10pt">F</td>
            </c:if>
            <c:if test="${fn:length(lista.nro_registro)<5 }">
                <td style="font-size:30pt; color:red"><c:out value="${lista.nro_registro}"/></td> 
            </c:if>
            <c:if test="${fn:length(lista.nro_registro)>5 }">
                <td style="font-size:8pt"><c:out value="${lista.nro_registro}"/></td> 
            </c:if>
            <c:if test="${lista.nro!='0' }">
                <td style="font-size:9pt; color:blue" align="center"><c:out value="${lista.nro}"/></td>  
            </c:if>
            <c:if test="${lista.nro=='0' }">
                <td style="font-size:30pt; color:red" align="center"><c:out value="${lista.nro}"/></td>  
            </c:if>
               <td style="font-size:10pt;"><c:out value="${lista.carnet}"/></td>
            <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/fichas"/>'>
                <td>     
                    <div><a class="btn btn-primary" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Ficha</a></div>
                    <input type="hidden" name="id_paciente"   value='<c:out value="${lista.id_paciente}"/>' >
                    <input type="hidden" name="accion"        value='Ficha'>
                </td>
            </form>
            </tr> 
        </c:forEach>  
    </table>
    </div>    
    </c:if>  

                
    <c:if test="${listaFichas != null}">         
    <div class="table-responsive">    
    <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
        <tr style="font-size:9pt">
            <th bgcolor="#F2F2F2"> No. </th>
            <th bgcolor="#F2F2F2"> Fecha Hora </th>
            <th bgcolor="#F2F2F2"> Matricula </th>  
            <th bgcolor="#F2F2F2"> TIPO</th>
        </tr>  
        <c:forEach var="lista" items="${listaFichas}" varStatus="contador">
            <tr style="font-size:10pt">
            <td style="font-size:10pt"><c:out value="${contador.count}"/></td>
            <td><fmt:formatDate value="${lista.fecha}" pattern='dd/MM/yyyy'/>
                <font style="color:blue"><b><fmt:formatDate value="${lista.fecha}" pattern='HH:mm'/></b></font></td>
            <td style="font-size:10pt;"><c:out value="${lista.nro_registro}"/></td>
            <c:if test="${lista.id_riesgo==0}"> 
                <td style="font-size:10pt;">Ya Atendido</td>
            </c:if>    
            <c:if test="${lista.id_riesgo==1}"> 
                <td style="font-size:10pt; color:red">En espera</td>
            </c:if> 
            </tr> 
        </c:forEach>  
    </table>
    </div>    
    </c:if>
                
</div>
<%@ include file="../Inferior.jsp" %>