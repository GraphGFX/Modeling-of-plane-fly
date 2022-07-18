import javax.swing.*;
public class Main extends JFrame {
    public Main(){
        JFrame f1 = new JFrame();
        f1.setTitle("ДОСЛІДЖЕННЯ ХАРАКТЕРИСТИК СТІЙКОСТІ ТА КЕРОВАНОСТІ ДОВГОПЕРІОДИЧНОГО РУХУ ЛІТАКА В ПОВЗДОВЖНІЙ ПЛОЩИНІ НА ПЕОМ");
        f1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f1.setBounds(500,200,965,200);
        f1.getContentPane();
        f1.add(new GraphicsAndFrames());
        f1.setVisible(true);
    }
    public static void main(String[] args) {
        Main main = new Main();
    }
}