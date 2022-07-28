package org.example.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class webAppInit extends AbstractAnnotationConfigDispatcherServletInitializer { /* Абстрактный класс, который сам реализует интерфейс
  webApplicationInitializer */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {webConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"}; /* все запросы через слеш, подаются на диспетчер сервелет*/
    }

}
