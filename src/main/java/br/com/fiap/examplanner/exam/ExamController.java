package br.com.fiap.examplanner.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/exam")
public class ExamController {

  @Autowired
  ExamService service;

  @Autowired
  MessageSource messageSource;

  @GetMapping
  public String index(Model model, @AuthenticationPrincipal OAuth2User user){
    model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
    model.addAttribute("username", user.getAttribute("name"));
    model.addAttribute("exams", service.findAll());
    return "exam/index";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable Long id, RedirectAttributes redirect){
    if(service.delete(id)){
      redirect.addFlashAttribute("success", getMessage("exam.delete.success"));
    }else{
      redirect.addFlashAttribute("error", getMessage("exam.notfound"));
    }
    return "redirect:/exam";
  }

  @GetMapping("/new")
  public String form(Exam exam){
    return "exam/form";
  }

  @PostMapping
  public String Save(@Valid Exam exam, BindingResult result, RedirectAttributes redirect){
    if (result.hasErrors()) return "/exam/form";
    service.save(exam);
    redirect.addFlashAttribute("success", getMessage("exam.create.sucess"));
    return "redirect:/exam";
  }

  private String getMessage(String code){
    return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
  }
}
