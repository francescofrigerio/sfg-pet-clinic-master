package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by jt on 7/22/18.
 */
@RequestMapping("/owners")
@Controller
public class OwnerController {
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {

        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {

        dataBinder.setDisallowedFields("id");
    }

    // LEZIONE 187
    //@RequestMapping("/list")
    //@RequestMapping("/index")
    //@RequestMapping("/") KO
    @RequestMapping("/list")
    public String listOwners(Model model){

        System.out.println("Start listOwners ");
        model.addAttribute("owners",ownerService.findAll());
        return "owners/index";

    }

    // Nonn riesco a farli funzionare insieme
    @RequestMapping("/index")
    public String listOwnersByIndex(Model model){

        System.out.println("Start listOwnersByIndex ");
        model.addAttribute("owners",ownerService.findAll());
        return "owners/index";

    }

    @RequestMapping("/find")
    public String findOwners(Model model){
        // LEZIONE 127
        //return "notimplemented";
        // LEZIONE 127
        // LEZIONE 187
        boolean isLessons187 = true ;
        if (isLessons187) {
            // APPUNTI LEZIONE 213
            // Model and view restituiti sono
            // come un oggetto composito
            // dal modello e la stringa del percorso
            // che coincide con il nome della vista
            // Tecnica molto simile è quella di iniettare
            // il modello nel controller
            model.addAttribute("owners", Owner.builder().build());
            return "owners/findOwners";

        }
        else
            return "notimplemented";

    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        List<Owner> results = ownerService.findAllByLastNameLike("%"+ owner.getLastName() + "%");

        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            // 1 owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    // LEZIONE 213
    // IL PROPRIETARIO E' GIA MAPPATO
    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        // Recuperiamo il proprietario partendo
        // dal valore del suo ID come Long e non intero
        // a differenza del codice originale fornito da Spring
        // tornando al dettaglio dello stesso
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }
    // LEZIONE 213

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            Owner savedOwner =  ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

}
