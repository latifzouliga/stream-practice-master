package com.cydeo.streampractice.practice;

import com.cydeo.streampractice.model.*;
import com.cydeo.streampractice.service.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Component
public class Practice {

    public static CountryService countryService;
    public static DepartmentService departmentService;
    public static EmployeeService employeeService;
    public static JobHistoryService jobHistoryService;
    public static JobService jobService;
    public static LocationService locationService;
    public static RegionService regionService;

    public Practice(CountryService countryService, DepartmentService departmentService,
                    EmployeeService employeeService, JobHistoryService jobHistoryService,
                    JobService jobService, LocationService locationService,
                    RegionService regionService) {

        Practice.countryService = countryService;
        Practice.departmentService = departmentService;
        Practice.employeeService = employeeService;
        Practice.jobHistoryService = jobHistoryService;
        Practice.jobService = jobService;
        Practice.locationService = locationService;
        Practice.regionService = regionService;

    }

    // You can use the services above for all the CRUD (create, read, update, delete) operations.
    // Above services have all the required methods.
    // Also, you can check all the methods in the ServiceImpl classes inside the service.impl package, they all have explanations.

    // Display all the employees
    public static List<Employee> getAllEmployees() {
        return employeeService.readAll();
    }

    // Display all the countries
    public static List<Country> getAllCountries() {
        return countryService.readAll();
    }

    // Display all the departments
    public static List<Department> getAllDepartments() {
        return departmentService.readAll();
    }

    // Display all the jobs
    public static List<Job> getAllJobs() {
        return jobService.readAll();
    }

    // Display all the locations
    public static List<Location> getAllLocations() {
        return locationService.readAll();
    }

    // Display all the regions
    public static List<Region> getAllRegions() {
        return regionService.readAll();
    }

    // Display all the job histories
    public static List<JobHistory> getAllJobHistories() {
        return jobHistoryService.readAll();
    }

    // Display all the employees' first names
    public static List<String> getAllEmployeesFirstName() {
        return getAllEmployees().stream()
                .map(Employee::getFirstName)
                .collect(toList());
/**
 List<Employee> employees = new ArrayList<>();
 List<String> firstNames = new ArrayList<>();
 for (Employee each : employees){
 firstNames.add(each.getFirstName());
 }
 return firstNames;
 */

    }

    // Display all the countries' names
    public static List<String> getAllCountryNames() {
        return getAllCountries().stream()
                .map(Country::getCountryName)
                .collect(toList());

        /**
         List<Country> countries = getAllCountries();
         List<String> countryNames = new ArrayList<>();
         for (Country each : countries){
         countryNames.add(each.getCountryName());
         }
         return countryNames;
         */

    }

    // Display all the departments' managers' first names
    public static List<String> getAllDepartmentManagerFirstNames() {
        return getAllDepartments().stream()
                .map(department -> department.getManager().getFirstName())
                .collect(toList());

        /**
         getAllDepartments().stream()
         .map(Department::getManager)
         .map(Employee::getFirstName)
         .collect(toList());
         */

        /**
         List<Department> departments = getAllDepartments();
         List<String> managersFirstNames = new ArrayList<>();
         for (Department department : departments){
         managersFirstNames.add(department.getManager().getFirstName());
         }
         return managersFirstNames;
         */
    }

    // Display all the departments where manager name of the department is 'Steven'
    public static List<Department> getAllDepartmentsWhichManagerFirstNameIsSteven() {
        return departmentService.readAll().stream()
                .filter(department -> department.getManager().getFirstName().equals("Steven"))
                .collect(toList());

    }

    // Display all the departments where postal code of the location of the department is '98199'
    public static List<Department> getAllDepartmentsWhereLocationPostalCodeIs98199() {
        return departmentService.readAll().stream()
                .filter(department -> department.getLocation().getPostalCode().equals("98199"))
                .collect(toList());

    }

    // Display the region of the IT department
    public static Region getRegionOfITDepartment() throws Exception {
        //        Optional<Department> optionalDepartment = getAllDepartments().stream()
//                .filter(department -> department.getDepartmentName().equals("IT"))
//                .findFirst();
//
//        Department department = null;
//
//        if (!optionalDepartment.isEmpty()) {
//            department = optionalDepartment.get();
//        }


//        return getAllDepartments().stream()
//                .filter(department -> department.getDepartmentName().equals("IT"))
//                .findFirst().orElseThrow(() -> new Exception("Department not found!"))
//                .getLocation().getCountry().getRegion();

//        return departmentService.readAll().stream()
//                .filter(p -> p.getDepartmentName().equals("IT"))
//                .map(p -> p.getLocation().getCountry().getRegion())
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Department not found!"));
        /**
         return departmentService.readAll().stream()
         .filter(p -> p.getDepartmentName().equals("IT"))
         .map(p -> p.getLocation().getCountry().getRegion())
         .collect(toList()).get(0);
         */
        return getAllDepartments().stream()
                .filter(department -> department.getDepartmentName().equals("IT"))
                .map(department -> department.getLocation().getCountry().getRegion())
                .findFirst().orElseThrow(() -> new Exception("Department not found!"));
    }

    // Display all the departments where the region of department is 'Europe'
    public static List<Department> getAllDepartmentsWhereRegionOfCountryIsEurope() {
        return getAllDepartments().stream()
                .filter(department -> department.getLocation().getCountry().getRegion().getRegionName().equals("Europe"))
                .collect(toList());
    }

    // Display if there is any employee with salary less than 1000. If there is none, the method should return true
    public static boolean checkIfThereIsNoSalaryLessThan1000() {
        return !employeeService.readAll().stream()
                .anyMatch(employee -> employee.getSalary() < 1000);

//        return employeeService.readAll().stream()
//                .allMatch(employee -> employee.getSalary() > 1000);

//        return employeeService.readAll().stream()
//                .noneMatch(employee -> employee.getSalary() < 1000);

//        return employeeService.readAll().stream()
//                .filter(employee -> employee.getSalary() < 1000)
//                .findAny()
//                .isEmpty();

//        return employeeService.readAll().stream()
//                .filter(employee -> employee.getSalary() < 1000)
//                .collect(toList())
//                .isEmpty();


    }

    // Check if the salaries of all the employees in IT department are greater than 2000 (departmentName: IT)
    public static boolean checkIfThereIsAnySalaryGreaterThan2000InITDepartment() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().getDepartmentName().equals("IT"))
                .allMatch(employee -> employee.getSalary() > 2000);
    }

    // Display all the employees whose salary is less than 5000
    public static List<Employee> getAllEmployeesWithLessSalaryThan5000() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() < 5000)
                .collect(toList());
    }

    // Display all the employees whose salary is between 6000 and 7000
    public static List<Employee> getAllEmployeesSalaryBetween() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() > 6000)
                .filter(employee -> employee.getSalary() < 7000)
                .collect(toList());

//        return employeeService.readAll().stream()
//                .filter(employee -> employee.getSalary() > 6000 && employee.getSalary() < 7000)
//                .collect(toList());
    }

    // Display the salary of the employee Grant Douglas (lastName: Grant, firstName: Douglas)
    public static Long getGrantDouglasSalary() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getFirstName().equals("Douglas") && employee.getLastName().equals("Grant"))
                //.map(Employee::getSalary)
                //.findAny()
                .findAny()
                .orElseThrow(RuntimeException::new)
                .getSalary();
    }

    // Display the maximum salary an employee gets
    public static Long getMaxSalary() {
        return getAllEmployees().stream()
                .max(comparing(Employee::getSalary))
                .get()
                .getSalary();

//        return employeeService.readAll().stream()
//                .map(Employee::getSalary)
//                .reduce(Long::max)
//                .get();

//        return employeeService.readAll().stream()
//                .map(Employee::getSalary)
//                .max(Long::compareTo)
//                .get();

//        return getAllEmployees().stream()
//                .sorted(comparing(Employee::getSalary).reversed())
//                .map(Employee::getSalary)
//                .findFirst()
//                .get();

//        return employeeService.readAll().stream()
//                .map(Employee::getSalary)
//                .reduce(Long::max)
//                .get();

//        return employeeService.readAll().stream()
//                .collect(Collectors.maxBy(comparing(Employee::getSalary))).get().getSalary();

//        return employeeService.readAll().stream()
//                .mapToLong(Employee::getSalary)
//                .max()
//                .orElseThrow();

//        return employeeService.readAll().stream()
//                .map(Employee::getSalary)
//                .collect(Collectors.reducing(Long::max))
//                .get();

    }

    // Display the employee(s) who gets the maximum salary
    public static List<Employee> getMaxSalaryEmployee() {

        return getAllEmployees().stream()
                .filter(employee -> employee.getSalary().equals(getMaxSalary()))
                .collect(toList());


    }

    // Display the max salary employee's job
    public static Job getMaxSalaryEmployeeJob() {
//        return getAllEmployees().stream()
//                .max(comparing(Employee::getSalary))
//                .map(Employee::getJob)
//                .get();

        return getMaxSalaryEmployee().get(0).getJob();

    }

    // Display the max salary in Americas Region
    public static Long getMaxSalaryInAmericasRegion() {
        return getAllEmployees().stream()
                .filter(employee -> employee.getDepartment().getLocation().getCountry().getRegion().getRegionName().equals("Americas"))
                .max(comparing(Employee::getSalary))
                .get().getSalary();
    }

    // Display the second maximum salary an employee gets
    public static Long getSecondMaxSalary() {
//        return getAllEmployees().stream()
//                .sorted(comparing(Employee::getSalary).reversed())
//                .collect(toList())
//                .get(1)
//                .getSalary();

//        return getAllEmployees().stream()
//                .sorted(comparing(Employee::getSalary).reversed())
//                .map(Employee::getSalary)
//                .distinct()
//                .skip(1)
//                .findFirst()
//                .get();
//        return getAllEmployees().stream()
//                .filter(employee -> !(employee.getSalary() == getMaxSalary()))
//                .map(Employee::getSalary)
//                .max(Long::compareTo)
//                .get();

        return getAllEmployees().stream()
                .filter(employee -> !employee.getSalary().equals(getMaxSalary()))
                .sorted(comparing(Employee::getSalary).reversed())
                .findFirst()
                .get().getSalary();
    }

    // Display the employee(s) who gets the second maximum salary
    public static List<Employee> getSecondMaxSalaryEmployee() {

        return getAllEmployees().stream()
                .filter(employee -> employee.getSalary().equals(getSecondMaxSalary()))
                .collect(toList());

    }

    // Display the minimum salary an employee gets
    public static Long getMinSalary() {
        return getAllEmployees().stream()
                .min(comparing(Employee::getSalary))
                .get()
                .getSalary();
    }

    // Display the employee(s) who gets the minimum salary
    public static List<Employee> getMinSalaryEmployee() {
        return getAllEmployees().stream()
                .min(comparing(Employee::getSalary))
                .stream()
                .collect(toList());
    }

    // Display the second minimum salary an employee gets
    public static Long getSecondMinSalary() {
        return getAllEmployees().stream()
                .filter(employee -> !employee.getSalary().equals(getMinSalary()))
                .sorted(comparing(Employee::getSalary))
                .findFirst()
                .get().getSalary();


    }

    // Display the employee(s) who gets the second minimum salary
    public static List<Employee> getSecondMinSalaryEmployee() {

        return getAllEmployees().stream()
                .filter(employee -> employee.getSalary().equals(getSecondMinSalary()))
                .collect(toList());
    }

    // Display the average salary of the employees
    public static Double getAverageSalary() {
        return getAllEmployees().stream()
                .collect(Collectors.averagingLong(Employee::getSalary));
    }

    // Display all the employees who are making more than average salary
    public static List<Employee> getAllEmployeesAboveAverage() {
        return getAllEmployees().stream()
                .filter(employee -> employee.getSalary() > getAverageSalary())
                .collect(toList());
    }

    // Display all the employees who are making less than average salary
    public static List<Employee> getAllEmployeesBelowAverage() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() < getAverageSalary())
                .collect(toList());
    }

    // Display all the employees separated based on their department id number
    public static Map<Long, List<Employee>> getAllEmployeesForEachDepartment() {
        return getAllEmployees().stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment().getId()));
    }

    // Display the total number of the departments
    public static Long getTotalDepartmentsNumber() {


//        return getAllDepartments().stream()
//                .count();

        return (long) getAllDepartments().size();
    }

    // Display the employee whose first name is 'Alyssa' and manager's first name is 'Eleni' and department name is 'Sales'
    public static Employee getEmployeeWhoseFirstNameIsAlyssaAndManagersFirstNameIsEleniAndDepartmentNameIsSales() {

//        better solution

//        return getAllEmployees().stream()
//                .filter(employee -> employee.getFirstName().equals("Alyssa") &&
//                        employee.getManager().getFirstName().equals("Eleni") &&
//                        employee.getDepartment().getDepartmentName().equals("Sales")
//                )
//                .findAny()
//                .orElseThrow();

        return getAllEmployees().stream()
                .filter(employee -> employee.getFirstName().equals("Alyssa"))
                .filter(employee -> employee.getManager().getFirstName().equals("Eleni"))
                .filter(employee -> employee.getDepartment().getDepartmentName().equals("Sales"))
                .findAny()
                .orElseThrow();
    }

    // Display all the job histories in ascending order by start date
    public static List<JobHistory> getAllJobHistoriesInAscendingOrder() {
        return getAllJobHistories().stream()
                .sorted(comparing(JobHistory::getStartDate))
                .collect(toList());
    }

    // Display all the job histories in descending order by start date
    public static List<JobHistory> getAllJobHistoriesInDescendingOrder() {
        return getAllJobHistories().stream()
                .sorted(comparing(JobHistory::getStartDate).reversed())
                .collect(toList());
    }

    // Display all the job histories where the start date is after 01.01.2005
    public static List<JobHistory> getAllJobHistoriesStartDateAfterFirstDayOfJanuary2005() {
        return getAllJobHistories().stream()
                .filter(jobHistory -> jobHistory.getStartDate().isAfter(LocalDate.of(2005, 1, 1)))
                .collect(toList());
    }

    // Display all the job histories where the end date is 31.12.2007 and the job title of job is 'Programmer'
    public static List<JobHistory> getAllJobHistoriesEndDateIsLastDayOfDecember2007AndJobTitleIsProgrammer() {
        return getAllJobHistories().stream()
                .filter(jobHistory -> jobHistory.getEndDate().isEqual(LocalDate.of(2007, 12, 31)) &&
                        jobHistory.getJob().getJobTitle().equals("Programmer"))
                .collect(toList());
    }

    // Display the employee whose job history start date is 01.01.2007 and job history end date is 31.12.2007 and department's name is 'Shipping'
    public static Employee getEmployeeOfJobHistoryWhoseStartDateIsFirstDayOfJanuary2007AndEndDateIsLastDayOfDecember2007AndDepartmentNameIsShipping() {
//        return getAllJobHistories().stream()
//                .filter(jobHistory -> jobHistory.getStartDate().equals(LocalDate.of(2007, 1, 1)) &&
//                        jobHistory.getEndDate().isEqual(LocalDate.of(2007, 12, 31)) &&
//                        jobHistory.getDepartment().getDepartmentName().equals("Shipping"))
//                .map(JobHistory::getEmployee)
//                .findFirst()
//                .orElseThrow();

        return getAllJobHistories().stream()
                .filter(jobHistory -> jobHistory.getStartDate().equals(LocalDate.of(2007, 1, 1)) &&
                        jobHistory.getEndDate().isEqual(LocalDate.of(2007, 12, 31)) &&
                        jobHistory.getDepartment().getDepartmentName().equals("Shipping"))
                .findFirst()
                .get().getEmployee();
    }

    // Display all the employees whose first name starts with 'A'
    public static List<Employee> getAllEmployeesFirstNameStartsWithA() {
        return getAllEmployees().stream()
                .filter(employee -> employee.getFirstName().startsWith("A"))
                .collect(toList());
    }

    // Display all the employees whose job id contains 'IT'
    public static List<Employee> getAllEmployeesJobIdContainsIT() {
        return getAllEmployees().stream()
                .filter(employee -> employee.getJob().getId().contains("IT"))
                .collect(toList());
    }

    // Display the number of employees whose job title is programmer and department name is 'IT'
    public static Long getNumberOfEmployeesWhoseJobTitleIsProgrammerAndDepartmentNameIsIT() {
        return getAllEmployees().stream()
                .filter(employee -> employee.getJob().getJobTitle().equals("Programmer") &&
                        employee.getDepartment().getDepartmentName().equals("IT"))
                .count();
    }

    // Display all the employees whose department id is 50, 80, or 100
    public static List<Employee> getAllEmployeesDepartmentIdIs50or80or100() {
//        return getAllEmployees().stream()
//                .filter(employee -> employee.getDepartment().getId() == 50L ||
//                                    employee.getDepartment().getId() == 80L ||
//                                    employee.getDepartment().getId() == 100L)
//                .collect(toList());

        return getAllEmployees().stream()
                .filter(employee -> List.of(50L, 80L, 100L)
                        .contains(employee.getDepartment().getId()))
                .collect(Collectors.toList());
    }

    // Display the initials of all the employees
    // Note: You can assume that there is no middle name
    public static List<String> getAllEmployeesInitials() {

        return getAllEmployees().stream()
                .map(employee -> employee.getFirstName().charAt(0) + "" + employee.getLastName().charAt(0))
                .collect(toList());
    }

    // Display the full names of all the employees
    public static List<String> getAllEmployeesFullNames() {
        return getAllEmployees().stream()
                .map(employee -> employee.getFirstName() + " " + employee.getLastName())
                .collect(toList());
    }

    // Display the length of the longest full name(s)
    public static Integer getLongestNameLength() {

        return getAllEmployeesFullNames().stream()
                .max(comparing(String::length))
                .get().length();
    }

    // Display the employee(s) with the longest full name(s)
    public static List<Employee> getLongestNamedEmployee() {

        return getAllEmployees().stream()
                .filter(employee -> employee.getFirstName().length() + employee.getLastName().length() + 1 == getLongestNameLength())
                .collect(toList());

    }

    // Display all the employees whose department id is 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIs90or60or100or120or130() {
        return getAllEmployees().stream()
                .filter(employee -> employee.getDepartment().getId() == 60L ||
                        employee.getDepartment().getId() == 90L ||
                        employee.getDepartment().getId() == 100L ||
                        employee.getDepartment().getId() == 120L ||
                        employee.getDepartment().getId() == 130L)
                .collect(toList());
    }

    // Display all the employees whose department id is NOT 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIsNot90or60or100or120or130() {
        return getAllEmployees().stream()
                .filter(employee -> !(employee.getDepartment().getId() == 60L ||
                        employee.getDepartment().getId() == 90L ||
                        employee.getDepartment().getId() == 100L ||
                        employee.getDepartment().getId() == 120L ||
                        employee.getDepartment().getId() == 130L))
                .collect(toList());

//        return getAllEmployees().stream()
//               .filter(employee -> !getAllEmployeesDepartmentIdIsNot90or60or100or120or130().add(employee))
//                .collect(toList());
    }

}
