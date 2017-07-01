package com.hps.april.common.utils.context;

import java.io.IOException;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class AprilResourceLoader implements FactoryBean, InitializingBean {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private Resource[] resources;
	private String[] resourceLocations;
	
	public void afterPropertiesSet() throws Exception {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

		if (resourceLocations != null){
			for (int i=0; i<resourceLocations.length;i++) {
				String resourceName = (String) resourceLocations[i];

				try {
					Resource[] configResources = resourcePatternResolver.getResources(resourceName);
					
					if (configResources.length == 0) {
						logger.warn("Unable to find resource for specified definition ["+resourceName+"] ");
					}
					
					addResources(configResources);
					
				} catch (IOException ex) {
					throw new BeanDefinitionStoreException(
							"Error accessing bean definition resource [" + resourceName + "]", ex);
				} catch (BeanDefinitionStoreException ex) {
					throw new FatalBeanException("Unable to load group definition: " +
							"group resource name [" + resourceName + "]", ex);
				}
			}
		}
	}
	
	protected void addResources(Resource[] resources){
		if (this.resources == null) this.resources = resources;
		else
			for (int i=0; i<resources.length; i++){
				Resource resource = resources[i];
				ArrayUtils.add(this.resources, resource);
			}
	}
	
	

	public Object getObject() throws Exception {
		return resources;
	}

	public Class getObjectType() {
		return resources.getClass();
	}

	public boolean isSingleton() {
		return true;
	}

	public String[] getResourceLocations() {
		return resourceLocations;
	}

	public void setResourceLocations(String[] resourceLocations) {
		this.resourceLocations = resourceLocations;
	}
	
	

}
