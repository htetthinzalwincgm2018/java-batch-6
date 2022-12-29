package com.cgm.crud.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.crud.form.ResetForm;
import com.cgm.crud.service.PasswordService;

@Controller
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = { "/passwordReset" }, method = RequestMethod.GET)
    public ModelAndView passwordResetForm() {
        ModelAndView mv = new ModelAndView("passwordReset");
        ResetForm resetForm = new ResetForm();
        mv.addObject("resetForm", resetForm);

        return mv;
    }

    @RequestMapping(value = { "/actionPasswordReset" }, method = RequestMethod.POST)
    public ModelAndView passwordReset(@Valid @ModelAttribute("resetForm") ResetForm resetForm, BindingResult result,
            HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("passwordReset");

        if (result.hasErrors()) {
            return mv;
        }

        resetForm = passwordService.createResetToken(resetForm.getEmail());

        String url = getBaseUrl(request) + request.getServletPath() + '/' + resetForm.getToken();
        try {
            this.sendMail(url, resetForm);
        } catch (UnsupportedEncodingException | MessagingException e) {

        }

        mv.setViewName("redirect:/");

        return mv;
    }

    private void sendMail(String url, @Valid ResetForm resetForm)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("htetthinzarlwin.cgm2018@gmail.com", "CGM Support");
        helper.setTo(resetForm.getEmail());

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + url
                + "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    private String getBaseUrl(HttpServletRequest request) {
        String url = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 0) {
            url = url + ":" + request.getServerPort();
        }
        url = url + request.getContextPath();
        return url;
    }
}
