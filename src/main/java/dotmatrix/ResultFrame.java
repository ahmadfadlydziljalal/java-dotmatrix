/*
 *  Created By Dzil | Papa Raya
 */
package dotmatrix;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map;
import javax.swing.JFrame;
import simple.escp.Template;
import simple.escp.data.DataSource;
import simple.escp.swing.PrintPreviewPane;

/**
 *
 * @author dzil
 */
public class ResultFrame extends JFrame {

    Template template;
    DataSource dataSource;
     Map map;


    public Template getTemplate() {
        return template;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public ResultFrame(Template template, DataSource dataSource) {
        
        super();
        this.template = template ;
        this.dataSource = dataSource;
    }
    
    public ResultFrame(Template template, Map map) {
        
        super();
        this.template = template ;
        this.map = map;
    }
    
     
    public void display(){
        
        // Hardcode debug
        PrintPreviewPane printPreviewPane = new PrintPreviewPane(); // Error: Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
        printPreviewPane.display(template, dataSource);

       
        //PrintPreviewPane printPreviewPane = new PrintPreviewPane(this.template, this.map, null);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(printPreviewPane, BorderLayout.CENTER);          

        setPreferredSize(new Dimension(500, 500));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
    }

}
