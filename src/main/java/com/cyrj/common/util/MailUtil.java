package com.cyrj.common.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * @author 
 * @function 邮件发送工具类
 */
@Component
public class MailUtil {

    private static String MAIL_SENDER ="hzh2@zsit.cc"; //邮件发送者


    private static Logger logger = LoggerFactory.getLogger(MailUtil.class);

    /**
     * 发送一个简单格式的邮件
     *
     * @param recipient //邮件接收人
     * @param subject //邮件主题
     * @param content //邮件内容
     * 
     */
    public static void sendSimpleMail(JavaMailSender javaMailSender,String[] recipient,String subject,String content) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //邮件发送人
            simpleMailMessage.setFrom(MAIL_SENDER);
            //邮件接收人
            simpleMailMessage.setTo(recipient);
            //邮件主题
            simpleMailMessage.setSubject(subject);
            //邮件内容
            simpleMailMessage.setText(content);
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }

    /**
     * 发送一个HTML格式的邮件
     *
     * @param recipient //邮件接收人
     * @param subject //邮件主题
     * @param content //邮件内容
     */
    public static void sendHTMLMail(JavaMailSender javaMailSender,String recipient,String subject,String content) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(recipient);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);
            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            System.out.println(e);
            logger.error("邮件发送失败：{}", e.getMessage());
        }
    }

    /**
     * 发送带附件格式的邮件
     *
     * @param recipient //邮件接收人
     * @param subject //邮件主题
     * @param content //邮件内容
     * @param fileUrl //附件url地址
     */
    public static void sendAttachmentMail(JavaMailSender javaMailSender,String recipient,String subject,String content,String fileUrl) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(recipient);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);
            //文件路径
            FileSystemResource file = new FileSystemResource(new File(fileUrl));
            mimeMessageHelper.addAttachment("mail.png", file);

            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }
}
