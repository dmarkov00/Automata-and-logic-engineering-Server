package ale.server.controllers;

import ale.server.ale.calculations.utils.AssignmentsManager;
import ale.server.models.AssignmentResult;
import ale.server.models.Formula;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AssignmentController {

    @CrossOrigin(origins = "https://automata-and-logic-engineering.herokuapp.com")
    @PostMapping(path = "/calculate/{nr}")
    public ResponseEntity calculateResult(@PathVariable("nr") int assignmentNr, @RequestBody Formula formula)

    {
        AssignmentsManager assignmentsManager = new AssignmentsManager();

        // Get result based on the formula and the assignment number
        AssignmentResult assignmentResult = assignmentsManager.generateAssignmentResult(formula, assignmentNr);

        if (assignmentResult == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            return ResponseEntity.ok(assignmentResult);
        }
    }

}

