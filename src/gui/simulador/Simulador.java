package gui.simulador;
import eventos.Eventos;
import gui.contenido.ButtonSimulador;
import gui.contenido.Lienzo;
import gui.contenido.Switch;
import gui.contenido.Texto;
import gui.contenido.Tree;
import gui.contenido.scroll.ModernScrollPane;
import gui.editor.Editor;
import tools.Colour;
import tools.Constrains;
import tools.Fuentes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public final class Simulador extends Lienzo {
    private Integer iteracion;
    private Component component;
    private JPanel datos;
    private JPanel panel;
    private JTabbedPane codigos;
    private ButtonSimulador clean;
    private ButtonSimulador nextIteracion;
    private ButtonSimulador send;
    private Switch codigo;
    private Switch pause;
    private Texto texto;
    public static final Dimension canvasSize;
    static {
        canvasSize = new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.73),(int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.8));
    }
    /**
     * Esquema de los simuladores del proyecto
     */
    public Simulador(){
        this(new JPanel(new GridBagLayout()));
        component.setPreferredSize(canvasSize);
        component.setSize(canvasSize);
        component.setMinimumSize(canvasSize);
    }
    /**
     * Esquema de los simuladores del proyecto
     * @param component {@link Component} a mostrar el objeto simulado
     */
    public Simulador(Component component){
        super(new GridBagLayout(),false);
        this.component = component;
        iteracion = 0;
        init();
    }
    /**
     * Instanciacion y acomodamiento de los componentes del panel
     */
    private void init() {
        codigo=new Switch("Visualización del Código",false);
        pause=new Switch("Paso a Paso",false);
        send=new ButtonSimulador("Enviar",true,e -> {
            if (pause.isOnOff()) getAcciones().iteracion1();
            else getAcciones().iteracion0();
        });
        clean=new ButtonSimulador("Limpiar",false,e -> getAcciones().clean());
        nextIteracion=new ButtonSimulador("Siguiente",false, e -> getAcciones().iteracion1());
        panel=new JPanel(new GridBagLayout());
        panel.setBackground(Colour.GRISPANEL.getColor());
        JLabel desc=new JLabel("Descripción");
        desc.setFont(Fuentes.UBUNTULIGHT14.getFont());
        desc.setForeground(Colour.BLANCO.getColor());
        desc.setBackground(Colour.AZULTITLE.getColor());
        desc.setOpaque(true);
        codigo.setModificable(false);
        pause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                codigo.setModificable(pause.isOnOff());
                if (!pause.isOnOff() && codigo.isOnOff()) codigo.setOnOff(pause.isOnOff());
            }
        });
        Insets insets=new Insets(0,0,0,0);
        Constrains.addComp(component,this,new Rectangle(0,0,1,4),0,0,insets,GridBagConstraints.NORTHWEST,GridBagConstraints.NONE);
        Constrains.addComp(getBack(),panel,new Rectangle(0,0,1,2),0,0,new Insets(15,15,15,5),GridBagConstraints.WEST,GridBagConstraints.NONE);
        Constrains.addComp(getHome(),panel,new Rectangle(1,0,1,2),0,0,new Insets(20,5,20,15),GridBagConstraints.WEST,GridBagConstraints.NONE);
        Constrains.addComp(panel,this,new Rectangle(0,4,1,1),1,1,insets,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
        Constrains.addCompX(desc,this,new Rectangle(1,0,1,1),1,insets,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL);
        Constrains.addCompIy(texto=new Texto(4,55),this,new Rectangle(1,1,1,1),1,0,insets,35,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
        Constrains.addCompIy(new ModernScrollPane(datos=new JPanel(new GridBagLayout())),this,new Rectangle(1,2,1,1),1,0,insets,200, GridBagConstraints.CENTER,GridBagConstraints.BOTH);
        Constrains.addComp(codigos=new JTabbedPane(JTabbedPane.TOP),this,new Rectangle(1,3,1,2),1,1,insets,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
    }
    /**
     * Plantilla por defecto para reiniciar el simulador
     */
    public void clean(){
        Eventos.enable(true,send,pause,getBack(),getHome());
        Eventos.enable(false,nextIteracion,clean,codigo);
        pause.setOnOff(false);
        codigo.setOnOff(false);
        setIteracion(0);
        Eventos.scroll((Editor)codigos.getComponentAt(0),0);
        ((Editor)codigos.getComponentAt(0)).setLine(false);
    }
    /**
     * Plantilla por defecto de acomodamiento para el panel de control
     * @param datos {@link Component} de control de datos ingresados por el usuario
     */
    public void acomodamientoPanelControl(Component datos){
        Constrains.addCompX(datos,panel,new Rectangle(2,0,1,1),1,new Insets(10,80,5,5), GridBagConstraints.EAST,GridBagConstraints.BOTH);
        Constrains.addCompX(send,panel,new Rectangle(3,0,1,1),1,new Insets(10,5,5,5),GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(pause,panel,new Rectangle(4,0,1,1),1,new Insets(10,5,5,100),GridBagConstraints.WEST,GridBagConstraints.NONE);
        Constrains.addCompX(codigo,panel,new Rectangle(4,1,1,1),1,new Insets(5,8,10,100),GridBagConstraints.WEST,GridBagConstraints.NONE);
        Constrains.addCompX(nextIteracion,panel,new Rectangle(3,1,1,1),1,new Insets(5,5,10,5),GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(clean,panel,new Rectangle(2,1,1,1),1,new Insets(5,80,10,5),GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL);
    }
    /**
     * Acomodamiento por defecto cuando se utiliza productos
     * @param number número resultado
     * @param producto secuencia de producto
     */
    public void acomodamientoProducto(JLabel number,JLabel producto){
        Constrains.addCompX(number, (Container) component,new Rectangle(0,0,1,1),1,new Insets(40,50,50,50), GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL);
        Constrains.addCompX(producto,(Container) component,new Rectangle(0,1,1,1),1,new Insets(40,30,50,30),GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL);
    }
    /**
     * Añade los codigos que necesita el simulador
     * @param editor {@link Editor} con el código correspondiente
     * @param title nombre del código a añadir
     */
    public void addCodes(Editor editor,String title){
        codigos.add(title, editor);
    }
    /**
     * Fijar un texto determinado en el área de notificaciones
     * @param texto texto a fijar determinadamente
     */
    public void setTexto(String texto){
        this.texto.setEditable(true);
        this.texto.setText(texto);
        this.texto.setEditable(false);
    }
    /**
     * Variables que se van a agregar
     * @param trees {@link Tree} a agregar
     */
    public void setDatos(Tree... trees){
        for (int i = 0; i < trees.length; i++) Constrains.addComp(trees[i],datos,new Rectangle(0,i,1,1),1,1,new Insets(i==0 ? 7:1,10,i==trees.length-1 ? sizeTree(trees.length) : 1,5),GridBagConstraints.CENTER,GridBagConstraints.BOTH);
    }
    /**
     * Da el tamaño del espacio libre en el panel datos
     * @param length {@link Tree} en el panel
     * @return espacio libre en el panel
     */
    private int sizeTree(int length){
        int size=200-(10*length);
        return size > 0 ? size : 8;
    }
    /**
     * Componente de área de Notificaciones
     * @return área de notificaciones
     */
    public Texto getTexto() {
        return texto;
    }
    /**
     * Panel de control de comandos del simulador
     * @return panel de control
     */
    public JPanel getPanel() {
        return panel;
    }
    /**
     * Obtiene el área gráfica del simulador
     * @return área gráfica del simulador
     */
    public Component getComponent() {
        return component;
    }
    /**
     * Obtiene el contenedor de los códigos del simulador
     * @return Contenedor de los códigos del simulador
     */
    public JTabbedPane getCodigos() {
        return codigos;
    }
    /**
     * Obtiene la cantidad pulsaciones del paso a paso del simulador
     * @return cantidad de pulsaciones
     */
    public int getIteracion() {
        return iteracion;
    }
    /**
     * Fija un nuevo valor a la iteración de pulsaciones del simulador
     * @param iteracion nuevo valor de iteración
     */
    public void setIteracion(Integer iteracion) {
        this.iteracion = iteracion;
    }
    /**
     * Incrementa a paso uno las pulsaciones del paso a paso del simulador en la ejecución
     */
    public void incrementIteracion(){
        iteracion++;
    }
    /**
     * Decrementa a paso uno las pulsaciones del paso a paso del simulador en la ejecución
     */
    public void decrementIteracion(){
        iteracion--;
    }
    /**
     * Obtiene el componente de paso a paso del simulador
     * @return elección del paso a paso del simulador
     */
    public Switch getPause() {
        return pause;
    }
    /**
     * Obtiene el componente de visualización del código en el paso a paso en el simulador
     * @return visualización del paso a paso
     */
    public Switch getCodigo() {
        return codigo;
    }
    /**
     * Boton de envio de datos al simulador
     * @return envio de datos
     */
    public ButtonSimulador getSend() {
        return send;
    }
    /**
     * Boton de reinicio del simulador
     * @return reinicio del simulador
     */
    public ButtonSimulador getClean() {
        return clean;
    }
    /**
     * Boton de paso a paso del simulador
     * @return boton del paso a paso
     */
    public ButtonSimulador getNextIteracion() {
        return nextIteracion;
    }
}