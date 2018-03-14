package ale.server.controllers;

import ale.server.ale.calculations.AssignmentsManager;
import ale.server.models.AssignmentResult;
import ale.server.models.Formula;
import org.springframework.web.bind.annotation.*;

@RestController
public class AssignmentController {

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/calculate/{nr}")
    public AssignmentResult calculateResult(@PathVariable("nr") int assignmentNr, @RequestBody Formula formula)

    {
        AssignmentsManager assignmentsManager = new AssignmentsManager();


        return assignmentsManager.generateAssignmentResult(formula, assignmentNr);
    }

}

