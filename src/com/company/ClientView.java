package com.company;

import java.util.List;
import java.util.Scanner;

class ClientView {
    private static Scanner scan = new Scanner(System.in);
    private static ClientController clientController = new ClientController();
    //이거는 리스트를 그대로 받아와서 메인에서 PC객체 한번 써볼려는
    // 연습으로 해본거


    static class IOException extends Exception{
        IOException(String errMsg){
            super(errMsg);
        }
    }

    public static void PcBorrow() {
        String borrowOk = "";  //borrowOk 였던 변수이름을 borrowOk로 바꿈
        if(!clientController.IsPowerOn()) {
            while (true) {
                System.out.println("PC를 빌리시겠습니까? 빌리시겠다면 예, 빌리지 않겠다면 아니오를 입력하세요.");
                borrowOk = scan.nextLine();
                if ("".equals(borrowOk)) {
                    borrowOk = scan.nextLine();
                }
                try {
                    if (borrowOk.equals("예")) {
                        ChooseSeat();
                    } else if (borrowOk.equals("아니오")) {
                        System.out.println("메뉴로 이동합니다.");
                    } else throw new IOException("잘못된 입력이야.");
                } catch (IOException e) {
                    String exceptionMessage = e.getMessage();
                    System.out.println(exceptionMessage);
                    continue;
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            }
        }
        else{
            System.out.println("PC를 이용중입니다.");
        }
    }

    public static void ChooseSeat() throws Exception {
        while (true) {
            System.out.println("몇번 좌석을 선택하시겠습니까?");
            int selectSeat = scan.nextInt(); //seat 였던 변수를 selectSeat로 바꿔봄
            if ((selectSeat == 0 || selectSeat > 32)) {
                throw new Exception("0에서 32까지의 정수를 입력해주세요.");
            } else if (clientController.CheckPc(selectSeat)) {
                System.out.println("이런~ pc가 사용중이네요~~ 다른 pc를 이용해주세요~");
                continue;
            } else {
                ChoosePayment(selectSeat);
            }
             /* catch (Exception e) {
                System.out.println("1~10 안의 숫자를 선택해주세요.");
                continue;
            }
            */
            break;
        }
    }

    public static void ChoosePayment(int selectSeat) {  //TermOfpayment 를 IsPrepayment로 바꿈
        while (true) {
            try {
                System.out.println("선불은 1번, 후불은 2번을 입력하세요.");
                int isPrepayment = scan.nextInt();
                if (isPrepayment == 1) {
                    PrePayment(selectSeat);
                } else if (isPrepayment == 2) {
                    System.out.println("즐겁게 이용하세요!");
                    // 여기부분
                    clientController.DeferredPayment(selectSeat);
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("올바른 숫자를 입력해주세요.");
                continue;
            }
            break;
        }
    }

    public static void PrePayment(int selectSeat) {
        while (true) {
            int useTime;
            try {
                System.out.println("몇시간을 이용하시겠습니까? 최대 20시간까지 이용가능 합니다.");
                System.out.println("최대 20시간까지 이용가능 합니다,");
                System.out.println("1시간에 800원입니다,");
                useTime = scan.nextInt();
                if (useTime == 0 || useTime > 20) {
                    throw new Exception();
                } else {
                    int prize = useTime * 800;
                    System.out.println("가격은" + prize + "입니다.");
                    System.out.println("즐거운 이용 되세요~~");
                    clientController.PrePayment(selectSeat, useTime);
                }
            } catch (Exception e) {
                System.out.println("시간을 다시 입력하세요.");
                continue;
            }
            break;
        }
    }

    public static void ChangeSeat() {
        while (true) {
            try {
                if(clientController.IsPowerOn()){
                    System.out.println("몇번 자리로 이동하시겠습니까?");
                    int movingSeat = scan.nextInt();
                    if (movingSeat == 0 || movingSeat > 32) {
                        throw new Exception();
                    }
                    else if(movingSeat == clientController.PC.getPcNumber()){
                        System.out.println("같은자리를 이동할순 없어요!");
                    }
                    else if(clientController.CheckPc(movingSeat)) {
                        System.out.println("이미 사용중인 자리입니다.");
                    }
                    else{
                        clientController.PcMove(movingSeat);
                        System.out.println("자리가 이동되었습니다.");
                    }
                }
            } catch (Exception e) {
                System.out.println("올바른 숫자를 입력하세요.");
                continue;
            }
            break;
        }
    }

    public static void PcClose() {

        clientController.TurnOff();
        System.out.println("사용이 정상적으로 종료되었습니다.");

    }

    public static void main(String args[]) {
        int selectedMenuIndex = 0;
        while (true) {
            System.out.println("================================");
            System.out.println("=============메뉴 선택--=========");
            System.out.println("============1.PC 이용============");
            System.out.println("============2.PC 자리 조회=======");
            System.out.println("============3.PC 자리 이동========");
            System.out.println("============4.PC 이용 종료========");
            System.out.println("================================");
            System.out.println("메뉴 입력");
            try {
                selectedMenuIndex = scan.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("숫자를 입력하세요.");
                scan.next();
            }
            switch (selectedMenuIndex) {
                case 1:
                    PcBorrow();
                    break;
                case 2:
                    clientController.AllCheck();
                    break;
                case 3:
                    ChangeSeat();
                    break;
                case 4:
                    clientController.TurnOff();
                    System.out.println("다음에 또 오십시오.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못 누르셨습니다.");
            }
        }
    }
}