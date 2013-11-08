package org.pxcbpmisframework.core.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 
 * @ClassName: EncodingFilter   
 * @Description: TODO(指定的编码，但是这个被spring的拦截器替代了)   
 * @author Mr_Pxc  
 * @date 2013-6-27 上午09:30:07   
 * @project_name：BPMIS_1            
 * @change    
 * @remark    
 * @version 1.0
 */
public class EncodingFilter implements Filter {

	private String encoding = null;

	public void destroy() {
		encoding = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String encoding = getEncoding();
		if (encoding == null) {
			encoding = "utf-8";
		}
		request.setCharacterEncoding(encoding);// 在请求里设置上指定的编码
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	private String getEncoding() {
		return this.encoding;
	}

}
