package Helper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class TrayIconHelper {
    private TrayIcon trayIcon;

    // GERA O ICONE QUE FICA NA BANDEJA
    public void GenerateIcon( ) throws AWTException {

        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();

            // Criamos um ActionListener para a ação de encerramento do programa.
            ActionListener exitListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            };

            //Criando um objeto PopupMenu
            PopupMenu popup = new PopupMenu("Menu de Opções");
            MenuItem defaultItem = new MenuItem("Sair");
            //na linha a seguir associamos os objetos aos eventos
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);
            popup.addSeparator();

            //criando um objeto do tipo TrayIcon
            this.trayIcon = new TrayIcon(this.getIcon("azul"), "Launcher OnContabil", popup);
            this.trayIcon.setImageAutoSize(true);

            //Tratamento de erros
            tray.add(this.trayIcon);
        } else {
            //Caso o item  System Tray não for suportado
            JOptionPane.showMessageDialog(null,"recurso ainda não esta disponível pra o seu sistema");
        }
    }

    // SETA MENSAGEM A SER MOSTRADA AO PASSAR MOUSE
    public void setTooltip (String mensagem) {
        this.trayIcon.setToolTip(mensagem);
    }

    private Image getIcon(String iconName) {
        URL path = this.getClass().getClassLoader().getResource(iconName + ".png");
        return Toolkit.getDefaultToolkit().getImage(path);
    }

    // SETA IMAGEM DO ICONE
    public void setIcon(String iconName) {
        Image newImage = getIcon(iconName);
        this.trayIcon.setImage(newImage);
    }
}


