package com.epam.infohandling.composite;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
@NoArgsConstructor
public class Composite implements Component {

    private final List<Component> components = new ArrayList<>();

    public Composite(List <Component> components) {
        this.components.addAll(components);
    }

    public void add(Component child) {
        components.add(child);
    }

    @Override
    public int size() {
        return components.size();
    }
}
