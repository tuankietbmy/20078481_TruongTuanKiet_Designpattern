package pattern;

import entity.Employee;
import java.util.Scanner;

// State Pattern
interface WorkState {
    void doWork();
}

class DirectorState implements WorkState {
    @Override
    public void doWork() {
        System.out.println("Quản lý và điều hành công ty.");
    }
}

class TeamLeaderState implements WorkState {
    @Override
    public void doWork() {
        System.out.println("Đi tuần tra và gán việc cho nhân viên.");
    }
}

class OfficeStaffState implements WorkState {
    @Override
    public void doWork() {
        System.out.println("Làm việc văn phòng và pha cà phê.");
    }
}

class StateEmployee extends Employee {
    private WorkState workState;

    public StateEmployee(String name, String position, WorkState workState) {
        super(name, position);
        this.workState = workState;
    }

    public void performWork() {
        System.out.print(getName() + " (" + getPosition() + "): ");
        workState.doWork();
    }
}

public class State {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập chức vụ (Giám đốc, Đội trưởng, Nhân viên VP): ");
        String position = scanner.nextLine();

        WorkState workState;
        switch (position.toLowerCase()) {
            case "giám đốc":
                workState = new DirectorState();
                break;
            case "đội trưởng":
                workState = new TeamLeaderState();
                break;
            case "nhân viên vp":
                workState = new OfficeStaffState();
                break;
            default:
                System.out.println("Chức vụ không hợp lệ!");
                scanner.close();
                return;
        }

        StateEmployee employee = new StateEmployee("Người dùng", position, workState);
        employee.performWork();
        scanner.close();
    }
}
