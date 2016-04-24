/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luceneprueba;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;

/**
 *
 * @author JAno
 */
public class Reader {
    private final DirectoryReader indexReader;
    private final IndexSearcher indexSearcher;
    private final Analyzer analyzer;
    
    public Reader(Directory directory, Analyzer analyzer) throws IOException{
        this.indexReader = DirectoryReader.open(directory);
        this.indexSearcher = new IndexSearcher(this.indexReader);
        this.analyzer = analyzer;
    }

    public DirectoryReader getIndexReader() {
        return indexReader;
    }

    public IndexSearcher getIndexSearcher() {
        return indexSearcher;
    }
    
    public void searchOnIndex(String wordQuery){
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("review", analyzer);
        Query query;
        try {
            query = parser.parse(wordQuery);
            ScoreDoc[] hits = indexSearcher.search(query, 1000).scoreDocs;
            
            if (hits.length == 0){
                 System.out.println("[Search] No se han encontrado coincidencias.");
            } else {
                System.out.println("[Search] Se han encontrado: " + hits.length + " coincidencias.");
                for (ScoreDoc hit : hits) {
                    Document hitDoc = indexSearcher.doc(hit.doc);
                    System.out.println("[Search] Resultado: " + hitDoc.toString());
                }
                indexReader.close();
            }

        } catch (ParseException | IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
