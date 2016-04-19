/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luceneprueba;

import java.io.IOException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class LucenePrueba {

    public static void main(String[] args) throws IOException {
        try{
            Analyzer analyzer = new StandardAnalyzer();

            // Store the index in memory:
            Directory directory = new RAMDirectory();
            // To store an index on disk, use this instead:
            //Directory directory = FSDirectory.open("/tmp/testindex");
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter iwriter = new IndexWriter(directory, config);
            
            Document doc = new Document();
            String text = "This is the text to be indexed HOLA.";
            doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
            iwriter.addDocument(doc);
            
            doc = new Document();
            doc.add(new Field("fieldname", "hola chao", TextField.TYPE_STORED));
            iwriter.addDocument(doc);
            
            iwriter.close();
            
            // Now search the index:
            DirectoryReader ireader = DirectoryReader.open(directory);
            IndexSearcher isearcher = new IndexSearcher(ireader);
            // Parse a simple query that searches for "text":
            QueryParser parser = new QueryParser("fieldname", analyzer);
            Query query = parser.parse("hola NOT HOLA");
            ScoreDoc[] hits = isearcher.search(query, 2).scoreDocs;
            // Iterate through the results:
            System.out.println("hits length: "+hits.length);
            for (ScoreDoc hit : hits) {
                Document hitDoc = isearcher.doc(hit.doc);
                System.out.println("Resultado: "+ hitDoc.get("fieldname"));
            }
            ireader.close();
            directory.close();
        }
        catch(Exception e){
            System.out.println(e);
        }        
    }
    
}
