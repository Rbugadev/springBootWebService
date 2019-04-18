package fr.afcepf.al33.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.afcepf.al33.conv.Convertisseur;
import fr.afcepf.al33.dao.DeviseDao;
import fr.afcepf.al33.dto.ResConv;
import fr.afcepf.al33.entity.Devise;

@CrossOrigin("*")//pour accepter de répondre à des appels ajax 
                 //provenant d'autres domaines/applications/...

@RestController //@Component de type @RestController
@RequestMapping(value="/rest/devises" , headers="Accept=application/json")
public class DeviseRestCtrl {

	@Autowired //injection du "business service" 
	private Convertisseur convertisseur;
	
	@Autowired //injection le dao (temporairement) 
	private DeviseDao deviseDao;
	
	//URL= http://localhost:8080/springBootWebService/rest/devises
	//en mode POST avec la partie body invisible de la requete contenant
	//{ "code" : "cxy" , "monnaie" : "monnaieXy" , "tauxChange": 2.2  }
	@RequestMapping(value="" , method=RequestMethod.POST)
	public Devise createOrUpdateDevise(@RequestBody @Valid Devise devise) {
		deviseDao.save(devise);
		return devise; //quelquefois (pas ici) l'objet retourné comporte id auto_incr
	}
	
	//URL : ..../rest/devises/convertir?montant=50&source=EUR&cible=USD
	@RequestMapping(value="/convertir" , method=RequestMethod.GET)
	public ResConv convertir(@RequestParam("montant")Double montant,
							@RequestParam("source")String source,
							@RequestParam("cible")String cible) {
		Double res = convertisseur.convertir(montant, source, cible);
		return new ResConv(montant, source, cible,res);
	}
	
	//URL= http://localhost:8080/springBootWebService/rest/devises/EUR
	@RequestMapping(value="/{codeDevise}" , method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteDeviseByCode(@PathVariable("codeDevise") 
	                                            String codeDevise) {
		//try {
			Devise d = deviseDao.findById(codeDevise).get();//get() remonte exception si null
			deviseDao.delete(d);
			return new ResponseEntity<>(HttpStatus.OK);
		/*} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}*/ //traitement d'exception dejà effectué par defaut
	}
	
	//URL= http://localhost:8080/springBootWebService/rest/devises/EUR
	@RequestMapping(value="/{codeDevise}" , method=RequestMethod.GET)
	public ResponseEntity<?> getDeviseByCode(@PathVariable("codeDevise") String codeDevise) {
		Devise d = deviseDao.findById(codeDevise).orElse(null);
		if(d!=null)
			return new ResponseEntity<Devise>(d,HttpStatus.OK);//200=OK
		else
			return new ResponseEntity<String>
		      ("{ \"err\" : \"pas bras, pas de chocolat\"}",
		    		  HttpStatus.NOT_FOUND) ;//404=NOT_FOUND
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
			return toutesDevises.stream()
					  .filter((d)-> d.getTauxChange()>=tauxChangeMini)
					  .collect(Collectors.toList());
	}

}
