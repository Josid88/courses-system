package com.dev.courses.webapp;

import com.dev.courses.dao.UserDao;
import com.dev.courses.dao.UserDaoHibernate;
import com.dev.courses.entity.UserEntity;
import com.dev.courses.security.SecurityService;
import com.dev.courses.security.SecurityServiceHardcodedImpl;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.SessionFactory;

import java.util.UUID;


@WebListener
public class CoursesContextListener implements ServletContextListener {

    public static final String SESSION_FACTORY = "SessionFactory";
    public static final String SECURITY_SERVICE = "SecurityService";

    @Override
    public void contextInitialized(ServletContextEvent context) {
        HibernateInitializer hibernateInitializer = new HibernateInitializer();
        SessionFactory sessionFactory = hibernateInitializer.init();
        context.getServletContext().setAttribute(SESSION_FACTORY, sessionFactory);

        UserDao userDao = new UserDaoHibernate(sessionFactory);

        SecurityService securityService = new SecurityServiceHardcodedImpl(userDao);
        context.getServletContext().setAttribute(SECURITY_SERVICE, securityService);

        createUser_DELETE_THIS(userDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent context) {
        SessionFactory sessionFactory = (SessionFactory) context.getServletContext().getAttribute(SESSION_FACTORY);
        sessionFactory.close();
    }

    private void createUser_DELETE_THIS(UserDao userDao) {
        UserEntity adminUser = userDao.getUser("admin", "admin");
        if (adminUser == null) {
            adminUser = new UserEntity();
            adminUser.setUsername("admin");
            adminUser.setPassword("admin");
            UUID uuid = UUID.randomUUID();
            adminUser.setUId( uuid.toString() );
            userDao.save(adminUser);
        }
    }

}
