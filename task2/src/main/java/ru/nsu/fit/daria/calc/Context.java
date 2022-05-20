package ru.nsu.fit.daria.calc;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    private final Stack<Double> stack;
    private final Map<String, Double> consts;

    public Context() {
        stack = new Stack<>();
        consts = new HashMap<>();
    }

   public void put(String str, Double value){
        consts.put(str, value);
    }

    public Map<String, Double> getConstants(){
        return consts;
    }

    public Double get(String macros){
        return consts.get(macros);
    }

    public void push(Double value) {
        stack.push(value);
    }

    public Double pop() {
        return stack.pop();
    }

    public Integer getStackSize() {
        return stack.size();
    }

    public Stack<Double> getStack() {
        return stack;
    }
}
