package example.rest.local;

import com.google.inject.AbstractModule;

import example.rest.IDictionaryService;

public class DictionaryLocalModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IDictionaryService.class).toInstance(new DictionaryMemService());
    }
}
