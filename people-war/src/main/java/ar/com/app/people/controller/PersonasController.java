package ar.com.app.people.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.app.people.model.Persona;
import ar.com.app.people.model.Personas;

/**
 * @author rabella
 * 
 */
@Controller
@RequestMapping(value = "/personas", produces = {APPLICATION_JSON_VALUE})
public class PersonasController {

	private PersonaServices personaManager;
	
	@Autowired
	public PersonasController(PersonaServices personaManager) {
		super();
		this.personaManager = personaManager;
	}
	
	@RequestMapping(value = "", produces = { "application/json" },   method = RequestMethod.GET)
	public ResponseEntity<Personas> getPersonas(
	        @RequestParam(value = "page", required = false, defaultValue="0") Integer page,
	        @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
	        @RequestParam(value = "perNombre", required = false) String perNombre,
	        @RequestParam(value = "perTipoDocumento", required = false) String perTipoDocumento) {
		Personas personas = new Personas();
		
		Page<Persona> foundPersonas = personaManager.getPersonas(page, pageSize, perNombre, perTipoDocumento);
		personas.getPersonas().addAll(foundPersonas.getContent());
		personas.setCount((int) foundPersonas.getTotalElements());
		
		return new ResponseEntity<>(personas, HttpStatus.OK);
	}

	@RequestMapping(value = "", produces = { "application/json" },  consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
		return new ResponseEntity<>(personaManager.save(persona), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", produces = { "application/json" },  consumes = { "application/json" }, method = RequestMethod.PUT)
    public ResponseEntity<Persona> updatePersona(@PathVariable("id") Long perId,
            @RequestBody Persona persona) {
        return new ResponseEntity<>(personaManager.update(perId, persona), HttpStatus.OK);
    }
	
	@RequestMapping(value = "/{id}", produces = { "application/json" },  consumes = { "application/json" }, method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePersona(@PathVariable("id") Long perId) {
	    personaManager.delete(perId);
	    return new ResponseEntity<>(HttpStatus.OK);
    }
	
}
