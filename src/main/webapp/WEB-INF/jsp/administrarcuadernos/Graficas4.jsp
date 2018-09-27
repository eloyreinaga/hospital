<%@ include file="../Superior.jsp" %>
<script language = 'JavaScript' SRC="./validar.js"></script>
<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
<jsp:useBean id="now" class="java.util.Date" />

<script type="text/javascript">
$(function () {
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: 'Poblaci�n de Pacientes  Atendidos Emergencias'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '{point.name}:<b>{point.y}</b>'
                }
            }
        },
        series: [{
                type: 'pie',
                name: 'Pacientes',
                data: [
    <c:forEach var="calidad" items="${grafica1}" varStatus="contador">
                    ['Medicina Interna', <c:out value="${calidad.suma1}"/>],
                    ['Traumatologia', <c:out value="${calidad.suma2}"/>],
                    ['Cirugia General', <c:out value="${calidad.suma3}"/>],
                    ['Neurocirugia', <c:out value="${calidad.suma4}"/>]
    </c:forEach>
                ]
            }]
    });
});
</script>

<form name="forma" method="POST" action='<c:url value="/Graficas.do"/>' >
    <center>
        <table class="table table-striped table-bordered table-condensed table-responsive">
            <tr>
                <th colspan="2"><center>BUSQUEDA DE DATOS APARA GRAFICA</center></th>
            </tr>
            <tr><td><table><tr>
                            <td>Fecha inicio  </td>
                            <td ><SELECT NAME="diai">
                                    <c:forEach var="dias" items="${dias}">
                                        <option value="<c:out value="${dias}"/>" <c:if test="${dias == dia}">selected</c:if>> 
                                            <c:out value="${dias}"/></option></c:forEach></SELECT>
                                    <SELECT NAME="mesi">
                                    <c:forEach var="meses" items="${meses}">
                                        <option value="<c:out value="${meses}"/>" <c:if test="${meses == mes}">selected</c:if>> 
                                            <c:out value="${meses}"/></option></c:forEach></SELECT>
                                    <SELECT NAME="anioi">
                                    <c:forEach var="anios" items="${anios}">
                                        <option value="<c:out value="${anios}"/>" <c:if test="${anios == anio}">selected</c:if>> 
                                            <c:out value="${anios}"/></option></c:forEach></SELECT>
                                    <SELECT NAME="horai">
                                    <c:forEach var="horas" items="${horas}">
                                        <option value="<c:out value="${horas}"/>" <c:if test="${horas == hora}">selected</c:if>> 
                                            <c:out value="${horas}"/></option></c:forEach></SELECT>
                                    <SELECT NAME="minutoi">
                                    <c:forEach var="minutos" items="${minutos}">
                                        <option value="<c:out value="${minutos}"/>" <c:if test="${minutos == minuto}">selected</c:if>> 
                                            <c:out value="${minutos}"/></option></c:forEach></SELECT>
                                    <br><font size="2" color="blue">&nbsp;&nbsp;&nbsp;Dia&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    Mes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A�o&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    Hora&nbsp;&nbsp;&nbsp;&nbsp;Minuto</font>        
                                </td>
                            </tr>
                        </table></td>
                    <td><table><tr>
                                <td>Fecha final  </td>
                                <td ><SELECT NAME="diaf">
                                    <c:forEach var="dias" items="${dias}">
                                        <option value="<c:out value="${dias}"/>" <c:if test="${dias == dia2}">selected</c:if>> 
                                            <c:out value="${dias}"/></option></c:forEach></SELECT>
                                    <SELECT NAME="mesf">
                                    <c:forEach var="meses" items="${meses}">
                                        <option value="<c:out value="${meses}"/>" <c:if test="${meses == mes2}">selected</c:if>> 
                                            <c:out value="${meses}"/></option></c:forEach></SELECT>
                                    <SELECT NAME="aniof">
                                    <c:forEach var="anios" items="${anios}">
                                        <option value="<c:out value="${anios}"/>" <c:if test="${anios == anio2}">selected</c:if>> 
                                            <c:out value="${anios}"/></option></c:forEach></SELECT>
                                    <SELECT NAME="horaf">
                                    <c:forEach var="horas" items="${horas}">
                                        <option value="<c:out value="${horas}"/>" <c:if test="${horas == hora2}">selected</c:if>> 
                                            <c:out value="${horas}"/></option></c:forEach></SELECT>
                                    <SELECT NAME="minutof">
                                    <c:forEach var="minutos" items="${minutos}">
                                        <option value="<c:out value="${minutos}"/>" <c:if test="${minutos == minuto2}">selected</c:if>> 
                                            <c:out value="${minutos}"/></option></c:forEach></SELECT>
                                    <br><font size="2" color="blue">&nbsp;&nbsp;&nbsp;Dia&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    Mes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A�o&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    Hora&nbsp;&nbsp;&nbsp;&nbsp;Minuto</font>        
                                </td>
                            </tr></table>
                    </td></tr>
            </table>
        </center>
        <center>
            <input type="submit" name="boton" class="btn btn-primary" value="Poblacion Atendida">  
            <input type="submit" name="boton" class="btn btn-primary" value="Poblacion Atendida Especialidad">  
        <c:if test="${rol != '36'}">
            <input type="submit" name="boton" class="btn btn-primary" value="Vacunacion">  
        </c:if> 
        <input type="submit" name="boton" class="btn btn-primary" value="Produccion Servicios">
    </center>
</form>

<c:if test="${grafica1 != null}">
    <script src="js/highcharts.js"></script>
    <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</c:if>            

<%@ include file="../Inferior.jsp" %>