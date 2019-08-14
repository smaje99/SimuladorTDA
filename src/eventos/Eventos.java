package eventos;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import gui.contenido.About;
import gui.contenido.Save;
import gui.contenido.Switch;
import gui.contenido.Tree;
import gui.contenido.scroll.ModernScrollPane;
import gui.principal.Contenedor;
import tools.Paneles;
public abstract class Eventos {
    /**
     * Variable estatica que contiene todos los paneles agregados al proyecto
     */
    private static JPanel panel;
    /**
     *Establece el panel principal que contiene todos los paneles de proyecto
     * @param panel {@link JPanel}
     */
    public static void setPanel(JPanel panel){
        Eventos.panel=panel;
    }
    /**
     * Mediante el nombre de los paneles definidos mediante un enum de String muestra el panel deseado en la intefaz gráfica
     * @see Paneles
     * @param panel {@link String}
     */
    public static void show(Paneles panel){
        Contenedor.panelActivo = panel;
        ((CardLayout)Eventos.panel.getLayout()).show(Eventos.panel,panel.toString());
    }
    /**
     * Abre el navegador con el link fijado
     * @param url {@link String} url a abrir por el usuario
     */
    public static void link(String url){
        try{
            if (Desktop.isDesktopSupported()) Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (Exception e) {//None
        }
    }
    /**
     * About de Seros,<br>
     * se ejecuta una única vez por ejecución
     * @param component {@link Component} en el cual se va a ejecutar el about
     * @see About
     */
    public static void about(Component component){
        if (About.show) {
            About.show=false;
            new About(component).setVisible(true);
        }
    }
    /**
     * Modifica el disponibilidad de los componentes dados
     * @param enable disponibilidad a dar a los componentes
     * @param components componentes a modificar disponibilidad
     */
    public static void enable(boolean enable,Component... components) {
        for (Component component : components) if (component instanceof Switch) ((Switch) component).setModificable(enable);
        else component.setEnabled(enable);
    }
    /**
     * Valor a la posición del scroll dado
     * @param pane scroll a posicionar
     * @param value posición a dar al scroll
     * @see ModernScrollPane
     */
    public static void scroll(ModernScrollPane pane, int value){
        pane.getVerticalScrollBar().setValue(value);
    }
    /**
     * Valor a dar a una variable de un determinado nodo del árbol
     * @param tree árbol a trabajar
     * @param index posición de la variable
     * @param valor valor a dar a la variable en el árbol
     * @see Tree
     */
    public static void variable(Tree tree,int index,Object valor){
        tree.getDato(index).setValor(valor.toString());
        tree.updateUI();
    }
    /**
     * Duerme el la ejecución lineal del proceso por los milisegundo establecidos
     * @param mili milisegundos a establecer
     */
    public static void sleep(long mili){
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {//None
        }
    }
    /**
     * Convierte una cadena de texto a HTML
     * @param n cadena a convertir
     * @return HTML de la cadena dada
     */
    public static String html(String n){
        return "<html>"+n+"</html>";
    }
    /**
     * Opciones {@link JPopupMenu} para los componentes.</br>
     * Acciona el panel seleccionado
     * @param paneles paneles a navegar
     * @return opciones de componente
     */
    public static JPopupMenu menu(Paneles... paneles){
        JPopupMenu menu=new JPopupMenu();
        boolean simulador=true;
        boolean demostracion=true;
        boolean ejercicios=true;
        for (Paneles panel:paneles) {
            if (panel.toString().contains("Simulador") || panel.toString().contains("Demostración") || panel.toString().contains("Ejercicios")){
                if (panel.toString().contains("Simulador")) {
                    if (simulador) menu.addSeparator();
                    simulador=false;
                }
                else if (panel.toString().contains("Ejercicios")) {
                    if (ejercicios) menu.addSeparator();
                    ejercicios=false;
                }
                else if (panel.toString().contains("Demostración")) {
                    if (demostracion) menu.addSeparator();
                    demostracion=false;
                }
            }
            JMenuItem item=new JMenuItem(panel.toString());
            item.addActionListener(e -> {
                try {
                    if (!Contenedor.panelActivo.equals(Paneles.PRINCIPAL)) Contenedor.paneles.get(Contenedor.panelActivo).getAcciones().clean();
                } catch (NullPointerException en){//None
                } finally {
                    Eventos.show(panel);
                }
            });
            menu.add(item);
            if (panel.toString().equals(Paneles.PRINCIPAL.toString())) menu.addSeparator();
        }
        return menu;
    }
    /**
     * Obtiene la ruta indicada a guardar el archivo que se va a exportar
     * @return ruta indicada de destino
     */
    public static File saveFile(boolean isImage) throws FileNotFoundException {
        return new Save(isImage).getFile();
    }
    /**
     * Crea una imagen a partir del componente inidicado
     * @param component componente a convetir en imagen
     * @return imagen creada a partir del componente dado
     */
    public static RenderedImage createImageOf(Component component){
        BufferedImage image=new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        component.paint(image.getGraphics());
        return image;
    }
}