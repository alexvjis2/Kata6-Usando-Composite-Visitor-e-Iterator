package kata6iterator;

import filesystem.FileSystemReader;

public class Kata6Iterator {

    public static void main(String[] args) {
        new FileSystemReader( args.length == 0 ? "." : args[0] ).read();
    }
}
