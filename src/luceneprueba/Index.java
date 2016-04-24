/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luceneprueba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Iterator;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import luceneprueba.CustomAnalyzers.ReviewAnalyzer;
/**
 *
 * @author JAno
 */
public class Index {
    private String path;
    private String indexName;
    private String jsonFilePath;
    private final Directory directory;
    private final Analyzer analyzer = new ReviewAnalyzer();
    //private final Analyzer analyzer = new StandardAnalyzer();
    //private final Analyzer indexAnalyzer = new StandardAnalyzer();
    
    public Index(String path, String indexName) throws IOException{
        this.path = path;
        this.indexName = indexName;
        this.directory = new SimpleFSDirectory(FileSystems.getDefault().getPath(path, indexName));
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Directory getDirectory() {
        return directory;
    }

    public Analyzer getAnalyzer() {
        return analyzer;
    }
    
    public void create(){
        try{
            // Storing index in disk
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter iwriter = new IndexWriter(directory, config);

            Document doc;
            File dir = new File("files/input");
            File[] files = dir.listFiles();
            
            BufferedReader br;
            String fileContent;
            JSONArray jsonArray;
            JSONParser jsonParser = new JSONParser();
            
            for(File file : files){
                if(file.isFile() && file.canRead() && file.getName().endsWith(".txt")){
                    System.out.println("[Index]Indexando el archivo: "+file.getName());                   
                    
                    br = new BufferedReader(new FileReader(file));
                    fileContent = br.readLine();
                    jsonArray = (JSONArray) jsonParser.parse(fileContent);
                    Iterator i = jsonArray.iterator();
                    JSONObject json;
                    
                    while(i.hasNext()){
                        json = (JSONObject) i.next();
                        doc = new Document();
                        doc.add(new TextField("title", (String) json.get("Title"), Field.Store.YES));
                        doc.add(new TextField("score", (String) json.get("Score"), Field.Store.YES));
                        doc.add(new TextField("review", (String) json.get("Review"), Field.Store.YES));
                        doc.add(new TextField("date", (String) json.get("Date"), Field.Store.YES));
                        iwriter.addDocument(doc);
                    }  
                    iwriter.close();
                    //doc.add(new StringField("path", file.getPath(), Field.Store.YES));
                    //doc.add(new Textfield("content"))
                }
            }                
        }
        catch(IOException | ParseException e){
            System.out.println(e.getLocalizedMessage());
        }
    }
    
}
