package nl.uva.sne.drip.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import nl.uva.sne.drip.service.DRIPService;
import nl.uva.sne.drip.service.ToscaTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-10T17:15:46.465Z")

@Controller
public class ScalerApiController implements ScalerApi {

    private static final Logger log = LoggerFactory.getLogger(ScalerApiController.class);

    private final HttpServletRequest request;

    @Autowired
    private ToscaTemplateService toscaTemplateService;

    @Autowired
    private DRIPService dripService;

    @Value("${message.broker.queue.provisioner}")
    private String provisionerQueueName;

    @org.springframework.beans.factory.annotation.Autowired
    public ScalerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<String> scaleProvisionedToscaTemplateByID(
            @ApiParam(value = "ID of topolog template to plan", required = true)
            @PathVariable("id") String id,
            @NotNull @ApiParam(value = "The node to scale", required = true)
            @Valid @RequestParam(value = "node_name", required = true) String nodeName,
            @NotNull @ApiParam(value = "The number of nodes to scale", required = true)
            @Valid @RequestParam(value = "node_num", required = true) Integer nodeNum) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("text/plain")) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
