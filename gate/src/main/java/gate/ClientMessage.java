package gate;

import com.google.protobuf.Message;
import gate.utils.ClientConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protobuf.GenerateCode.cli2srv.login.Auth;

import java.io.IOException;
import java.nio.channels.Channel;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.IntSummaryStatistics;

/**
 * Created by Dell on 2016/2/2.
 */
public class ClientMessage {
    private static final Logger logger = LoggerFactory.getLogger(ClientMessage.class);

    public static HashMap<Integer, Transfer> tranferHandlerMap = new HashMap<>();
    public static HashMap<Class<?>, Integer> msg2ptoNum = new HashMap<>();

    @FunctionalInterface
    public interface Transfer{
        void process(Message msg, ClientConnection conn) throws IOException;
    }

    public static void registerTranferHandler(Integer ptoNum, Transfer tranfer, Class<?> cla) {
        if (tranferHandlerMap.get(ptoNum) == null)
            tranferHandlerMap.put(ptoNum, tranfer);
        else {
            logger.error("pto has been registered, ptoNum: {}", ptoNum);
            return;
        }

        if(msg2ptoNum.get(cla) == null)
            msg2ptoNum.put(cla, ptoNum);
        else {
            logger.error("pto has been registered in msg2ptoNum, ptoNum: {}", ptoNum);
            return;
        }
    }

    public static MsgTranferHandler getTransferHandler(int ptoNum, Message msg) {

        return null;
    }

    public static void initTransferHandler() {
        ClientMessage.registerTranferHandler(1000, ClientMessage::transfer2Auth, Auth.CLogin.class);
    }

    public static void transfer2Logic(Message msg, ClientConnection conn) {

    }

    public static void transfer2Auth(Message msg, ClientConnection conn) {

    }
}
