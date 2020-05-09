/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.10).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package nl.uva.sne.drip.api;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-06T13:31:49.386Z")

@Api(value = "tosca_template", description = "the tosca_template API")
public interface ToscaTemplateApi {

    @ApiOperation(value = "Deletes a tosca topology template", nickname = "deleteToscaTemplateByID", notes = "If the topology is provisoned it will delete the provison (Infrastructure). If it is deployed it will delete the deploymet too (Application)", response = String.class, authorizations = {
        @Authorization(value = "auth", scopes = {
            @AuthorizationScope(scope = "read:ToscaTemplate", description = "read your topolog template")
            ,
            @AuthorizationScope(scope = "write:ToscaTemplate", description = "modify topolog template in your account")
        })
    }, tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = String.class)
        ,
        @ApiResponse(code = 400, message = "Invalid ID supplied")
        ,
        @ApiResponse(code = 404, message = "ToscaTemplate not found")})
    @RequestMapping(value = "/manager/tosca_template/{id}",
            method = RequestMethod.DELETE)
    ResponseEntity<String> deleteToscaTemplateByID(@ApiParam(value = "ID of topology template to return", required = true) @PathVariable("id") String id, @ApiParam(value = "The node(s) to delete") @Valid @RequestParam(value = "node_name", required = false) List<String> nodeName);

    @ApiOperation(value = "Find topolog template by ID", nickname = "getToscaTemplateByID", notes = "Returns a single topolog template", response = String.class, authorizations = {
        @Authorization(value = "auth", scopes = {
            @AuthorizationScope(scope = "read:ToscaTemplate", description = "read your topolog template")
            ,
            @AuthorizationScope(scope = "write:ToscaTemplate", description = "modify topolog template in your account")
        })
    }, tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = String.class)
        ,
        @ApiResponse(code = 400, message = "Invalid ID supplied")
        ,
        @ApiResponse(code = 404, message = "ToscaTemplate not found")
        ,
        @ApiResponse(code = 405, message = "Invalid input")})
    @RequestMapping(value = "/manager/tosca_template/{id}",
            produces = {"text/plain"},
            method = RequestMethod.GET)
    ResponseEntity<String> getToscaTemplateByID(@ApiParam(value = "ID of topolog template to return", required = true) @PathVariable("id") String id);

    @ApiOperation(value = "Get all topolog template IDs", nickname = "getToscaTemplateIDs", notes = "Returns all IDs", response = String.class, responseContainer = "List", authorizations = {
        @Authorization(value = "auth", scopes = {
            @AuthorizationScope(scope = "read:ToscaTemplate", description = "read your topolog template")
            ,
            @AuthorizationScope(scope = "write:ToscaTemplate", description = "modify topolog template in your account")
        })
    }, tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = String.class, responseContainer = "List")
        ,
        @ApiResponse(code = 400, message = "Invalid ID supplied")
        ,
        @ApiResponse(code = 404, message = "ToscaTemplate not found")
        ,
        @ApiResponse(code = 405, message = "Invalid input")})
    @RequestMapping(value = "/manager/tosca_template/ids",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<String>> getToscaTemplateIDs();

    @ApiOperation(value = "Updates exisintg topolog template", nickname = "updateToscaTemplateByID", notes = "", response = String.class, authorizations = {
        @Authorization(value = "auth", scopes = {
            @AuthorizationScope(scope = "read:ToscaTemplate", description = "read your topolog template")
            ,
            @AuthorizationScope(scope = "write:ToscaTemplate", description = "modify topolog template in your account")
        })
    }, tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = String.class)
        ,
        @ApiResponse(code = 400, message = "Invalid ID supplied")
        ,
        @ApiResponse(code = 404, message = "ToscaTemplate not found")
        ,
        @ApiResponse(code = 405, message = "Invalid input")})
    @RequestMapping(value = "/manager/tosca_template/{id}",
            produces = {"text/plain"},
            consumes = {"multipart/form-data"},
            method = RequestMethod.PUT)
    ResponseEntity<String> updateToscaTemplateByID(@ApiParam(value = "ID of topolog template to return", required = true) @PathVariable("id") String id, @ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile file);

    @ApiOperation(value = "upload a tosca template description file", nickname = "uploadToscaTemplate", notes = "uploads and validates TOSCA template file", response = String.class, authorizations = {
        @Authorization(value = "auth", scopes = {
            @AuthorizationScope(scope = "read:ToscaTemplate", description = "read your topolog template")
            ,
            @AuthorizationScope(scope = "write:ToscaTemplate", description = "modify topolog template in your account")
        })
    }, tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = String.class)
        ,
        @ApiResponse(code = 405, message = "Invalid input")})
    @RequestMapping(value = "/manager/tosca_template",
            consumes = {"multipart/form-data"},
            method = RequestMethod.POST)
    ResponseEntity<String> uploadToscaTemplate(@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile file);

}
