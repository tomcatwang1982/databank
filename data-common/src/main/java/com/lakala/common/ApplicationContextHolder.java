package com.lakala.common;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * 
 * 项目名称：base_demo   
 * 类名称：ApplicationContextHolder   
 * 类描述：   用于持有spring的applicationContext,一个系统只能有一个ApplicationContextHolder 
 * 			bean配置到ApplicationContext.xml文件中.这样保证了一个运行环境下只有一个实例,并且也间接保证了只有一个ApplicationContext对象
 *			Holder模式的主要功能是将一些Bean可以转为静态方法调用.方便使用.适用于一些系统只存在单例(singleton)并且 十分常用 的基础服务对象.这些基础服务如果每次使用spring注入,只会增加无谓的代码及一些不确定性.
 * 			使用方法:<bean class="com.gv.cc.common.spring.ApplicationContextHolder"/>
 * 			在java代码中则可以如此使用: 
 * 			BlogDao dao = (BlogDao)ApplicationContextHolder.getBean("blogDao");

 * 修改备注：   
 * @version
 */
		
public class ApplicationContextHolder implements ApplicationContextAware{
	
	private static Log log = LogFactory.getLog(ApplicationContextHolder.class);
	private static ApplicationContext applicationContext;
	@SuppressWarnings("all")
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		if(this.applicationContext != null) {
			throw new IllegalStateException("ApplicationContextHolder already holded 'applicationContext'.");
		}
		this.applicationContext = context;
		log.info("holded applicationContext,displayName:"+applicationContext.getDisplayName());
	}
	
	public static ApplicationContext getApplicationContext() {
		if(applicationContext == null)
			throw new IllegalStateException("'applicationContext' property is null,ApplicationContextHolder not yet init.");
		return applicationContext;
	}
	
	public static Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}
	
	public static void cleanHolder() {
		applicationContext = null;
	}
}
