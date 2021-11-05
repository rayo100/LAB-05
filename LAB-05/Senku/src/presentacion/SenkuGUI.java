


public class SenkuGUI extends JFrame{
	
	private SenkuGUI(){
		super("Senku");
		Senku senku = new Senku();	/*MODELO*/
		prepareElementos();			/*VISTA*/
		prepareAcciones();			/*CONTROLADOR*/
		
		inicie();
	}
	
	public static void main(String[] args){
		SenkuGUI gui= new SenkuGUI();
		gui.setVisible(true);
	}
	
	private void prepareElementos(){
		Dimension screenSize = Toolkit.getDfaultToolkit().getSreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		width = width / 2;
		height = height / 2;
		
		frame.pack();
		frame.setLocationRelativeTo(null);
	
		this.setPreferredSize(new Dimension(width, height));
		
	}
	
}
