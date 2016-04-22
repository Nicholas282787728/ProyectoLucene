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
    private final DirectoryReader ireader;
    private final IndexSearcher isearcher;
    private final Analyzer analyzer;
    
    public Reader(Directory directory, Analyzer analyzer) throws IOException{
        this.ireader = DirectoryReader.open(directory);
        this.isearcher = new IndexSearcher(this.ireader);
        this.analyzer = analyzer;
    }

    public DirectoryReader getIreader() {
        return ireader;
    }

    public IndexSearcher getIsearcher() {
        return isearcher;
    }
    
    public void searchOnIndex(String word){
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("review", analyzer);
        Query query;
        try {
            query = parser.parse(word);
            ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs;
            // Iterate through the results:
            System.out.println("hits length: "+hits.length);
            for (ScoreDoc hit : hits) {
                Document hitDoc = isearcher.doc(hit.doc);
                System.out.println("Resultado: "+ hitDoc.toString());
            }
            ireader.close();
        } catch (ParseException | IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
