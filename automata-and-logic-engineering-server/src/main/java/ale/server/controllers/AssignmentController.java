package ale.server.controllers;

import ale.server.ale.calculations.Assignments;
import ale.server.models.AssignmentResult;
import ale.server.models.Formula;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
public class AssignmentController {

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/calculate/{nr}")
    public AssignmentResult calculateResult(@PathVariable("nr") int assignmentNr, @RequestBody Formula formula)

    {
        Assignments assignments = new Assignments();


        return assignments.generateAssignmentResult(formula, assignmentNr);
    }

}

