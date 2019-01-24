
package filesystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Directory implements Component, Iterable {

    List<Component> children = new ArrayList<Component>();
    private final String name;
    private Component parent;

    public Directory(String name, Component parent) {
        this.name = name;
        this.parent = parent;
    }
    
    public Directory(String name) { this(name, null); }
    
    @Override
    public float getSize() {
        float size = 0;
        for (Component child : children) {
            size += child.getSize();
        }
        return size;
    }

    @Override
    public boolean add(Component component) {
        return children.add(component);
    }

    @Override
    public Component getChild(int n) {
        return children.get(n);
    }

    @Override
    public String getRoute() {
        if (parent != null) {
            return parent.getRoute() + "/" + name;
        }
        return name;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Component getParent() {
        return parent;
    }
    
    @Override
    public Iterator<Component> iterator() { return children.iterator(); }
    
    @Override
    public void accept(ComponentVisitor cv) {
        cv.visitDirectory(this);
    }
}
