package fr.afcepf.al33.dao;

import org.springframework.data.repository.CrudRepository;

import fr.afcepf.al33.entity.Devise;

public interface DeviseDao extends CrudRepository<Devise,String>{
	/*
	//méthodes héritées de CrudRepository:
	Optional<Devise> findById(String code);
	List<Devise> findAll();
	void save(Devise d);//au sens saveOrUpdate (selon num/id null ou pas)
	void deleteById(String codeDevise);
	*/
	
}
