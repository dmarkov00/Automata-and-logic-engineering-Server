package ale.server.controllers;

import ale.server.ale.calculations.assignments.AssignmentOne;
import ale.server.models.AssignmentOneResult;
import ale.server.models.Formula;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@RestController
public class AssignmentController {

    @PostMapping("/calculate/{nr}")
    public AssignmentOneResult calculateResult(@PathVariable("nr") long assignmentNr, @RequestBody Formula formula, HttpServletResponse response)

    {
        String parsedFormula = "";
        try {
            parsedFormula = java.net.URLDecoder.decode(formula.getFormula(), "UTF-8");
            System.out.println(parsedFormula);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        AssignmentOneResult result = AssignmentOne.getAssignmentOneResult(parsedFormula);

        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        return result;
    }

}

