package ar.com.app.people.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author rabella
 * 
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Persona {

    public enum TipoDocumento{
        DNI,
        PASAPORTE,
        CEDULA
    }
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Long perId;
	
	@Basic(optional = false)
	private String perNombre;
	
	@Basic(optional = false)
    private String perApellido;
	
	@Basic(optional = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
    private Date perFechaNacimiento;
	
	@Basic(optional = false)
    private Long perNumeroDocumento;
	
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
    private TipoDocumento perTipoDocumento;
	
	
	public Persona() {}
	
	public Persona(String perNombre, String perApellido, Date perFechaNacimiento, Long perNumeroDocumento, TipoDocumento perTipoDocumento) {
		this.perNombre = perNombre;
		this.perApellido = perApellido;
		this.perFechaNacimiento = perFechaNacimiento;
		this.perNumeroDocumento = perNumeroDocumento;
		this.perTipoDocumento = perTipoDocumento;
	}

	@JsonProperty("perId")
	public Long getPerId() {
		return perId;
	}
	
	public void setPerId(Long perId) {
        this.perId = perId;
    }

	@JsonProperty("perNombre")
	public String getPerNombre() {
		return perNombre;
	}

	public void setPerNombre(String perNombre) {
		this.perNombre = perNombre;
	}

	@JsonProperty("perApellido")
    public String getPerApellido() {
        return perApellido;
    }

    public void setPerApellido(String perApellido) {
        this.perApellido = perApellido;
    }

    @JsonProperty("perFechaNacimiento")
    public Date getPerFechaNacimiento() {
        return perFechaNacimiento;
    }

    public void setPerFechaNacimiento(Date perFechaNacimiento) {
        this.perFechaNacimiento = perFechaNacimiento;
    }

    @JsonProperty("perNumeroDocumento")
    public Long getPerNumeroDocumento() {
        return perNumeroDocumento;
    }

    public void setPerNumeroDocumento(Long perNumeroDocumento) {
        this.perNumeroDocumento = perNumeroDocumento;
    }

    @JsonProperty("perTipoDocumento")
    public TipoDocumento getPerTipoDocumento() {
        return perTipoDocumento;
    }

    public void setPerTipoDocumento(TipoDocumento perTipoDocumento) {
        this.perTipoDocumento = perTipoDocumento;
    }	
}
