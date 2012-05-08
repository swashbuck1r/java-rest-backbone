package example.rest.db;

import com.google.inject.AbstractModule;

import example.rest.IDictionaryService;

public class DictionaryDBModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IDictionaryService.class).to(DictionaryDBService.class);
    }

}
