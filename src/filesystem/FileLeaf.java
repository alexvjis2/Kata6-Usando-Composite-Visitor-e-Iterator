
package filesystem;

public class FileLeaf implements Component {

    private float size;
    private final String name;
    private Component parent;
    
    public FileLeaf(float size, String name, Component parent) {
        this.size = size;
        this.name = name;
        this.parent = parent;
    }
    
    public FileLeaf(float size, String name) { this(size, name, null); }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public float getSize() { return size; }

    @Override
    public boolean add(Component component) { return false; }

    @Override
    public Component getChild(int n) { return null; }

    @Override
    public String getRoute() {
        if (parent != null) {
            return parent.getRoute() + "/" + name;
        }
        return name;
    }

    @Override
    public Component getParent() { return parent; }

    @Override
    public void accept(ComponentVisitor cv) {
        cv.visitFileLeaf(this);
    }
}
