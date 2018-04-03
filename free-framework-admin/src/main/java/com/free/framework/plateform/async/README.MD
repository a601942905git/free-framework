servlet3.0异步实现：

    1、用户发起请求之后
    2、请求到达执行的控制器中进行处理
    3、调用AsyncContext asyncContext = request.startAsync();
    4、此时使用一个新的线程来代理请求线程
    5、请求线程直接返回到容器线程池中,继续用于接收客户端的请求
    6、通过使用java8的CompleteFuture进行异步处理,合并请求
    7、在CompleteFuture.whenComplete()方法中使用
        // 响应客户端请求
        asyncContext.getResponse().getWriter().write(JSONObject.toJSONString(list));
        // 完成异步操作
        asyncContext.complete();