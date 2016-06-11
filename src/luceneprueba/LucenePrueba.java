/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luceneprueba;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.Scanner;
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
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;

import luceneprueba.utils.Menu;

public class LucenePrueba {

    public static void main(String[] args) throws IOException {
        try{
            int opcion;
            Index index = new Index("files/", "indice_invertido");                        
            do{
                Menu.printMainMenu();
                Scanner in = new Scanner(System.in);
                opcion = in.nextInt();
                
                switch(opcion){
                    case 1:
                        index.create();
                        System.out.println("Índice creado con éxito");
                        break;
                    case 2:
                        Reader reader = new Reader(index.getDirectory(), index.getAnalyzer());
                        System.out.print("Ingrese la palabra a buscar: ");
                        Scanner query = new Scanner(System.in);
                        String queryReview = query.nextLine();
                        System.out.println("Ingrese la fecha: ");
                        String queryDate = query.nextLine();
                        reader.searchOnIndex(queryReview, queryDate);
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Opción inválida");
                        break;
                }
            } while(opcion != 3);
            
            index.getDirectory().close();
            
        }
        catch(Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }        
    }
    
}
