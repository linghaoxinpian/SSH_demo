package core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import core.constant.Constant;
import core.permission.PermissionCheck;
import taxservice.user.entity.User;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		//所有过滤器的第一步基本都是强转类型为：HttpServletRequest和HttpServletResponse。原因是带Http的含有更多的有助于开发的方法
		HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
		//2.获取请求的url，
		String uri = httpServletRequest.getRequestURI();	//url与uri的区别：url更长一点，带有更多信息，uri短一点，这里不需要url里的那么多信息，所以选择uri
		//3.判断是否为非登录请求，若是，则拦截
		if(uri.contains("sys/login_")){
			//登录请求，放行
			chain.doFilter(servletRequest, servletResponse);
		}else{
			//非登录请求,检查
			if(httpServletRequest.getSession().getAttribute(Constant.SYS_USER)!=null){
				//已登录，继续检查访问的是否是：纳税服务
				if(uri.contains("nsfw")){
					//访问的是 "纳税服务",继续判断是否具有访问权限
					WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(httpServletRequest.getSession().getServletContext());	//获取该Web应用从启动时就创建的第一个Spring容器，不用ClassPath...的方法是因为会过度消耗内存
					PermissionCheck permission= (PermissionCheck) webApplicationContext.getBean("permissionCheck");					
					if(permission.isAccessible((User) httpServletRequest.getSession().getAttribute(Constant.SYS_USER), "nsfw")){
						//有权限访问
						chain.doFilter(httpServletRequest, httpServletResponse);
					}else{
						//无权限访问,重定向
						httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/sys/login_toNoPermissionUI.action");
					}
				}else{					
					chain.doFilter(httpServletRequest, httpServletResponse);
				}
			}else{
				//未登录，重定向到登陆页面
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/sys/login_toLoginUI.action");
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
