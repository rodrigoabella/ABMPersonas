package ar.com.app.version;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ar.com.app.version.VersionController;
import ar.com.app.version.VersionResponse;

import static org.junit.Assert.assertEquals;

/**
 * @author rabella
 */
public class VersionControllerTest {

    @Test
    public void getReturnsVersion() throws Exception {
        String version = "1.0.0-SNAPSHOT";
        VersionController controller = new VersionController(version);

        final ResponseEntity<VersionResponse> response = controller.versionGet();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getVersion(), version);
   }
}