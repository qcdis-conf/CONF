package nl.uva.sne.drip.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import nl.uva.sne.drip.model.Exceptions.TypeExeption;
import nl.uva.sne.drip.service.DRIPService;
import nl.uva.sne.drip.service.ToscaTemplateService;
import nl.uva.sne.drip.sure.tosca.client.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-10T17:15:46.465Z")

@Controller
public class ToscaTemplateApiController implements ToscaTemplateApi {

    private static final Logger log = LoggerFactory.getLogger(ToscaTemplateApiController.class);

    private final HttpServletRequest request;

    @Autowired
    private ToscaTemplateService toscaTemplateService;

    @Autowired
    private DRIPService dripService;

    @org.springframework.beans.factory.annotation.Autowired
    public ToscaTemplateApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<String> deleteToscaTemplateByID(
            @ApiParam(value = "ID of topology template to return", required = true)
            @PathVariable("id") String id, @ApiParam(value = "The node(s) to delete")
            @Valid @RequestParam(value = "node_names", required = false) List<String> nodeName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("text/plain")) {
            try {
                dripService.delete(id, nodeName);
                return new ResponseEntity<>("", HttpStatus.OK);
            } catch (IOException | ApiException | TypeExeption | TimeoutException | InterruptedException ex) {
                java.util.logging.Logger.getLogger(ToscaTemplateApiController.class.getName()).log(Level.SEVERE, null, ex);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (NotFoundException ex) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Override
    public ResponseEntity<String> getToscaTemplateByID(@ApiParam(value = "ID of topolog template to return", required = true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("text/plain")) {

            try {
                String ymlStr = toscaTemplateService.findByID(id);
                return new ResponseEntity<>(ymlStr, HttpStatus.OK);
            } catch (JsonProcessingException | NotFoundException ex) {
                java.util.logging.Logger.getLogger(ToscaTemplateApiController.class.getName()).log(Level.SEVERE, null, ex);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @Override
    public ResponseEntity<String> updateToscaTemplateByID(@ApiParam(
            value = "ID of topolog template to return", required = true)
            @PathVariable("id") String id, @ApiParam(value = "file detail")
            @Valid @RequestPart("file") MultipartFile file) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("text/plain")) {
            try {
                id = toscaTemplateService.updateToscaTemplateByID(id, file);
                return new ResponseEntity<>(id, HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type ", e);
                java.util.logging.Logger.getLogger(ToscaTemplateApiController.class.getName()).log(Level.SEVERE, null, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Override
    public ResponseEntity<String> uploadToscaTemplate(@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile file) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("*/*")) {
            try {
                String id = toscaTemplateService.saveFile(file);
                return new ResponseEntity<>(id, HttpStatus.OK);
            } catch (IOException e) {
                java.util.logging.Logger.getLogger(ToscaTemplateApiController.class.getName()).log(Level.SEVERE, null, e);
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @Override
    public ResponseEntity<List<String>> getToscaTemplateIDs() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            List<String> ids = toscaTemplateService.getAllIds();
            return new ResponseEntity<>(ids, HttpStatus.NOT_IMPLEMENTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

}
