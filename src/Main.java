//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Objects;

import java.util.Objects;

class Employee {
    private static int idCounter = 1;
    private int id;
    private String fullName;
    private int department;
    private int salary;

    public Employee(String fullName, int department, int salary) {
        this.id = idCounter++;
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }
    public int getDepartment() {
        return department;
    }
    public int getSalary() {
        return salary;
    }
    public void setDepartment(int department) {
        this.department = department;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return salary == employee.salary;
    }

    @Override
    public String toString() {
        return "Сотрудник[id=" + id + ", ФИО=" + fullName + ", отдел=" + department + ", зарплата=" + salary + "]";
    }

    public void printShortInfo() {
        System.out.println("Сотрудник[ФИО=" + fullName + ", зарплата=" + salary + "]");
    }
}

class EmployeeBook {
    private Employee[] employees = new Employee[10];

    // 1. Получить список всех сотрудников
    public void printAllEmployees() {
        for (Employee e : employees) {
            if (e != null) {
                System.out.println(e);
            }
        }
    }

    // 2. Подсчитать среднее значение зарплат
    public double averageSalary() {
        int count = 0;
        int sum = 0;
        for (Employee e : employees) {
            if (e == null) break;
            sum += e.getSalary();
            count++;
        }
        return count > 0 ? (double) sum / count : 0;
    }

    // 3. Подсчитать налоги (PROPORTIONAL/PROGRESSIVE)
    public void printTaxes(String type) {
        for (Employee e : employees) {
            if (e == null) break;
            int salary = e.getSalary();
            double tax = 0;
            switch (type) {
                case "PROPORTIONAL":
                    tax = salary * 0.13;
                    break;
                case "PROGRESSIVE":
                    if (salary <= 150) tax = salary * 0.13;
                    else if (salary <= 350) tax = salary * 0.17;
                    else tax = salary * 0.21;
                    break;
                default:
                    System.out.println("Неизвестный тип налогообложения");
                    continue;
            }
            System.out.println("Сотрудник id=" + e.getId() + ": налог=" + String.format("%.2f", tax));
        }
    }

    // 4. Проиндексировать зарплаты по отделу
    public void indexSalaryByDepartment(int department, int percent) {
        for (Employee e : employees) {
            if (e == null) break;
            if (e.getDepartment() != department) continue;
            int oldSalary = e.getSalary();
            int newSalary = oldSalary + oldSalary * percent / 100;
            if (oldSalary == newSalary) continue;
            e.setSalary(newSalary);
        }
    }

    // 5. Найти первого сотрудника отдела с зарплатой больше указанной
    public void findFirstByDepartmentAndSalary(int department, int wage) {
        for (int i = 0; i < employees.length; i++) {
            Employee e = employees[i];
            if (e != null && e.getDepartment() == department && e.getSalary() > wage) {
                System.out.print("Порядковый номер в списке: " + i + ", ");
                e.printShortInfo();
                break;
            }
        }
    }

    // 6. Вывести первых N сотрудников с зарплатой меньше wage
    public void printEmployeesWithSalaryLessThan(int wage, int employeeNumber) {
        int count = 0;
        int i = 0;
        while (i < employees.length && count < employeeNumber) {
            Employee e = employees[i];
            if (e != null && e.getSalary() < wage) {
                e.printShortInfo();
                count++;
            }
            i++;
        }
    }

    // 7. Проверить, есть ли сотрудник (по зарплате)
    public boolean containsEmployee(Employee emp) {
        for (Employee e : employees) {
            if (e == null) break;
            if (e.equals(emp)) return true;
        }
        return false;
    }

    // 8. Добавить сотрудника
    public boolean addEmployee(Employee emp) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = emp;
                return true;
            }
        }
        return false;
    }

    // 9. Найти сотрудника по id
    public Employee findById(int id) {
        for (Employee e : employees) {
            if (e == null) break;
            if (e.getId() == id) return e;
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        EmployeeBook book = new EmployeeBook();

        // Добавление 11 сотрудников (последний не поместится)
        for (int i = 0; i < 11; i++) {
            Employee emp = new Employee(
                    "Сотрудник " + (i + 1),
                    (i % 5) + 1,
                    50 + (i * 40)
            );
            boolean result = book.addEmployee(emp);
            System.out.println("Добавление сотрудника " + emp.getFullName() + ": " + result);
        }

        System.out.println("\nСписок всех сотрудников:");
        book.printAllEmployees();

        System.out.println("\nСредняя зарплата: " + book.averageSalary());

        System.out.println("\nНалоги (PROPORTIONAL):");
        book.printTaxes("PROPORTIONAL");

        System.out.println("\nНалоги (PROGRESSIVE):");
        book.printTaxes("PROGRESSIVE");

        System.out.println("\nИндексация зарплат отдела 2 на 10%:");
        book.indexSalaryByDepartment(2, 10);
        book.printAllEmployees();

        System.out.println("\nПервый сотрудник отдела 3 с зарплатой больше 100:");
        book.findFirstByDepartmentAndSalary(3, 100);

        System.out.println("\nПервые 3 сотрудника с зарплатой меньше 200:");
        book.printEmployeesWithSalaryLessThan(200, 3);

        System.out.println("\nПроверка containsEmployee (зарплата 130):");
        Employee probe = new Employee("Кто-то", 1, 130);
        System.out.println(book.containsEmployee(probe));

        System.out.println("\nПоиск сотрудника по id=3:");
        Employee found = book.findById(3);
        System.out.println(found != null ? found : "Не найден");
    }
}