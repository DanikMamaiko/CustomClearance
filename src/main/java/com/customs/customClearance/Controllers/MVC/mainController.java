package com.customs.customClearance.Controllers.MVC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class mainController {

    @GetMapping("/")
    public String getCarAge(Model model){

        List<String> carAgeList = new ArrayList<>();
        carAgeList.add("До 3-ёх лет");
        carAgeList.add("От 3-ёх до 5-ти лет");
        carAgeList.add("От 5-ти лет");

        model.addAttribute("carAgeList", carAgeList);

        return "backend/main";
    }

    @GetMapping("/calculate")
    public String calculateCarCost(
            @RequestParam("age") String age,
            @RequestParam("engineType") String engineType,
            @RequestParam("volume") String volume,
            @RequestParam("cost") String cost,
            @RequestParam("entity") String entity,
            @RequestParam("fuel") String fuel,
            @RequestParam(value = "withPrivileges", required = false) boolean withPrivileges,
            Model model
    ){

        model.addAttribute("age", age);
        model.addAttribute("engineType", engineType);
        model.addAttribute("volume", volume);
        model.addAttribute("cost", cost);
        model.addAttribute("entity", entity);
        model.addAttribute("fuel", fuel);
        model.addAttribute("withPrivileges", withPrivileges);

        double carDoubleCost = Double.parseDouble(cost);
        double carDoubleVolume = Double.parseDouble(volume);

        double clearanceCost = 0;

        if(entity.equals("Физ.лицо")){

            if(age.equals("До 3-ёх лет")){

                clearanceCost = (carDoubleCost <= 8500) ?
                        0.54 * carDoubleCost > 2.5 * carDoubleVolume ? 0.54 * carDoubleCost : 2.5 * carDoubleVolume
                        : (carDoubleCost <= 16700) ?
                        0.48 * carDoubleCost > 3.5 * carDoubleVolume ? 0.48 * carDoubleCost : 3.5 * carDoubleVolume
                        : (carDoubleCost <= 42300) ?
                        0.48 * carDoubleCost > 5.5 * carDoubleVolume ? 0.48 * carDoubleCost : 5.5 * carDoubleVolume
                        : (carDoubleCost <= 84500) ?
                        0.48 * carDoubleCost > 7.5 * carDoubleVolume ? 0.48 * carDoubleCost : 7.5 * carDoubleVolume
                        : (carDoubleCost <= 169000) ?
                        0.48 * carDoubleCost > 15 * carDoubleVolume ? 0.48 * carDoubleCost : 15 * carDoubleVolume
                        : 0.48 * carDoubleCost > 20 * carDoubleVolume ? 0.48 * carDoubleCost : 20 * carDoubleVolume;

                // Check Engine type
                clearanceCost = (engineType.equals("Топливо")) ? clearanceCost : 0;

                // Check Discount due to privileges
                clearanceCost = (withPrivileges) ? clearanceCost/2 : clearanceCost;


            } else if(age.equals("От 3-ёх до 5-ти лет")) {

                clearanceCost = (carDoubleVolume <= 1000) ? 1.5 * carDoubleVolume
                        : (carDoubleVolume <= 1500) ? 1.7 * carDoubleVolume
                        : (carDoubleVolume <= 1800) ? 2.5 * carDoubleVolume
                        : (carDoubleVolume <= 2300) ? 2.7 * carDoubleVolume
                        : (carDoubleVolume <= 3000) ? 3.0 * carDoubleVolume
                        : carDoubleVolume * 3.6;

                // Check Engine type
                clearanceCost = (engineType.equals("Топливо")) ? clearanceCost : 0;

                // Check Discount due to privileges
                clearanceCost = (withPrivileges) ? clearanceCost/2 : clearanceCost;

            } else {

                clearanceCost = (carDoubleVolume <= 1000) ? 3.0 * carDoubleVolume
                        : (carDoubleVolume <= 1500) ? 3.2 * carDoubleVolume
                        : (carDoubleVolume <= 1800) ? 3.5 * carDoubleVolume
                        : (carDoubleVolume <= 2300) ? 4.8 * carDoubleVolume
                        : (carDoubleVolume <= 3000) ? 5.0 * carDoubleVolume
                        : carDoubleVolume * 5.7;

                // Check Engine type
                clearanceCost = (engineType.equals("Топливо")) ? clearanceCost : 0;

                // Check Discount due to privileges
                clearanceCost = (withPrivileges) ? clearanceCost/2 : clearanceCost;

            }

        } else {

            if(fuel.equals("Бензин")){

                if(age.equals("До 3-ёх лет")) {

                    clearanceCost = (carDoubleVolume <= 1000) ?
                            0.3 * carDoubleCost > 1.2 * carDoubleVolume ? 0.3 * carDoubleCost : 1.2 * carDoubleVolume
                            : (carDoubleVolume <= 1500) ?
                            0.3 * carDoubleCost > 1.45 * carDoubleVolume ? 0.3 * carDoubleCost : 1.45 * carDoubleVolume
                            : (carDoubleVolume <= 1800) ?
                            0.3 * carDoubleCost > 1.5 * carDoubleVolume ? 0.3 * carDoubleCost : 1.5 * carDoubleVolume
                            : (carDoubleVolume <= 3000) ?
                            0.3 * carDoubleCost > 2.15 * carDoubleVolume ? 0.3 * carDoubleCost : 2.15 * carDoubleVolume
                            : 0.3 * carDoubleCost > 2.8 * carDoubleVolume ? 0.3 * carDoubleCost : 2.8 * carDoubleVolume;

                    // Check Engine type
                    clearanceCost = (engineType.equals("Топливо")) ? clearanceCost : 0;

                } else if(age.equals("От 3-ёх до 5-ти лет")) {

                    clearanceCost = (carDoubleVolume <= 1000) ?
                            0.35 * carDoubleCost > 1.2 * carDoubleVolume ? 0.35 * carDoubleCost : 1.2 * carDoubleVolume
                            : (carDoubleVolume <= 1500) ?
                            0.35 * carDoubleCost > 1.45 * carDoubleVolume ? 0.35 * carDoubleCost : 1.45 * carDoubleVolume
                            : (carDoubleVolume <= 1800) ?
                            0.35 * carDoubleCost > 1.5 * carDoubleVolume ? 0.35 * carDoubleCost : 1.5 * carDoubleVolume
                            : (carDoubleVolume <= 3000) ?
                            0.35 * carDoubleCost > 2.15 * carDoubleVolume ? 0.35 * carDoubleCost : 2.15 * carDoubleVolume
                            : 0.35 * carDoubleCost > 2.8 * carDoubleVolume ? 0.35 * carDoubleCost : 2.8 * carDoubleVolume;

                    // Check Engine type
                    clearanceCost = (engineType.equals("Топливо")) ? clearanceCost : 0;

                } else {

                    clearanceCost = (carDoubleVolume <= 1000) ? 2.5 * carDoubleVolume
                            : (carDoubleVolume <= 1500) ? 2.7 * carDoubleVolume
                            : (carDoubleVolume <= 1800) ? 2.9 * carDoubleVolume
                            : (carDoubleVolume <= 3000) ? 4.0 * carDoubleVolume
                            : 5.8 * carDoubleVolume;

                    // Check Engine type
                    clearanceCost = (engineType.equals("Топливо")) ? clearanceCost : 0;

                }
            // if Disel
            } else {

                if(age.equals("До 3-ёх лет")) {

                    clearanceCost = (carDoubleVolume <= 1500) ?
                            0.3 * carDoubleCost > 1.45 * carDoubleVolume ? 0.3 * carDoubleCost : 1.45 * carDoubleVolume
                            : (carDoubleVolume <= 2500) ?
                            0.3 * carDoubleCost > 1.9 * carDoubleVolume ? 0.3 * carDoubleCost : 1.9 * carDoubleVolume
                            : 0.3 * carDoubleCost > 2.8 * carDoubleVolume ? 0.3 * carDoubleCost : 2.8 * carDoubleVolume;

                    // Check Engine type
                    clearanceCost = (engineType.equals("Топливо")) ? clearanceCost : 0;

                } else if (age.equals("От 3-ёх до 5-ти лет")) {

                    clearanceCost = (carDoubleVolume <= 1500) ?
                            0.35 * carDoubleCost > 1.45 * carDoubleVolume ? 0.35 * carDoubleCost : 1.45 * carDoubleVolume
                            : (carDoubleVolume <= 2500) ?
                            0.35 * carDoubleCost > 2.15 * carDoubleVolume ? 0.35 * carDoubleCost : 2.15 * carDoubleVolume
                            : 0.35 * carDoubleCost > 2.8 * carDoubleVolume ? 0.35 * carDoubleCost : 2.8 * carDoubleVolume;

                    // Check Engine type
                    clearanceCost = (engineType.equals("Топливо")) ? clearanceCost : 0;

                } else {

                    clearanceCost = (carDoubleVolume <= 1500) ? 2.7 * carDoubleVolume
                            : (carDoubleVolume <= 2500) ? 4.0 * carDoubleVolume
                            : 5.8 * carDoubleVolume;

                    // Check Engine type
                    clearanceCost = (engineType.equals("Топливо")) ? clearanceCost : 0;

                }

            }

        }


        model.addAttribute("clearanceCost", clearanceCost);

        return "backend/result";

    }

}
