package kodlama.io.rentACar.business.concretes;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandsResponse;
import kodlama.io.rentACar.business.rules.BrandBusinessRules;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service  // bu sınıf bir business  nesnesidir
@AllArgsConstructor
public class BrandManager implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;




    @Override
    public List<GetAllBrandsResponse> getAll() {

        List<Brand> brands=brandRepository.findAll();
       /* List<GetAllBrandsResponse> brandsResponses=new ArrayList<>();

       *//* for (Brand brand:brands) {
            GetAllBrandsResponse responseItem=new GetAllBrandsResponse();
            responseItem.setName(brand.getName());
            responseItem.setId(brand.getId());
            brandsResponses.add(responseItem);
        }*/
        List<GetAllBrandsResponse> brandsResponses=brands.stream().map(brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class)).collect(Collectors.toList());

        return  brandsResponses;
    }

    @Override
    public GetByIdBrandsResponse getById(int id) {
       Brand brand= brandRepository.findById(id).orElseThrow();
       GetByIdBrandsResponse response=this.modelMapperService.forResponse().map(brand, GetByIdBrandsResponse.class);
        return response;
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {

       // Brand brand = new Brand();
        // brand.setName(createBrandRequest.getName());

        this.brandBusinessRules.checkIfBrandExists(createBrandRequest.getName());
        Brand brand=this.modelMapperService.forRequest().map(createBrandRequest,Brand.class);

        this.brandRepository.save(brand);

    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        Brand brand=this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
        this.brandRepository.save(brand);
    }

    @Override
    public void delete(int id) {
  this.brandRepository.deleteById(id);
    }

}
