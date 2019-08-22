netty 使用4.1.10 不要使用5 , 5已经废弃了

netty也可以作为一个servlet服务器

webSocket是解决HTTP无状态的问题,也就是说webSocket是一个长连接; 构建于HTTP协议之上的升级协议;


Google ProtoBuf:
    使用protobuf编译器生成代码 , 注意:千万不要去修改生成的代码!
    protoc --java_out=./ ./Student.proto    

RPC : Remote Produce Call , 远程调用过程 , 很多RCP框架都是跨语言的;


学习新技术: 直接去官方网站

P17 - P31暂时不看

字节流的输入流和输出流的基础是inputStream和outputStream这两个抽象类,字节流的输入和输出都是这两个的子类实现的;
字符流是JAVA1.1后新增加的以字符为单位进行输入和输出处理的流,字符流的基础是抽象类Reader和Writer(最底层还是字节流|);

I/O流的链接
    文件 -> 文件流(FileInputStream) -> 增加一个缓冲(BufferInputStream) -> 增加读取基本类型的功能(DataInputStream) -> 数据 ;
    数据 -> 输出基本数据类型(DataOutputStream) -> 缓冲(BufferInputStream) -> FileOutputStream 写入到文件中
    
装饰模式:
    装饰类与被装饰类实现同一个接口(感觉有点像代理模式) , 扩展功能 , 避免越来越多的子类
java.io最为核心的概念是流(stream),面向流的编程.在java.io中,一个流要么是输入流,要么是输出流,不可能同时既是输出流又是输入流;
java.nio拥有3个核心概念,Selector,Channel,Buffer;
    在NIO中,我们是面向块(block)或是缓冲区(buffer)编程的.Buffer本身就是一块内存,底层实现上,实际是个数组.数据的读写都是通过Buffer来实现的;
    除数组外,Buffer还提供了对于数据的结构化访问方式,并且可以追踪到系统的读写过程;
    基本数据类都有各自对于的Buffer类型,如IntBuffer,LongBuffer;
    Channel 指的是可以向其写入数据或是从中读取数据的对象,类似于java.io中的stream;
    所有数据的读写都是通过Buffer来进行的,永远不会出现直接向channel写入数据或是读取数据的情况;
    与stream不同的是,Channel是双向的,一个流可能是InputStream或是OutputStream,Channel打开后则可以进行读取,写入;
    
  Buffer : position limit capacity  
    mark 和 reset成双出现
    0 <= mark <= position <= limit <= capacity (还有个offset很少用,默认0)
    clear : 恢复成最初始状态
    flip : 读写转换, 就是将 position赋给limit , 然后position变成0 , mark变为-1
    堆内/堆外内存 Heap / Direct
    

Buffer
    address表示堆外地址(只有直接缓冲才有,DirectorBuffer)
    HeadBuffer 字节数组在堆内, 与IO打交道的时候需要将堆内中的字节数组拷贝一份到操作系统中,然后操作系统中才能与IO打交道;
    DirectBuffer省去了拷贝堆内字节到操作系统(0拷贝),而是直接在操作系统开辟一块空间与IO交互;
    
使用windows telnet ip port 只能发送一个字符, 进步命令行使用ctrl + ]就可以发送完整的字符串 , send 内容 
    
P42 keep on
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    







