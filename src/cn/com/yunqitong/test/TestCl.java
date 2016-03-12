package cn.com.yunqitong.test;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCl {
	@Test
	public void testRegix(){
		/*Pattern pattern=Pattern.compile("^[0-9]*{11}$");
		Matcher matcher=pattern.matcher("13022250556");
		boolean matches = matcher.matches();
		if(matches){
			System.out.println("对的");
		}else{
			System.out.println("错的");
		}*/
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		DefaultSqlSessionFactory bean = (DefaultSqlSessionFactory) context.getBean("sqlSessionFactory");
		SqlSession openSession = bean.openSession();
		System.out.println("2");
		System.out.println("1");
	}
}
