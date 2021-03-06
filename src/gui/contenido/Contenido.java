package gui.contenido;

import org.constrains.Constrains;
import org.constrains.View;
import org.constrains.Weight;

import acciones.Acciones;
import acciones.Eventos;
import gui.contenido.scroll.ModernScrollPane;
import tools.Fuentes;
import tools.Paneles;
import tools.Text;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;

public final class Contenido extends Lienzo {
    private JPanel contenido;
    private JLabel title;
    private ModernScrollPane pane;

    /**
     * Esquema de los paneles de contenido del proyecto
     */
    public Contenido() {
        super(new GridBagLayout(), true);
        setAcciones(new Acciones() {
            @Override
            public void iteracion0() {//None
            }

            @Override
            public void iteracion1() {//None
            }

            @Override
            public void clean() {
                Eventos.scroll(pane, 0);
            }
        });
        init();
    }

    /**
     * Instanciación y acomodamiento de los componentes del panel
     */
    private void init() {
        title = new JLabel();
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(Fuentes.UBUNTU_LIGHT_50.getFont());
        title.setOpaque(false);
        contenido = new JPanel(new GridBagLayout());
        contenido.setOpaque(false);
        pane = new ModernScrollPane(contenido);
        pane.setWheelScrollingEnabled(true);
        pane.getViewport().setView(contenido);
        pane.setOpaque(false);
        pane.getViewport().setOpaque(false);
        pane.setBorder(BorderFactory.createEmptyBorder());
        pane.getVerticalScrollBar().setUnitIncrement(50);
        pane.setFocusable(true);
        back(Text.VENTANA_PRINCIPAL.toString(), Paneles.PRINCIPAL);
        Weight weight00 = new Weight(0, 0);
        Constrains.addComp(
                new View(title, this),
                new Rectangle(0, 0, 3, 1),
                new Weight(1, 1),
                new Insets(15, 15, 8, 10),
                new Point(GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL)
        );
        Constrains.addComp(
                new View(pane, this),
                new Rectangle(0, 1, 3, 1),
                new Weight(1, 25),
                new Insets(5, 20, 10, 15),
                new Point(GridBagConstraints.NORTH, GridBagConstraints.BOTH)
        );
        Constrains.addComp(
                new View(getBack(), this),
                new Rectangle(0, 2, 1, 1),
                weight00,
                new Insets(10, 7, 10, 2),
                new Point(GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE)
        );
        Constrains.addComp(
                new View(getNext(), this),
                new Rectangle(2, 2, 1, 1),
                weight00,
                new Insets(10, 2, 10, 7),
                new Point(GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE)
        );
    }

    /**
     * Descripción del contenido de la temática
     * @return contenido {@link JPanel}
     */
    public JPanel getContenido() {
        return contenido;
    }

    /**
     * Título del contenido
     * @return title {@link JLabel}
     */
    public JLabel getTitle() {
        return title;
    }
}