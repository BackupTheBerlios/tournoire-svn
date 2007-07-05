/*
 * TournoireAboutBox.java
 */

package tournoire;

import application.Action;
import application.Application;
import application.ResourceMap;

public class TournoireAboutBox extends javax.swing.JDialog {

    public TournoireAboutBox(java.awt.Frame parent) {
        super(parent);
        initComponents();
        getRootPane().setDefaultButton(closeButton);
    }

    @Action public void closeAboutBox() {
        setVisible(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        javax.swing.JLabel appTitleLabel = new javax.swing.JLabel();
        javax.swing.JLabel versionLabel = new javax.swing.JLabel();
        javax.swing.JLabel appVersionLabel = new javax.swing.JLabel();
        javax.swing.JLabel vendorLabel = new javax.swing.JLabel();
        javax.swing.JLabel appVendorLabel = new javax.swing.JLabel();
        javax.swing.JLabel homepageLabel = new javax.swing.JLabel();
        javax.swing.JLabel appHomepageLabel = new javax.swing.JLabel();
        javax.swing.JLabel appDescLabel = new javax.swing.JLabel();
        javax.swing.JLabel imageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(application.Application.getInstance(tournoire.TournoireApp.class).getContext().getResourceMap(TournoireAboutBox.class).getString("title")); // NOI18N
        setName("aboutBox"); // NOI18N
        setResizable(false);

        closeButton.setAction(application.Application.getInstance(tournoire.TournoireApp.class).getContext().getActionMap(TournoireAboutBox.class, this).get("closeAboutBox"));

        appTitleLabel.setFont(appTitleLabel.getFont().deriveFont(appTitleLabel.getFont().getStyle() | java.awt.Font.BOLD, appTitleLabel.getFont().getSize()+4));
        appTitleLabel.setText(application.Application.getInstance(tournoire.TournoireApp.class).getContext().getResourceMap(TournoireAboutBox.class).getString("Application.title")); // NOI18N

        versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getStyle() | java.awt.Font.BOLD));
        versionLabel.setText(application.Application.getInstance(tournoire.TournoireApp.class).getContext().getResourceMap(TournoireAboutBox.class).getString("versionLabel.text")); // NOI18N

        appVersionLabel.setText(application.Application.getInstance(tournoire.TournoireApp.class).getContext().getResourceMap(TournoireAboutBox.class).getString("Application.version")); // NOI18N

        vendorLabel.setFont(vendorLabel.getFont().deriveFont(vendorLabel.getFont().getStyle() | java.awt.Font.BOLD));
        vendorLabel.setText(application.Application.getInstance(tournoire.TournoireApp.class).getContext().getResourceMap(TournoireAboutBox.class).getString("vendorLabel.text")); // NOI18N

        appVendorLabel.setText(application.Application.getInstance(tournoire.TournoireApp.class).getContext().getResourceMap(TournoireAboutBox.class).getString("Application.vendor")); // NOI18N

        homepageLabel.setFont(homepageLabel.getFont().deriveFont(homepageLabel.getFont().getStyle() | java.awt.Font.BOLD));
        homepageLabel.setText(application.Application.getInstance(tournoire.TournoireApp.class).getContext().getResourceMap(TournoireAboutBox.class).getString("homepageLabel.text")); // NOI18N

        appHomepageLabel.setText(application.Application.getInstance(tournoire.TournoireApp.class).getContext().getResourceMap(TournoireAboutBox.class).getString("Application.homepage")); // NOI18N

        appDescLabel.setText(application.Application.getInstance(tournoire.TournoireApp.class).getContext().getResourceMap(TournoireAboutBox.class).getString("appDescLabel.text")); // NOI18N

        imageLabel.setIcon(application.Application.getInstance(tournoire.TournoireApp.class).getContext().getResourceMap(TournoireAboutBox.class).getIcon("imageLabel.icon")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(imageLabel)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(versionLabel)
                            .add(vendorLabel)
                            .add(homepageLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(appVersionLabel)
                            .add(appVendorLabel)
                            .add(appHomepageLabel)))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, appTitleLabel)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, appDescLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .add(closeButton))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(imageLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(appTitleLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(appDescLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(versionLabel)
                    .add(appVersionLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(vendorLabel)
                    .add(appVendorLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(homepageLabel)
                    .add(appHomepageLabel))
                .add(19, 19, Short.MAX_VALUE)
                .add(closeButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    // End of variables declaration//GEN-END:variables
    
}