package spring.security.config;

import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "spring.*" })
@EnableTransactionManagement
@Import(value = { LoginSecurityConfig.class })
public class LoginApplicationConfig extends WebMvcConfigurerAdapter{
	
	@Bean
    public SessionFactory sessionFactory() {
            LocalSessionFactoryBuilder builder =
		new LocalSessionFactoryBuilder(dataSource());
            builder.scanPackages("spring.model")
                  .addProperties(getHibernateProperties());

            return builder.buildSessionFactory();
    }
	
	private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect",
            "org.hibernate.dialect.PostgreSQLDialect");
        prop.put("hibernate.enable_lazy_load_no_trans", "true");
        return prop;
	}

	@Bean(name = "dataSource")
	public BasicDataSource dataSource() {
	
	BasicDataSource ds = new BasicDataSource();
	    ds.setDriverClassName("org.postgresql.Driver");
	ds.setUrl("jdbc:postgresql://localhost:5432/erp");
	ds.setUsername("postgres");
	ds.setPassword("postgres");
	return ds;
	}
	
	//Create a transaction manager
	@Bean
	public HibernateTransactionManager txManager() {
	        return new HibernateTransactionManager(sessionFactory());
	}

	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	//Resource handler
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	}
	
}