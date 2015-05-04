package test;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by sony on 2015/4/22.
 */
public class Demo2Server {
    public static void main(String args[]) {
        Server server = new Server(6080);
        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase("src/main/webapp");
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
