package filesystem;

public interface Component {
    float getSize();
    String getRoute();
    String getName();
    boolean add(Component component);
    Component getChild(int n);
    Component getParent();
    void accept(ComponentVisitor cv);
}
