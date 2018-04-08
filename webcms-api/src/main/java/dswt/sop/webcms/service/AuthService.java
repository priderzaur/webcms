package dswt.sop.webcms.service;

import dswt.sop.webcms.dto.Role;
import dswt.sop.webcms.dto.Session;
import dswt.sop.webcms.dto.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthService {

    public ResponseEntity<String> createNewUserService(User user){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/webcms/api/auth/test",
                user.getEmail(), String.class);
        System.out.println(response.getBody());
        return response;
    }

    public List<Session> getUserSessions(String userId){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Session[]> responseEntity = restTemplate.getForEntity("/webcms/api/auth/users/"+userId+"/sessions", Session[].class);
        Session[] objects = (Session[])responseEntity.getBody();//??
        MediaType contentType = responseEntity.getHeaders().getContentType();
        HttpStatus statusCode = responseEntity.getStatusCode();
        List<Session> sessionList = Arrays.asList(objects);
        return sessionList;
    }

    public ResponseEntity<User> modifyUser(int userId, User user, String method) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI("/webcms/api/auth/users/"+userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<User> response;
        switch (method){
            case "PUT":
                response = restTemplate.exchange(uri, HttpMethod.PUT, entity, User.class);
                break;
            case "PATCH":
                response = restTemplate.exchange(uri, HttpMethod.PATCH, entity, User.class);
                break;
            case "DELETE":
                restTemplate.delete(uri);
                response = new ResponseEntity<User>(HttpStatus.OK);
                break;
            default:
                //throw custom exc?
                response = new ResponseEntity<User>(HttpStatus.OK);
                break;
        }
        return response;
    }

    public List<Role> getUserRoles(String userId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Role[]> responseEntity = restTemplate.getForEntity("/webcms/api/auth/users/"+userId+"/roles", Role[].class);
        Role[] roles = responseEntity.getBody();
        //MediaType contentType = responseEntity.getHeaders().getContentType();
        //HttpStatus statusCode = responseEntity.getStatusCode();
        List<Role> roleList = Arrays.asList(roles);
        return roleList;
    }

    public ResponseEntity<User> setUserRoles(String userId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> response = restTemplate.postForEntity("/webcms/api/auth/users/"+userId+"/roles",
                null, User.class);//???
        System.out.println(response.getBody());
        return response;
    }
}
