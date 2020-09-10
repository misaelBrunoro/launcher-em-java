package Helper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class TrayIconHelper {
    private TrayIcon trayIcon;

    public void GenerateIcon( ) {

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
            this.trayIcon = new TrayIcon(this.getIcon("icon"), "Launcher OnContabil", popup);
            this.trayIcon.setImageAutoSize(true);

            //Tratamento de erros
            try {
                tray.add(this.trayIcon);
            } catch (AWTException e) {
                System.err.println("Erro, TrayIcon não pode ser adicionado.");
            }
        } else {
            //Caso o item  System Tray não for suportado
            JOptionPane.showMessageDialog(null,"recurso ainda não esta disponível pra o seu sistema");
        }
    }

    public void setTooltip (String mensagem) {
        this.trayIcon.setToolTip(mensagem);
    }

    private Image getIcon(String iconName) {
        URL path = this.getClass().getClassLoader().getResource(iconName + ".png");
        return Toolkit.getDefaultToolkit().getImage(path);
    }

    public void setIcon(String iconName) {
        Image newImage = getIcon(iconName);
        this.trayIcon.setImage(newImage);
    }
}


