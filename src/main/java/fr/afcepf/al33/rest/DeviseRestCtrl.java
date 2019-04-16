package fr.afcepf.al33.rest;

import fr.afcepf.al33.conv.Convertisseur;
import fr.afcepf.al33.dao.DeviseDao;
import fr.afcepf.al33.entity.Devise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // URL = http://localhost:8080/springBootWebService/rest/devises
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Devise> getDevises(){
        return (List<Devise>) deviseDao.findAll();
    }

    // URL = http://localhost:8080/springBootWebService/rest/devises/{tauxChange}
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Devise> getDevisesTauxChangeMin(@RequestParam(value = "tauxChangeMin", required = false) double tauxChangeMin){
        return (List<Devise>) deviseDao.findAll();
    }
}
