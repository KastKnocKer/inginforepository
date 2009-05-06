    import java.awt.BorderLayout;
    import java.awt.GridLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.io.File;
    import java.io.FileFilter;
    import java.util.StringTokenizer;

    import javax.swing.JButton;
    import javax.swing.JDialog;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JPanel;
    import javax.swing.JScrollPane;
    import javax.swing.JTree;
    import javax.swing.ScrollPaneConstants;
    import javax.swing.border.TitledBorder;
    import javax.swing.event.TreeSelectionEvent;
    import javax.swing.event.TreeSelectionListener;
    import javax.swing.tree.DefaultMutableTreeNode;
    import javax.swing.tree.DefaultTreeModel;
    import javax.swing.tree.TreeSelectionModel;

    public class FinestraCercaFile extends JDialog
    {
      // bottone di conferma
      private JButton bottoneOk = null;

      // albero
      private JTree albero = null;
      
      // modello dell'albero
      private DefaultTreeModel modelloAlbero = null;

      // radice dell'albero
      private DefaultMutableTreeNode nodoRadiceAlbero = null;

      // percorso del file selezionato
      private String percorsoSelezionato = null;

      public FinestraCercaFile()
      {
        // titolo finestra
        this.setTitle("Selezionatore di file --- by Cocco Alessandro"); 
        
        // imposto la finestra come modale
        this.setModal(true); 
        
        // finestra non ridimensionabile
        this.setResizable(false);

        // chiudi finestra e libera le risorse quando si clicca sulla x
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // gestore di layout
        this.getContentPane().setLayout(new BorderLayout());

        // compongo l'interfaccia della finestra
        this.getContentPane().add(preparaPannelloAlbero(), BorderLayout.CENTER);
        this.getContentPane().add(preparaPannelloBottoni(), BorderLayout.SOUTH);
        
        // compatto la finestra
        this.pack(); 
        
        // centro la finestra rispetto allo schermo
        this.setLocationRelativeTo(null); 
       
        // rendo visibile la finestra
        this.setVisible(true);
      }

      private JScrollPane preparaPannelloAlbero()
      { 
        // creo la radice
        this.nodoRadiceAlbero = new DefaultMutableTreeNode(""); 
        
        // istanzio l'albero
        this.albero = new JTree(this.nodoRadiceAlbero);
        
        // ottengo il modello
        this.modelloAlbero = (DefaultTreeModel)this.albero.getModel();
        
        // impostazioni varie
        this.albero.setEditable(false);
        this.albero.setShowsRootHandles(true);
        this.albero.setRootVisible(false);
        this.albero.setVisibleRowCount(25);

        // ascoltatore
        this.albero.addTreeSelectionListener(new TreeSelectionListener()
        {
          public void valueChanged(TreeSelectionEvent e)
          {
            // ottengo il nome del nodo selezionato
            String nomeNodoSelezionato = leggiNomeNodoSelezionato();

            // se vale null non ci sono nodi selezionati
            if (nomeNodoSelezionato == null)
            {
              // disattivo il bottone di conferma
              bottoneOk.setEnabled(false);
            }
            else 
            {
              // ottengo il percorso del nodo selezionato
              String percorsoDaSpezzettare = albero.getSelectionPath().toString();
              
              // il percorso del nodo selezionato e' nel formato 
             // [radice, sottonodo1, sottonodo2, ....,  sottonodoN]
              // da questa stringa voglio un percorso del tipo
             // radice/sottonodo1/sottonodo2/.../sottonodoN 
              // tolgo le parentesi dall'inizio e dalla fine
              percorsoDaSpezzettare =
              percorsoDaSpezzettare.substring(1, percorsoDaSpezzettare.length() - 1);

              // spezzetto la stringa (con la virgola come delimitatore
              StringTokenizer spezzettatore = new StringTokenizer(percorsoDaSpezzettare, ",", false);

              // cancello l'eventuale percorso precedente
              percorsoSelezionato = new String();

              // ricompongo il percorso
              while (spezzettatore.hasMoreTokens())
              {
                percorsoSelezionato += spezzettatore.nextToken().trim() + "/";
              }
              
              // tolgo la barra finale
              percorsoSelezionato =
              percorsoSelezionato.substring(0, percorsoSelezionato.length() - 1);

              // abilito il bottone di selezione
              bottoneOk.setEnabled(true);
            }
          }
        });

        // popolo l'albero a partire dalla cartella corrente
        // le sotto-cartelle vengono caricate per ricorsione
        caricaAlbero(new File("./").listFiles(), nodoRadiceAlbero);
        
        // ricarico il contenuto dell'albero
        modelloAlbero.reload(); 

        // pannello scorrevole per l'albero
        JScrollPane pannelloScorrevole = new JScrollPane(this.albero,
ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        return pannelloScorrevole;
      }

      private JPanel preparaPannelloBottoni()
      {
        JPanel pannelloSotto = new JPanel(new GridLayout(1, 4));
        pannelloSotto.setBorder(new TitledBorder(""));

        // creo i bottoni
        bottoneOk = new JButton("Ok");
        JButton bottoneAnnulla = new JButton("Annulla");

        bottoneOk.setEnabled(false);

        // ascolatore bottoneOk
        bottoneOk.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e) 
          {
            dispose();
            setVisible(false);
          }
        });

        // ascolatore bottoneAnnulla
        bottoneAnnulla.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            percorsoSelezionato = null;

            dispose();
            setVisible(false);
          }
        });

        // le etichette vuote servono per l'impaginazione 
        // (in questo modo posso allineare i bottoni in basso a destra senza altri pannelli)
        pannelloSotto.add(new JLabel(""));
        pannelloSotto.add(new JLabel(""));

        pannelloSotto.add(bottoneOk);

        pannelloSotto.add(bottoneAnnulla);

        return pannelloSotto;
      }

     private void caricaAlbero(File[] files, DefaultMutableTreeNode nodoGenitore)
      {
        // scorro i file della cartella corrente
        for (int i = 0; i < files.length; i++) 
        {
          // se il file in realta' e' una cartella creo un nuovo nodo e avvio la ricorsione
          if (files[i].isDirectory())
          {
            // creo il nuovo nodo
            DefaultMutableTreeNode nuovoNodo = new DefaultMutableTreeNode(files[i].getName());

            // lo aggiungo all'albero
            modelloAlbero.insertNodeInto(nuovoNodo, nodoGenitore, nodoGenitore.getChildCount());

            // chiamo la funzione con i file contenuti nella cartella corrente
            caricaAlbero(files[i].listFiles(), nuovoNodo);
          }
          else // il file e' effettivamente un file
          {
            // aggiungo il nome del file all'albero
            modelloAlbero.insertNodeInto(new DefaultMutableTreeNode(files[i].getName()), nodoGenitore,
            nodoGenitore.getChildCount());
          }
        }
      }

      public DefaultMutableTreeNode leggiNodoSelezionato()
      {
        try
        {
          // restituisco il nodo selezionato
          return (DefaultMutableTreeNode) (albero.getSelectionPath().getLastPathComponent());
        }
        catch (NullPointerException ex)
        {
          // se non si sta selezionando un nodo, restituisce null
          return null;
        }
      }

      public String leggiNomeNodoSelezionato()
      {
        try
        {
          // restituisco il nome del nodo selezionato
          return leggiNodoSelezionato().toString();
        }
        catch (NullPointerException ex)
        {
          // se non si sta selezionando un nodo, restituisce null
          return null;
        }
      }

      public String percorsoSelezionato()
      {
        return percorsoSelezionato;
      }

      public static void main(String[] args)
      {
        String percorsoSelezionato = new FinestraCercaFile().percorsoSelezionato();
        
        System.out.println("percorso selezionato: " + percorsoSelezionato);
      }
    }
