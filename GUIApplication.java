package compro2.ex;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class GUIApplication {
    private List<String> data = new ArrayList<>();
    private JTextArea textArea;
    private DrawingPanel drawingPanel;

    private boolean isDrawing = true; // 그리기 모드인지 여부를 나타내는 플래그

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUIApplication app = new GUIApplication();
            app.createAndShowGUI();
        });
    }

    protected void createAndShowGUI() {
        JFrame frame = new JFrame("GUI Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // 그림 그리기와 텍스트 입력을 위한 패널
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        // 그림 그리는 패널
        drawingPanel = new DrawingPanel();
        mainPanel.add(drawingPanel);

        // 데이터와 사용자 입력을 보여주기 위한 텍스트 영역
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        mainPanel.add(new JScrollPane(textArea));

        frame.add(mainPanel, BorderLayout.CENTER);

        // 그림을 이미지로 저장하는 버튼
        JButton saveDrawingButton = new JButton("Save Drawing as JPG");
        saveDrawingButton.setToolTipText("저장");
        saveDrawingButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                // 파일이 .jpg 확장자를 가지도록 함
                if (!file.getName().toLowerCase().endsWith(".jpg")) {
                    file = new File(file.getAbsolutePath() + ".jpg");
                }
                saveDrawingAsImage(file);
            }
        });

        // 텍스트를 .txt 파일로 저장하는 버튼
        JButton saveTextButton = new JButton("Save Text as txt");
        saveTextButton.setToolTipText("저장");
        saveTextButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                // 파일이 .txt 확장자를 가지도록 함
                if (!file.getName().toLowerCase().endsWith(".txt")) {
                    file = new File(file.getAbsolutePath() + ".txt");
                }
                saveTextAsTxt(file);
            }
        });

        // 리셋하는 버튼
        JButton resetButton = new JButton("Reset");
        resetButton.setToolTipText("초기화");
        resetButton.addActionListener(e -> {
            data.clear();
            updateTextArea();
            drawingPanel.clearDrawing();
            drawingPanel.clearImage(); // 로드된 이미지 지우기
        });
        
        // 그리기/편집 모드를 전환하는 버튼
        JButton modeButton = new JButton("Drawing");
        modeButton.setToolTipText("그리기/수정 모드 전환");
        modeButton.addActionListener(e -> {
            isDrawing = !isDrawing;
            if (isDrawing) {
                modeButton.setText("Drawing");
            } else {
                modeButton.setText("Remove");
            }
            drawingPanel.setMode(isDrawing);
            drawingPanel.clearImage();
        });

        // 이미지 불러오는 버튼
        JButton loadImageButton = new JButton("Load Image");
        loadImageButton.setToolTipText("이미지 불러오기");
        loadImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    BufferedImage img = ImageIO.read(selectedFile);
                    drawingPanel.loadImage(img);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // 버튼들을 담을 패널
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5));  // 그리드 레이아웃으로 변경
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));  // 패딩 추가
        buttonPanel.add(saveDrawingButton);
        buttonPanel.add(saveTextButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(modeButton); // 모드 버튼 추가
        buttonPanel.add(loadImageButton); // 이미지 불러오기 버튼 추가

        frame.add(buttonPanel, BorderLayout.NORTH);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");  // 테마 설정
        } catch(Exception ignored){}

        frame.pack();
        frame.setVisible(true);
    }

    private void updateTextArea() {
        textArea.setText("");
        for (String item : data) {    
            textArea.append(item + "\n");
        }
    }

    private void saveDrawingAsImage(File file) {
        // 그림 그리는 패널의 크기 가져오기
        int drawingWidth = drawingPanel.getWidth();
        int drawingHeight = drawingPanel.getHeight();

        // 그림을 위한 BufferedImage 생성
        BufferedImage drawingImage = new BufferedImage(drawingWidth, drawingHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = drawingImage.createGraphics();

        // 배경색을 흰색으로 설정
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, drawingWidth, drawingHeight);

        // 그림 그리는 패널의 내용 그리기
        drawingPanel.paint(g);

        try {
            // 그림을 JPG 파일로 저장
            ImageIO.write(drawingImage, "jpg", file);
            JOptionPane.showMessageDialog(null, "Drawing saved successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            g.dispose();
        }
    }

    private void saveTextAsTxt(File file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println(textArea.getText().trim());
            JOptionPane.showMessageDialog(null, "Text saved successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private class DrawingPanel extends JPanel {
        private List<Point> points = new ArrayList<>();
        private BufferedImage loadedImage = null;
        private boolean isDrawingMode = true; // 그리기/편집 모드를 나타내는 플래그

        public DrawingPanel() {
            setPreferredSize(new Dimension(400, 200));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (isDrawingMode) {
                        points.add(e.getPoint());
                        repaint();
                    } else {
                        editDrawing(e.getPoint()); // 편집 모드일 때 편집 수행
                    }
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (isDrawingMode) {
                        points.add(e.getPoint());
                        repaint();
                    } else {
                        editDrawing(e.getPoint()); // 편집 모드일 때 편집 수행
                    }
                }
            });
            
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (loadedImage != null) {
                g.drawImage(loadedImage, 0, 0, this); // 로드된 이미지 그리기
            }
            for (Point point : points) {
                g.fillOval(point.x, point.y, 4, 4);
            }
        }

        public void clearDrawing() {
            points.clear();
            repaint();
        }

        public void clearImage() {
            loadedImage = null;
            repaint();
        }
        
        public void setMode(boolean drawingMode) {
            isDrawingMode = drawingMode;
        }

        private void editDrawing(Point point) {
            // 포인트가 없으면 반환
            if (points.isEmpty()) {
                return;
            }

            // 클릭한 위치 주변에 작은 원 생성
            int clickX = point.x;
            int clickY = point.y;
            int circleRadius = 8;

            // 원 안에 있는 포인트들을 저장할 리스트
            List<Point> pointsToRemove = new ArrayList<>();
            for (Point p : points) {
                double distance = getDistance(point, p);
                if (distance < circleRadius) {
                    pointsToRemove.add(p);
                }
            }

            // 원 안에 있는 포인트들을 제거
            points.removeAll(pointsToRemove);
            repaint();
        }

        public void loadImage(BufferedImage img) {
            loadedImage = img;
            repaint();
        }

        // 두 점 사이의 거리를 계산하는 보조 메서드
        private double getDistance(Point p1, Point p2) {
            int dx = p2.x - p1.x;
            int dy = p2.y - p1.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }
}
