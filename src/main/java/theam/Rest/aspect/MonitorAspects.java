/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theam.Rest.aspect;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author equipo
 */
@Aspect
@Configuration
public class MonitorAspects {
    private static final Logger LOG = LogManager.getLogger(MonitorAspects.class);

    @Before("execution(* theam.Rest..*.*(..))")
    public void runBeforeMethod(JoinPoint jp){
        LOG.info("LOG ASPECT >> Entering Method: " + jp.getSignature().getName());
        LOG.info("LOG ASPECT >> Class Name: " + jp.getSignature().getDeclaringType());
        Object[] args = jp.getArgs();
        LOG.info("LOG ASPECT >> Arguments :  " + Arrays.toString(args));
        LOG.info("LOG ASPECT >> Target class : " + jp.getTarget().getClass().getName());
    }

}
