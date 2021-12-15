import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class AttachAgent {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        String agentpath= AttachAgent.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if(agentpath.contains("C:")||agentpath.contains("D:")||agentpath.contains("E:")){
            agentpath=agentpath.substring(1);
        }
        if (args.length == 0) {
            System.err.println("参数缺少，例：java -jar Log4j-Agent.jar 23232 ");
            System.err.println("23232 为要Attach的进程号！");
            System.exit(-1);
        }
        VirtualMachine virtualMachine = VirtualMachine.attach(args[0]);
        virtualMachine.loadAgent(agentpath);
        System.out.println("Patch Success!!!");
        virtualMachine.detach();

    }

}
