package com.oakhole.core.email;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.thymeleaf.context.Context;

/**
 * Created by oakhole on 2014-03-25 06:40.
 */
@SuppressWarnings("ALL")
@DirtiesContext
@ContextConfiguration(locations = {"/applicationContext.xml", "/email/applicationContext-email.xml"})
public class MailServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private MimeMailService mimeMailService;



    @Test
    public void sendMimeMail() {
        Context context = new Context();
        context.setVariable("username","admin");
        context.setVariable("data","spring");
        context.setVariable("context", "context");
        mimeMailService.setTemplate("email2.html");
        mimeMailService.setTo("18652023713@wo.com.cn");
        mimeMailService.setSubject("Service Mail");
        mimeMailService.setContext(context);
        mimeMailService.sendNotificationMail();
    }
}
