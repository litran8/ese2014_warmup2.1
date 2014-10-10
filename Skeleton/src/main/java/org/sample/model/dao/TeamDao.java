package org.sample.model.dao;

import org.sample.model.Team;
import org.springframework.data.repository.CrudRepository;

/**
 * Grundlegenden Datenbankoperationen Create (Datensatz anlegen),
 * Read oder Retrieve (Datensatz lesen),
 * Update (Datensatz aktualisieren) und
 * Delete oder Destroy (Datensatz löschen)
 * 
 * @author Lina Tran
 *
 */

public interface TeamDao extends CrudRepository<Team,Long> {

	/**
	 * Returns all instances of the type.
	 * 
	 * @return all entities
	 */
	public Iterable<Team> findAll();
	
}
