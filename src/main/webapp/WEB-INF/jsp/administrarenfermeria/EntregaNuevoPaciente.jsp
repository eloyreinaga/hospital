<%@ include file="../Superior.jsp" %>


<div style="font-size:15pt"> Entregar Medicamentos al Paciente(<c:out value = "${sw}"/>)</div>

<div><a class="volver" href='EntregaMedicamentos.do'>Volver</a></div>
<br>

<form name="adicionarpaciente" method="POST">

    <table class="formulario" width="100%">
        <tr>
            <th colspan="3"><font size=2>DATOS PERSONALES </font></th>
        </tr>    

        <tr>    
            <td>Nombres  </td>    
            <td>::</td>
            <td><input type="text" name="nombres" value="<c:out value = "${nombres}"/>" size="50" onblur='validar(nombres, "A")'/></td>
        </tr>    


        <tr>    
            <td>Numero Clave de Documento  </td>    
            <td>::</td>	      
            <td><input type="text" name="num_cladoc" value="<c:out value = "${num_cladoc}"/>" maxlength=60/></td>
        </tr>        

    </table>

    <center>
        <input type="submit" class="siguiente" value='Siguiente' onclick="document.adicionarpaciente.action = '<c:url value="/EntregaMedicamentoPaciente.do"/>'"></td>
    </center>
    <input type="hidden" name='sw' value='<c:out value="${sw}"/>'>
    <input type="hidden" name='accion' value='<c:out value="entregarya"/>'>
    <input type="hidden" name='id_historial' value='<c:out value="${id_historial}"/>'>
</form>
