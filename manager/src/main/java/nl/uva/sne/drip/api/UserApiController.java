package nl.uva.sne.drip.api;

import nl.uva.sne.drip.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Level;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-10T17:15:46.465Z")

@Controller
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<String> createUser(
            @ApiParam(value = "Created user object", required = true)
            @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<>(objectMapper.readValue("\"\"", String.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                java.util.logging.Logger.getLogger(UserApiController.class.getName()).log(Level.SEVERE, null, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<String> userLoginGet(@NotNull @ApiParam(value = "The user name for login", required = true) @Valid @RequestParam(value = "username", required = true) String username, @NotNull @ApiParam(value = "The password for login in clear text", required = true) @Valid @RequestParam(value = "password", required = true) String password) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<>(objectMapper.readValue("\"\"", String.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                java.util.logging.Logger.getLogger(ProvisionerApiController.class.getName()).log(Level.SEVERE, null, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> userLogoutGet() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> userUsernameDelete(@ApiParam(value = "The name that needs to be deleted", required = true) @PathVariable("username") String username) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<User> userUsernameGet(@ApiParam(value = "The name that needs to be fetched. Use user1 for testing. ", required = true) @PathVariable("username") String username) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{  \"firstName\" : \"firstName\",  \"lastName\" : \"lastName\",  \"password\" : \"password\",  \"userStatus\" : 6,  \"id\" : 0,  \"email\" : \"email\",  \"username\" : \"username\"}", User.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                java.util.logging.Logger.getLogger(ProvisionerApiController.class.getName()).log(Level.SEVERE, null, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> userUsernamePut(
            @ApiParam(value = "name that need to be updated", required = true)
            @PathVariable("username") String username,
            @ApiParam(value = "Updated user object", required = true)
            @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
