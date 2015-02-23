/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import hibernate.HibernateUtil;
import hibernate.Inmueble;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Josué
 */
public class ModeloInmueble {
    
  public static List<Inmueble> get() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from Inmueble";
        Query q = session.createQuery(hql);
        List<Inmueble> inmuebles = q.list();
        session.getTransaction().commit();
        session.close();
        return inmuebles;
    }
    
    public static Inmueble get(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //Forma 1
        /*String hql = "from Prueba where id = " +id;
        Query q = session.createQuery(hql);
        Prueba prueba = ((List<Prueba>)q.list()).get(0);*/
        //Forma 2
        Inmueble i = (Inmueble)session.get(Inmueble.class, Integer.parseInt(id));
        session.getTransaction().commit();
        session.close();
        return i;
    }
    
    public static void delete(Integer idAndroid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from Inmueble where idAndroid = " +idAndroid;
        Query q = session.createQuery(hql);
        Inmueble i = ((List<Inmueble>)q.list()).get(0);
        session.delete(i);
        session.getTransaction().commit();
        session.flush(); // me garantiza que a partir de este punto la operación ya está realizada
        session.close();
    }
    
    public static void delete(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Inmueble i = (Inmueble)session.load(Inmueble.class, Integer.parseInt(id));
        //Inmueble i = (Inmueble)session.get(Inmueble.class, Integer.parseInt(id));
        session.delete(i);
        session.getTransaction().commit();
        session.flush(); // me garantiza que a partir de este punto la operación ya está realizada
        session.close();
    }
    
    public static void insert(Inmueble inmueble) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(inmueble);
        
        session.getTransaction().commit();
        session.flush(); // me garantiza que a partir de este punto la operación ya está realizada
        
        session.close();
    }
    
    public static void update(Inmueble inmueble) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(inmueble);
        
        session.getTransaction().commit();
        session.flush(); // me garantiza que a partir de este punto la operación ya está realizada
        session.close();
    }
    
    
}