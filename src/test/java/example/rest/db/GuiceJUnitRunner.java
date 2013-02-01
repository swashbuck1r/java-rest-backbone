package example.rest.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

// Helper for loading Guice-based testcases
// Credit: Fabio Strozzi
// Based on blog post from - http://fabiostrozzi.eu/2011/03/27/junit-tests-easy-guice/
public class GuiceJUnitRunner extends BlockJUnit4ClassRunner {
    private Injector injector;
    private boolean hasPersistence;
     
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    public @interface GuiceModules {
        Class<?>[] value();
    }
    
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    public @interface GuicePersist {
        String value();
    }
    
    public interface ModuleFactory {
    	public List<Module> createGuiceModules();
    }
 
    @Override
    public Object createTest() throws Exception {
        Object obj = super.createTest();
        
        if(hasPersistence) {
        	injector.getInstance(PersistService.class).start();
        }
        
        injector.injectMembers(obj);
        return obj;
    }
 
    public GuiceJUnitRunner(Class<?> klass) throws InitializationError {
        super(klass);
        List<Module> modules = new ArrayList<Module>(Arrays.asList(new Module[0]));
        addPersistenceModule(klass, modules);
        addModules(getModulesFor(klass), modules);
        injector = Guice.createInjector(modules);
    }
 
    private void addModules(Class<?>[] classes, List<Module> modules) throws InitializationError {
        for (int i = 0; i < classes.length; i++) {
            try {
            	modules.add((Module) (classes[i]).newInstance());
            } catch (InstantiationException e) {
                throw new InitializationError(e);
            } catch (IllegalAccessException e) {
                throw new InitializationError(e);
            }
        }
    }
 
    private Class<?>[] getModulesFor(Class<?> klass) throws InitializationError {
        GuiceModules annotation = klass.getAnnotation(GuiceModules.class);
        if (annotation == null)
            throw new InitializationError(
                    "Missing @GuiceModules annotation for unit test '" + klass.getName()
                            + "'");
        return annotation.value();
    }
    
    private void addPersistenceModule(Class<?> klass, List<Module> modules) {
    	GuicePersist annotation = klass.getAnnotation(GuicePersist.class);
    	if(annotation != null) {
    		modules.add(new JpaPersistModule(annotation.value()));
    		hasPersistence = true;
    	}
    }
}