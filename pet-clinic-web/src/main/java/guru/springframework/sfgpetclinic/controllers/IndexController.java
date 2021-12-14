package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jt on 7/22/18.
 */
@Controller
public class IndexController {

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(){

        return "index";
    }

    // LEZIONE 127
    // GESTISCO IL CASO IN CUI UNA
    // PAGINA NON SIA STATA IMPLEMENTATA
    // O NON SIA RAGGIUNGIBILE PER QUALCHE MOTIVO
    @RequestMapping("/oups")
    public String oupsHandler(){

        return "notimplemented";
    }
    // LEZIONE 127
}
