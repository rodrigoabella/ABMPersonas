package ar.com.app.version;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author rabella
 */
@Controller
@RequestMapping(value = "/version", produces = {APPLICATION_JSON_VALUE})
public class VersionController {

    private final String version;

    @Autowired
    public VersionController(@Value("${people.build.version}") String versionResponse) {
        this.version = versionResponse;
    }

    @RequestMapping(value = "", produces = { "application/json" },   method = RequestMethod.GET)
    public ResponseEntity<VersionResponse> versionGet() {
        final VersionResponse response = new VersionResponse(version);
        return ResponseEntity.ok(response);
    }
}
