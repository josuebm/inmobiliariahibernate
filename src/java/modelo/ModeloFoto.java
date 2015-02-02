/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import hibernate.HibernateUtil;
import hibernate.Foto;
import hibernate.Inmueble;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Josué
 */
public class ModeloFoto {

    public static List<Foto> get(int idInmueble) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from Foto where idinmueble =" + idInmueble;
        Query q = session.createQuery(hql);
        List<Foto> fotos = q.list();
        //List<Foto> fotos = (List<Foto>) q.list();
        //for (Foto f : fotos)
        //    Hibernate.initialize(f);
        session.getTransaction().commit();
        session.close();
        return fotos;
    }

    public static Foto get(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //Forma 1
        /*String hql = "from Prueba where id = " +id;
         Query q = session.createQuery(hql);
         Prueba prueba = ((List<Prueba>)q.list()).get(0);*/
        //Forma 2
        Foto f = (Foto) session.get(Foto.class, Integer.parseInt(id));
        session.getTransaction().commit();
        session.close();
        return f;
    }

    public static void delete(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Foto f;
        f = (Foto) session.load(Foto.class, Integer.parseInt(id));
        session.delete(f);
        //Foto f = (Foto)session.get(Foto.class, Integer.parseInt(id));
        session.getTransaction().commit();
        session.flush(); // me garantiza que a partir de este punto la operación ya está realizada
        session.close();
    }

    public static void insert(Foto foto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(foto);

        session.getTransaction().commit();
        session.flush(); // me garantiza que a partir de este punto la operación ya está realizada
        session.close();
    }

    public static void update(Foto foto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.update(foto);

        session.getTransaction().commit();
        session.flush(); // me garantiza que a partir de este punto la operación ya está realizada
        session.close();
    }

}
