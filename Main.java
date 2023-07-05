import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {
    private static Grille grille;
    private static AutomateCellulaire automate;
    private static int etape;

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java Main <typeautomate> <fichier>");
            return;
        }

        int typeAutomate = Integer.parseInt(args[0]);
        String fichier = args[1];

        grille = Grille.creerGrilleDepuisFichier(fichier);

        switch (typeAutomate) {
            case 0:
                automate = new AutomateUnidimensionnel(grille);
                break;
            case 1:
                automate = new JeuDeLaVie(grille);
                break;
            case 2:
                int positionX = grille.getTailleX() / 2;
                int positionY = grille.getTailleY() / 2;
                Direction direction = Direction.HAUT;
                automate = new Fourmi(grille, positionX, positionY, direction);
                break;
            default:
                System.out.println("Type d'automate invalide.");
                return;
        }

        etape = 0;

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Automate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(grille.getTailleX(), grille.getTailleY()));
        mainPanel.add(gridPanel, BorderLayout.CENTER);

        JButton button = new JButton("Etape suivante");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                automate.simulerGeneration();
                etape++;
                System.out.println("Etape " + etape + ":");
                grille.afficherGrille();
                updateGridPanel(gridPanel);
            }
        });
        mainPanel.add(button, BorderLayout.SOUTH);

        updateGridPanel(gridPanel);

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);

        System.out.println("Grille initiale:");
        grille.afficherGrille();
    }

    private static void updateGridPanel(JPanel gridPanel) {
        gridPanel.removeAll();

        Cellule[][] cellules = grille.getCellules();
        for (int i = 0; i < grille.getTailleX(); i++) {
            for (int j = 0; j < grille.getTailleY(); j++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setPreferredSize(new Dimension(20, 20));

                if (cellules[i][j].getEtat()) {
                    cellPanel.setBackground(Color.BLACK);
                } else {
                    cellPanel.setBackground(Color.WHITE);
                }
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                gridPanel.add(cellPanel);
            }
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }
}
