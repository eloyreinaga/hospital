<%@ include file="../Superior.jsp" %>

<jsp:useBean id="now" class="java.util.Date" />

<div><a class="btn btn-success" href='ListarLibros.do'>Volver</a></div>

<form name="adicionacat" method="POST">
    <center>
        <table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
            <tr>
                <th colspan="3" bgcolor="#F2F2F2"><center>INTRODUZCA LOS DATOS ACTIVIDADES</center></th>
            </tr>
            <tr>  
                <td align="right" bgcolor="#F2F2F2">Fecha de Actividad  </td>
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
                        <br><font size="2" color="blue">&nbsp;&nbsp;&nbsp;Dia&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        Mes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Año</font>        
                    </td>
                </tr>
                <tr >
                    <td align="right" bgcolor="#F2F2F2">Actividad::  </td>	      
                    <td>
                        <SELECT NAME="actividad">
                            <option value="1">ACTIVIDAD EDUCATIVA </option>
                            <option value="2">ACTIVIDAD DE LA COMUNIDAD CON PARTICIPACIÓN DEL ESTABLECIMIENTO </option>
                            <option value="3">SUPERVISIÓN RECIBIDA </option>
                            <option value="4">CAPACITACIÓN RECIBIDA </option>
                            <option value="5">REUNIÓN CON EL COMITÉ LOC. DE SALUD REALIZADA </option>
                            <option value="6">REUNIÓN CON AUTORIDADES LOCALES DE SALUD REALIZADA </option>
                            <option value="7">FERIA REALIZADA </option>
                            <option value="8">REUNIÓN DEL CAI REALIZADA </option>
                            <option value="9">TEMA DE LA ACTIVIDAD EDUCATIVA </option>
                            <option value="10">REUNIONES, FERIAS Y ACTIVIDADES EDUCATIVAS-LUGAR </option>
                    </td>
                </tr> 
                <tr >
                    <td align="right" bgcolor="#F2F2F2">Participantes::  </td>	      
                    <td>
                        <SELECT NAME="actnumero">
                            <option value="0">-- seleccione --</option>
                            <option value="1">Nº DE VISITAS FAMILIARES REALIZADAS</option>
                            <option value="2">Nº DE FAMILIAS NUEVAS CARPETIZADAS</option>
                            <option value="3">Nº DE FAMILIAS CARPETIZADAS CON SEGUIMIENTO </option>
                            <option value="4">Nº DE PARTICIPANTES Y TIPO DE PERSONAL-MEDICOS </option>
                            <option value="5">Nº DE PARTICIPANTES Y TIPO DE PERSONAL-ENFERMERAS </option>
                            <option value="6">Nº DE PARTICIPANTES Y TIPO DE PERSONAL-AUXILIAR </option>
                            <option value="7">Nº DE PARTICIPANTES Y TIPO DE PERSONAL-ODONTOLOGO </option>
                            <option value="8">Nº DE PARTICIPANTES Y TIPO DE PERSONAL-OTRO </option>
                            <option value="9">Nº DE COMUNIDADES QUE PARTICIPARON EN EL CAI </option>
                            <option value="10">Nº DE PARTICIPANTES Y TIPO DE PERSONAL-ESCOLARES </option>
                            <option value="11">Nº DE PARTICIPANTES Y TIPO DE PERSONAL-JÓVENES </option>
                            <option value="12">Nº DE PARTICIPANTES Y TIPO DE PERSONAL-ADULTOS </option>
                            <option value="13">Nº DE PARTICIPANTES Y TIPO DE PERSONAL-DIRIGENTES </option>
                            <option value="14">Nº DE PARTICIPANTES Y TIPO DE PERSONAL-PROMOTORES </option>
                            <option value="15">Nº DE PARTICIPANTES Y TIPO DE PERSONAL-OTROS </option>
                            <option value="16">Nº DE VISITAS FAMILIARES PLANIFICADAS </option>
                            <option value="17">Nº DE SUGERENCIAS Y AGRADECIMIENTOS POR USUARIOS </option>
                            <option value="18">Nº DE QUEJAS Y RECLAMOS POR USUARIOS </option>
                            <option value="19">Nº DE AUTOEVALUACIONES PARA ACREDITACION </option>
                            <option value="20">Nº DE AUDITORIAS INTERNAS EN SALUD </option>
                        </SELECT>
                        Num. 
                        <SELECT NAME="numero">
                            <option value="0">-- 0 --</option>
                            <option value="1"> 1 </option>
                            <option value="2"> 2 </option>
                            <option value="3"> 3 </option>
                            <option value="4"> 4 </option>
                            <option value="5"> 5 </option>
                            <option value="6"> 6 </option>
                            <option value="7"> 7 </option>
                            <option value="8"> 8 </option>
                            <option value="9"> 9 </option>
                            <option value="10"> 10 </option>
                            <option value="11"> 11 </option>
                            <option value="12"> 12 </option>
                            <option value="13"> 13 </option>
                            <option value="14"> 14 </option>
                            <option value="15"> 15 </option>
                            <option value="16"> 16 </option>
                            <option value="17"> 17 </option>
                            <option value="18"> 18 </option>
                            <option value="19"> 19 </option>
                            <option value="20"> 20 </option>
                            <option value="21"> 21 </option>
                            <option value="22"> 22 </option>
                            <option value="23"> 23 </option>
                            <option value="24"> 24 </option>
                            <option value="25"> 25 </option>
                            <option value="26"> 26 </option>
                            <option value="27"> 27 </option>
                            <option value="28"> 28 </option>
                            <option value="29"> 29 </option>
                            <option value="30"> 30 </option>
                            <option value="35"> 35 </option>
                            <option value="40"> 40 </option>
                            <option value="45"> 45 </option>
                            <option value="50"> 50 </option>
                            <option value="60"> 60 </option>
                            <option value="70"> 70 </option>
                            <option value="80"> 80 </option>
                            <option value="90"> 90 </option>
                            <option value="100"> 100 </option>
                            <option value="110"> 110 </option>
                            <option value="120"> 120 </option>
                            <option value="130"> 130 </option>
                            <option value="140"> 140 </option>
                            <option value="150"> 150 </option>
                            <option value="160"> 160 </option>
                            <option value="170"> 170 </option>
                            <option value="180"> 180 </option>
                            <option value="190"> 190 </option>
                            <option value="200"> 200 </option>
                            <option value="250"> 250 </option>
                            <option value="300"> 300 </option>
                            <option value="350"> 350 </option>
                            <option value="400"> 400 </option>
                            <option value="450"> 450 </option>
                            <option value="500"> 500 </option>
                        </SELECT>	
                    </td>
                </tr>  
                <tr>
                    <td align="right" bgcolor="#F2F2F2"> Tema Actividad </td>
                    <td><input type="text" name="tema" value="" maxlength="200" size="70" /></td>
                </tr>  
                <tr>    
                    <td align="right" bgcolor="#F2F2F2">Lugar   </td>    
                    <td><input type="text" name="lugar" value=""  maxlength="200" size="70" /></td>
                </tr>    
                <tr>
                    <td align="right" bgcolor="#F2F2F2"> Duracion  </td>
                    <td><SELECT NAME="duracion">
                            <option value="0">-- 0 --</option>
                            <option value="1"> 1 </option>
                            <option value="2"> 2 </option>
                            <option value="3"> 3 </option>
                            <option value="4"> 4 </option>
                            <option value="5"> 5 </option>
                            <option value="6"> 6 </option>
                            <option value="7"> 7 </option>
                            <option value="8"> 8 </option>
                            <option value="9"> 9 </option>
                            <option value="10"> 10 </option>
                            <option value="11"> 11 </option>
                            <option value="12"> 12 </option>
                            <option value="13"> 13 </option>
                            <option value="14"> 14 </option>
                            <option value="15"> 15 </option>
                            <option value="16"> 16 </option>
                            <option value="17"> 17 </option>
                            <option value="18"> 18 </option>
                            <option value="19"> 19 </option>
                            <option value="20"> 20 </option>
                            <option value="21"> 21 </option>
                            <option value="22"> 22 </option>
                            <option value="23"> 23 </option>
                            <option value="24"> 24 </option>
                            <option value="25"> 25 </option>
                            <option value="26"> 26 </option>
                            <option value="27"> 27 </option>
                            <option value="28"> 28 </option>
                            <option value="29"> 29 </option>
                            <option value="30"> 30 </option>
                            <option value="35"> 35 </option>
                            <option value="40"> 40 </option>
                            <option value="45"> 45 </option>
                            <option value="50"> 50 </option>
                            <option value="55"> 55 </option>
                            <option value="60"> 60 </option>
                            <option value="90"> 90 </option>
                            <option value="120"> 120 </option>
                            <option value="150"> 150 </option>
                            <option value="180"> 180 </option>
                            <option value="210"> 210 </option>
                            <option value="240"> 240 </option>
                        </SELECT>	Minutos</td>
                </tr> 

            </table>
        </center>   
        <center>
            <input type="submit" class="btn btn-primary" value='Grabar' onclick="document.adicionacat.accion1.value = 'Guardar';
                    document.adicionacat.action = '<c:url value="/ReporteOdonMensual.do"/>';">
    </center>
    <input type="hidden" name='accion1'    value='Guardar'>
    <input type="hidden" name='mes'        value='<c:out value="${mes}"/>'>
    <input type="hidden" name='gestion'    value='<c:out value="${gestion}"/>'>
</form>

<div class=titulo> </div>




<table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
    <tr style="font-size:9pt">
        <th bgcolor="#F2F2F2"> NRO </th>
        <th bgcolor="#F2F2F2"> FECHA </th>
        <th bgcolor="#F2F2F2"> ACTIVIDAD </th>
        <th bgcolor="#F2F2F2"> TEMA </th>
        <th bgcolor="#F2F2F2"> NUMERO </th>
        <th bgcolor="#F2F2F2"> ELIMINAR </th> 
    </tr>  
    <c:forEach var="lista" items="${listaractividad}" varStatus="contador">
        <tr style="font-size:9pt" >
            <td align="center"><c:out value="${contador.count}"/></td>
            <td><fmt:formatDate value="${lista.fecha}" pattern='dd/MM/yyyy'/></td>
            <td><c:out value="${lista.actividad}"/></td>
            <td><c:out value="${lista.tema}"/></td>
            <td><c:out value="${lista.numero}"/></td>      
        <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/ReporteOdonMensual.do"/>'>
            <td>     
                <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();"> Eliminar</a></div>
                <input type="hidden" name='id_actividad'    value='<c:out value="${lista.id_actividad}"/>'>
                <input type="hidden" name="accion1"    value='Eliminar' >
                <input type="hidden" name="sw1"        value='1' >
            </td>
        </form>
    </tr> 
</c:forEach>
</table>
