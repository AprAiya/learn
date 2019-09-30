package edu.hubu.learn.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import edu.hubu.learn.entity.Flower;
import edu.hubu.learn.service.FlowerService;

@Controller
@RequestMapping("/flower")
public class FlowerController {

    @Autowired
    private FlowerService flowerService;

    @RequestMapping("/{id}")
    public ModelAndView flower(@PathVariable long id) {
        ModelAndView mav = new ModelAndView();
        Flower flower = flowerService.getFlower(id);
        mav.addObject("flower", flower);
        mav.setViewName("flower");
        return mav;
    }

    @RequestMapping("/list")
    public ModelAndView flowers(){
        ModelAndView mav = new ModelAndView();
        List<Flower> flowers = flowerService.getFlowers();
        mav.addObject("flowers", flowers);
        mav.setViewName("flowers");
        return mav;
    }
}