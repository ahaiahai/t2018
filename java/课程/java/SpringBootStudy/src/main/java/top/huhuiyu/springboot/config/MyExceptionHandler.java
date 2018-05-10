package top.huhuiyu.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import top.huhuiyu.springboot.util.JsonMessage;

@ControllerAdvice
@ResponseBody
public class MyExceptionHandler {
  private static final Logger LOG = LoggerFactory.getLogger(MyExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public JsonMessage handleException(Exception ex) {
    LOG.error("处理发生错误", ex);
    if (ex instanceof NoHandlerFoundException) {
      JsonMessage json = JsonMessage.getFail("请求的资源不存在。。。");
      json.setCode(404);
      return json;
    }
    return JsonMessage.getFail("服务器忙，请稍后重试...");
  }

}
