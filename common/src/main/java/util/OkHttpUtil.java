package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * okHttp 工具类
 *
 * @author zhuzishuang
 * @date 2022/5/17
 */
@Component
public class OkHttpUtil {

    @Autowired
    private OkHttpClient okHttpClient;
    @Autowired
    private ObjectMapper mapper;

    private final Logger log = LoggerFactory.getLogger(OkHttpUtil.class);

    public static final MediaType MEDIA_TYPE_FORM = MediaType.parse("application/x-www-form-urlencoded");
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_XML = MediaType.parse("application/xml; charset=utf-8");
    public static final String CHARSET_UTF8 = "UTF-8";
    public static final String CHARSET_GB2312 = "GB2312";
    public static final String CHARSET_GBK = "GBK";

    /**
     * get 请求
     *
     * @param url 请求url地址
     * @return string
     */
    public String get(String url) {
        return get(url, null, null);
    }


    /**
     * get 请求
     *
     * @param url    请求url地址
     * @param params 请求参数 map
     * @return string
     */
    public String get(String url, Map<String, String> params) {
        return get(url, params, null);
    }

    /**
     * get 请求
     *
     * @param url     请求url地址
     * @param headers 请求头字段 {k1, v1 k2, v2, ...}
     * @return string
     */
    public String get(String url, String[] headers) {
        return get(url, null, headers);
    }


    /**
     * get 请求
     *
     * @param url     请求url地址
     * @param params  请求参数 map
     * @param headers 请求头字段 {k1, v1 k2, v2, ...}
     * @return string
     */
    public String get(String url, Map<String, String> params, String[] headers) {
        StringBuilder sb = new StringBuilder(url);
        if (params != null && params.keySet().size() > 0) {
            boolean firstFlag = true;
            for (String key : params.keySet()) {
                if (firstFlag) {
                    sb.append("?").append(key).append("=").append(params.get(key));
                    firstFlag = false;
                } else {
                    sb.append("&").append(key).append("=").append(params.get(key));
                }
            }
        }

        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.length > 0) {
            if (headers.length % 2 == 0) {
                for (int i = 0; i < headers.length; i = i + 2) {
                    builder.addHeader(headers[i], headers[i + 1]);
                }
            } else {
                log.warn("headers's length[{}] is error.", headers.length);
            }

        }

        Request request = builder.url(sb.toString()).build();
        log.info("do get request and url[{}]", sb.toString());
        return execute(request);
    }

    /**
     * post 请求
     *
     * @param url 请求url地址
     * @return string
     */
    public String post(String url) {
        RequestBody requestBody = RequestBody.create("{}", MEDIA_TYPE_JSON);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        log.info("do post request and url[{}], mediaType={}", url, MEDIA_TYPE_JSON);
        return execute(request);
    }

    /**
     * post 请求, 请求数据为 json 的字符串
     *
     * @param url  请求url地址
     * @param data 请求数据, json 字符串
     * @return string
     */
    public String post(String url, String data) {
        try {
            log.info("do post request and url[{}],data={}, mediaType={}", url, mapper.writeValueAsString(data), MEDIA_TYPE_JSON);
        } catch (JsonProcessingException e) {
            log.error("Json入参格式错误", e.getMessage());
        }
        RequestBody requestBody = RequestBody.create(data, MEDIA_TYPE_JSON);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return execute(request);
    }

    /**
     * post 请求
     *
     * @param url    请求url地址
     * @param params 请求参数 map
     * @return string
     */
    public String post(String url, Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        try {
            log.info("do post request and url[{}],data={}, mediaType={}", url, mapper.writeValueAsString(params), MEDIA_TYPE_JSON);
        } catch (JsonProcessingException e) {
            log.error("Json入参格式错误", e.getMessage());
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        return execute(request);
    }

    /**
     * post 请求, 请求数据为 xml/json 的字符串
     *
     * @param url  请求url地址
     * @param data 请求数据, xml/json 字符串
     * @return string
     */
    public String post(String url, String data, MediaType mediaType) {
        try {
            log.info("do post request and url[{}],data={}, mediaType={}", url, mapper.writeValueAsString(data), mediaType);
        } catch (JsonProcessingException e) {
            log.error("Json入参格式错误", e.getMessage());
        }
        RequestBody requestBody = RequestBody.create(data, mediaType);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return execute(request);
    }

    /**
     * post 请求, 请求数据为 xml/json 的字符串
     *
     * @param url   请求url地址
     * @param token 令牌
     * @param data  请求数据, xml/json 字符串
     * @return string
     */
    public Response post(String url, String token, String data, MediaType mediaType) {
        log.info("do post request and url[{}], mediaType={}", url,mediaType);
        RequestBody requestBody = RequestBody.create(data, mediaType);
        Request request = new Request.Builder().url(url).addHeader("Authorization", token).post(requestBody).build();
        try {
            return okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String execute(Request request) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                log.error("请求异常，code={} message={}", response.code(), response.message());
            }
        } catch (Exception e) {
            log.error("请求异常，errmsg={}, cause={}", e.getMessage(), e.getCause());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }

    private Socket socket = null;

    /**
     * socket 请求, 请求数据为 xml 的字符串
     * @param host 请求ip地址
     * @param port 请求端口
     * @param xmlData  请求数据, xml 字符串
     * @param charset  字符集
     * @return string
     */
    public String socket(String host, Integer port, String xmlData, String charset) {
        log.info("do socket request and host[{}],port[{}]", host, port);
        Writer writer = null;
        BufferedReader bufferedReader = null;
        String result = "";
        try {
            InetAddress inetAddress = InetAddress.getByName(host);
            // 测试主机是否在线 5 秒超时
            if (inetAddress.isReachable(5000)) {
                socket = new Socket(inetAddress, port);
                // 往服务写数据
                writer = new OutputStreamWriter(socket.getOutputStream(), charset);
                writer.write(xmlData);
                writer.flush();
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), charset));
                result =  bufferedReader.lines().collect(Collectors.joining());
            } else {
                log.error("请求异常，{}服务器超时",host);
            }
        } catch (UnknownHostException e) {
            log.error("请求异常，未知的服务器={}",host);
        } catch (IOException e) {
            log.error("请求异常，host={},port={}",host, port);
        } finally {
            try {
                if (writer != null && bufferedReader != null & socket != null) {
                    writer.close();
                    bufferedReader.close();
                    socket.close();
                }
            } catch (IOException e) {
                log.error("socket请求, 关闭流异常");
            }
        }
        return result;
    }
}
