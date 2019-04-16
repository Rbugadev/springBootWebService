package fr.afcepf.al33.rest;

import fr.afcepf.al33.conv.Convertisseur;
import fr.afcepf.al33.dao.DeviseDao;
import fr.afcepf.al33.entity.Devise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController // Component de type @RestController
@RequestMapping(value = "/rest/devises", headers = "Accept=application/json")
public class DeviseRestCtrl {

    @Autowired // injection du business service
    private Convertisseur convertisseur;

    @Autowired
    private DeviseDao deviseDao;

    // URL = http://localhost:8080/springBootWebService/rest/devises/EUR
    @RequestMapping(value = "/{codeDevise}", method = RequestMethod.GET)
    public Devise getDeviseByCode(@PathVariable("codeDevise") String codeDevise){
        return  deviseDao.findById(codeDevise).get();
    }
}
