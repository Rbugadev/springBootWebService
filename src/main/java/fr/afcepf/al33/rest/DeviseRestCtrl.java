package fr.afcepf.al33.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.afcepf.al33.conv.Convertisseur;
import fr.afcepf.al33.dao.DeviseDao;
import fr.afcepf.al33.entity.Devise;

@RestController //@Component de type @RestController
@RequestMapping(value="/rest/devises" , headers="Accept=application/json")
public class DeviseRestCtrl {

	@Autowired //injection du "business service" 
	private Convertisseur convertisseur;
	
	@Autowired //injection le dao (temporairement)
	private DeviseDao deviseDao;


    @RequestMapping(value="" , method=RequestMethod.POST)
	public Devise createOrUpdateDevise(@RequestBody Devise devise){
	    deviseDao.save(devise);
	    return devise;
    }
	
	//URL= http://localhost:8080/springBootWebService/rest/devises/EUR
	@RequestMapping(value="/{codeDevise}" , method=RequestMethod.GET)
	public ResponseEntity<?> getDeviseByCode(@PathVariable("codeDevise") String codeDevise) {
		Devise devise = deviseDao.findById(codeDevise).orElse(null);
		Map<String, Object> response = new HashMap<>();
		if (devise!=null){
            return new ResponseEntity<Devise>(devise, HttpStatus.OK);
        } else {
        	response.put("message", "La devise n'existe pas dans la base de données");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
	}
	
	//URL= http://localhost:8080/springBootWebService/rest/devises
	//URL= http://localhost:8080/springBootWebService/rest/devises?tauxChangeMini=1
	@RequestMapping(value="" , method=RequestMethod.GET)
	public List<Devise> getDevises(@RequestParam(value="tauxChangeMini",
	                               required=false) Double tauxChangeMini) {
		
		List<Devise> toutesDevises = (List<Devise>) deviseDao.findAll();
		
		if(tauxChangeMini==null)
			return toutesDevises;
		else
			return toutesDevises.stream().filter((d)-> d.getTauxChange()>=tauxChangeMini).collect(Collectors.toList());
	}

}