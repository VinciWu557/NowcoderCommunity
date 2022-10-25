package com.nowcoder.community;

import com.nowcoder.community.config.AlphaConfig;
import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.assertj.ApplicationContextAssertProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
// 选择测试用的配置类
@ContextConfiguration(classes = CommunityApplication.class)
// 那个类想要得到 Spring容器, 就要实现接口 ApplicationContextAware
class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	// ApplicationContext 是 BeanFactory 的接口, 本质上是一个工厂类
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext() {
		// 类名@hashCode
		// org.springframework.web.context.support.GenericWebApplicationContext@40db2a24, started on Sat Oct 22 10:11:41 CST 2022
		System.out.println(applicationContext);
		// 按类获取Bean
		// 优先找 @Primary
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());
		// 按名获取Bean >> 返回类型为 Object 需要强制转换或者加参数
		alphaDao = applicationContext.getBean("alphaHibernate", AlphaDao.class);
		System.out.println(alphaDao.select());
	}

	@Test
	public void testBeanManagement(){
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		// com.nowcoder.community.service.AlphaService@2241f05b
		System.out.println(alphaService);

		alphaService = applicationContext.getBean(AlphaService.class);
		// 默认使用单例
		System.out.println(alphaService);
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format((new Date())));
	}
	// 你虽然没调另外的test方法，但是你在对应的Bean上加了注解，不管是谁触发的容器创建，这个初始化都无法避免
	// 程序启动时，准确的说是服务器创建容器后，他就会自动初始化Bean

	// 依赖注入: 一般放在属性之前
	@Autowired
	// 想注入特定的Bean
	@Qualifier("alphaHibernate")
	private AlphaDao alphaDao;

	@Autowired
	private AlphaService alphaService;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void testDI(){
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}

}
