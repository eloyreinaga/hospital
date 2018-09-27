<%@ include file="../Superior.jsp" %>

<jsp:useBean id="now" class="java.util.Date" />


<c:if test="${accion == 'Mostrar'}">
    <div style="font-size:15pt"> Listado de Reportes Mensiales Odontologia</div>
</c:if>
<c:if test="${accion == 'Adicionar'}">
    <div style="font-size:15pt"> Agregando Nuevo Informe Mensual Odontologia</div>
</c:if>
<br>
<table>
    <tr>
    <form name=formanuevo method=post action='<c:url value="/ReporteOdonMensual.do"/>'>
        <td colspan="2">
            <div class="agregar">
                <a href="javascript:document.formanuevo.submit();" >Nuevo</a>
                <input type=hidden name=accion value='Adicionar'>
            </div></td>
    </form>
    <tr>
</table>

<table class="tabla">
    <tr>
        <th> MES </th>
        <th> GESTION </th>    
        <th> MODIFICAR </th>
            <c:if test="${accion == 'Mostrar'}">
            <th> MOSTRAR </th> 
            </c:if>

    </tr>  
    <c:forEach var="lista" items="${listarImm}" varStatus="contador">
        <tr style="font-size:9pt">
            <c:if test="${lista.mes == 1 }">   <td align="left"center">Enero</td>   </c:if>  
            <c:if test="${lista.mes == 2 }">   <td align="left">Febrero</td>   </c:if>  
            <c:if test="${lista.mes == 3 }">   <td align="left">Marzo</td>   </c:if>  
            <c:if test="${lista.mes == 4 }">   <td align="left">Abril</td>   </c:if>  
            <c:if test="${lista.mes == 5 }">   <td align="left">Mayo</td>   </c:if>  
            <c:if test="${lista.mes == 6 }">   <td align="left">Junio</td>   </c:if>  
            <c:if test="${lista.mes == 7 }">   <td align="left">Julio</td>   </c:if>  
            <c:if test="${lista.mes == 8 }">   <td align="left">Agosto</td>   </c:if>  
            <c:if test="${lista.mes == 9 }">   <td align="left">Septiembre</td>   </c:if>  
            <c:if test="${lista.mes == 10 }">   <td align="left">Octubre</td>   </c:if>  
            <c:if test="${lista.mes == 11 }">   <td align="left">Noviembre</td>   </c:if>  
            <c:if test="${lista.mes == 12 }">   <td align="left">Diciembre</td>   </c:if>  

                <td><c:out value="${lista.gestion}"/></td>    

        <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/ReporteOdonMensual.do"/>'>
            <td>     
                <div><a class="btn btn-warning btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Actualizar</a></div>
                <input type="hidden" name="mes" value=<c:out value="${lista.mes}"/> >
                <input type="hidden" name="gestion" value=<c:out value="${lista.gestion}"/> >         
                <input type="hidden" name="accion" value='Modificar' >
                <input type="hidden" name="sw" value='1' >
            </td>
        </form>
        <c:if test="${accion == 'Mostrar'}">

            <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/ReporteOdonMensual.do"/>'>
                <td>     
                    <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();"> Ver Datos</a></div>
                    <input type="hidden" name="mes" value=<c:out value="${lista.mes}"/> >
                    <input type="hidden" name="gestion" value=<c:out value="${lista.gestion}"/> >         
                    <input type="hidden" name="accion" value='Mostrar' >
                    <input type="hidden" name="sw1" value='1' >
                </td>
            </form>
        </c:if>
    </c:forEach>
</table>

