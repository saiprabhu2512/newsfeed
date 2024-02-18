package com.demo.instagram.newsfeed.exception;

import org.springframework.http.HttpStatus;

public class MyBlogException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String msg;

    private HttpStatus status;

    public MyBlogException(String msg, HttpStatus status) {
        super();
        this.msg = msg;
        this.status = status;
    }

    public MyBlogException(String msg1, HttpStatus status,String msg) {
        super();
        this.msg = msg;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }


}
