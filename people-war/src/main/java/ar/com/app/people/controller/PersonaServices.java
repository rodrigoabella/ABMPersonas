package ar.com.app.people.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ar.com.app.people.model.Persona;
import ar.com.app.people.model.PersonaRepository;
import ar.com.app.people.model.Persona.TipoDocumento;

/**
 * @author rabella
 *
 */
@Component
public class PersonaServices {
	private static final String DELETE_ERROR_MESSAGE = "persona con id: %s no existe y no pudo ser eliminada";
    private static final String UPDATE_ERROR_MESSAGE = "persona con id: %s no existe y no pudo ser actualizada";
    private PersonaRepository personaRepository;
	
	@Autowired
	public PersonaServices(PersonaRepository personaRepository) {
		this.personaRepository = personaRepository;
	}
	
	public Page<Persona> getPersonas(Integer page, Integer pageSize, String perNombre, String perTipoDocumento) {

	    Persona persona = new Persona();
	    updatePerNombre(perNombre, persona);
	    updatePerTipoDocumento(perTipoDocumento, persona);    
	    ExampleMatcher matcher = ExampleMatcher.matching()
	            .withMatcher("perNombre", ExampleMatcher.GenericPropertyMatcher
	                                                            .of(ExampleMatcher.StringMatcher.CONTAINING).ignoreCase())
	            .withIgnoreNullValues();
	    
	    Page<Persona> personaPage = personaRepository.findAll(Example.of(persona, matcher), PageRequest.of(page, pageSize));
	    return personaPage;
    }

    private void updatePerTipoDocumento(String perTipoDocumento,
            Persona persona) {
        if (perTipoDocumento != null && !perTipoDocumento.isEmpty()) {
	        persona.setPerTipoDocumento(TipoDocumento.valueOf(perTipoDocumento.toUpperCase()));
	    }
    }

    private void updatePerNombre(String perNombre, Persona persona) {
        if (perNombre != null && !perNombre.isEmpty()) {
	        persona.setPerNombre(perNombre);
	    }
    }
	
	public Persona save(Persona persona){
		personaRepository.save(persona);
		return persona;
	}

    public Persona update(Long perId, Persona persona) {
        Optional<Persona>  personaOptional = personaRepository.findById(perId);
        Persona foundPerson = personaOptional.orElseThrow(() -> new IllegalArgumentException(String.format(UPDATE_ERROR_MESSAGE, perId)));
        persona.setPerId(foundPerson.getPerId());
        personaRepository.save(persona);
        return persona;
    }

    public void delete(Long perId) {
        Optional<Persona>  personaOptional = personaRepository.findById(perId);
        Persona foundPerson = personaOptional.orElseThrow(() -> new IllegalArgumentException(String.format(DELETE_ERROR_MESSAGE, perId)));;
        personaRepository.delete(foundPerson);
    }
}
