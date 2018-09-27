package org.ayaic.ctrl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InicioCtrl {

    @Autowired
    MiFacade mi;

    @GetMapping("/api/success_app")
    public ArrayList success_app(HttpServletRequest hsr) throws UnknownHostException {

        String ip = InetAddress.getLocalHost().getHostName();
        ArrayList<String> array = new ArrayList<>();
        array.add(ip);
        return array;
    }

}
