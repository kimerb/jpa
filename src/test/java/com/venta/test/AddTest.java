package com.venta.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;
import com.venta.dto.Categoria;
import com.venta.dto.Cliente;
import com.venta.dto.Detalle;
import com.venta.dto.Factura;
import com.venta.dto.Producto;

public class AddTest {
	EntityManagerFactory enf;
	EntityManager en;
	@Before
	public void iniciar(){
		enf = Persistence.createEntityManagerFactory("venta");
		en = enf.createEntityManager();
	}
	@Test
	public void AddCategoria() throws ParseException {
		int n = 25;   // n>5
		
		/************* CATEGORIA *************/
		en.getTransaction().begin();		
		for (int i=1; i<=n; i++) {
			Categoria c = new Categoria(i, "Bebiba " + i);
			en.persist(c);
		}
		en.getTransaction().commit();
		
		/************* PRODUCTO *************/
		en.getTransaction().begin();
		for (int i=1; i<=n; i++) {
			Categoria categoria_id = en.find(Categoria.class, (int)(Math.random()*n)+1);
			int stock = (int)(Math.random()*200)+1;
			Producto p = new Producto("Producto " + i, stock, categoria_id);
			en.persist(p);
		}
		en.getTransaction().commit();
		
		/************* CLIENTE *************/
		en.getTransaction().begin();
		for (int i=1; i<=n; i++) {
			Cliente c = new Cliente("Elmer "+i, "Blas "+i, "Huaraz "+i, String.valueOf(new Formatter().format("%08d", i)));
			en.persist(c);
		}
		en.getTransaction().commit();
		
		/************* FACTURA - DETALLE *************/
		en.getTransaction().begin();
		for (int i=1; i<=n; i++) {
			//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			//Date fecha = df.parse("2019-01-12");
			Date fecha = new Date();
			Cliente cliente_id = en.find(Cliente.class, (int)(Math.random()*n)+1);
			Factura c = new Factura(i, i, fecha, cliente_id);
			en.persist(c);
			// DETALLE
			int nd = i>20? Math.round(i/4) : 5;
			for (int J=1; J<=Math.random()*nd+1; J++) {
				Producto producto_id = en.find(Producto.class, (int)(Math.random()*n)+1);
				Factura factura_id = en.find(Factura.class, i);
				Detalle d = new Detalle(12, 10, producto_id, factura_id);
				en.persist(d);
			}
		}
		en.getTransaction().commit();
	}
}
