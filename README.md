# hello-thrift
一个用thrift实现的简单PRC小程序

### 1 Thrift数据类型
Thrift 脚本可定义的数据类型包括以下几种类型：
#### 基本类型：
bool：布尔值，true 或 false，对应 Java 的 boolean  
byte：8 位有符号整数，对应 Java 的 byte  
i16：16 位有符号整数，对应 Java 的 short  
i32：32 位有符号整数，对应 Java 的 int  
i64：64 位有符号整数，对应 Java 的 long  
double：64 位浮点数，对应 Java 的 double  
string：未知编码文本或二进制字符串，对应 Java 的 String  
#### 结构体类型：
struct：定义公共的对象，类似于 C 语言中的结构体定义，在 Java 中是一个 JavaBean
#### 容器类型：
list：对应 Java 的 ArrayList  
set：对应 Java 的 HashSet  
map：对应 Java 的 HashMap  
#### 异常类型：
exception：对应 Java 的 Exception
#### 服务类型：
service：对应服务的类

### 数据传输协议
Thrift 可以让用户选择客户端与服务端之间传输通信协议的类别，在传输协议上总体划分为文本 (text) 和二进制 (binary) 传输协议，为节约带宽，提高传输效率，一般情况下使用二进制类型的传输协议为多数，有时还会使用基于文本类型的协议，这需要根据项目 / 产品中的实际需求。  
1. TBinaryProtocol : 二进制编码格式进行数据传输  
2. TCompactProtocol : 高效率的、密集的二进制编码格式进行数据传输  
3. TJSONProtocol : 使用 JSON 的数据编码协议进行数据传输  
4. TSimpleJSONProtocol : 只提供 JSON 只写的协议，适用于通过脚本语言解析  

*注意：客户端和服务端的协议要一致*  

### 传输层
1. TSocket：使用阻塞式 I/O 进行传输，是最常见的模式  
2. TFramedTransport：使用非阻塞方式，按块的大小进行传输，类似于 Java 中的 NIO  
*注意：若使用 TFramedTransport 传输层，其服务器必须修改为非阻塞的服务类型*
```
 TNonblockingServerTransport serverTransport; 
 serverTransport = new TNonblockingServerSocket(port); 
 Hello.Processor processor = new Hello.Processor(new HelloServiceImpl()); 
 TServer server = new TNonblockingServer(processor, serverTransport);
```
3. TNonblockingTransport：使用非阻塞方式，用于构建异步客户端
### 服务端类型
1. TSimpleServer：单线程服务器端使用标准的阻塞式 I/O  
```
TServerSocket serverTransport = new TServerSocket(7911); 
 TProcessor processor = new Hello.Processor(new HelloServiceImpl()); 
 TServer server = new TSimpleServer(processor, serverTransport); 
 server.serve();
```
2. TThreadPoolServer：多线程服务器端使用标准的阻塞式 I/O  
3. TNonblockingServer：多线程服务器端使用非阻塞式 I/O  

### 服务端编码步骤
1. 实现服务处理接口impl
2. 创建TProcessor
3. 创建TServerTransport
4. 创建TProtocol
5. 创建TServer
6. 启动Server

### 客户端编码步骤
1. 创建Transport
2. 创建TProtocol
3. 基于TTransport和TProtocol创建Client
4. 调用Client的相应方法

### Thrift代码生成  
编译命令：*thrift-0.10.0.exe -r -gen java ./Hello.thrift*  
其中，thrift-0.10.0.exe 是官网提供的windows下编译工具
