package ua.edu.nuos.lab11_java.main.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.nuos.lab11_java.main.data.Customer;
import ua.edu.nuos.lab11_java.main.service.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {
    private final CustomerService service = new CustomerService();
    private final List<Customer> customers = new ArrayList<>();

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/manage")
    public String manage(Model model) {
        model.addAttribute("customers", customers);
        return "fragments/customerManage";
    }

    @GetMapping("/about")
    public String about() {
        return "fragments/about";
    }

    @GetMapping("/clearTable")
    public String clearTable() {
        customers.clear();
        return "fragments/customerManage";
    }

    @GetMapping("/loadFromTxt")
    public String loadFromTxt(Model model) {
        customers.clear();
        customers.addAll(service.readListTxt("customers.txt"));
        model.addAttribute("customers", customers);
        return "fragments/customerManage";
    }

    @GetMapping("/loadFromBin")
    public String loadFromBin(Model model) {
        customers.clear();
        customers.addAll(service.readListBinary("customers.bin"));
        model.addAttribute("customers", customers);
        return "fragments/customerManage";
    }

    @GetMapping("/saveToTxt")
    public String saveToTxt(Model model) {
        service.outputListTxt(customers, "customers.txt");
        model.addAttribute("customers", customers);
        return "fragments/customerManage";
    }

    @GetMapping("/saveToBin")
    public String saveToBin(Model model) {
        service.outputListBinary(customers, "customers.bin");
        model.addAttribute("customers", customers);
        return "fragments/customerManage";
    }

    @GetMapping("/byFirstLetter")
    public String firstLetter(@RequestParam String prefix, Model model) {
        List<Customer> newCustomers = service.findCustomersByLetters(customers, prefix);
        model.addAttribute("customers", newCustomers);
        return "fragments/customerManage";
    }

    @GetMapping("/byCreditRange")
    public String findCard(@RequestParam int a, int b, Model model) {
        List<Customer> newCustomers = service.findCustomersByCreditCardRange(customers, a, b);
        model.addAttribute("customers", newCustomers);
        return "fragments/customerManage";
    }

    @GetMapping("/byNegative")
    public String findCardNegative(Model model) {
        List<Customer> newCustomers = service.findCustomerByArrears(customers);
        model.addAttribute("customers", newCustomers);
        return "fragments/customerManage";
    }

    @GetMapping("/byTotalSpent")
    public String findByTotalSpent(Model model) {
        List<Customer> newCustomers = service.customersByTotalPurchases(customers);
        model.addAttribute("customers", newCustomers);
        return "fragments/customerManage";
    }

    @GetMapping("/byCustomerId")
    public String findByCustomerId(@RequestParam int ID, Model model) {
        List<Customer> newCustomers = service.findCustomerById(customers, ID);
        model.addAttribute("customers", newCustomers);
        return "fragments/customerManage";
    }

    @GetMapping("/findByCityAndName")
    public String findByCityAndName(Model model) {
        Map<String, List<Customer>> grouped = service.groupCustomersByCityAndName(customers);
        model.addAttribute("customers", customers);
        model.addAttribute("grouped", grouped);
        return "fragments/customerManage";
    }

    @GetMapping("/totalSpentByCity")
    public String byTotalSpentInCity(@RequestParam String city, Model model) {
        Map<String, Integer> allData = service.calcByTotalPurchase(customers);
        Integer total = allData.get(city);
        model.addAttribute("customers", customers);
        model.addAttribute("city", city);
        model.addAttribute("total", total);
        return "fragments/customerManage";
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam int delID, Model model) {
        List<Customer> newCustomers = service.removeCustomerById(customers, delID);
        model.addAttribute("customers", newCustomers);
        return "fragments/customerManage";
    }


    @GetMapping("/addCustomer")
    public String addCustomer(@RequestParam int _id,
                              @RequestParam String name,
                              @RequestParam String _city,
                              @RequestParam Integer cardNumber,
                              @RequestParam Integer accBalance,
                              @RequestParam Integer numberOfPurchases,
                              @RequestParam Integer totalPurchases,
                              Model model) {
        List<Customer> newCustomers = service.addCustomer(customers, new Customer(_id,name,_city,cardNumber,accBalance,numberOfPurchases,totalPurchases));
        model.addAttribute("customers", newCustomers);
        return "fragments/customerManage";
    }
}
