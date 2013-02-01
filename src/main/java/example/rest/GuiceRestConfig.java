package example.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;

import example.rest.db.DictionaryDBModule;

public class GuiceRestConfig extends GuiceServletContextListener {
    private ServletContext sc;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.sc = servletContextEvent.getServletContext();
        super.contextInitialized(servletContextEvent);
    }

    @Override
    protected Injector getInjector() {
    	ArrayList<Module> modules = new ArrayList<Module>();
    	modules.add(new RestModule());
    	addDictionaryModules(modules);
    	
    	Injector injector = Guice.createInjector(modules);
    	
    	return injector;
    }

    private void addDictionaryModules(List<Module> modules) {
        String mode = this.sc.getInitParameter("mode");
        sc.log("Application mode: " + mode);
        if (mode != null && mode.equals("prod")) {
        	modules.add(new JpaPersistModule("appPU"));
        } else {
        	modules.add(new JpaPersistModule("localPU"));
        }
        
        modules.add(new DictionaryDBModule());
    }
}
