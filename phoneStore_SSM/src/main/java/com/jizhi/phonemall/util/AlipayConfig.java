package com.jizhi.phonemall.util;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102400749924";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCnV3+iIxK0PVRCzIvA7JdCI2lXidEESC7UO49lJ6fUvU0wkRtQn/CqkuASX1CMiOAmBcV8YIAlxqpYGaj6pnuCssGc9irmH8VyEmugE/76drJkhYG4EXirY2+HcwlGPsq3RI+Cow9JcTk+YRihz1jT+niwhqoG+c0cHR2xl7vHOaFlV2boNYdWhoazO8sh9EN9HJ6O6KvngO7IOlMetTjseUtcPTOrA1AHig2LOq5PVnTaznj+PdnTWR42AEy/Y+aWq8jreZnDOOrxrOk8P8N2M8p4Lsjzrmlv80s3wiQwEY656kzNIPVYKqxAjGeZjc6zmNzD/vbyLsUVMPV8ZuUHAgMBAAECggEAaDThoyrqG9lRHmPX52StcJ8ymzOUOHFmz/zi99/w8FaNud+yvctLiDz8UhSOyWT3CFZxu1kwk9WaAFDCXIvNv/7m+IKHBlTklHuKD95D2t6NinFk7SXcGgZKznNBZD/35pZIM9asz1Q1jHXUa/b93stL2smRGdT2+DndYtkVMyLbT8ZQJ6L5lNuF5orpqZ+52oxY+wlBw4/h88Kgdv0QLsNkSVC9kGEFceXtYeXK0sh5BQh18EGx6HkAPEqMLiSZ1F3yMTmEsnDoqnYcxaBY+nPKg2T8nILFnGw+4QYJATqkSd/VNz6nvazzWjyt/wxk9xbooyvzHTDzqMyKo4yY+QKBgQDU3/AlcT71hKATkV2SZtqZBZCgS7ALuoPLIz0wIjya/36HAMX0B/DqX1xEyQOlJear5KDGPT/KYZq5H56pe3wEIx34lWesiY6xNgZEBow7XS4tHGrbzMl9nu82aJl2co6z7QQWGtjNi4VXb9dIZHb6F4WrmOb0NudnEckGIf5LWwKBgQDJPiPkcgT1RS5wKJf7yxfwF/KlArDzv+gfP94Kd9qsKoTBlIxdF+l30tNNpj/p4C5Kr8aRKI8X17JIrQ5M5y0UiKUDcjFhCmIGUy9Q6TdQQIkuVa11BJ+X3B6mq1xHWO9b0QUOeYBI5IjfESd8iTyRVKWtOBZfJeqRbuO1HaE4xQKBgQCXGr6kVVaTgKkAOtdo7ImOxVx77D3ylVqn/0OXvzW18FYoDmx1ZI2mUDp2uRlVSSoMKiXVkfSN++qQLdm6BGVrqmHD6onE93/KWsbn0p4IJYR5kQppK1x7Ry4WOPl2EqspHL0h07zBaqAkCMeQI4Aw1jGUNNz7zV1E1GMVb1vKsQKBgA+Va0wedDnWEKGI0zQhZwoloJmcmr1VaBE7beNboqhgZoVjDneLCX5I6ioCLMLcpFkOQY8uF96dymWMWSYlZL1JU5Xvutkr56lbubdHSAxD4xuecN8163HkDs+TR4MS5P40MnMLf56la9/yQR+ItRL//hBq3nihxAddEe8Ni1WNAoGAZ5EIgAHjdVm0lJSTeB22/t+MZwjTnNFxJ6zyzlHTphe+0liwJFOjKZkJ4zWru94yxUuhorPyQRGGLahUCdErUuCX2xeaRA+iSNjrmVnXNajwqoqTzuB2dpvWhStv9t0kCY/2bS9yMqnhuwMDWHX5ZBU7BjHO/PwKFzkHYBEQnFE=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlbD7GIHDGNRRS9PlMOc5HtSM4/Yvc/zb0SIBouJz75cWoLmb/nvGyIziDwL6SGdevEaek5q3lXHtVvMbFGQ2c7ll/9ipf6aQ9+wbFmYOOgBnz8bvXA8AsLAD3teYu1e7/2TyJnwfzu1AxKTXCIoJTZ4W8uKm19ffrA8tQB+8JmOMVjm7JGgDPcmi3bjWfJUyyqU3w+rBCd7IXztGzdKa/8bJTfcvpmy7zcYc0gh7KDgU5prGqvB0bheBhedRk03MRPcGYu4sWqlV0nMreWzLdoOQoTqWL4XCD9qjrq6VVoBbs1xe8BWdPpeyIL9Y2+yUPfXO0CsOlXMlok+R7G5L8QIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://39.106.229.122/phoneStore_SSM/pay/notify_url.action";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://39.106.229.122/phoneStore_SSM/pay/return_url.action";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关:
    //https://openapi.alipaydev.com/gateway.do
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 打印日志存放位置
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
