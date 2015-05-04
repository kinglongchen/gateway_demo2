package com.kinglong.demo2.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sony on 2015/4/24.
 */
//@Slf4j
public class ForwardUtil {

    private static String CONTENT_TYPE = "Content-Type";
    private static final int HTTP_CONNECTION_TIMEOUT = 60 * 1000;
    private static final int HTTP_READ_TIMEOUT = 60 * 1000;
    public static String server = "http://127.0.0.1:8082";

   public enum Method{
        POST,GET,DELETE,PUT;
        public static Method getMethod(String method) {
            return valueOf(method.toUpperCase());
        }
    }

    public static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void doForward(HttpServletRequest request,HttpServletResponse response) throws IllegalArgumentException{
        String path = getRequestPath(request);
        HttpRequestBase des_req = null;
        switch(getRequestMethod(request)) {
            case GET:
                des_req = new HttpGet(path);
                break;
            case POST:
                des_req = new HttpPost(path);
                break;
            case DELETE:
                des_req = new HttpDelete(path);
                break;
            case PUT:
                break;
            default:
                throw new IllegalArgumentException("错误的请求方法");
        }
        forward(request,response,des_req);
    }


    public static void forward(HttpServletRequest src_req,HttpServletResponse src_resp,HttpRequestBase des_req) {
        CloseableHttpResponse des_resp = null;
        InputStream src_in = null;
        OutputStream src_out = null;
        try {
            //关联转发的请求头
            srcReq2desReqHeaderBridge(src_req, des_req);
            //设置转发请求的超时
            setReqConfig(des_req);
            //判断是否是HttpEntityEnclosingRequestBase,如果是的话,
            //转发请求的body
            if (des_req instanceof HttpEntityEnclosingRequestBase) {
                src_in = src_req.getInputStream();
                //获取转发body的Entity
                InputStreamEntity forwardReqEntity = new InputStreamEntity(src_in);
                //设置转发body的Entity
                ((HttpEntityEnclosingRequestBase) des_req).setEntity(forwardReqEntity);
            }
            //执行转发请求,并获得response
            des_resp = httpClient.execute(des_req);
            //管理响应的头信息
            desResp2srcRespHeaderBridge(des_resp, src_resp);
            //获得响应Entity
            HttpEntity forwardRespEntity = des_resp.getEntity();

            src_out = src_resp.getOutputStream();

            //转发响应
            forwardRespEntity.writeTo(src_out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭操作
            try {
                if (des_resp !=null) {
                    des_resp.close();
                }
                if (src_in !=null) {
                    src_in.close();
                }
                if (src_out != null) {
                    src_out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void srcReq2desReqHeaderBridge(HttpServletRequest httpServletRequest, HttpRequestBase httpRequestBase) {
        //Content-Type头信息添加
        associateSReq2ReqHeaders(CONTENT_TYPE, httpServletRequest, httpRequestBase);
    }

    private static void desResp2srcRespHeaderBridge(HttpResponse httpResponse,
                                                    HttpServletResponse httpServletResponse) {
        //关联响应状态信息
        httpServletResponse.setStatus(httpResponse.getStatusLine().getStatusCode());
        //关联Content-Type信息
        associateResp2SRespHeader(CONTENT_TYPE, httpResponse, httpServletResponse);
    }

    private static void associateSReq2ReqHeaders(String headerNames, HttpServletRequest httpServletRequest, HttpRequestBase g_req) {
        Enumeration<String> enumeration = httpServletRequest.getHeaders(headerNames);
        while (enumeration.hasMoreElements()) {
            String value = enumeration.nextElement();
            g_req.addHeader(headerNames, value);
        }
    }

    private static void associateResp2SRespHeader(String headerNames,
                                                  HttpResponse httpResponse,
                                                  HttpServletResponse httpServletResponse) {
        Header[] headers = httpResponse.getHeaders(headerNames);
        for (Header header : headers) {
            httpServletResponse.addHeader(headerNames, header.getValue());
        }

    }

    public static Method getRequestMethod(HttpServletRequest request) {
        return Method.getMethod(request.getMethod());
    }

    public static String getRequestPath(HttpServletRequest request) {
        String uri = request.getRequestURI();
        Pattern p = Pattern.compile("^/v\\d\\.\\d");
        Matcher m = p.matcher(uri);
        uri = m.replaceAll("");
        String queryString = request.getQueryString();
        if (queryString!=null) {
            return server + uri + "?" + queryString;
        } else {
            return server + uri;
        }
    }

    private static void setReqConfig(HttpRequestBase request) {
        RequestConfig g_RequestConfig = RequestConfig.custom().
                setSocketTimeout(HTTP_READ_TIMEOUT).setConnectTimeout(HTTP_CONNECTION_TIMEOUT).build();//设置请求和传输超时时间
        request.setConfig(g_RequestConfig);
    }
}