package ar.com.app.people.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * @author rabella
 *
 */
public interface PersonaRepository extends CrudRepository<Persona, Long>, QueryByExampleExecutor<Persona> {
    
}
