代理模式:
    通过代理对象来实现对目标对象的增强

实现方式:

    一、静态代理实现方式:
        ①代理对象和目标对象实现同一个接口或者父类
        ②在代理类中持有目标对象,调用目标对象方法

        静态代理的缺点:
        ①需要编写很多的代理类
        ②如果接口类修改发生改变,那么代理对象和目标对象都需要进行维护

    二、基于JDK的动态代理实现方式:
        ①创建代理工厂,不实现接口,但是需要执行接口类型
        ②调用jdk的Proxy.newProxyInstance()方法来动态生成代理对象
        ③通过动态生成的代理对象来调用目标对象方法,从而触发InvocationHandler事件处理器方法
        
        总结:
        代理对象不需要实现接口,但是目标对象一定要实现接口
        
    三、基于cglib的动态代理实现方式:
        ①目标对象不需要实现接口
        ②使用Enhancer创建子类代理对象
        ③通过子类代理对象调用目标对象的方法,从而触发intercept方法的执行
        
        总结:
        目标对象不需要实现接口
        
    总结:
        如果对执行某个类增强,那么可以使用静态代理
        如果对所有实现了接口的类增强,那么可以使用jdk动态代理
        如果对所有没有实现接口的类增强,那么可以使用cglib动态代理
        
    


