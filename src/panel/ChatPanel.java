package panel;

import util.ControlUtil;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.security.Key;
import java.util.ArrayList;

public class ChatPanel extends Panel {
    private ArrayList<String> chat = new ArrayList<>();

    private int NUM_CHATS = 4;
    private int MAX_CHAT_LENGTH = 32;
    private int CHAT_SPACING = 10;
    private int CHAT_OFFSETX = 3;
    private int CHAT_OFFSETY = 10;
    private int CHAT_BOTTOM_POS = 50;

    private int debugx, debugy;

    private String message = "";

    public ChatPanel(int minX, int minY) {
        super(minX, minY);

        this.width = 12*32;
        this.height = 2*32;

        this.canvas = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

/*
        chat.add("hello");
        chat.add("goodbye");
        chat.add("The quick brown fox jumps over the lazy dog");
        chat.add("YOU DIED");
        chat.add("stop");
*/
    }

    @Override
    public BufferedImage draw() {
        Graphics g = canvas.createGraphics();

        g.setColor(Color.red);
        g.fillRect(debugx - minX, debugy - minY, 2, 2);

        g.setColor(new Color(169,132,95));
        g.fillRect(0, 0, width, height);

        g.setColor(Color.black);
//        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
//        g.drawString("The quick brown fox jumps over the lazy dog", 2, 20);
//        g.drawString("asyv n savuon;ev wuanva;bn bna;vinwevunav wgn aw", 2, 30);

        for(int i = Math.max(0, chat.size()-NUM_CHATS); i < chat.size(); i++) {
            g.drawString(chat.get(i), CHAT_OFFSETX, CHAT_SPACING * (i - Math.max(0, chat.size()-NUM_CHATS)) + CHAT_OFFSETY);
        }

        g.drawString(message, CHAT_OFFSETX, CHAT_BOTTOM_POS + CHAT_OFFSETY);

        return canvas;
    }

    @Override
    public void mousePress(int x, int y) {
        debugx = x;
        debugy = y;

        System.out.println(x);
        System.out.println(y);

        if(collides(x,y)) {
            System.out.println("b");
            ControlUtil.chatting = true;
        } else {
            System.out.println("c");
            ControlUtil.chatting = false;
        }
    }

    @Override
    public void keyType(KeyEvent e) {
        if(ControlUtil.chatting) {
            message += e.getKeyChar();
        }
    }

    @Override
    public void keyPress(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            chat.add(message);
            message = "";
        }

    }
}
