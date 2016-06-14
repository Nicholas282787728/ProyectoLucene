/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luceneprueba.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author JAno
 */
public class Review {
    
    private int fecha;
    private double score;
    private List<String> topicos;
    private double [] clasificador1;
    private double [] clasificador2;
    
    public Review(int fecha, double score, List<String> topicos, double [] clasificador1, double [] clasificador2){
        this.fecha = fecha;
        this.score = score;
        this.topicos = topicos;
        this.clasificador1 = clasificador1;
        this.clasificador2 = clasificador2;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    public double[] getClasificador1() {
        return clasificador1;
    }

    public void setClasificador1(double[] clasificador1) {
        this.clasificador1 = clasificador1;
    }

    public double[] getClasificador2() {
        return clasificador2;
    }

    public void setClasificador2(double[] clasificador2) {
        this.clasificador2 = clasificador2;
    }
    
    public List<Review> getListByJson(JSONArray reviews){
        List<Review> reviewsList = new ArrayList();
        Iterator i = reviews.iterator();
        
        JSONObject json;
        int date;
        double puntaje;
        List<String> topics = new ArrayList();
        double [] clas1 = new double[3];
        double [] clas2 = new double[3];
        Iterator j;
        
        while(i.hasNext()){
            json = (JSONObject) i.next();
            date = (int) json.get("fecha");
            puntaje = (double) json.get("score");
            j = (Iterator) json.get("topicos");
            while(j.hasNext()){
                topics.add((String) j.next());
            }
            j = (Iterator) json.get("clasificador1");
            int count = 0;
            while(j.hasNext()){
                clas1[count] = (double) j.next();
                count++;
            }
            j = (Iterator) json.get("clasificador2");
            count = 0;
            while(j.hasNext()){
                clas2[count] = (double) j.next();
                count++;
            }
            reviewsList.add(new Review(date, puntaje, topics, clas1, clas2));
        }
        
        return reviewsList;
    }
    
    public List<Review> sortByDate(List<Review> reviews){
        
        Collections.sort(reviews, Review.ReviewDate);
        return reviews;
    }
    
    public static Comparator<Review> ReviewDate = (Review r1, Review r2) -> {
        int date1 = r1.getFecha();
        int date2 = r2.getFecha();
        
        /*For ascending order*/
        return date1-date2;
        
        /*For descending order*/
        //rollno2-rollno1;
    };
}
