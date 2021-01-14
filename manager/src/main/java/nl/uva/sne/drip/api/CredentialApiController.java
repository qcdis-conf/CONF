package nl.uva.sne.drip.api;

import nl.uva.sne.drip.model.tosca.Credential;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import nl.uva.sne.drip.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-10T17:15:46.465Z")

@Controller
public class CredentialApiController implements CredentialApi {

    private static final Logger log = LoggerFactory.getLogger(CredentialApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CredentialService credentialService;

    @org.springframework.beans.factory.annotation.Autowired
    public CredentialApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<String> createCredentials(@ApiParam(
            value = "Created user object", required = true)
            @Valid @RequestBody Credential body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            String id = credentialService.save(body);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @Override
    public ResponseEntity<List<String>> getCredentialIDs() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            List<String> ids = credentialService.getAllIds();
            return new ResponseEntity<>(ids, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
