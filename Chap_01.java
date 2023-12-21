//package computerProgramming2;
//import java.util.*;
//
//public class Chap_01 {
//    public static void main(String[] args) {
//        // 극장 좌석 10자리 배열 : int[] seats;
//        // 좌석 배치표 보여 준다 : viewSeats()
//        // 예약된 좌석은 1 그외 0 : viewSeats(), ex) 0 0 0 0 0 0 0 0 0 0
//        // 예약된 좌석, 잘못 입력은 잘못 입력 출력 input (1 ~ 10), 그외 : 잘못 입력, 예외 처리
//
//        // 클래스 작성, 클래스 변수 선언
//        // public static Scanner input = new Scanner(System.in);
//        // public static int[] seats; : 좌석 예약 상태
//
//        final int size = 10;
//        initSeat(size);
//        question();
//        System.out.println("프로그램을 종료합니다.");
//    }
//
//    public class SeatReservation{
//        public static Scanner input = new Scanner(System.in);
//        public static int[] seats;
//        private static void initSeat(int num) {
//            seats = new int[num];
//            for(int i = 0; i < num; i++){
//                seats[i]=0;
//            }
//        }
//    }
//    public static void seatReservation() {
//        public static Scanner input = new Scanner(System.in);
//        public static int[] seats;
//    }
//    public static void viewCurrentSeat() {
//        private static void question() {
//            // 좌석 예약 여부 질문
//            // y or n
//            // question 다시 호출
//            viewCurrentSeat();
//        }
//    }
//}
