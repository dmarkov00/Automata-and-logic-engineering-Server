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
    public AssignmentResult calculateResult(@PathVariable("nr") long assignmentNr, @RequestBody Formula formula)

    {
        String parsedFormula = "";
        try {
            parsedFormula = java.net.URLDecoder.decode(formula.getFormula(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        AssignmentResult result = Assignments.generateAssignmentOneResult(parsedFormula);

        return result;
    }

}

