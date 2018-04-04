package dswt.sop.webcms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by riordachioaia on 04.04.2018.
 */

@RestController
@RequestMapping(value="/webcms/api/auth")
public class AuthController {

    @RequestMapping(value="/users", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createNewUser(){
        return new ResponseEntity.ok(HttpStatus.OK)
    }
}
