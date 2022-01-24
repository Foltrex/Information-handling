package com.epam.infohandling.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {

    private final List<Component> children = new ArrayList<Component>();


    public Composite() {
    }

    public Composite(List <Component> components) {
        children.addAll(components);
    }


    @Override
    public void add(Component child) {
        children.add(child);
    }

    @Override
    public List<Component> getComponents() {
        return children;
    }

    @Override
    public int size() {
        return children.size();
    }
}
