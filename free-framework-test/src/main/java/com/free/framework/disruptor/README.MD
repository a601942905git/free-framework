Disruptor:
    高性能队列,高性能体现在disruptor使用了无锁机制并且通过解决了伪共享的问题
    使用步骤:
        ①定义需要传递的事件
        ②定义事件工厂
        ③定义消费者
        ④定义生产者:
            ①生产者只有RingBuffer
            ②申请插槽,获取插槽中的事件
            ③发布
        ⑤运行
            ①创建事件工厂
            ②创建线程池
            ③定义RingBufferSize,只能是2的平方
            ④创建Disruptor
            ⑤设置消费者
            ⑥创建RingBuffer
            ⑥启动Disruptor
            ⑦调用生产者方法
            
        