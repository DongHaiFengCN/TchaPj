package com.application.tchapj.utils2.agentweb;

import android.webkit.WebView;

import java.util.Map;
import java.util.Set;

/**
 * @author cenxiaozhong
 * @date 2017/5/13
 * @since 1.0.0
 */
public class JsInterfaceHolderImpl extends JsBaseInterfaceHolder {

	private static final String TAG = JsInterfaceHolderImpl.class.getSimpleName();
	private WebView mWebView;
	private AgentWeb.SecurityType mSecurityType;

	static JsInterfaceHolderImpl getJsInterfaceHolder(WebView webView, AgentWeb.SecurityType securityType) {

		return new JsInterfaceHolderImpl(webView, securityType);
	}


	JsInterfaceHolderImpl(WebView webView, AgentWeb.SecurityType securityType) {
		super(securityType);
		this.mWebView = webView;
		this.mSecurityType = securityType;
	}

	@Override
	public JsInterfaceHolder addJavaObjects(Map<String, Object> maps) {


		if (!checkSecurity()) {
			LogUtils.e(TAG, "The injected object is not safe, give up injection");
			return this;
		}

		Set<Map.Entry<String, Object>> sets = maps.entrySet();
		for (Map.Entry<String, Object> mEntry : sets) {

			Object v = mEntry.getValue();
			boolean t = checkObject(v);
			if (!t) {
				throw new JsInterfaceObjectException("This object has not offer method javascript to call ,please check addJavascriptInterface annotation was be added");
			} else {
				addJavaObjectDirect(mEntry.getKey(), v);
			}
		}

		return this;
	}

	@Override
	public JsInterfaceHolder addJavaObject(String k, Object v) {

		if (!checkSecurity()) {
			return this;
		}
		boolean t = checkObject(v);
		if (!t) {
			throw new JsInterfaceObjectException("this object has not offer method javascript to call , please check addJavascriptInterface annotation was be added");
		} else {
			addJavaObjectDirect(k, v);
		}
		return this;
	}

	private JsInterfaceHolder addJavaObjectDirect(String k, Object v) {
		LogUtils.i(TAG, "k:" + k + "  v:" + v);
		this.mWebView.addJavascriptInterface(v, k);
		return this;
	}


}