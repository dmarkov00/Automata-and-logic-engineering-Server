package ale.server.controllers;

import ale.server.models.AssignmentResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class AssignmentController {

    @PostMapping("/calculate/{nr}")
    public String calculateResult(@PathVariable("nr") long assignmentNr, @RequestBody String formula) {

        return formula + assignmentNr;
    }

}

