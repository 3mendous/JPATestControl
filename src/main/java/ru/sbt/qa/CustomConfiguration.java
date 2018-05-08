package ru.sbt.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Configuration;
import javax.ws.rs.ext.ContextResolver;

/**
 * Jersey Provide to customise the Jackson ObjectMapper.
 */
@Configuration
public class CustomConfiguration implements ContextResolver<ObjectMapper> {

    /** The Object Mapper to use. */
    private final ObjectMapper mapper;

    /**
     * Default constructor, which creates the ObjectMapper and add the custom modules.
     */
    public CustomConfiguration() {
        Hibernate5Module hibernate5Module = new Hibernate5Module();
        hibernate5Module.configure(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION, false);

        mapper = new ObjectMapper();
        mapper.registerModule(hibernate5Module);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }

}
