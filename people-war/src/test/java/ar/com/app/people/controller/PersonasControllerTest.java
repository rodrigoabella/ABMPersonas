package ar.com.app.people.controller;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ar.com.app.people.controller.PersonasController;
import ar.com.app.people.model.Persona;
import ar.com.app.people.model.PersonaRepository;
import ar.com.app.people.model.Personas;
import ar.com.app.people.model.Persona.TipoDocumento;
import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Rule;

/**
 * @author rabella
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/people-context-test.xml" })
public class PersonasControllerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private PersonasController personasController;

    @Autowired
    private PersonaRepository personaRepository;

    @Before
    public void init() throws ParseException {
        personaRepository.deleteAll();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("1990-09-25");
        Persona persona = new Persona("rodrigo", "abella", date, 35259976L,
                TipoDocumento.DNI);
        personaRepository.save(persona);
    }

    @Test
    public void testDeletePerson() throws Exception {
        Persona persona = personaRepository.findAll().iterator().next();

        assertEquals(1, personaRepository.count());
        assertEquals("rodrigo", persona.getPerNombre());

        ResponseEntity<?> responseEntity = personasController.deletePersona(persona.getPerId());

        assertEquals(0, personaRepository.count());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteNonExistentPerson() throws Exception {
        final Long invalidId = -1L;

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("persona con id: -1 no existe y no pudo ser eliminada");
        personasController.deletePersona(invalidId);
    }
    
    @Test
    public void testUpdatePerson() throws Exception {
        Persona persona = personaRepository.findAll().iterator().next();

        assertEquals(1, personaRepository.count());
        assertEquals("rodrigo", persona.getPerNombre());
        
        persona.setPerNombre("roli");

        ResponseEntity<Persona> responsePersona = personasController.updatePersona(persona.getPerId(), persona);

        assertEquals(1, personaRepository.count());
        assertEquals(HttpStatus.OK, responsePersona.getStatusCode());
        assertEquals(persona.getPerNombre(), responsePersona.getBody().getPerNombre());
    }

    @Test
    public void testUpdateNonExistentPerson() throws Exception {
        final Long invalidId = -1L;
        Persona persona = new Persona();

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("persona con id: -1 no existe y no pudo ser actualizada");
        personasController.updatePersona(invalidId, persona);
    }
    
    @Test
    public void testCreatePerson() throws Exception {

        assertEquals(1, personaRepository.count());

        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("1990-09-25");
        Persona nuevaPersona = new Persona("roli", "abella", date, 35259976L,
                TipoDocumento.DNI);
        
        ResponseEntity<Persona> responseNuevaPersona = personasController.createPersona(nuevaPersona);

        assertEquals(2, personaRepository.count());
        assertEquals(HttpStatus.CREATED, responseNuevaPersona.getStatusCode());
        assertEquals(nuevaPersona.getPerNombre(), responseNuevaPersona.getBody().getPerNombre());
    }
    
    @Test
    public void testGetAllPersonas() throws Exception {
        Persona persona = personaRepository.findAll().iterator().next();
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("1990-09-25");
        Persona nuevaPersona = new Persona("roli", "abella", date, 35259976L,
                TipoDocumento.DNI);
        
        personasController.createPersona(nuevaPersona).getBody();

        assertEquals(2, personaRepository.count());
        
        ResponseEntity<Personas> personasResponse = personasController.getPersonas(0, 15, null, null);
        
        assertEquals(HttpStatus.OK, personasResponse.getStatusCode());
        assertEquals(Integer.valueOf(2), personasResponse.getBody().getCount());
        assertEquals(persona.getPerNombre(), personasResponse.getBody().getPersonas().get(0).getPerNombre());
        assertEquals(nuevaPersona.getPerNombre(), personasResponse.getBody().getPersonas().get(1).getPerNombre());
    }
    
    @Test
    public void testGetPersonasWithPagination() throws Exception {
        Persona persona = personaRepository.findAll().iterator().next();
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("1990-09-25");
        Persona nuevaPersona = new Persona("roli", "abella", date, 35259976L,
                TipoDocumento.DNI);
        
        nuevaPersona = personasController.createPersona(nuevaPersona).getBody();

        assertEquals(2, personaRepository.count());
        
        ResponseEntity<Personas> personasResponse = personasController.getPersonas(0, 1, null, null);
        
        assertEquals(HttpStatus.OK, personasResponse.getStatusCode());
        assertEquals(Integer.valueOf(2), personasResponse.getBody().getCount());
        assertEquals(1, personasResponse.getBody().getPersonas().size());
        
        assertEquals(persona.getPerNombre(), personasResponse.getBody().getPersonas().get(0).getPerNombre());
        
        personasResponse = personasController.getPersonas(1, 1, null, null);
        assertEquals(nuevaPersona.getPerNombre(), personasResponse.getBody().getPersonas().get(0).getPerNombre());
    }
    
    @Test
    public void testGetPersonasWithFilters() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("1990-09-25");
        Persona nuevaPersona = new Persona("roDRiGoo", "abella", date, 35259976L,
                TipoDocumento.CEDULA);
        
        nuevaPersona = personasController.createPersona(nuevaPersona).getBody();

        assertEquals(2, personaRepository.count());
        
        ResponseEntity<Personas> personasResponse = personasController.getPersonas(0, 15, "RoDri", "CEDULA");
        
        assertEquals(HttpStatus.OK, personasResponse.getStatusCode());
        assertEquals(Integer.valueOf(1), personasResponse.getBody().getCount());
        assertEquals(1, personasResponse.getBody().getPersonas().size());
        
        assertEquals(nuevaPersona.getPerNombre(), personasResponse.getBody().getPersonas().get(0).getPerNombre());
    }
    
    
}