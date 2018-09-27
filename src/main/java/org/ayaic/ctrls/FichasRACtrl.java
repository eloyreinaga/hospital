package org.ayaic.ctrls;

import java.util.List;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fichasinternet")
public class FichasRACtrl {

    @Autowired
    MiFacade mi;

    @RequestMapping(value = "/fichas", method = RequestMethod.GET)
    public List<Pacientes> fichas(@RequestParam("matricula") String matriculaRest, @RequestParam("carnet") String carnetRest) {

        String nombres = matriculaRest;
        String carnet = carnetRest;

        nombres = nombres.trim();
        nombres = nombres.replaceAll(" +", " ");
        carnet = carnet.trim();
        carnet = carnet.replaceAll(" +", " ");

        nombres = nombres.trim() + " " + carnet.trim();
        nombres = nombres.replaceAll("\\s", ":*&");///:*&
        nombres = nombres.replaceAll("ñ", "n");
        nombres = nombres.replaceAll("Ñ", "N");

        Historiales dato = new Historiales();
        dato.setNombres(nombres);
        List resultado = mi.getListarReservacionesInternet(dato);

        return resultado;
    }

    @RequestMapping(value = "/pacientes", method = RequestMethod.GET)
    public List<Pacientes> pacientes(@RequestParam("matricula") String matriculaRest, @RequestParam("carnet") String carnetRest) {

        String nombres = matriculaRest;
        String carnet = carnetRest;

        nombres = nombres.trim();
        nombres = nombres.replaceAll(" +", " ");
        carnet = carnet.trim();
        carnet = carnet.replaceAll(" +", " ");

        nombres = nombres.trim() + " " + carnet.trim();
        nombres = nombres.replaceAll("\\s", ":*&");
        nombres = nombres.replaceAll("ñ", "n");
        nombres = nombres.replaceAll("Ñ", "N");

        Pacientes paciente = new Pacientes();
        paciente.setNombres(nombres);
        List resultado = mi.getListarPacientesFichas(paciente);

        return resultado;
    }

}
