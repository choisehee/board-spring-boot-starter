package com.himedia.jdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.himedia.jdbc.util.JDBCConnectionManager;

//JDBCConnectionManager를 빈으로 등록하는 환경 설정 클래스

@Configuration //@Configuration 을 가지고 있으므로 자동으로 빈으로 등록 동신에 BoardAutoConfiguration가 가지고 있는 @Bean 설정들도 모두 처리되므로 JDBCConnectionManager 객체도 빈으로 등록
@EnableConfigurationProperties(JDBCConnectionManagerProperties.class)
public class BoardAutoConfiguration {
	
	//자동화 유지보수에 좋은 예
	@Autowired
	private JDBCConnectionManagerProperties properties;

	@Bean
	@ConditionalOnMissingBean
	public JDBCConnectionManager getJDBCConnectionManager() {
		JDBCConnectionManager manager = new JDBCConnectionManager();
		manager.setDriverClass(properties.getDriverClass());
		manager.setUrl(properties.getUrl());
		manager.setUsername(properties.getUsername());
		manager.setPassword(properties.getPassword());
		return manager;
	}
	
	//직접 정의
//	@Bean
//	@ConditionalOnMissingBean //등록하려는 빈이 메모리에 없는 경우에만 현재 빈 등록을 처리한 지금은 우리가 h2를 사용하므로 Chapter2에 JDBCConnectionManager객체가 있으모로 현재 이 빈은 생성되지 않는다
//	public JDBCConnectionManager getJDBCConnectionManager() {
//		JDBCConnectionManager manager = new JDBCConnectionManager();
//		manager.setDriverClass("oracle.jdbc.driver.OracleDriver");
//		manager.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
//		manager.setUsername("hr");
//		manager.setPassword("12341234");
//		return manager;
//	}

}
