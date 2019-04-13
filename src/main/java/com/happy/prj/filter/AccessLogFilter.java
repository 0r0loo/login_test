package com.happy.prj.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessLogFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		// 외부에서 요청된(외부포트로 접근한 :8091)
		String remoteArr = StringUtils.defaultString(request.getRemoteAddr(), "-");

		String uri = StringUtils.defaultIfEmpty(req.getRequestURI(), "");
		String url = (req.getRequestURL() == null) ? "" : req.getRequestURL().toString();

		// 주소의 ? 뒤에 있는 "키=값&..."
		String queryString = StringUtils.defaultIfEmpty(req.getQueryString(), "");

		// Header 정보(Referer)
		String refer = StringUtils.defaultString(req.getHeader("Referer"), "-");

		// Header 정보(User-Agent)
		String agent = StringUtils.defaultString(req.getHeader("User-Agent"), "-");

		String fullUrl = url;
		fullUrl += StringUtils.isNotEmpty(queryString) ? "?" + queryString : queryString;

		StringBuffer result = new StringBuffer();
		result.append(":").append(remoteArr).append(":").append(fullUrl).append(":").append(refer).append(":")
				.append(agent);

		logger.info("[LOG FILTER] + " + result.toString());

		// 원래 가던 경로로 보냄
		chain.doFilter(req, response);

	}

	@Override
	public void destroy() {
	}

}
