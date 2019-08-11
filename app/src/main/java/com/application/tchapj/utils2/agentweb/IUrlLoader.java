package com.application.tchapj.utils2.agentweb;

import java.util.Map;

/**
 * @author cenxiaozhong
 * @date 2017/6/3
 * @update 4.0.0
 * @since 2.0.0
 */
public interface IUrlLoader {


	void loadUrl(String url);

	void loadUrl(String url, Map<String, String> headers);

	void reload();

	void loadData(String data, String mimeType, String encoding);

	void stopLoading();

	void loadDataWithBaseURL(String baseUrl, String data,
                             String mimeType, String encoding, String historyUrl);

	void postUrl(String url, byte[] params);

	HttpHeaders getHttpHeaders();
}
