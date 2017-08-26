package com.xingxue.class11.constants;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2017080408027355";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDw1ZAqnAdlDmOLJZCNDDrOAm3KrLDjCzkKWQAqA1/kBmHKOjhyEkgaZmjEkjAoxeshvMocJeE8tsPsUj03nFnW0dPa+fa6uAOIenHoQYe0XhbHzzwS3m4vRuvpPKtHEHtrxOjfNfHgM30F89aTpNUOURfxWs4tv7gIM7biQa01DTSEJ/AdZp0IcOrhIj9qr+YqKKXcqFLA564mms5n1NFBPtRTvUj8JefTbSM7bQSaVaziTHEXEJ5MVyiVWsZkuPV4U6dZUJcH2nmVz4v3V1jK0Qu4nDwsdVNpjwXQceoTTKHfF7+RIbyKF7rS0X119DTQPEp/89DswdN0FxOEjipAgMBAAECggEABBTS4SHmYyQqPDspVFelhEOyGVhbPS9kXJTjohmSgXcHM2aTABtiGHzs4143nq/ulcuAUKpcJyQWoP/O0LbrMFZleRJQljlcQ1PNl/9m3yGB5uCq1I6h8JT6/I/RbIdJi4E2zIObc3SL7JmoNJXmfl9fvM+yqIkSkAK0JZyy9nro2XRVd55DbnaQoJA282wPDNe6kvlrCIs5Ok6J28pmUVy07pXfz+8oo19+Q5ehHJ/8PuldngTLNFQxo0HEs0kaSW2UW0UO4Hgceru4gwm7ulpHxEvhrhf7kRMtYiv7MzW01I+jMkp0icEOnoAvbe5nDqZbYxpNrVh5h5fCfiq0YQKBgQDDjsUbPfJ2JLJhF3worZN9oatgwgnzRPTmKkMYLy8Wu7ivvoGxvZzVqOAI2h83hHCWanNMWvhlBWjIZP6ttevZvhx2Bfx++atnIGsK25tHs5cyeX0hzhEk5kRqyTrWHSHmyH5Fi52bDdqvZx1FjRBH9u9NfRZ6yUb0ZD70YSzCNQKBgQCsfOdFdjAHt9pwj2L7uWPfO/+ZxMN6EmI7xJjhP+A0//aBIfWRW90kw7ddH0Xyz+oDQxTSEZEcHNg4gIDdikQ5cVKeeXghRNG3QPqsC5a7ojW5nLkMTQrqR8Lc/SVJ6+fy7ppAjO0vdaGgcGfEnoXVZtwaCIFRfvFi20gABXZrJQKBgEHeArHMSbBVZ+ef+2fQHw9/trOeZ6+SmFI2BedNbTThAbHgHtr5HKwyd1ND9KPEbg+bxz3+ng+YpNRqgAWOcuQiTn/KQGOcQIk5uBQuJ70c1l5f4S/Tsp6VGJJHORQJN/P9r9pIzkr9fZz6xDc67jLvD2cwLLQqu0VxxmqVmHfJAoGAMlaJT/Y78iSvtSXDZ18lTGLAJ2oK+jcfP/4R97qifhUtJWZ5uiArG62ad6zGxyPyUV1kWCIUTTYFzAeElCLj4je2OtWMP3lMEsT94vPG259tOmwIfRSG2PR0Y/Xkyfv0xyrnDWuTVMRplN75CaKRwEOyfE57HwODAqyoCImPk5kCgYEAsyIH84UWDUb/qbx1U62SkO0EtGuvi8PtjoEDo1cthX/0qZMEgdqsnkmPuxucN5FRFqjkWel4v1OSb6eD8wS5v2kIPG1xcAkCEUsAjjiLPwP0MwexvQ8o6G2yG5ixI84iBD1mnLO7dMLRq9p317vK6x5Gb22wJLqaC7eumDcB37s=";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg8NWQKpwHZQ5jiyWQjQw6zgJtyqyw4ws5ClkAKgNf5AZhyjo4chJIGmZoxJIwKMXrIbzKHCXhPLbD7FI9N5xZ1tHT2vn2urgDiHpx6EGHtF4Wx888Et5uL0br6TyrRxB7a8To3zXx4DN9BfPWk6TVDlEX8VrOLb+4CDO24kGtNQ00hCfwHWadCHDq4SI/aq/mKiil3KhSwOeuJprOZ9TRQT7UU71I/CXn020jO20EmlWs4kxxFxCeTFcolVrGZLj1eFOnWVCXB9p5lc+L91dYytELuJw8LHVTaY8F0HHqE0yh3xe/kSG8ihe60tF9dfQ00DxKf/PQ7MHTdBcThI4qQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/alipay/return";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://www.itxingxue.com.cn";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

	// 支付宝网关
	public static String log_path = "F:\\";


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
