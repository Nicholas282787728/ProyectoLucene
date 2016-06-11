/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luceneprueba;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author JAno
 */
public class Reader {
    private final DirectoryReader indexReader;
    private final IndexSearcher indexSearcher;
    //private Analyzer analyzer;
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
    
    /* // TODO: Fix setAnalyzer
    public setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }
    */
    public void searchOnIndex(String wordQuery, String date){
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("review", analyzer);
        Query query;
        try {
            query = parser.parse("date: \"+" + date + "\" AND " + wordQuery);
            ScoreDoc[] hits = indexSearcher.search(query, 60).scoreDocs;
            
            if (hits.length == 0){
                 System.out.println("[Search] No se han encontrado coincidencias.");
            } else {
                System.out.println("[Search] Se han encontrado: " + hits.length + " coincidencias.");
                int i = 1;
                JSONArray reviews = new JSONArray();
                for (ScoreDoc hit : hits) {
                    Document hitDoc = indexSearcher.doc(hit.doc);
                    //System.out.println(i+".- Score: " + hit.score + ", Doc: " + hitDoc.get("movieId") + ", path: " + hitDoc.get("path") + ", Tile review: " + hitDoc.get("title"));
                    JSONObject json = new JSONObject();
                    json.put("date", hitDoc.get("date"));
                    json.put("genre", hitDoc.get("genre"));
                    json.put("review", hitDoc.get("review"));
                    json.put("score", hitDoc.get("score"));
                    reviews.add(json);
                    i++;
                }
                FileWriter file = new FileWriter("files/output/reviews_" + date.replace(" ", "") + ".json");
                file.write(reviews.toJSONString());
                file.flush();
                file.close();
                indexReader.close();
            }

        } catch (ParseException | IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
        
    }
    
    public void searchOnIndex(String date){
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("review", analyzer);
        Query query;
        try {
            query = parser.parse("date: \"+" + date + "\"");
            ScoreDoc[] hits = indexSearcher.search(query, 1).scoreDocs;
            
            if (hits.length == 0){
                 System.out.println("[Search] No se han encontrado coincidencias.");
            } else {
                System.out.println("[Search] Se han encontrado: " + hits.length + " coincidencias.");
                int i = 1;
                JSONArray reviews = new JSONArray();
                for (ScoreDoc hit : hits) {
                    Document hitDoc = indexSearcher.doc(hit.doc);
                    //System.out.println(i+".- Score: " + hit.score + ", Doc: " + hitDoc.get("movieId") + ", path: " + hitDoc.get("path") + ", Tile review: " + hitDoc.get("title"));
                    JSONObject json = new JSONObject();
                    json.put("date", hitDoc.get("date"));
                    json.put("genre", hitDoc.get("genre"));
                    json.put("review", hitDoc.get("review"));
                    json.put("score", hitDoc.get("score"));
                    reviews.add(json);
                    i++;
                }
                FileWriter file = new FileWriter("files/output/review_date.json");
                file.write(reviews.toJSONString());
                file.flush();
                file.close();
                indexReader.close();
            }

        } catch (ParseException | IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
        
    }
    
}
