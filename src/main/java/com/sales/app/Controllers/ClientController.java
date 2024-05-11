package com.sales.app.Controllers;

import com.sales.app.entities.Client;
import com.sales.app.services.ClientService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ClientController {
    final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping({ "/clients", "/" })
    public String findAllClients(Model model, @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size) {

        var currentPage = page.orElse(1);
        var pageSize = size.orElse(5);

        var clientPage = clientService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("clients", clientPage);

        var totalPages = clientPage.getTotalPages();
        if (totalPages > 0) {
            var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "list-clients";
    }

    @RequestMapping("/searchClient")
    public String searchClient(@Param("keyword") String keyword, Model model) {

        model.addAttribute("clients", clientService.searchClients(keyword));
        model.addAttribute("keyword", keyword);
        return "list-clients";
    }

    @RequestMapping("/client/{id}")
    public String findClientById(@PathVariable("id") Long id, Model model) {

        model.addAttribute("client", clientService.findClientById(id));
        return "list-client";
    }

    @GetMapping("/add-client")
    public String showCreateForm(Client client, Model model) {

        return "add-client";
    }

    @RequestMapping("/add-client")
    public String createClient(Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-client";
        }

        clientService.createClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/update-client/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute("client", clientService.findClientById(id));
        return "update-client";
    }

    @RequestMapping("/update-client/{id}")
    public String updateClient(@PathVariable("id") Long id, Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            client.setId(id);
            return "update-client";
        }

        clientService.updateClient(client);
        return "redirect:/clients";
    }

    @RequestMapping("/remove-client/{id}")
    public String deleteClient(@PathVariable("id") Long id, Model model) {
        clientService.deleteClient(id);

        return "redirect:/clients";
    }
}
