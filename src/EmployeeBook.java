public class EmployeeBook {
    private Employee[] employees = new Employee[10];

    public void printAllEmployees() {
        for (Employee e : employees) {
            if (e != null) {
                System.out.println(e);
            }
        }
    }

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

    public boolean containsEmployee(Employee emp) {
        for (Employee e : employees) {
            if (e == null) break;
            if (e.equals(emp)) return true;
        }
        return false;
    }

    public boolean addEmployee(Employee emp) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = emp;
                return true;
            }
        }
        return false;
    }

    public Employee findById(int id) {
        for (Employee e : employees) {
            if (e == null) break;
            if (e.getId() == id) return e;
        }
        return null;
    }
}
