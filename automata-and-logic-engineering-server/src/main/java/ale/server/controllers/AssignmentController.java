package ale.server.controllers;

import ale.server.ale.calculations.assignments.AssignmentOne;
import ale.server.models.AssignmentOneResult;
import ale.server.models.AssignmentResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class AssignmentController {

    @PostMapping("/calculate/{nr}")
    public AssignmentOneResult calculateResult(@PathVariable("nr") long assignmentNr, @RequestBody String formula) {

        AssignmentOneResult result = AssignmentOne.getAssignmentOneResult(formula);

        return result;
    }

}

