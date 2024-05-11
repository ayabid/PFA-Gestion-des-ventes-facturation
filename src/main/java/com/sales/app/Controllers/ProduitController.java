
package com.sales.app.Controllers;
import com.sales.app.models.FileUtil;
import com.sales.app.entities.Produit;
import com.sales.app.services.ProduitService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller

public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/produits")
    public String findAllProduits(Model model, @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("id").descending());
        Page<Produit> produitPage = produitService.findPaginated(pageable);

        model.addAttribute("produits", produitPage);

        int totalPages = produitPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "list-produits";
    }

    @GetMapping("/searchProduits")
    public String searchProduits(@RequestParam("keyword") String keyword, Model model) {
        List<Produit> produits = produitService.searchProduits(keyword);
        model.addAttribute("produits", produits);
        return "list-produits";
    }

    @GetMapping("/add-produit")
    public String showCreateForm(Model model) {
        model.addAttribute("produit", new Produit());
        return "add-produit";
    }

    @PostMapping("/create-produit")
    public String createProduit(@ModelAttribute("produit") Produit newProduit,
                                @RequestParam("image") MultipartFile file,
                                RedirectAttributes redirectAttributes) throws IOException {

        // Convertir le fichier en un tableau d'octets
        byte[] imageData = FileUtil.convertMultipartFileToByteArray(file);

        // Créer le nouveau produit avec l'image
        produitService.createProduit(newProduit, imageData);

        redirectAttributes.addFlashAttribute("successMessage", "Produit créé avec succès!");
        return "redirect:/produits";
    }
    @PostMapping("/update-produit/{id}")
    public String updateProduit(@PathVariable("id") Long id,
                                @ModelAttribute("produit") Produit updatedProduit,
                                @RequestParam("image") MultipartFile file,
                                RedirectAttributes redirectAttributes) throws IOException {

        Produit existingProduit = produitService.findProduitById(id);

        existingProduit.setNom(updatedProduit.getNom());
        existingProduit.setDescription(updatedProduit.getDescription());
        existingProduit.setPrix(updatedProduit.getPrix());
        existingProduit.setQStock(updatedProduit.getQStock());

        // Convertir le fichier en un tableau d'octets
        byte[] imageData = FileUtil.convertMultipartFileToByteArray(file);

        // Mettre à jour le produit avec l'image
        produitService.updateProduit(existingProduit, imageData);

        redirectAttributes.addFlashAttribute("successMessage", "Produit modifié avec succès!");
        return "redirect:/produits";
    }


    @GetMapping("/update-produit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Produit produit = produitService.findProduitById(id);
        model.addAttribute("produit", produit);
        return "update-produit";
    }



    @RequestMapping("/remove-produit/{id}")
    public String deleteProduit(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        produitService.deleteProduit(id);
        redirectAttributes.addFlashAttribute("successMessage", "Produit supprimé avec succès!");
        return "redirect:/produits";
    }
}


