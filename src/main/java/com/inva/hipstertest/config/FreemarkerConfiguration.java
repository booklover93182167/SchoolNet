package com.inva.hipstertest.config;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;

@Configuration
public class FreemarkerConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(false);
        resolver.setPrefix("/freemarker/");
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
        //factory.setTemplateLoaderPath("/templates/viewFTL");
        factory.setTemplateLoaderPaths("classpath:org/springframework/web/servlet/view/freemarker", "/templates/viewFTL");
        factory.setDefaultEncoding("UTF-8");
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        freemarker.template.Configuration configuration = factory.createConfiguration();
        configuration.addAutoImport("spring", "spring.ftl");
        result.setConfiguration(configuration);
        return result;
    }


    class ClassPathTldsLoader{
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void loadClassPathTlds() {
        freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(Arrays.asList("/META-INF/security.tld"));
        }
    }

    @Bean
    @ConditionalOnMissingBean(ClassPathTldsLoader.class)
    public ClassPathTldsLoader classPathTldsLoader(){
        return new ClassPathTldsLoader();
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/scripts/**")
            .addResourceLocations("classpath:/scripts/");
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("locale/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
