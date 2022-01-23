package com.epam.infohandling;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {

    private final List<Component> children = new ArrayList<Component>();

    public void add(Component child) {
        children.add(child);
    }

    public Composite() {
    }

    public Composite(List <Component> components) {
        children.addAll(components);
    }

    @Override
    public List<Component> getComponents() {
        return children;
    }

    @Override
    public void remove(Component component) {
        children.remove(component);
    }
}
