package com.napa.OpenWorld.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
    @GetMapping("/")
    public String index() {
        return "main/index";
    }
    @GetMapping("/logiin")
    public String logiin() {
        return "user/logiin";
    }
    @GetMapping("/logiiin")
    public String logiiin() {
        return "user/logiiin";
    }
    @GetMapping("/photo")
    public String photo() {
        return "/index2";
    }
    @GetMapping("/photo2")
    public String photo2() {
        return "/index3";
    }
    @GetMapping("/images")
    public String images() {
        return "uploadphoto/images";
    }
    @GetMapping("/imagedetails")
    public String imagedetails() {
        return "uploadphoto/imagedetails";
    }
    @GetMapping("/images3")
    public String images3() {
        return "/images3";
    }
    @GetMapping("/imagedetails3")
    public String imagedetails3() {
        return "/imagedetails3";
    }
    @GetMapping("/MainAdminPage")
    public String MainAdminPage() {
        return "admin/MainAdminPage";
    }
    @GetMapping("/beginner")
    public String beginner() {
        return "books/beginner";
    }
    @GetMapping("/elementary")
    public String elementary() {
        return "books/elementary";
    }
    @GetMapping("/preintermediate")
    public String preintermediate() {return "books/pre-intermediate";}
    @GetMapping("/ourphotos")
    public String ourphotos() {return "/ourphotos";}

}

