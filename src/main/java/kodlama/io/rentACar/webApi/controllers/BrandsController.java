package kodlama.io.rentACar.webApi.controllers;


import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.concretes.BrandManager;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@RestController  // annotation-bilgilendirme
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandsController {
    private  BrandService brandService;

    @GetMapping()
    public List<GetAllBrandsResponse> getAll() {
        return brandService.getAll();
    }
    @GetMapping("/{id}")
    public GetByIdBrandsResponse getById(@PathVariable int id) {
        return brandService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(code= HttpStatus.CREATED)
    public void add(@RequestBody()  @Valid CreateBrandRequest createBrandRequest) {   //@Valid kısmını   @NotNull @NotBlank  @Size(min = 3,max = 20) kısımlarını görmesi için koyduk


        this.brandService.add(createBrandRequest);
    }

    @PutMapping()
    public void update( @RequestBody() UpdateBrandRequest updateBrandRequest){
     this.brandService.update(updateBrandRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.brandService.delete(id);
    }
}
