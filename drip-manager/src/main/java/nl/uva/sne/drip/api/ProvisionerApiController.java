package nl.uva.sne.drip.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import nl.uva.sne.drip.service.DRIPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-10T17:15:46.465Z")

@Controller
public class ProvisionerApiController implements ProvisionerApi {

    private static final Logger log = LoggerFactory.getLogger(ProvisionerApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${message.broker.queue.provisioner}")
    private String queueName;
    @Autowired
    private DRIPService dripService;

    @org.springframework.beans.factory.annotation.Autowired
    public ProvisionerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<String> provisionPlanToscaTemplateByID(
            @ApiParam(value = "ID of topolog template to plan", required = true)
            @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("text/plain")) {
            dripService.setRequestQeueName(queueName);
            String planedYemplateId = dripService.execute(id);
            return new ResponseEntity<>(planedYemplateId, HttpStatus.NOT_IMPLEMENTED);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
