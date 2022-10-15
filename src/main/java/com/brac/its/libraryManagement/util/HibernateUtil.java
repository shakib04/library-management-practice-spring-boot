package com.brac.its.libraryManagement.util;

import com.brac.its.libraryManagement.model.FbPage;
import org.aspectj.bridge.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static void getSessionFactory2() {
        try {
            Configuration conf = new Configuration().configure();
            registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
            sessionFactory = conf.buildSessionFactory(registry);
        } catch (Throwable ex) {
            System.err.println("Fai1ed to create session factory object" + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Long fbpageId = null;
        try {
            transaction = session.beginTransaction();
            FbPage fbPage = new FbPage();
            fbPage.setPageName("a helpful blog");
            fbpageId = (Long) session.save(fbPage);
            List pages = session.createQuery("FROM fbpage").list();
            for (FbPage fbPage1: pages){
                System.out.println(fbPage1.getPageName());
            }
            fo
        }catch (Ex)
    }

        public static SessionFactory getSessionFactory () {
        if (sessionFactory == null) {
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();

                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);

                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();

                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

        public static void shutdown () {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    }
