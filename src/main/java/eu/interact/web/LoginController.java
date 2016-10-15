/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.interact.web;

import eu.interact.domain.UserRole;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Root
 */
@Controller
public class LoginController {

    public static String USER_ROLE = "user.role";

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, @RequestParam String username,
            @RequestParam String password) {
        HttpSession httpSession = request.getSession();

        if ("institution".equals(username)) {
            httpSession.setAttribute(USER_ROLE, UserRole.INSTITUTION);
        } else if ("public".equals(username)) {
            httpSession.setAttribute(USER_ROLE, UserRole.THIRDPARTY);
        }

        return "redirect:/";
    }

    @GET
    @RequestMapping(value="/user")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody UserRole user(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        UserRole role = (UserRole)httpSession.getAttribute(USER_ROLE);
        
        return role;
    }
    
}
