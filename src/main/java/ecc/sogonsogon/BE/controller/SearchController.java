package ecc.sogonsogon.BE.controller;

import ecc.sogonsogon.BE.entity.Place;
import ecc.sogonsogon.BE.repository.PlaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class SearchController {
    @Autowired
    private PlaceRepository placeRepository;

    @GetMapping("/place/search")
    public String searchPlaces(@RequestParam List<String> categories,Model model) {
        List<Place> places = placeRepository.findByCategoryNames(categories, categories.size());
        model.addAttribute("places", places);
        return "";
    }

}
