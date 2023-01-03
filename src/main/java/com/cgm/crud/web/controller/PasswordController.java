package com.cgm.crud.web.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.crud.bl.dto.EmployeeDto;
import com.cgm.crud.web.form.ResetForm;
import com.cgm.crud.web.form.ResetPasswordForm;
import com.cgm.crud.bl.service.EmployeeServices;
import com.cgm.crud.bl.service.PasswordService;

@Controller
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private EmployeeServices employeeServices;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MessageSource messageSource;

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

        EmployeeDto empDto = employeeServices.findByEmail(resetForm.getEmail());

        if (empDto == null) {
            mv.addObject("errorMsg", messageSource.getMessage("M_SC_USR_0003", null, null));
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

    @RequestMapping(value = "/actionPasswordReset/{token}", method = RequestMethod.GET)
    public ModelAndView showResetPasswordForm(@PathVariable String token) {
        ModelAndView mv = new ModelAndView("404");

        ResetForm resetForm = passwordService.getDataByToken(token);

        if (resetForm == null) {
            mv.addObject("errorMsg", messageSource.getMessage("M_SC_USR_0005", null, null));
            return mv;
        }

        if (isTokenExpired(resetForm.getExpired_at())) {
            mv.addObject("errorMsg", messageSource.getMessage("M_SC_USR_0005", null, null));
            return mv;
        }

        ResetPasswordForm newResetForm = new ResetPasswordForm();
        newResetForm.setToken(token);

        mv.setViewName("resetForm");
        mv.addObject("resetForm", newResetForm);

        return mv;
    }

    @RequestMapping(value = { "/resetPassword" }, method = RequestMethod.POST)
    public ModelAndView resetPassword(@Valid @ModelAttribute("resetForm") ResetPasswordForm resetForm,
            BindingResult result, HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("resetForm");
        if (result.hasErrors()) {
            return mv;
        }

        if (!resetForm.getPassword().equals(resetForm.getConfirm_password())) {
            mv.addObject("errorMsg", messageSource.getMessage("M_SC_USR_0007", null, null));
            return mv;
        }

        String email = passwordService.getDataByToken(resetForm.getToken()).getEmail();

        ResetForm newResetForm = new ResetForm();
        newResetForm.setEmail(email);
        newResetForm.setPassword(resetForm.getPassword());

        passwordService.updatePassword(newResetForm);

        passwordService.deleteToken(email);

        mv.setViewName("redirect:/login");
        mv.addObject("msg", messageSource.getMessage("M_SC_USR_0004", null, null));

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

    private boolean isTokenExpired(Timestamp expired_at) {
        Timestamp now = new Timestamp(new Date().getTime());
        return now.after(expired_at);

    }
}
