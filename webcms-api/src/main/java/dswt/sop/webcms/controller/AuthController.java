package dswt.sop.webcms.controller;

import dswt.sop.webcms.dto.Role;
import dswt.sop.webcms.dto.Session;
import dswt.sop.webcms.dto.User;
import dswt.sop.webcms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by riordachioaia on 04.04.2018.
 */

@RestController
@RequestMapping(value="/webcms/api/auth")
public class AuthController {

    @Autowired
    private
    AuthService authService;

    //Creates a new user.
    @PostMapping(value="/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNewUser(@RequestBody User user){
        ResponseEntity<String> response = authService.createNewUserService(user);
        return response;
    }

    @PostMapping(value="/sessions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNewSession(@RequestBody User user){//??
        //ResponseEntity<String> response = authService.createNewUserService(user);
        return null;
    }

    //Returns a list of sessions belonging to the {user_id} user
    @GetMapping(value="/users/{user_id}/sessions")
    public List<Session> getUserSessions(@PathVariable(value="user_id") String userId){
        List userSessionsList = authService.getUserSessions(userId);
        return userSessionsList;
    }

    @RequestMapping(value="/users/{user_id}", method= {RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
    public ResponseEntity<User> modifyUser(@PathVariable(value="user_id") int userId, @RequestBody User user, HttpServletRequest request) throws URISyntaxException {
        ResponseEntity<User> response = authService.modifyUser(userId, user, request.getMethod());
        return response;
    }

    @GetMapping(value="/users/{user_id}/roles")
    public List<Role> getUserRoles(@PathVariable(value="user_id") String userId){
        return authService.getUserRoles(userId);
    }

    @PostMapping(value="/users/{user_id}/roles")//how are he roles coming??
    public ResponseEntity<User> setUserRoles(@PathVariable(value="user_id") String userId){
        return authService.setUserRoles(userId);
    }

    @RequestMapping(value="/test", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testCtr(){
        return new ResponseEntity<>(HttpStatus.FOUND);
    }
}
