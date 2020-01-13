package com.quyuanjin.imserver.handler.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SessionAndCommit {
    public SessionAndCommit() {
    }
    public static Session getSession() {
        Configuration configuration = new Configuration();
        Configuration configure = configuration.configure("hibernate.xml");
        SessionFactory sessionFactory = configure.buildSessionFactory();
        return sessionFactory.openSession();
    }

   public static void  getSessionAndCommit(Object o) {
        Configuration configuration = new Configuration();
        Configuration configure = configuration.configure("hibernate.xml");
        SessionFactory sessionFactory = configure.buildSessionFactory();
        Session session = sessionFactory.openSession();
        //开启事务
        Transaction tx = session.beginTransaction();
        //执行操作
        //session.save(user);
        session.saveOrUpdate(o);
        //提交事务
        tx.commit();
        //关闭事务
        session.close();
    }
    public static void  getSessionAndCommitMerge(Object o) {
        Configuration configuration = new Configuration();
        Configuration configure = configuration.configure("hibernate.xml");
        SessionFactory sessionFactory = configure.buildSessionFactory();
        Session session = sessionFactory.openSession();
        //开启事务
        Transaction tx = session.beginTransaction();
        //执行操作
        //session.save(user);
        session.merge(o);
        //提交事务
        tx.commit();
        //关闭事务
        session.close();
    }
}
