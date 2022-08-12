/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stefanini.jsf.stefanfood.bean;

import com.stefanini.jsf.stefanfood.dao.FornecedorDAO;
import com.stefanini.jsf.stefanfood.model.Fornecedor;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 *
 * @author Servidor
 */

@Named("fornecedorBean")
@RequestScoped
public class FornecedorBean implements Serializable {
    
    
    private Fornecedor fornecedor;
    
    @PostConstruct
    public void init() {
        fornecedor = new Fornecedor();
    }
    
        /**
     * @return the fornecedor
     */
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    public void salvaFornecedor() {
        try {
            FornecedorDAO.criaFornecedor(fornecedor);
        } catch (Exception ex) {
            Logger.getLogger(FornecedorBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro");
        } //finally {
            //this.fornecedor = new Fornecedor(); //Limpa o objeto. Talvez um build aqui?
        //}
    }
    
    public List<Fornecedor> exibeTodosFornecedores() {
        return FornecedorDAO.getAll();
    }
    
    public void exibeFornecedor() {
        System.out.println("Nome "+ fornecedor.getNome() + "CNPJ: "+ fornecedor.getCnpj());
    }
    
}
