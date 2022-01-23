package com.epam.infohandling;

import java.util.List;

public interface Component {
    void add(Component component);

    List<Component> getComponents();

    void remove(Component component);
}
