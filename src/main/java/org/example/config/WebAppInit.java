package org.example.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.EnumSet;


public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer { /* Абстрактный класс, который сам реализует интерфейс
  webApplicationInitializer */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; /* все запросы через слеш, подаются на диспетчер сервелет*/
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        registerCharacterEncodingFilter(servletContext);
        super.onStartup(servletContext);
        registerHiddenFieldFilter(servletContext);
    }

    private void registerHiddenFieldFilter(ServletContext context) {
        context.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true, "/*");
    }
    private void registerCharacterEncodingFilter(ServletContext context) {
        EnumSet<DispatcherType> dispatcherTypeEnum = EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC);
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8",true,true);
        FilterRegistration.Dynamic dynamic = context.addFilter("characterEncoding",characterEncodingFilter);
        dynamic.addMappingForUrlPatterns(dispatcherTypeEnum,true,"/*");
    }

}
