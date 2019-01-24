
package filesystem;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

public class FileSystemReader {

    private Directory root;
    
    public FileSystemReader (String fromDir) {
        File directory = new File(fromDir);
        if ( !directory.isDirectory() ) {
            throw new IllegalArgumentException("Not a directory: " + 
                    directory.getAbsolutePath());
        }
        
        root = new Directory(fromDir);
        recursiveRead(root);
        
        ComponentVisitor printer = new ComponentVisitor() {
            StringBuilder spaces = new StringBuilder();
            public void visitFileLeaf(FileLeaf fl) {
                System.out.printf("%9.1f KB   %s%s\n", fl.getSize(), spaces, fl.getName());
            }

            @Override
            public void visitDirectory(Directory dir) {
                Iterator<Component> it = dir.iterator();
                System.out.printf("%9.1f KB   %s%s\n",dir.getSize(), spaces, dir.getName());
                spaces.append("   ");
                while(it.hasNext()) {
                    it.next().accept(this);
                }
                spaces.setLength(spaces.length() - 3);
            }
        };
        
        root.accept(printer);
    }
    
    
    private void recursiveRead(Directory dirToRead) {
        File dir = new File(dirToRead.getRoute());
        File[] files = dir.listFiles();
        
        if(files == null) { return; }
        
        for (File f : files) {
            if (f.isDirectory()) {
                Directory subdir = new Directory(f.getName(), dirToRead);
                recursiveRead(subdir);
                dirToRead.add(subdir);
            } else {
                float size = f.length() / 1024f;
                dirToRead.add(
                    new FileLeaf(size, f.getName(), dirToRead)
                );
            }
        }
    }
}
