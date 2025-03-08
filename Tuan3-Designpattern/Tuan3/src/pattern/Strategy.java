package pattern;

import entity.Employee;
import java.util.Scanner;

// Strategy Pattern
interface WorkStrategy {
    void executeWork();
}

class DirectorStrategy implements WorkStrategy {
    @Override
    public void executeWork() {
        System.out.println("Quản lý và điều hành công ty.");
    }
}

class TeamLeaderStrategy implements WorkStrategy {
    @Override
    public void executeWork() {
        System.out.println("Đi tuần tra và gán việc cho nhân viên.");
    }
}

class OfficeStaffStrategy implements WorkStrategy {
    @Override
    public void executeWork() {
        System.out.println("Làm việc văn phòng và pha cà phê.");
    }
}

class StrategyEmployee extends Employee {
    private WorkStrategy workStrategy;

    public StrategyEmployee(String name, String position, WorkStrategy workStrategy) {
        super(name, position);
        this.workStrategy = workStrategy;
    }

    public void performWork() {
        System.out.print(getName() + " (" + getPosition() + "): ");
        workStrategy.executeWork();
    }
}

public class Strategy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập chức vụ (Giám đốc, Đội trưởng, Nhân viên VP): ");
        String position = scanner.nextLine();

        WorkStrategy workStrategy;
        switch (position.toLowerCase()) {
            case "giám đốc":
                workStrategy = new DirectorStrategy();
                break;
            case "đội trưởng":
                workStrategy = new TeamLeaderStrategy();
                break;
            case "nhân viên vp":
                workStrategy = new OfficeStaffStrategy();
                break;
            default:
                System.out.println("Chức vụ không hợp lệ!");
                scanner.close();
                return;
        }

        StrategyEmployee employee = new StrategyEmployee("Người dùng", position, workStrategy);
        employee.performWork();
        scanner.close();
    }
}