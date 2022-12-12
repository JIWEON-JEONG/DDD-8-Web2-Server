package ddd.caffeine.ratrip.util;

import static ddd.caffeine.ratrip.exception.ExceptionInformation.*;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.lang.Nullable;

import ddd.caffeine.ratrip.exception.CoreException;

public class YamlPropertySourceFactory implements PropertySourceFactory {
	@Override
	public PropertySource<?> createPropertySource(@Nullable String name, EncodedResource resource) throws IOException {
		Properties propertiesFromYaml = loadYamlIntoProperties(resource);
		if (name != null) {
			return new PropertiesPropertySource(name, propertiesFromYaml);
		}
		return new PropertiesPropertySource(resource.getResource().getFilename(), propertiesFromYaml);
	}

	private Properties loadYamlIntoProperties(EncodedResource resource) {
		try {
			YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
			factory.setResources(resource.getResource());
			factory.afterPropertiesSet();
			return factory.getObject();
		} catch (IllegalStateException e) {
			throw new CoreException(ILLEGAL_YML_PROPERTIES_EXCEPTION);
		}
	}
}
