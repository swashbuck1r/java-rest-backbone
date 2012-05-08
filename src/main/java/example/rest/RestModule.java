package example.rest;

import com.google.common.collect.ImmutableMap;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class RestModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(GuiceContainer.class);
        bind(WordResource.class);
        serve("/*")
                .with(GuiceContainer.class,
                        ImmutableMap.of(JSONConfiguration.FEATURE_POJO_MAPPING,
                                "true"));
    }
}
