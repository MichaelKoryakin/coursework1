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