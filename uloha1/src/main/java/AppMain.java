import asos.IProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {
    
    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});

        IProcessor mp = context.getBean(IProcessor.class);
        mp.process();

    }
}
