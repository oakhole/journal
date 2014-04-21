/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.oakhole.core.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

/**
 * MIME邮件服务类.
 * <p/>
 * 演示由Freemarker引擎生成的的html格式邮件, 并带有附件.
 *
 * @author calvin
 */
@SuppressWarnings("ALL")
public class MimeMailService {

    private static final String DEFAULT_ENCODING = "utf-8";
    private static Logger logger = LoggerFactory.getLogger(MimeMailService.class);
    private JavaMailSender mailSender;
    private TemplateEngine templateEngine;
    private String template;
    private String attachment;
    private String subject;
    private Context context;
    private String from;
    private String to;

    /**
     * 发送MIME格式的用户修改通知邮件.
     */
    public void sendNotificationMail() {

        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, DEFAULT_ENCODING);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            //添加文本内容
            String content = generateContent();
            helper.setText(content, true);
            //添加附件
            File attachment = generateAttachment();
            helper.addAttachment(attachment.getName(), attachment);

            mailSender.send(msg);
            logger.info("HTML版邮件已发送至{}",to);
        } catch (MessagingException e) {
            logger.error("构造邮件失败", e);
        } catch (Exception e) {
            logger.error("发送邮件失败", e);
        }
    }

    /**
     * 使用Freemarker生成html格式内容.
     */
    private String generateContent() throws MessagingException {
        return templateEngine.process(template, context);
    }

    /**
     * 获取classpath中的附件.
     */
    private File generateAttachment() throws MessagingException {
        try {
            Resource resource = new ClassPathResource(attachment);
            return resource.getFile();
        } catch (IOException e) {
            logger.error("构造邮件失败,附件文件不存在", e);
            throw new MessagingException("附件文件不存在", e);
        }
    }

    /**
     * Spring的MailSender.
     */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * thymeleaf的邮件模板引擎
     *
     * @param templateEngine
     */
    public void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
