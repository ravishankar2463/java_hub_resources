package com.javahub.springcore.postprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class LifecycleAuditPostProcessor implements BeanPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(LifecycleAuditPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.startsWith("orderProcessingService")) {
            log.info(">> [BeanPostProcessor] BeforeInitialization for bean: {}", beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.startsWith("orderProcessingService")) {
            log.info(">> [BeanPostProcessor] AfterInitialization for bean: {}", beanName);
        }
        return bean;
    }
}
