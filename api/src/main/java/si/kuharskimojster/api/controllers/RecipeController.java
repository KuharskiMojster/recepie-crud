package si.kuharskimojster.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.kuharskimojster.api.model.ResponseModel;
;

@RestController
@RequestMapping("/api/v1")
public class RecipeController {


    @GetMapping("/health")
    public ResponseEntity<ResponseModel> getHealth() {
        return new ResponseEntity<>(new ResponseModel("Healt check OK", HttpStatus.OK.value()), HttpStatus.OK);
    }

}
