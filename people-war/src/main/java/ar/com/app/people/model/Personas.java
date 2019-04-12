package ar.com.app.people.model;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Personas {

    private Integer count = null;
    private List<Persona> personas = new ArrayList<>();

    public Personas() {
    }

    public Personas(Integer count, List<Persona> personas) {
        this();
        this.count = count;
        this.personas = personas;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("personas")
    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
}
