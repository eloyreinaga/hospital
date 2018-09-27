<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../Superior.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />
<script language = 'JavaScript' SRC="./validar.js"></script>

<div><a class="btn btn-warning" href='/fichas'>Volver</a></div>

<table class="table table-striped table-condensed table-responsive">
    <tr>   
        <td valign="top" width="50%">
            <form name="adicionapaciente" method="POST" action='<c:url value="/fichas"/>'>
                <center>    
                    <table class="table table-striped table-bordered table-condensed table-responsive" width="20%">
                        <tr>
                            <th colspan="1" style="font-size:12pt" bgcolor="#F2F2F2"><center>ELIJA FECHA Y HORA DE CITA MEDICA</center></th>
                        </tr>
                    </table>    
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                <table class="table table-striped table-bordered table-condensed table-responsive" width="20%">
                                    <tr style="font-size:10pt">
                                        <td align="right" bgcolor="#F2F2F2">No. HCL:</td>    
                                        <td><c:out value = "${datos.hcl}"/></td>
                                    </tr>
                                    <tr style="font-size:10pt">
                                        <td align="right" bgcolor="#F2F2F2">Nombres:</td>    
                                        <td><c:out value = "${datos.nombres}"/></td>
                                    </tr>
                                    <tr style="font-size:10pt">
                                        <td align="right" bgcolor="#F2F2F2">Fecha Nacimiento:</td>    
                                        <td><c:out value="${fec_nacimiento}"/></td>
                                    </tr>
                                    <tr style="font-size:10pt">
                                        <td align="right" bgcolor="#F2F2F2">Edad:</td>    
                                        <td><font color="blue" size="4"> <c:out value="${datos.edad}"/> a <c:out value = "${datos.mes}"/> m <c:out value = "${datos.dia}"/> d </font></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                <table class="table table-striped table-bordered table-condensed table-responsive" width="20%">
                                   <tr style="font-size:10pt">
                                        <td align="right" bgcolor="#F2F2F2">Nro Matricula:</td>    
                                        <td style="font-size: 13pt; color: blue"><c:out value="${datos.nro_registro}"/>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${datos.nro}"/><font color="red"></font></td>
                                    </tr>
                                    <tr style="font-size:10pt">
                                        <td align="right" bgcolor="#F2F2F2">Servicio:</td>    
                                        <td><c:out value="${Consultorio}"/></td>
                                        <!--   <SELECT NAME="id_consultorio" onchange="javascript:document.adicionapaciente.submit();">
                                               <option value="">-- seleccione --</option>
                                               <c:forEach var="estado" items="${listarCargos}">
                                                   <option value="<c:out value="${estado.id_consultorio}"/>" <c:if test="${estado.id_consultorio == id_consultorio}">selected</c:if>>
                                                       <c:out value="${estado.consultorio}"/>
                                                   </option>
                                               </c:forEach>
                                           </SELECT>	
                                           <input type="hidden" name='id_paciente'     value='<c:out value="${id_paciente}"/>'>
                                           <input type="hidden" name='swci'            value='Si'>
                                       </td>-->
                                    </tr>
                                    <tr style="font-size:10pt">
                                        <td align="right" bgcolor="#F2F2F2">Medico:</td>    
                                        <td><c:out value="${nombres}"/> </td>
                                            <!--<SELECT NAME="id_persona" >
                                                <option value="0">-- seleccione --</option>
                                                <c:forEach var="perso" items="${listaPersonas}">
                                                    <option value="<c:out value="${perso.id_persona}"/>"<c:if test="${perso.id_persona == id_persona}">selected</c:if>> 
                                                        <c:out value="${perso.nombres}"/>
                                                    </option>
                                                </c:forEach>
                                            </SELECT>

                                        </td>-->
                                    </tr>
                                    <!--
                                    <tr style="font-size:10pt">
                                        <td align="right" bgcolor="#F2F2F2"> Fecha Consulta</td>	
                                        <td><SELECT NAME="diai">
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
                                            Mes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Año&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            &nbsp;Hora&nbsp;&nbsp;&nbsp;&nbsp;Minuto</font>        
                                        </td>
                                    </tr>
                                    -->
                                    <tr style="font-size:10pt">
                                        <td align="right" bgcolor="#F2F2F2">Fecha Atencion:</td>    
                                        <td style="font-size: 13pt"><c:out value="${fecha}"/> </td>
                                    </tr>
                                </table>
                            </div>
                        </div>  
                </center>

                <center>
                    <c:forEach var="detallefic" items="${listarFichas}">
                        <input class="btn btn-primary btn-lg" type="submit" name='accion1' value="<c:out value="${detallefic.nombres}"/>">
                    </c:forEach>
                </center>
  
                <!--                          
                <center>
                    <input type="submit" name='accion1' class="btn btn-primary btn-lg" value='Aceptar' onclick="document.adicionapaciente.action = '<c:url value="/ListarPacientesInter.do"/>';">
                    <br> 
                </center>
                -->
                <input type="hidden" name='id_paciente'     value='<c:out value="${id_paciente}"/>'>
                <input type="hidden" name="resvig"          value='<c:out value="${resvig}"/>'>
                <input type="hidden" name='swci'            value='<c:out value="${swci}"/>'>
                <input type="hidden" name='accion'          value='Aceptar'>
            </form>
        </td>

    </tr>
</table>
