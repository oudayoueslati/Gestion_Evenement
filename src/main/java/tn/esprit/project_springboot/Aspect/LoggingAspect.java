package tn.esprit.project_springboot.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @AfterThrowing(
            pointcut = "execution(* tn.esprit.project_springboot.service.*.ajouterTicketsEtAffecterAevenementEtInternaute(..))",
            throwing = "exception"
    )
    public void handleException(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        if (exception instanceof java.lang.UnsupportedOperationException) {
            log.info("Exception in method {} : Le nombre de places restantes dépasse le nombre de tickets demandés.", methodName);
        } else {
            log.error("Exception in method {} : {}", methodName, exception.getMessage());
        }
    }
}

