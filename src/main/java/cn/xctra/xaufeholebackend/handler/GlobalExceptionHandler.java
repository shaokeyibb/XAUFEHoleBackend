package cn.xctra.xaufeholebackend.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.hutool.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常拦截
    @ExceptionHandler
    public ResponseEntity<String> handlerException(Exception e) {
        if (e instanceof NotLoginException) {
            return ResponseEntity.status(HttpStatus.HTTP_UNAUTHORIZED).build();
        } else if (e instanceof NotRoleException) {
            return ResponseEntity.status(HttpStatus.HTTP_FORBIDDEN).build();
        }
        e.printStackTrace();
        return ResponseEntity.internalServerError().build();
    }
}
