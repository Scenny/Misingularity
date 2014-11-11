package util;

import java.io.IOException;
import java.io.RandomAccessFile;

public class SSDTreapBuilder<V extends Comparable<V>> {
	
	public void save(String filename) throws IOException {
		SSDTreap<V> treap = new SSDTreap<V>();
		
		RandomAccessFile idxFile = null;
		RandomAccessFile datFile = null;
		try {
            idxFile = new RandomAccessFile(filename + SSDTreap.idxSuffix, "rw");
            datFile = new RandomAccessFile(filename + SSDTreap.datSuffix, "rw");
            if (idxFile.length() != 0 || datFile.length() != 0) {
                throw new RuntimeException("File is not empty, are u sure you want to overwrite?");
            }
            
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
                idxFile.close();
                datFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}
}
