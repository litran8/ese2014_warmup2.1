package org.sample.model.dao;

import java.util.List;

import org.sample.model.User;
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

public interface UserDao extends CrudRepository<User,Long> {
	
	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id must not be {@literal null}.
	 * @return the entity with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException if {@code id} is {@literal null}
	 */
	public User findOne(Long userId);
	
	public List<User> findAll();
	
}
