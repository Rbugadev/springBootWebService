package fr.afcepf.al33.conv;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService //targetNamespace par defaut = http://conv.al33.afcepf.fr/
public interface Convertisseur {
	public double euroToFranc(@WebParam(name="montant")double montant);
	public double francToEuro(@WebParam(name="montant")double montant);
	public String getAuteur();
	public double convertir(@WebParam(name="montant")double montant,
			                @WebParam(name="source")String source, 
			                @WebParam(name="cible")String cible);
}
