package edu.hubu.learn.web;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.Flower;
import edu.hubu.learn.service.FlowerService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
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

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        flowerService.deleteFlower(id);
        ModelAndView mav = new ModelAndView("redirect:/flower/list");
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
        @RequestMapping("/add")
        public ModelAndView addFlower(){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("flower_add");
            return mav;
    }
    @RequestMapping("/do_add")
        public ModelAndView doAddFlower(Flower flower){
            flower.setAvatar("");
            flowerService.addFlower(flower);
            ModelAndView mav = new ModelAndView("redirect:/flower/list");
            return mav;
        }

        @RequestMapping("/modify/{id}")
        public ModelAndView modifyFlower(@PathVariable Long id) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("flower", flowerService.getFlower(id));
            mav.setViewName("flower_modify");
            return mav;
        }
    
        @RequestMapping("/do_modify")
        public ModelAndView doModifyFlower(Flower flower) {
            flower.setAvatar("");
            flowerService.modifyFlower(flower);
            ModelAndView mav = new ModelAndView("redirect:/flower/list");
            return mav;
        }
    
        @RequestMapping("/search")
        public ModelAndView searchFlower() {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("flower_search");
            return mav;
        }
    
        @RequestMapping("/do_search")
        public ModelAndView doSearchFlower(HttpServletRequest httpRequest) {
            ModelAndView mav = new ModelAndView();
            String keyword = httpRequest.getParameter("keyword");
            List<Flower> flowers = flowerService.searchFlowers(keyword);
            mav.addObject("flowers", flowers);
            mav.setViewName("flowers");
            return mav;
        }
        @RequestMapping("/add_avatar/{id}")
    public ModelAndView addFlowerAvatar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("flower", flowerService.getFlower(id));
        mav.setViewName("flower_add_avatar");
        return mav;
    }

    @RequestMapping("/do_add_avatar/{id}")
    public ModelAndView doAddFlowerAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable Long id) {
        try {
            String fileName = file.getOriginalFilename();
            String filePath = ResourceUtils.getURL("classpath:").getPath() + "../../../resources/main/static/";
            File dest = new File(filePath + fileName);
            log.info(dest.getAbsolutePath());
            file.transferTo(dest);
            Flower flower = flowerService.getFlower(id);
            flower.setAvatar(fileName);
            flowerService.modifyFlower(flower);
        } catch (Exception e) {
            log.error("upload avatar error", e.getMessage());
        }
        return new ModelAndView("redirect:/flower/list");
    }

}