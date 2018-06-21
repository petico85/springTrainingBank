package bank;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.List;

/*
* Aspektus orientált megoldások. Átnézni.
* */

@Component
@Aspect
public class loggerAspect { //advice + pointcut. Mit kell csinálni és mikor kell csinálni

    private List<String> methods = new ArrayList<>();

    public void clear() {
        methods.clear();
    }

    @Around("execution(* bank.BankService.*(..))") //aspectJ saját nyelve . benne van a diákban. minden bankservice metódusra fusson le
    //Around az hogy mikor. a diákban benne van mikor lehet, tehát mi lehet az around után
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable { //ez kapja el a futó metódusok neveit és egyéb adatait
        System.out.println("log: " + joinPoint.getSignature().getName());
        methods.add(joinPoint.getSignature().getName());
        return joinPoint.proceed();
    }
}