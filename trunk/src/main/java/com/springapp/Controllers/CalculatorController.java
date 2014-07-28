/*
package com.springapp.Controllers;

import com.springapp.Calculation.DataInputForm;
import com.springapp.Calculation.PiCalculator;
import com.springapp.mvc.CalculationValidator;
import com.springapp.mvc.RegisterForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

*/
/**
 * Created by Enot on 04.07.14.
 *//*

@Controller
@RequestMapping(value = "calculation")
@SessionAttributes("dataInputForm")
public class CalculatorController {
    private static final Logger logger = Logger.getLogger(CalculatorController.class);
    protected static int numberOfThreads;
    protected static int precision;
    protected static long elapsedTime;

    public CalculatorController() {
    }

    @Qualifier("piCalculator")
    @Autowired
    private PiCalculator piCalculator;

    @Qualifier("calculationValidator")
    @Autowired
    private CalculationValidator calculationValidator;

    public CalculatorController(CalculationValidator calculationValidator) {
        this.calculationValidator = calculationValidator;
    }

    @InitBinder("dataInputForm")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(calculationValidator);
//        binder.setValidator(new LoginValidator());
    }

  */
/*  @RequestMapping(value = "/GET", method = RequestMethod.GET)
//    @RequestMapping(method = RequestMethod.GET)
    private String getThreadsAndPrecision(ModelMap model) {
        model.addAttribute("dataInputForm", new DataInputForm());
        return "calculation";
    }  *//*




    @RequestMapping(value = "/GET", method = RequestMethod.GET)
    private ModelAndView getThreadsAndPrecision() {
        return new ModelAndView("calculation", "dataInputForm", new DataInputForm());
    }

    //    @RequestMapping(value = "/call.do", method = RequestMethod.POST)
//    @RequestMapping(method = RequestMethod.POST)
    @RequestMapping(value = "/POST", method = RequestMethod.POST)
    public ModelAndView calculMethod(@ModelAttribute(value = "dataInputForm")
                                     DataInputForm dataInputForm,
                                     BindingResult bindingResult,
                                     Model model) throws Exception {

//        Integer numberOfThreads = Integer.valueOf((String) request.getAttribute("numberOfThreads"));
//        Integer precision = Integer.valueOf((String) request.getAttribute("precision"));
        calculationValidator.validate(dataInputForm, bindingResult);
        if (bindingResult.hasErrors()) {
            logger.error("Validation error");
            return new ModelAndView("calculation", "dataInputForm", new DataInputForm());
        }
        precision = Integer.valueOf(dataInputForm.getPrecision());
        numberOfThreads = Integer.valueOf(dataInputForm.getNumberOfThreads());
        BigDecimal result = piCalculator.call();
        model.addAttribute("elapsedTime", elapsedTime);
        model.addAttribute("result", result);
        model.addAttribute("precision", dataInputForm.getPrecision());
        model.addAttribute("numberOfThreads", dataInputForm.getNumberOfThreads());
        return new ModelAndView("calculResults");
    }
}
*/
