package cl.tival.check.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.richclient.application.ApplicationLauncher;

public class CheckApp {

    private static final Log logger = LogFactory.getLog(CheckApp.class);

    public static void main( String[] args ) {
        logger.info("SimpleApp starting up");

        String rootContextDirectoryClassPath = "/ctx";
        
        String startupContextPath = rootContextDirectoryClassPath + "/check-startup-context.xml";

        String richclientApplicationContextPath = rootContextDirectoryClassPath + "/check-application-bundle.xml";
        
        try {
            new ApplicationLauncher(startupContextPath, 
                    new String[] { richclientApplicationContextPath });
        } catch (RuntimeException e) {
            logger.error("RuntimeException during startup", e);
        }
    }

}
