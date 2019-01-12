@@ -25,7 +25,7 @@ public class AddTest {
	}
	@Test
	public void AddCategoria() throws ParseException {
		int n = 25;   // i>5
		int n = 25;   // n>5
		
		/************* CATEGORIA *************/
		en.getTransaction().begin();		
@ -56,8 +56,9 @@ public class AddTest {
		/************* FACTURA - DETALLE *************/
		en.getTransaction().begin();
		for (int i=1; i<=n; i++) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = df.parse("2019-01-09");
			//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			//Date fecha = df.parse("2019-01-12");
			Date fecha = new Date();
			Cliente cliente_id = en.find(Cliente.class, (int)(Math.random()*n)+1);
			Factura c = new Factura(i, i, fecha, cliente_id);
			en.persist(c);
