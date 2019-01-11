package com.venta.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.venta.dto.Categoria;
import com.venta.dto.Cliente;
import com.venta.dto.Producto;

public class DeleteTest {
	EntityManagerFactory enf;
	EntityManager en;
	@Before
	public void iniciar(){
		enf = Persistence.createEntityManagerFactory("venta");
		en = enf.createEntityManager();
	}
	@Test
	public void DeleteCliente() {
		Categoria c = en.find(Categoria.class, 2);
		en.getTransaction().begin();
		en.remove(c);
		
		// ********************
		
		Producto p1 = en.find(Producto.class, 4);
		Producto p2 = en.find(Producto.class, 3);
		en.remove(p1);
		en.remove(p2);
		en.getTransaction().commit();
	}
}
