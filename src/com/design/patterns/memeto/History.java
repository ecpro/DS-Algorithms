package com.design.patterns.memeto;

import com.design.patterns.memeto.states.State;

import java.util.ArrayList;
import java.util.List;

public class History {

    private List<State> states = new ArrayList<>();

    public void push(State state) {
        states.add(state);
    }

    public State pop() {
        int size = states.size();
        if(size > 0) {
            State state = states.get(size - 1);
            states.remove(size - 1);
            return state;
        }
        return null;
    }
}
