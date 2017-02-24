import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by wangs on 2017/2/24.
 */
public class HelloServiceServer {
    public static final int PORT = 7988;

    public void start() {
        try {
            System.out.println("Start service on port " + PORT);
            // 关联处理器与 Hello 服务的实现
            TProcessor tProcessor = new Hello.Processor<Hello.Iface>(new HelloServiceImpl());
            // 设置服务端口为 7988
            TServerSocket serverSocket = new TServerSocket(PORT);
            TServer.Args tArgs = new TServer.Args(serverSocket);
            tArgs.processor(tProcessor);
            // 设置协议工厂为 TBinaryProtocol.Factory
            tArgs.protocolFactory(new TBinaryProtocol.Factory());


            TServer server = new TSimpleServer(tArgs);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HelloServiceServer server = new HelloServiceServer();
        server.start();
    }
}
