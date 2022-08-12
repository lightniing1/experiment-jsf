/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stefanini.jsf.stefanfood.dao;

import com.stefanini.jsf.stefafood.util.HibernateUtil;
import com.stefanini.jsf.stefanfood.model.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Servidor
 */
public class FornecedorDAO {
    
    public static void criaFornecedor (Fornecedor fornercedor) throws Exception { 
        
        Transaction transaction = null;
        Session session = HibernateUtil.getSession();
        
        try {
            transaction = session.beginTransaction();
            session.save(fornercedor);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception ("Erro ao criar Fornecedor");
        } finally {
            session.flush();
            session.close();
        }
    }
    
    public Fornecedor getFornecedor (String cnpj) {
        
        Fornecedor fornecedor = null;
        Session session = HibernateUtil.getSession();
        try {
             fornecedor = (Fornecedor) session.get(Fornecedor.class, cnpj);
        } finally {
            session.flush();
            session.close();
        }
        
        return fornecedor;
    }
    
    public static List<Fornecedor> getAll() {
        
        List<Fornecedor> fornecedores = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        
        try {
             fornecedores = session.createQuery("FROM Fornecedor").list();
        } finally {
            session.flush();
            session.close();
        }
        return fornecedores;
    }
    
    public static void deleteById(Fornecedor fornecedor) throws Exception {
        
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            //Fornecedor fornecedor = (Fornecedor) session.load(Fornecedor.class, fornecedor.getCnpj());
            session.delete(fornecedor);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception ("Erro ao excluir Fornecedor");
        } finally {
            session.flush();
            session.close();
        }   
    }
    
    public static void updateFornecedor(Fornecedor fornecedor) throws Exception {
        
        Transaction transaction = null;
        Session session = HibernateUtil.getSession();
        
        try {
            transaction = session.beginTransaction();
            session.update(fornecedor);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception ("Erro ao autalizar Fornecedor");
        } finally {
            session.flush();
            session.close();
        }   
        
    }
    
}
