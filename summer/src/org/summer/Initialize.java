package org.summer;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.summer.ioc.beans.BeanFactory;
import org.summer.ioc.scanner.ClassScanner;
import org.summer.utils.DomHelper;

import static org.summer.utils.Print.print;

public class Initialize implements ServletContextListener {

    private String DEFAULT_PATH = "\\WEB-INF\\classes\\";

    @Override
    public void contextDestroyed(ServletContextEvent context) {
        print("destroy");
    }

    @Override
    public void contextInitialized(ServletContextEvent context) {
        print("init");
        String configName = context.getServletContext().getInitParameter(
                "summer-config");
        String basePath = context.getServletContext().getRealPath("");
        String classesPath = basePath + DEFAULT_PATH;
        String configPath = classesPath + configName;
        String packageName = null;
        String packagePath = null;
        try {
            Document doc = DomHelper.readXml(configPath);
            Element ele = DomHelper.getElement(doc, "//beans/scanner");
            packageName = DomHelper.getAttribute(ele, "base-package");
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        packagePath = packageName.replaceAll("\\.", "/");

        String[] className = null;
        try {
            className = ClassScanner.scanClasses(new File(classesPath
                    + packagePath), packageName);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BeanFactory.INSTANCE.getInstance(className);
    }

}
