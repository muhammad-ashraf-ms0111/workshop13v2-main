package sg.app.workshop13v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import jakarta.validation.Valid;
import sg.app.workshop13v2.models.Contact;
import sg.app.workshop13v2.utils.Contacts;


@Controller
public class AddressBook {

@Autowired
Contacts ctcs;

@Autowired
ApplicationArguments appArgs;

@Value("${test.data.dir}")
  private String dataDir;

@GetMapping(path="/")
public String index(){
  return "index";
}

@GetMapping(path="/form")
public String showContactForm(Model model) {
  model.addAttribute("contact", new Contact());
  return "form";
}

// @PostMapping("/contact")
// public String saveContact(Model model, @ModelAttribute Contact contact){
//   model.addAttribute("contact", contact);
//   return "contactInfo";
// }

//BindingResult contains the validation results. see D13 pg 16

@PostMapping(path="/contact")
    public String saveContact(@Valid Contact contact, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "form";
        } 

        ctcs.saveContact(contact, model, appArgs, dataDir);
        return "contactInfo";
    }

    //contactId from ctc.setId
    @GetMapping("{contactId}")
    public String getContactById(Model model, @PathVariable String contactId) {
        ctcs.getContactById(model, contactId, appArgs, dataDir);
        return "contactInfo";
    }

    @GetMapping(path = "/list")
    public String getAllContacts(Model model) {
        ctcs.getAllContactInURI(model, appArgs, dataDir);
        return "contacts";
    }
}
