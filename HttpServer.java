package com.Learning;

public interface HttpServer {

    void start(HttpRequestHandler handler);

    void stop();
}
