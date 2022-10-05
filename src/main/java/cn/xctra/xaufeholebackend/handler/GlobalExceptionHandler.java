package cn.xctra.xaufeholebackend.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常拦截
    @ExceptionHandler
    public ResponseEntity<String> handlerException(Exception e) {
        e.printStackTrace();
        if (e instanceof NotLoginException) {
            return ResponseEntity.status(HttpStatus.HTTP_UNAUTHORIZED).build();
        }
        return ResponseEntity.internalServerError().build();
    }
}
