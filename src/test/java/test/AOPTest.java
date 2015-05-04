package test;

import com.kinglong.demo2.servlet.DemoServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


/**
 * Created by sony on 2015/4/28.
 */
public class AOPTest {
//    @Autowired
//    DemoServlet demoServlet;

    public static void main(String args[]) {
        String configPath = "src/main/webapp/WEB-INF/application-context.xml";
        ApplicationContext ctx = new FileSystemXmlApplicationContext(configPath);
        DemoServlet demoServlet = (DemoServlet) ctx.getBean("demoServlet");
        demoServlet.test();
    }
}
