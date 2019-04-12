package ar.com.app.version;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VersionResponse  {
  
  private String version;

  public VersionResponse(String version) {
    this.version = version;
  }

  @JsonProperty("version")
  public String getVersion() {
    return version;
  }
  
  public void setVersion(String version) {
    this.version = version;
  }
}
