package com.quyuanjin.imserver;


import com.quyuanjin.imserver.constant.Constant;
import com.quyuanjin.imserver.constant.State;
import com.quyuanjin.imserver.handler.session.HibernateUtil;
import com.quyuanjin.imserver.pojo.PojoContract;
import com.quyuanjin.imserver.pojo.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static com.quyuanjin.imserver.handler.session.SessionAndCommit.getSession;

@SpringBootApplication
public class ImserverApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ImserverApplication.class, args);

        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql ="from User user where user.id=1";
        Query q = session.createQuery(hql);
        List<User> userList = q.list();
        if (userList.size() == 0) {
            //添加机器人
            HibernateUtil.add(new User("","您的机器人助手","妮妮","1",
                    "",
                    "", Constant.URL+"File/portrait/upload/666.png",
                    Constant.sex_female,"",""));
        }
        tx.commit();
        session.close();
        new ChatServer().start(8089);



    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/File/**")
                .addResourceLocations("file:File/");
    }
}
