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
public class logAspect {
    private static final Logger LOG = LogManager.getLogger(logAspect.class);

    @Before("execution(* theam.Rest..*.*(..))")
    public void runBeforeMethod(JoinPoint jp){
        LOG.info("ASPECT >> Entering Method: " + jp.getSignature().getName());
        LOG.info("ASPECT >> Class Name: " + jp.getSignature().getDeclaringType());
        LOG.info("ASPECT >> Arguments :  " + Arrays.toString(jp.getArgs()));
        LOG.info("ASPECT >> Target class : " + jp.getTarget().getClass().getName());
    }
}
