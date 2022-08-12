/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stefanini.jsf.stefanfood.controller;

import com.stefanini.jsf.stefanfood.dao.FornecedorDAO;
import com.stefanini.jsf.stefanfood.model.Fornecedor;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;


/**
 *
 * @author Servidor
 */

@Named("fornecedorBean")
@RequestScoped
public class FornecedorBean implements Serializable {
    
    private Fornecedor fornecedor;
    private List<Fornecedor> fornecedores;
   
    
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
    
    @PostConstruct
    public void init() {
        fornecedor = new Fornecedor();
        setFornecedores(FornecedorDAO.getAll());
    }
    
    public void salvaFornecedor() {
        try {
            FornecedorDAO.criaFornecedor(fornecedor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Criado com sucesso"));
        } catch (Exception ex) {
            Logger.getLogger(FornecedorBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro");
        } finally {
            this.setFornecedores(FornecedorDAO.getAll());
            //this.fornecedor = new Fornecedor(); //Limpa o objeto. Talvez um build aqui?
        }
    }
    
    public void excluirFornecedor(Fornecedor fornecedor) throws Exception {
        FornecedorDAO.deleteById(fornecedor);
        fornecedores.remove(fornecedor);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluido com sucesso"));
    }
    
    public void atualizarFornecedor(Fornecedor fornecedor) throws Exception {
        FornecedorDAO.updateFornecedor(fornecedor);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atualizado com sucesso"));
    }
    
    public void exibeFornecedor() {
        System.out.println("Nome "+ fornecedor.getNome() + "CNPJ: "+ fornecedor.getCnpj());
    }
    
}
