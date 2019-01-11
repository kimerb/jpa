package com.venta.test;

import java.util.Formatter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.venta.dto.Cliente;

public class AddTest2 {
	EntityManagerFactory enf;
	EntityManager en;
	@Before
	public void iniciar(){
		enf = Persistence.createEntityManagerFactory("venta");
		en = enf.createEntityManager();
	}
	@Test
	public void AddCliente() {
		en.getTransaction().begin();
		Cliente c1 = new Cliente("Elmer 1", "Blas 1", "Huaraz 1", "00000001");
		en.persist(c1);
		
		for (int i=2; i<=100; i++) {
			Cliente c2 = new Cliente("Elmer "+i, "Blas "+i, "Huaraz "+i, String.valueOf(new Formatter().format("%08d", i)));
			en.persist(c2);
		}
		en.getTransaction().commit();
	}
}