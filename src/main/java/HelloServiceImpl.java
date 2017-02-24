import org.apache.thrift.TException;

/**
 * Created by wangs on 2017/2/24.
 */
public class HelloServiceImpl implements Hello.Iface {
    public String helloString(String param) throws TException {
        return param;
    }

    public int helloInt(int param) throws TException {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param;
    }

    public boolean helloBoolean(boolean param) throws TException {
        return param;
    }

    public void helloVoid() throws TException {
        System.out.println("Hello world!");
    }

    public String helloNull() throws TException {
        return null;
    }
}
