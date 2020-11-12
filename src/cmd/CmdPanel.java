/*
 * Created by JFormDesigner on Thu Oct 08 16:06:43 CST 2020
 */

package cmd;

import exception.CmdException;
import log.MyLog;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class CmdPanel extends JFrame {
    public CmdPanel() {
        initComponents();
        history = new ArrayList<>();
        currentCommandIndex = -1;
    }

    /**
     * 文本区域键盘事件监听
     *
     * @param e 键盘事件
     */
    private void cmdTextAreaKeyType(KeyEvent e) throws Exception {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            pasteEvent();
        } else {
            switch (e.getKeyCode()) {
//            回车事件
                case KeyEvent.VK_ENTER: {
                    String currentTxt = cmdTextArea.getText();
                    if (!cmdStr.equals(currentTxt)) {
//                    提取出本次命令
                        String command = currentTxt.substring(cmdStr.length(), currentTxt.length() - 1);
                        if (command.length() > 0) {
                            history.add(command);
                            currentCommandIndex = history.size() - 1;
                        }
                        commandExec(command);
                    }
                    break;
                }
//          键盘回退事件
                case KeyEvent.VK_BACK_SPACE: {
                    String currentTxt = cmdTextArea.getText();
//                如果是本次的新命令，可以清除到新命令开头，不可以继续删除
                    if (currentTxt.length() < cmdStr.length()) {
                        currentTxt = cmdStr;
                        currentCommandIndex = history.size() - 1;
                    }
                    setText(currentTxt);
                    break;
                }
//            键盘向上按键
                case KeyEvent.VK_UP: {
//                只有历史命令个数大于1才可以向上翻
                    if (history.size() >= 1) {
                        cmdTextArea.setText(cmdStr + history.get(currentCommandIndex));
                        cmdTextArea.setCaretPosition(cmdTextArea.getText().length());
                        currentCommandIndex--;
//                    保证不越界
                        if (currentCommandIndex < 0) {
                            currentCommandIndex = 0;
                        }
                    }
                    break;
                }
//            键盘向下按键
                case KeyEvent.VK_DOWN: {
//                只有历史命令个数大于1才可以向下翻
                    if (history.size() >= 1) {
                        cmdTextArea.setText(cmdStr + history.get(currentCommandIndex));
                        cmdTextArea.setCaretPosition(cmdTextArea.getText().length());
                        currentCommandIndex++;
//                    保证不越界
                        if (currentCommandIndex >= history.size()) {
                            currentCommandIndex = history.size() - 1;
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    }

    /**
     * 初始化页面组件
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        JScrollPane cmdScrollPanel = new JScrollPane();
        cmdTextArea = new JTextArea();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== cmdScrollPanel ========
        {
            cmdScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            cmdScrollPanel.setBorder(null);
            cmdScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            //---- cmdTextArea ----
            cmdTextArea.setForeground(Color.white);
            cmdTextArea.setBackground(Color.black);
            cmdTextArea.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", cmdTextArea.getFont().getStyle(), 16));
            cmdTextArea.setColumns(124);
            cmdTextArea.setRows(3);
            cmdTextArea.setLineWrap(true);
            cmdTextArea.setWrapStyleWord(true);
            cmdTextArea.setBorder(null);
            cmdTextArea.setCaretColor(Color.white);
            cmdTextArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            cmdTextArea.setMargin(new Insets(0, 0, 0, 15));
            cmdTextArea.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    try {
                        cmdTextAreaKeyType(e);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                @Override
                public void keyTyped(KeyEvent e) {
                    try {
                        cmdTextAreaKeyType(e);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
            adjustCaret();
            cmdScrollPanel.setViewportView(cmdTextArea);
        }
        contentPane.add(cmdScrollPanel);
        cmdScrollPanel.setBounds(0, 0, 1025, 655);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(1015, 690);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        /*
         * 开启 or 关闭 cmd 面板
         * */
        this.setVisible(false);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 执行命令后，调整光标位置
     */
    private void adjustCaret() {
        cmdTextArea.append(TERMINAL);
        cmdStr = cmdTextArea.getText();
        cmdTextArea.setCaretPosition(cmdTextArea.getText().length());
    }

    /**
     * 获取历史命令打印字符串
     */
    public synchronized String historyString() {
        StringBuilder builder = new StringBuilder();
        for (String command : history) {
            builder.append("    ").append(command).append('\n');
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public synchronized void clear() {
        cmdTextArea.setText("");
        adjustCaret();
    }

    private void pasteEvent() {
        System.out.println("copy");
        Transferable trans = CLIPBOARD.getContents(DataFlavor.stringFlavor);
//            如果剪贴板存在，且支持文本，获取文本
        if (trans != null && trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                String pasteCommand = (String) trans.getTransferData(DataFlavor.stringFlavor);
                String[] commands = pasteCommand.split("\n");
                System.out.println(Arrays.toString(commands));
                cmdTextArea.setText(cmdStr);
                for (String commandSection : commands) {
                    commandSection = commandSection.trim();
                    if (commandSection.length() > 0) {
                        history.add(commandSection);
                        currentCommandIndex = history.size() - 1;
                        cmdTextArea.append(commandSection + "\r\n");
                        commandExec(commandSection);
//                        adjustCaret();
                    }

                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public synchronized void printAndAdjust(String str) {
        cmdTextArea.append(str + '\n');
        adjustCaret();
    }

    public synchronized void print(String str) {
        cmdTextArea.append(str + '\n');
    }

    /**
     * 执行命令
     */
    private void commandExec(String command) throws Exception {
        MyLog.command(command);
        try {
            if (parser != null) {
                boolean parse = parser.parse(command);
                if (!parse) {
                    this.adjustCaret();
                }
            } else {
                printAndAdjust("No Parser Loading");
                adjustCaret();
            }
        } catch (Exception e) {
            //如果是已知的cmd错误，打印错误信息
            if (e instanceof CmdException) {
                printAndAdjust(((CmdException) e).getErrMsg());
            } else {
                printAndAdjust(e.getMessage());
            }

        }
    }

    private Parser parser = null;

    public synchronized void setParser(Parser parser) {
        this.parser = parser;
    }


    public String getTERMINAL() {
        return TERMINAL;
    }

    private synchronized void setText(String text) {
        cmdTextArea.setText(text);
    }

    private JTextArea cmdTextArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    /**
     * 上回执行过后的cmd的文本备份
     */
    private String cmdStr;
    /**
     * 终端表示串
     */
    private static final String TERMINAL = "Terminal $: ";
    /**
     * 保存历史命令的容器
     */
    private final ArrayList<String> history;
    /**
     * 指向上一条命令的指针
     */
    private int currentCommandIndex;
    private static final Clipboard CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();
}
