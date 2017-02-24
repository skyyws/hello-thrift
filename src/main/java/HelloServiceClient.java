import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by wangs on 2017/2/24.
 */
public class HelloServiceClient {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 7988;
    public static final int TIMEOUT = 3000;

    public void start() {
        TTransport transport = null;
        try {
            // 设置调用的服务地址为本地，端口为 7988
            transport = new TSocket(SERVER_IP, SERVER_PORT);
            // 设置传输协议为 TBinaryProtocol,协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            Hello.Client client = new Hello.Client(protocol);

            transport.open();
            // 调用服务的 helloVoid 方法
            client.helloVoid();
            System.out.println(client.helloString("Wangs"));
            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HelloServiceClient client = new HelloServiceClient();
        client.start();
    }
}
