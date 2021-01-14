package nl.uva.sne.drip.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;
import nl.uva.sne.drip.service.DRIPService;
import org.springframework.beans.factory.annotation.Autowired;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-10T17:15:46.465Z")

@Controller
public class DeployerApiController implements DeployerApi {

    private static final Logger log = LoggerFactory.getLogger(DeployerApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private DRIPService dripService;

    @org.springframework.beans.factory.annotation.Autowired
    public DeployerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<String> deployProvisionToscaTemplateByID(
            @ApiParam(value = "ID of topolog template to deploy", required = true)
            @PathVariable("id") String id) {

        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                String planedYemplateId = dripService.deploy(id, null);
                return new ResponseEntity<>(planedYemplateId, HttpStatus.OK);

            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(DeployerApiController.class.getName()).log(Level.SEVERE, null, ex);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
