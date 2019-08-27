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


编码:
    ASCII 美国信息标准交换标准代码
        7bit来表示一个字符,共计可以表示128字符;
    ISO-8859-1
        8bit表示一个字符,即用一个字节(byte) 来表示一个字符,共计可以表示256个字符
    gb2313(国标)
        两个字节表示一个汉字
    gbk (对gb2313的扩展)   
    gb18030 
    unicode 采用两个字节来表示一个字符
    UTF Unicode Translation Format
        unicode是一种编码方式, UTF是一种存储方式;UTF-8是Unicode的实现方式之一;
    UTF-8 变成字节表示形式
     一般来说,UTF-8会通过3个字符来表示一个中文;
    BOM
    
零拷贝机制 : 操作系统提供了那么就有;操作系统不提供,那么无济于事;
    用户空间 内核空间 硬件
    
Reactor模式:
    netty整体架构是Reactor模式的
    boss监听 OP_ACCEPT事件,该事件一个客户端值触发一次
    work监听OP_READ和OP_WRITE事件,如果业务逻辑比较耗时,那么在handler的channelRead0中我们可能需要格外的线程池去执行我们的业务代码;
    netty中有一个对缓冲区预判的类,根据上一次记录对缓冲区(ByteBuffer)大小进行增大或减少(减少是根据前两次),缓冲区也是有最大值和最小值限制的,最小为64,默认为1024,最大为65536字节,可伸缩缓冲区类(AdaptiveRecvByteBufAllocator)
    对于缓冲区是否使用堆内存还是堆外内存,是根据是否有系统属性或者其他决定的,使用堆外内存其实底层还是通过ByteBuffer.allocateDirect(容量数)进行分配缓冲区大小的;
        如果是堆内存的话,底层其实是直接new一个byte数组(MaxMessageHandle.allocate方法可以看到缓冲区具体分配的细节);
    DefaultChannelConfig : Netty配置类
  
Reactor模式的角色构成(五种角色)
    1.Handle(句柄或是描述符):本质上表示一种资源是由操作系统提供的,该资源用于表示一个个的事件,比如说文件描述符或是针对网络编程中的Socket描述符.事件既可以来自于外部,也可以来自于内部;外部事件比如说客户端的连接请求,客户端发送过来的数据等;内部事件比如说操作系统产生的定时器事件等.它本质上就是一个文件描述符.Handle是事件产生的发源地;
    2.Synchronous Event Demultiplexer(同步时间分离器):它本身是一个系统调用,用于等待事件的发生(事件可能是一个,可能是多个).调用方在调用它的时候灰被阻塞,一直阻塞到同步事件分离器上有事件产生为止.对于Linux来说,同步事件分离器指的就是常用的I/O多路复用机制,比如说select,poll,epoll等.在Java NIO领域中,同步事件分离器对应的组件就是Selector;对应的阻塞方法就是select();
    3.Event Handler(事件处理器): 本身由多个回调方法构成.这些回调方法构成了与应用相关的对于某个事件的反馈机制.Netty相对于Java Nio来说,在事件处理器这个角色上进行了升级,它为我们提供了大量的回调方法.供我们在特定事件产生时实现相应的回调方法进行业务逻辑的处理;
    4.Concrete Event Handler(具体时间处理器):是事件处理器的实现.它本身实现了事件处理器所提供的各个回调方法,从而实现了特定于业务的逻辑.它本质上就是我们所编写的一个个的处理器实现;
    5.Initiation Dispatcher(初始分发器):实际上就是Reactor角色,它本身定义了一些规范,这些规范用于控制事件的调度方式,同时又提供了应用进行事件处理器的注册,删除等设施.它本身是整个事件处理器的核心所在,Initiation Dispatcher会通过同步事件分离器来等待事件的发生.一旦事件发生,Initiation Dispatcher首先会分离出每一个事件,然后调用事件处理器,最后调用相关的回调方法来处理这些事件;

Reactor模式的流程:
    1.当应用想Initiation Dispatcher注册具体的事件处理器时,应用会标识出该事件处理器希望Initiation Dispatcher在某个事件发生时向其通知的事件,该事件与Handler关联;
    2.Initiation Dispatcher会要求每个事件处理器向其传递内部的Handle,该Handle向操作系统标识了事件处理器;   
    3.当所有的事件处理器注册完毕后,应用汇调用handle_events方法来启动Initiation Dispatcher的事件循环.这时,Initiation Dispatcher 会将每个注册的事件处理器的Handle合并起来,并使用同步事件分离器等待这些事件的发生.比如说,TCP协议层会使用select同步事件分离器来等待客户端发送的数据到达连接的socket handle上;
    4.当与某个事件源对应的Handle变为ready状态时(比如说,TCP socket变为等待读状态)同步事件分离器就会通知Initiation Dispatcher.
    5.Initiation Dispatcher会触发事件处理器的回调方法,从而相应这个处于ready状态的Handle.当事件发生时,Initiation Dispatcher会将被事件源激活的Handle作为key来寻找并分发恰当的事件处理器回调方法; 
    
    
    58 24
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    







