package example.rest.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class Utils {
    private static final SessionFactory sessionFactory = buildSessionFactory(null);

    private static SessionFactory buildSessionFactory(String configurationFile) {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            if (configurationFile == null)
                return new AnnotationConfiguration().configure()
                        .buildSessionFactory();
            else
                return new AnnotationConfiguration().configure(
                        configurationFile).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
