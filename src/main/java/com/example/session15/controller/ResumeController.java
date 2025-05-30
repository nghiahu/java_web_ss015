package com.example.session15.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.session15.model.Resume;
import com.example.session15.service.resume.ResumeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class ResumeController {
    @Autowired
    private ResumeServiceImp resumeService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("addResume")
    public String addResume(Model model) {
        model.addAttribute("resume", new Resume());
        return "addResume";
    }

    @GetMapping("listResume")
    public String listResume(Model model) {
        model.addAttribute("resumes", resumeService.getAllResumes());
        return "listResume";
    }

    @PostMapping("save-resume")
    public String saveResume(@Valid @ModelAttribute("resume") Resume resume, BindingResult bindingResult,
                             @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "addResume";
        }
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) uploadResult.get("url");
            if (url == null && url.isEmpty()) {
                throw  new RuntimeException("Invalid URL");
            }
            resume.setImageUrl(url);
            resumeService.addResume(resume);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/listResume";
    }

    @GetMapping("remove-resume")
    public String removeResume(@RequestParam("id") int id) {
        resumeService.removeResume(id);
        return "redirect:/listResume";
    }

    @GetMapping("updateResume")
    public String updateResume(@RequestParam("id") int id, Model model) {
        model.addAttribute("resume", resumeService.getResume(id));
        return "updateResume";
    }

    @PostMapping("update")
    public String update(@Valid @ModelAttribute("resume") Resume resume, BindingResult bindingResult,
                         @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "updateResume";
        }
        try {
            if (file != null && !file.isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                String url = (String) uploadResult.get("url");
                if (url == null || url.isEmpty()) {
                    throw new RuntimeException("Invalid URL");
                }
                resume.setImageUrl(url);
            } else {
                Resume oldResume = resumeService.getResume(resume.getId());
                resume.setImageUrl(oldResume.getImageUrl());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resumeService.updateResume(resume);
        return "redirect:/listResume";
    }
}
