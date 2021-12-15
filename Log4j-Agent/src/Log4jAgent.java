import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class Log4jAgent {
    public static void agentmain(String agentArgs, Instrumentation instrumentation) {

        instrumentation.addTransformer(new Log4jTransformer(), true);
        Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
        for (Class loadedClass : allLoadedClasses) {
            if (loadedClass.getName() == "org.apache.logging.log4j.core.lookup.JndiLookup") {
                try {
                    instrumentation.retransformClasses(loadedClass);
                } catch (UnmodifiableClassException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
