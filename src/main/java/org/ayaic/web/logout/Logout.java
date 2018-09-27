package org.ayaic.web.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Logout {

    @RequestMapping("/logout.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("__sess_cliente");
        request.getSession().invalidate();
        return new ModelAndView("Distro", "url", ".");
    }
}
