package com.cyrj.common.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.cyrj.common.exception.TokenException;
import com.cyrj.common.util.Response;


@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(Exception.class)
    @ResponseBody
    public Response resolveException(Exception e,HttpServletRequest httpServletRequest) {
       
        Response response = new Response();
        
      //保存异常日志
        Map paramMap = new HashMap();
        paramMap.put("requestType",httpServletRequest.getMethod());
        paramMap.put("requestUrl",httpServletRequest.getRequestURI());
        paramMap.put("requestAdr",httpServletRequest.getServerName());
        
        
        if(e instanceof MethodArgumentNotValidException){
        	MethodArgumentNotValidException a = (MethodArgumentNotValidException)e;
        	List<FieldError> fieldErrors=a.getBindingResult().getFieldErrors(); 
    		Map map = new HashMap();
    		for (FieldError error:fieldErrors){
    			map.put(error.getField(), error.getDefaultMessage());
    		}
    		return response.failure(map);
        }else if(e instanceof MethodArgumentTypeMismatchException)
        {
        	return response.failure(e.getMessage());
        }
      
        //获取异常名称
        String exceptionType = getExceptionType(e);
        
        if(exceptionType.equals("Token校验失败异常:TokenException"))
        {
        	
        	return response.failure("Token校验失败异常:未登录,或者token错误!"+e.getMessage());
        }
        paramMap.put("exceptionName",exceptionType);
        
        //获取异常详细信息
        if(null != e.getMessage()){
            paramMap.put("exceptionMsg",e.getMessage());
        }else{
            StackTraceElement[] steArr = e.getStackTrace();
            StringBuffer steStr = new StringBuffer("");
            if(steArr.length > 0){
                for(StackTraceElement ste: steArr){
                    steStr.append(ste.toString());
                }
            }
            paramMap.put("exceptionMsg",steStr.toString());
        }
        
        return response.failure(exceptionType + e.getMessage());
    }
	
	private String getExceptionType(Exception e){
        String exceptionType = "otherException";

        if(e instanceof NullPointerException){
            exceptionType = "空指针异常:NullPointerException";
        }
        else if(e instanceof ClassCastException){
            exceptionType = "类型强制转换异常:ClassCastException";
        }
        else if(e instanceof ArrayIndexOutOfBoundsException){
            exceptionType = "数组下标越界异常:ArrayIndexOutOfBoundsException";
        }
        else if(e instanceof SecurityException){
            exceptionType = "违背安全原则异常:SecurityException";
        }
        else if(e instanceof NegativeArraySizeException){
            exceptionType = "数组负下标异常:NegativeArraySizeException";
        }
        else if(e instanceof ArithmeticException){
            exceptionType = "算术异常:ArithmeticException";
        }
        else if(e instanceof FileNotFoundException){
            exceptionType = "文件未找到异常:FileNotFoundException";
        }
        else if(e instanceof NumberFormatException){
            exceptionType = "数字转换异常:NumberFormatException";
        }
        else if(null != e.getCause() && e.getCause() instanceof SQLSyntaxErrorException){
            exceptionType = "SQL语法异常:SQLSyntaxErrorException";
        }
        else if(null != e.getCause() && e.getCause() instanceof SQLException){
            exceptionType = "SQL异常:SQLException";
        }
        else if(e instanceof IOException){
            exceptionType = "IO异常:IOException";
        }
        else if(e instanceof NoSuchMethodException){
            exceptionType = "方法未找到异常:NoSuchMethodException";
        }
        else if(e instanceof IllegalAccessException){
            exceptionType = "违法的访问异常:IllegalAccessException";
        }
        else if(e instanceof EnumConstantNotPresentException){
            exceptionType = "枚举常量不存在异常:EnumConstantNotPresentException";
        }
        else if(e instanceof IllegalMonitorStateException){
            exceptionType = "违法的监控状态异常:IllegalMonitorStateException";
        }
        else if(e instanceof IllegalStateException){
            exceptionType = "违法的状态异常:IllegalStateException";
        }
        else if(e instanceof IllegalThreadStateException){
            exceptionType = "违法的线程状态异常:IllegalThreadStateException";
        }
        else if(e instanceof IndexOutOfBoundsException){
            exceptionType = "索引越界异常:IndexOutOfBoundsException";
        }
        else if(e instanceof InstantiationException){
            exceptionType = "实例化异常:InstantiationException";
        }
        else if(e instanceof InterruptedException){
            exceptionType = "被中止异常:InterruptedException";
        }
        else if(e instanceof NoSuchFieldException){
            exceptionType = "属性不存在异常:NoSuchFieldException";
        }
        else if(e instanceof StringIndexOutOfBoundsException){
            exceptionType = "字符串索引越界异常:StringIndexOutOfBoundsException";
        }
        else if(e instanceof TypeNotPresentException){
            exceptionType = "类型不存在异常:TypeNotPresentException";
        }
        else if(e instanceof UnsupportedOperationException){
            exceptionType = "不支持的方法异常:UnsupportedOperationException";
        }
        else if(e instanceof HttpMessageNotReadableException){
            exceptionType = "参数解析异常:HttpMessageNotReadableException";
        }
        else if(e instanceof HttpMediaTypeNotSupportedException){
            exceptionType = "不支持当前媒体类型异常:HttpMediaTypeNotSupportedException";
        }
        else if(null != e.getCause() && e.getCause().getClass().equals(TokenException.class)){
            exceptionType = "Token校验失败异常:TokenException";
        }

        return exceptionType;
    }

}
