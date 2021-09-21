package alex.falendish.listener;

import alex.falendish.utils.FileUtils;

import lombok.extern.log4j.Log4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;

/**
 * Basic ServletContextListener for logging common operations.
 * @author Artem Malchenko
 */
//@Log4j
//public class Listener implements ServletContextListener,
//        HttpSessionListener, HttpSessionAttributeListener {
//
//    public Listener() {
//    }
//
//    public void contextInitialized(ServletContextEvent sce) {
//        DatabaseUtils.initializeDataSource();
//        try {
//            VariablesHolder.USD_TO_UAH = CurrencyConverter.getConversionRate("USD", "UAH");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        log.info("Servlet context initialized.");
//    }
//
//    public void contextDestroyed(ServletContextEvent sce) {
//        log.info("Servlet context destroyed.");
//    }
//
//    public void sessionCreated(HttpSessionEvent se) {
//        log.info("Session created.");
//    }
//
//    public void sessionDestroyed(HttpSessionEvent se) {
//        log.info("Session destroyed.");
//    }
//
//    public void attributeAdded(HttpSessionBindingEvent sbe) {
//        log.info("Attribute \"" + sbe.getName()+ "\" with value " + sbe.getValue() + " added");
//    }
//
//    public void attributeRemoved(HttpSessionBindingEvent sbe) {
//        log.info("Attribute \"" + sbe.getName() + "\" with value " + sbe.getValue() + " was deleted");
//    }
//
//    public void attributeReplaced(HttpSessionBindingEvent sbe) {
//        log.info("Attribute \"" + sbe.getName() + "\" with value " + sbe.getValue() + " was replaced");
//    }
//}
