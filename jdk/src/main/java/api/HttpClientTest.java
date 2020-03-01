package api;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class HttpClientTest {

    @Test
    public void testGet() throws IOException {


        DefaultHttpClient client = new DefaultHttpClient();
        //发送get请求
        HttpGet request = new HttpGet("https://www.booking.com/reviewlist.zh.html?tmpl=reviewlistpopup;pagename=hautes-de-vanoises;cc1=fr;hrwt=1;aid=349369");


        request.addHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.97 Safari/537.36");
        request.addHeader("Cache-Control", "max-age=0");
        request.addHeader("Connection", "keep-alive");
        request.addHeader("Upgrade-Insecure-Requests", "1");
        request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        request.addHeader("Accept-Encoding", "gzip, deflate, sdch");
        request.addHeader("Accept-Language", "zh-CN,zh;q=0.8");

        HttpResponse response = client.execute(request);

        /**请求发送成功，并得到响应**/
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//            /**读取服务器返回过来的json字符串数据**/
            String strResult = EntityUtils.toString(response.getEntity(),"UTF-8");
            /**把json字符串转换成json对象**/
            System.out.println(strResult);


            byte[] bytes = new byte[0];
            bytes = new byte[response.getEntity().getContent().available()];
            response.getEntity().getContent().read(bytes);
            String str = new String(bytes,"UTF-8");
            System.out.println(str);

        } else {

        }










//        CloseableHttpClient client = null;
//        CloseableHttpResponse response = null;
//        try{
//            /**
//             * 创建HttpClient对象
//             */
//            client = HttpClients.createDefault();
//            /**
//             * 创建URIBuilder
//             */
//            URIBuilder uriBuilder = new URIBuilder("https://www.booking.com/reviewlist.zh.html?tmpl=reviewlistpopup;pagename=hautes-de-vanoises;cc1=fr;hrwt=1;aid=349369");
//            /**
//             * 设置参数
//             */
//
//            List<NameValuePair> nvps=new ArrayList<>();
//            NameValuePair pair=new NameValuePair(){
//                @Override
//                public String getName() {
//                    return "tmpl";
//                }
//
//                @Override
//                public String getValue() {
//                    return "reviewlistpopup;pagename=hautes-de-vanoises;cc1=fr;hrwt=1;aid=349369";
//                }
//            };
//            nvps.add(pair);
//            uriBuilder.addParameters(nvps);
//            /**
//             * 创建HttpGet
//             */
//            HttpGet httpGet = new HttpGet(uriBuilder.build());
//            /**
//             * 设置请求头部编码
//             */
//            httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
//            /**
//             * 设置返回编码
//             */
//            httpGet.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
//            /**
//             * 请求服务
//             */
//            response = client.execute(httpGet);
//            /**
//             * 获取响应吗
//             */
//            int statusCode = response.getStatusLine().getStatusCode();
//            byte[] bytes = new byte[0];
//            bytes = new byte[response.getEntity().getContent().available()];
//            response.getEntity().getContent().read(bytes);
//            String str = new String(bytes);
//            System.out.println(str);
//        }catch (Exception e){
//
//        } finally {
//            response.close();
//            client.close();
//        }

    }

}
