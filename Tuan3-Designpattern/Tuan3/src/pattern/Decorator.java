package pattern;

import entity.Employee;
import java.util.Scanner;

// Decorator Pattern
interface Work {
    void executeWork();
}

class BasicWork implements Work {
    @Override
    public void executeWork() {
        System.out.println("Thực hiện công việc cơ bản.");
    }
}

abstract class WorkDecorator implements Work {
    protected Work decoratedWork;

    public WorkDecorator(Work decoratedWork) {
        this.decoratedWork = decoratedWork;
    }

    @Override
    public void executeWork() {
        decoratedWork.executeWork();
    }
}

class DirectorWorkDecorator extends WorkDecorator {
    public DirectorWorkDecorator(Work decoratedWork) {
        super(decoratedWork);
    }

    @Override
    public void executeWork() {
        super.executeWork();
        System.out.println("Quản lý và điều hành công ty.");
    }
}

class TeamLeaderWorkDecorator extends WorkDecorator {
    public TeamLeaderWorkDecorator(Work decoratedWork) {
        super(decoratedWork);
    }

    @Override
    public void executeWork() {
        super.executeWork();
        System.out.println("Đi tuần tra và gán việc cho nhân viên.");
    }
}

class OfficeStaffWorkDecorator extends WorkDecorator {
    public OfficeStaffWorkDecorator(Work decoratedWork) {
        super(decoratedWork);
    }

    @Override
    public void executeWork() {
        super.executeWork();
        System.out.println("Làm việc văn phòng và pha cà phê.");
    }
}

class DecoratorEmployee extends Employee {
    private Work work;

    public DecoratorEmployee(String name, String position, Work work) {
        super(name, position);
        this.work = work;
    }

    public void performWork() {
        System.out.print(getName() + " (" + getPosition() + "): ");
        work.executeWork();
    }
}

public class Decorator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập chức vụ (Giám đốc, Đội trưởng, Nhân viên VP): ");
        String position = scanner.nextLine();

        Work work;
        switch (position.toLowerCase()) {
            case "giám đốc":
                work = new DirectorWorkDecorator(new BasicWork());
                break;
            case "đội trưởng":
                work = new TeamLeaderWorkDecorator(new BasicWork());
                break;
            case "nhân viên vp":
                work = new OfficeStaffWorkDecorator(new BasicWork());
                break;
            default:
                System.out.println("Chức vụ không hợp lệ!");
                scanner.close();
                return;
        }

        DecoratorEmployee employee = new DecoratorEmployee("Người dùng", position, work);
        employee.performWork();
        scanner.close();
    }
}
