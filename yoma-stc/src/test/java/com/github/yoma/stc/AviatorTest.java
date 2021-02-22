package com.github.yoma.stc;

import java.util.HashMap;

import org.junit.Test;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import lombok.extern.slf4j.Slf4j;

/**
 * @author msh01
 * @version 1.0.0
 * @createTime 2020年04月20日 23:20:00
 */
@Slf4j
public class AviatorTest {

    @Test
    public void ss1() {
        String expression = "a-(b-c) > 100";
        Expression compiledExp = AviatorEvaluator.compile(expression);
        // Execute with injected variables.
        HashMap<String, Object> env = new HashMap<>();
        env.put("a", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);
        Boolean result = (Boolean)compiledExp.execute(env);
        log.info(result.toString());
    }

    @Test
    public void ss2() {
        String expression = "a+b";
        Expression compiledExp = AviatorEvaluator.compile(expression);
        // Execute with injected variables.
        HashMap<String, Object> env = new HashMap<>();
        env.put("a", 100);
        env.put("b", 45);
        log.info(compiledExp.execute(env).toString());
    }

    @Test
    public void ss3() {
        String expression = "println(\"hello, AviatorScript!\")";
        Expression compiledExp = AviatorEvaluator.compile(expression);
        // Execute with injected variables.
        HashMap<String, Object> env = new HashMap<>();
        env.put("a", 100);
        env.put("b", 45);
        compiledExp.execute();
        // log.info(compiledExp.execute().toString());
    }

    @Test
    public void ss4() {
        String expression = "println(getVariateValue(154))";
        Expression compiledExp = AviatorEvaluator.compile(expression);
        // Execute with injected variables.
        HashMap<String, Object> env = new HashMap<>();
        env.put("a", 100);
        env.put("b", 45);
        compiledExp.execute();
        // log.info(compiledExp.execute().toString());
    }
}
