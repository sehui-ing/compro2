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

public class GUIApplication_final {
    private final List<String> data = new ArrayList<>(); // 데이터 목록, 사용자 입력 저장
    private JTextArea textArea; // 텍스트 영역, 사용자가 텍스트를 입력
    private DrawingPanel drawingPanel; // 그림을 그리는 패널, 사용자가 그림을 그림
    private boolean isDrawing = true; // 그리기 모드인지 여부를 나타내는 플래그

    // GUI 애플리케이션 시작
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUIApplication app = new GUIApplication();
            app.createAndShowGUI();
        });
    }

    // GUI 생성 및 화면 표시를 위한 method
    private void createAndShowGUI() {
        // 메인 프레임을 생성
        JFrame frame = new JFrame("GUI Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // 그림 그리기와 텍스트 입력을 위한 메인 패널 생성 및 레이아웃 설정
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        // 그림 그리는 패널 생성 -> 메인 패널에 추가
        drawingPanel = new DrawingPanel();
        mainPanel.add(drawingPanel);

        // 텍스트 영역 생성 -> 메인 패널에 추가
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        mainPanel.add(new JScrollPane(textArea));

        // 메인 패널을 메인 프레임에 추가
        frame.add(mainPanel, BorderLayout.CENTER);

        /* 버튼 및 이벤트 리스터 설정 */
        // 1. 사용자가 그린 그림을 .jpg 파일로 저장하는 버튼
        JButton saveDrawingButton = new JButton("Save Drawing as JPG");
        saveDrawingButton.setToolTipText("이미지 저장");
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

        // 2. 텍스트를 .txt 파일로 저장하는 버튼
        JButton saveTextButton = new JButton("Save Text as txt");
        saveTextButton.setToolTipText("텍스트 저장");
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

        // 3. 로드된 이미지, 그린 그림, 작성한 텍스트를 초기화 하는 버튼
        JButton resetButton = new JButton("Reset");
        resetButton.setToolTipText("초기화");
        resetButton.addActionListener(e -> {
            data.clear();
            updateTextArea();
            drawingPanel.clearDrawing();
            drawingPanel.clearImage(); // 로드된 이미지 지우기
        });

        // 4. 그리기/지우기 모드를 전환하는 버튼
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
            drawingPanel.setDrawingImage(); // 이미지 drawing 설정
        });

        // 5. 이미지 불러오는 버튼
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

        // 6. 그리기 펜 색깔을 정하는 버튼
        JButton colorChooserButton = new JButton("Choose color");
        colorChooserButton.addActionListener(e -> {
            Color chosenColor = JColorChooser.showDialog(frame, "Choose a color", Color.BLACK);
            if (chosenColor != null) {
                drawingPanel.setCurrentColor(chosenColor);
            }
        });

        // 버튼 패널 생성 및 패널에 버튼 추가
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5));  // 그리드 레이아웃 설정
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));  // 패딩 추가
        buttonPanel.add(saveDrawingButton);
        buttonPanel.add(saveTextButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(modeButton);
        buttonPanel.add(loadImageButton);
        buttonPanel.add(colorChooserButton);

        // 버튼 패널을 메인 프레임에 추가
        frame.add(buttonPanel, BorderLayout.NORTH);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");  // 테마 설정
        } catch (Exception ignored) { // 예외 무시
        }

        // 프레임 크기를 조정 및 화면 표시
        frame.pack();
        frame.setVisible(true);
    }

    // 텍스트 영역을 최신 데이터로 업데이트하는 method
    private void updateTextArea() {
        textArea.setText("");
        data.stream().forEach(item -> textArea.append(item + "\n"));
    }

    // 사용자가 그린 그림을 이미지 파일로 저장하는 method
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

    // 텍스트 영역의 내용을 텍스트 파일로 저장하는 method
    private void saveTextAsTxt(File file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println(textArea.getText().trim());
            JOptionPane.showMessageDialog(null, "Text saved successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // 사용자의 펜 색, 위치 정보를 위한 클래스
    private static class ColorPoint {
        Point point; // 점의 위치 저장
        Color color; // 점의 색깔 저장

        // ColorPointer 생성자: 위치와 색상을 초기화
        public ColorPoint(Point point, Color color) {
            this.point = point;
            this.color = color;
        }
    }

    // 사용자의 그림판을 위한 클래스
    private static class DrawingPanel extends JPanel {
        private final List<ColorPoint> colorPoints = new ArrayList<>();  // 사용자가 그린 점들을 저장하는 리스트
        private BufferedImage loadedImage = null; // 로드된 이미지를 저장하는 필드
        private boolean isDrawingMode = true;  // 그리기/지우기 모드인지를 나타내는 플래그
        private Color currentColor = Color.BLACK; // 현재 선택된 색상을 저장

        // DrawingPanel 생성자: 패널 사이즈 설정과 이벤트 리스너 초기화
        public DrawingPanel() {
            setPreferredSize(new Dimension(400, 200)); // 패널의 기본 크기를 설정
            // 마우스 클릭 이벤트 처리
            addMouseListener(new MouseAdapter() {
                @Override // 마우스 눌렀을 때
                public void mousePressed(MouseEvent e) {
                    // 그리기 모드일 때
                    if (isDrawingMode) {
                        // 클릭된 위치에 색상을 가진 점을 추가 하고 다시 그리기
                        colorPoints.add(new ColorPoint(e.getPoint(), currentColor));
                        repaint();
                        // 지우기 모드일 때 지우기 수행
                    } else {
                        editDrawing(e.getPoint());
                    }
                }
            });
            // 마우스 클릭 이벤트 처리
            addMouseMotionListener(new MouseMotionAdapter() {
                @Override // 마우스 드래그 했을 때
                public void mouseDragged(MouseEvent e) {
                    // 그리기 모드일 때
                    if (isDrawingMode) {
                        // 클릭된 위치에 색상을 가진 점을 추가 하고 다시 그리기
                        colorPoints.add(new ColorPoint(e.getPoint(), currentColor));
                        repaint();
                        // 지우기 모드일 때 지우기 수행
                    } else {
                        editDrawing(e.getPoint());
                    }
                }
            });

        }

        // 현재 색상을 설정하는 method
        public void setCurrentColor(Color newColor) {
            currentColor = newColor;
        }

        // 패널에 그리는 method
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 로드된 이미지가 있으면 그리기
            if (loadedImage != null) {
                g.drawImage(loadedImage, 0, 0, this);
            }
            // 저장된 모든 점들을 그리기
            for (ColorPoint cp : colorPoints) {
                g.setColor(cp.color); // 각 점의 색상 설정
                g.fillOval(cp.point.x, cp.point.y, 4, 4);
            }
        }

        // 그린 내용을 모두 지우는 method
        public void clearDrawing() {
            colorPoints.clear();
            repaint();
        }

        // 로드된 이미지를 지우는 method
        public void clearImage() {
            loadedImage = null;
            repaint();
        }

        // 그림을 그리는 데 사용할 이미지를 설정하는 method
        public void setDrawingImage() {
            // 사용자가 그림을 그리는 데 사용할 이미지
            BufferedImage drawingImage;
            if (loadedImage != null) {
                // 로드된 이미지를 복사하여 그리기 이미지로 사용
                drawingImage = new BufferedImage(loadedImage.getWidth(),
                        loadedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = drawingImage.createGraphics();
                g2d.drawImage(loadedImage, 0, 0, null);
                g2d.dispose();
            }
            repaint();
        }

        // 그리기 모드를 설정하는 method
        public void setMode(boolean drawingMode) {
            isDrawingMode = drawingMode;
        }

        // 이미지를 로드하여 패널에 표시하는 method
        public void loadImage(BufferedImage img) {
            loadedImage = img;
            repaint();
        }

        // 그린 그림을 편집하는 method (점을 지우는 기능)
        private void editDrawing(Point point) {
            // 포인트가 없으면 반환
            if (colorPoints.isEmpty()) {
                return;
            }

            // 클릭한 위치 주변에 작은 원 생성
            int circleRadius = 8;
            // 원 안에 있는 포인트들을 저장할 리스트
            List<ColorPoint> colorPointsToRemove = new ArrayList<>();
            // 클릭한 위치 주변의 점들을 찾아 지우기
            for (ColorPoint cp : colorPoints) {
                double distance = getDistance(point, cp.point);
                if (distance < circleRadius) {
                    colorPointsToRemove.add(cp);
                }
            }
            colorPoints.removeAll(colorPointsToRemove);
            repaint();
        }

        // 두 점 사이의 거리를 계산하는 method
        private double getDistance(Point p1, Point p2) {
            int dx = p2.x - p1.x;
            int dy = p2.y - p1.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }
}
