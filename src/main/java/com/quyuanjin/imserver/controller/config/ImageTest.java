package com.quyuanjin.imserver.controller.config;

import com.quyuanjin.imserver.constant.Constant;
import com.quyuanjin.imserver.handler.session.HibernateUtil;
import com.quyuanjin.imserver.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSession;

@Controller
@Slf4j
public class ImageTest {
    private static String UPLOAD_PATH = "File/image/upload";
    private static String UPLOAD_VOICE_PATH = "File/voice/upload";
    private static String UPLOAD_PORTRAIT_PATH = "File/portrait/upload";


    //上传图片

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImage(MultipartFile image)  {
        try {
            String name = image.getOriginalFilename();

           // System.out.println(name);

            InputStream inputStream = image.getInputStream();
            Path directory = Paths.get(UPLOAD_PATH);
            if(!Files.exists(directory)){
                Files.createDirectories(directory);

            }
           long copy = Files.copy(inputStream, directory.resolve(name));

            return Constant.URL+ "File/image/upload/"+  name;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "上传失败";
        }

    }
    //上传语音

    @RequestMapping(value = "/uploadVoice", method = RequestMethod.POST)
    @ResponseBody
    public String uploadVoice(MultipartFile voice)  {
        try {
            String name = voice.getOriginalFilename();

            // System.out.println(name);

            InputStream inputStream = voice.getInputStream();
            Path directory = Paths.get(UPLOAD_VOICE_PATH);
            if(!Files.exists(directory)){
                Files.createDirectories(directory);

            }
             Files.copy(inputStream, directory.resolve(name));

            return Constant.URL+ "File/voice/upload/"+  name;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "上传失败";
        }

    }
    //上传头像
    @RequestMapping(value = "/uploadPortrait", method = RequestMethod.POST)
    @ResponseBody
    public String uploadPortrait(MultipartFile portrait, HttpServletRequest request)  {
       try {
            String por = portrait.getOriginalFilename();
            InputStream inputStream = portrait.getInputStream();
            Path directory = Paths.get(UPLOAD_PORTRAIT_PATH);
            if(!Files.exists(directory)){
                Files.createDirectories(directory);

            }
            long copy = Files.copy(inputStream, directory.resolve(por));

            String uid = request.getParameter("userid");
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            String hql = "from User rct where rct.id=?1";
            Query q = session.createQuery(hql);
            q.setParameter(1, Long.valueOf(uid));
            User user = (User) q.uniqueResult();
            user.setPortrait(Constant.URL+ "File/portrait/upload/"+ por);
            tx.commit();
            session.close();
            //设置新头像
            HibernateUtil.add(user);

            return Constant.URL+ "File/portrait/upload/"+ por;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "上传失败";
        }
      /*   try {
            String name = portrait.getOriginalFilename();

            // System.out.println(name);

            InputStream inputStream = portrait.getInputStream();
            Path directory = Paths.get(UPLOAD_PATH);
            if(!Files.exists(directory)){
                Files.createDirectories(directory);

            }
            long copy = Files.copy(inputStream, directory.resolve(name));

            return "http://192.168.43.75:8080/File/image/upload/"+  name;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "上传失败";
        }*/

    }
    //使用流将图片输出
    @GetMapping("/getImage/{name}")
    public void getImage(HttpServletResponse response, @PathVariable("name")String name) throws IOException {
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=girls.png");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(Files.readAllBytes(Paths.get(UPLOAD_PATH).resolve(name)));
        outputStream.flush();
        outputStream.close();
    }


}
