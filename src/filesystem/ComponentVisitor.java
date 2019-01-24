package filesystem;

public interface ComponentVisitor {
    void visitFileLeaf(FileLeaf fileLeaf);
    void visitDirectory(Directory dir);
}
