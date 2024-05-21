package ru.gb.sem11_prometheus.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Component
@Aspect
public class LoggingAspect {

    // logUser - метод, оборачивающий работу любого помеченного аннотацией метода.
    // На вход принимаем метод ProceedingJoinPoint.
    // Внутри - достаём название метода, его аннотации (массив аннотаций) и аргументы (массив объектов).
    // Обязательно вызываем метод. Результат его выполнения записываем в переменную Object.
    // В итоговую строку лога помещаем все данные о методе, а также записываем результат.
    @Around("@annotation(TrackUserAction)")
    public Object logUser(ProceedingJoinPoint targetMethod) throws Throwable {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");

        Annotation[] targetMethodAnno = ((MethodSignature) targetMethod.getSignature()).getMethod().getAnnotations();
        String targetMethodAnnoStr = targetMethodAnno.length > 0 ? " (" + Arrays.toString(targetMethodAnno) + ") " : "";

        Object[] targetMethodArgs = targetMethod.getArgs();
        String targetMethodArgsStr = targetMethodArgs.length > 0 ? " с аргументами ["
                + Arrays.toString(targetMethodArgs) + "]" : " без аргументов";

        Object resultValue = targetMethod.proceed();

        String log = ">>> LOG " +
                dateFormat.format(new Date()) +
                ": вызван метод " +
                targetMethod.getSignature().getName() +
                targetMethodAnnoStr +
                targetMethodArgsStr +
                " с результатом: " +
                resultValue +
                ".\n";

        System.out.println(log);

        return resultValue;
    }

    // МЕТОД НЕ РАБОТАЕТ ! точнее - из-за неправильной комбинации передаваемых в метод аргументов,
    // СПРИНГ не может создать БИН аспект-класса.
    // метод, реализующий @AfterReturning, не может принимать  JoinPoint / ProceedingJoinPoint на вход,
    // видимо потому, что метод уже отработал к моменту AfterReturning
    // ВЫВОД! МЕТОДЫ, РЕАЛИЗУЮЩИЕ КАЖДОГО ИЗ ADVICE, должны на вход принимать конкретные аргументы
    // THROWING - исключение, AFTERReturning - результат и т.д.

//    @AfterReturning(value = "@annotation(TrackUserAction)", returning = "resultValue")
//    public void anotherLogUser(Object resultValue, JoinPoint targetMethod) {
//        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
//
//        Annotation[] targetMethodAnno = ((MethodSignature) targetMethod.getSignature()).getMethod().getAnnotations();
//        String targetMethodAnnoStr = targetMethodAnno.length > 0 ? " (" + Arrays.toString(targetMethodAnno) + ") " : "";
//
//        Object[] targetMethodArgs = targetMethod.getArgs();
//        String targetMethodArgsStr = targetMethodArgs.length > 0 ? " с аргументами ["
//                + Arrays.toString(targetMethodArgs) + "]" : " без аргументов";
//
//        String log = ">>> LOG " +
//                dateFormat.format(new Date()) +
//                ": вызван метод " +
//                targetMethod.getSignature().getName() +
//                targetMethodAnnoStr +
//                targetMethodArgsStr +
//                " с результатом: " +
//                resultValue +
//                ".\n";
//
//        System.out.println(log);
//    }


}
