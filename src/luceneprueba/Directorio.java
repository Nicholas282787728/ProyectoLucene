/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luceneprueba;

import java.io.IOException;
import java.util.Collection;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.IndexInput;
import org.apache.lucene.store.IndexOutput;
import org.apache.lucene.store.Lock;

public class Directorio extends Directory{
    public Lock lock;
    
    @Override
    public String[] listAll() throws IOException {
        throw new UnsupportedOperationException("Not supported yet 1."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteFile(String string) throws IOException {
        throw new UnsupportedOperationException("Not supported yet 2."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long fileLength(String string) throws IOException {
        throw new UnsupportedOperationException("Not supported yet 3."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IndexOutput createOutput(String string, IOContext ioc) throws IOException {
        throw new UnsupportedOperationException("Not supported yet 4."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IndexOutput createTempOutput(String string, String string1, IOContext ioc) throws IOException {
        throw new UnsupportedOperationException("Not supported yet 5."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sync(Collection<String> clctn) throws IOException {
        throw new UnsupportedOperationException("Not supported yet 6."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void renameFile(String string, String string1) throws IOException {
        throw new UnsupportedOperationException("Not supported yet 7."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IndexInput openInput(String string, IOContext ioc) throws IOException {
        throw new UnsupportedOperationException("Not supported yet 8."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lock obtainLock(String string) throws IOException {
        try{
            return lock;
        }
        catch(Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet 10."); //To change body of generated methods, choose Tools | Templates.
    }
}
