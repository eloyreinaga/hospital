package org.ayaic.web.verCuerpo;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VerCuerpo {

    private final MiFacade mi;

    public VerCuerpo(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/verCuerpo.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        List lNoticias = this.mi.getListarNoticias();
        modelo.put("lNoticias", lNoticias);

        return new ModelAndView("verCuerpo/VerCuerpo", modelo);
    }
}
