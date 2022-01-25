package com.epam.infohandling.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Composite implements Component {

    private final List<Component> components = new ArrayList<Component>();


    public Composite() {
    }

    public Composite(List <Component> components) {
        this.components.addAll(components);
    }


    @Override
    public void add(Component child) {
        components.add(child);
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public int size() {
        return components.size();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Composite)) {
            return false;
        }

        Composite composite = (Composite) o;
        return Objects.equals(components, composite.getComponents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
    }

    @Override
    public String toString() {
        return "Composite{" +
                "components=" + components +
                '}';
    }
}
